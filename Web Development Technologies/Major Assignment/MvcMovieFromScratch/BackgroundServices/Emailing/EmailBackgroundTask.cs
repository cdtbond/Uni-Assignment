using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.ViewEngines;
using Microsoft.Extensions.Logging;
using DatabaseContext.Data;
using Microsoft.EntityFrameworkCore;
using Assignment2.ExtensionMethods;
using DatabaseContext.Models;
using DatabaseContext.ViewModels;
using Assignment2.Exceptions;
using System.IO;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.AspNetCore.Identity;

namespace Assignment2.BackgroundServices.Emailing
{


    // Using Tutorial 8 File BackgroundServices as a base to build from.
    public class EmailBackgroundTask
    {
        private readonly McbaContext _context;

        private readonly IMailer _mailer;
        private readonly Dictionary<string, string> _emails;

        public EmailBackgroundTask(IMailer mailer, McbaContext context,Dictionary<string,string> emails)
        {
            _mailer = mailer;
            _context = context;
            _emails = emails;
        }

        public async Task ProcessEmails(CancellationToken cancellationToken)
        {

            var emailCount = await _context.Email.CountAsync(cancellationToken);
            

            if(emailCount == 0)
            {
                // Produce first time report for everyone.

                await ProduceFirstEmailRunReport(cancellationToken);


            } 
            else
            {
                // Get the last instance of the email run.
                var emailRuns = await _context.Email.OrderBy(email => email.ModifyDate).LastAsync(cancellationToken);
                // Produce reports that may be first time or ongoing depending on customer.
                await ProduceMixedReport(emailRuns,cancellationToken);
            }

            Email email = new Email
            {
                ModifyDate = DateTime.UtcNow 
            };
            // Set the next email entry to the current UTC time.
            _context.Email.Add(email);
            await _context.SaveChangesAsync(cancellationToken);
        }

        private async Task ProduceMixedReport(Email emailRun,CancellationToken cancellationToken)
        {
            // For each Customer loop through their Accounts, then loop through their transactions to find if they have had any activity. 
            // Also check if there is any activity prior to the last emailRun. If not, it is a first time Report for this customer.

            // Current Balance
            // New Transactions
            // Amount Balance Changed

            var customers = await _context.Customer
                .Include(c => c.Accounts)
                .ThenInclude(a=>a.Transactions)
                .ToListAsync(cancellationToken);

            foreach(var c in customers)
            {
                
                // New Method.
                bool recentActivity = false;
                bool firstActivity = false;
                foreach(var a in c.Accounts)
                {
                    // If there has been activity since the previous email run, then produce the report. 
                    // Otherwise skip.
                    if(ActivitySinceLastRun(a, emailRun))
                    {
                        recentActivity = true;
                    }

                    if(ActivityBeforeLastRun(a,emailRun))
                    {
                        firstActivity = true;
                    }
                }

                // If there has been any activity across either account, then produce either a first time or ongoing report.
                // Otherwise skip this customer.
                if(recentActivity)
                {
                    // Here there were transactions that occurred before.
                    if(firstActivity)
                    {
                        await ProduceFirstTimeReport(c,emailRun,cancellationToken);
                    } else
                    {
                        await ProduceOngoingReport(c, emailRun, cancellationToken);
                    }
                }
            }
        }

        private async Task ProduceOngoingReport(Customer c,Email emailRun, CancellationToken cancellationToken)
        {
            
            List<AccountTransactionHelper> helpers = new();
            foreach (var account in c.Accounts)
            {
                
                var helper = new AccountTransactionHelper
                {
                    Balance = AccountExt.GetBalance(account),
                    // Only get the most recent transactions
                    Transactions =  account.Transactions.Where(t=>t.ModifyDate.CompareTo(emailRun.ModifyDate)>0).ToList()
                };


                helpers.Add(helper);
            }
            var emailModel = new EmailViewModel
            {
                Customer = c,
                Helpers = helpers
            };

            string email = _emails[c.CustomerID + ""];

            await _mailer.SendEmailAsync(c.CustomerName, email, "Your Ongoing Account Activity Statement", ConstructHTML.OngoingReport(emailModel),cancellationToken);
            //"s3767838@student.rmit.edu.au"
        }

        private async Task ProduceFirstTimeReport(Customer c, Email emailRun, CancellationToken cancellationToken)
        {
            List<AccountTransactionHelper> helpers = new();
            foreach (var account in c.Accounts)
            {

                var helper = new AccountTransactionHelper
                {
                    Balance = AccountExt.GetBalance(account),
                    // Only get the most recent transactions
                    Transactions = account.Transactions.Where(t => t.ModifyDate.CompareTo(emailRun.ModifyDate) > 0).ToList()
                };


                helpers.Add(helper);
            }
            var emailModel = new EmailViewModel
            {
                Customer = c,
                Helpers = helpers
            };

            string email = _emails[c.CustomerID + ""];
            await _mailer.SendEmailAsync(c.CustomerName, email, "Your Ongoing Account Activity Statement", ConstructHTML.OngoingReport(emailModel),cancellationToken);
        }

        private static bool ActivityBeforeLastRun(Account a, Email emailRun)
        {
            // Order Transactions.
            var order = a.Transactions.OrderBy(t => t.ModifyDate);

            // Get the earliest Transaction.
            var firstTrans = order.First();

            // Is the earliest Transaction before the last Pay Run?
            return DateTime.Compare(firstTrans.ModifyDate, emailRun.ModifyDate) < 0;
        }

        private static bool ActivitySinceLastRun(Account a, Email emailRun)
        {
            // Order Transactions.
            var order = a.Transactions.OrderBy(t => t.ModifyDate);
            
            // Get the most recent Transaction.
            var lastTrans = order.Last();

            // Is the most recent transaction before the last Pay Run?
            return DateTime.Compare(lastTrans.ModifyDate,emailRun.ModifyDate)>0;
        }

        private async Task ProduceFirstEmailRunReport(CancellationToken cancellationToken)
        {
            var customers = await _context.Customer
                            .Include(c => c.Accounts)
                            .ThenInclude(c => c.Transactions)
                            .ToListAsync(cancellationToken);
            //Loop through each customer to get their balance and transactions
            foreach ( var customer in customers)
            {
                List<AccountTransactionHelper> helpers = new();
                foreach (var account in customer.Accounts)
                {
                    var helper = new AccountTransactionHelper
                    {
                        Balance = AccountExt.GetBalance(account),
                        Transactions = account.Transactions.ToList()
                    };
                    //Add it to helper to be consumed by mail service
                    helpers.Add(helper);
                }
                var emailModel = new EmailViewModel
                {
                    Customer = customer,
                    Helpers = helpers
                };


                string email = _emails[c.CustomerID + ""];
                await _mailer.SendEmailAsync(customer.CustomerName, email, "Your First Account Activity Statement", ConstructHTML.FirstTimeReport(emailModel),cancellationToken);
                
            }
            
        }

    }
}
