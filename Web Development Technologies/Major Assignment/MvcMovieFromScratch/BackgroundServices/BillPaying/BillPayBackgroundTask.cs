using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using DatabaseContext.Data;
using Microsoft.EntityFrameworkCore;
using Assignment2.ExtensionMethods;
using DatabaseContext.Models;
using DatabaseContext.ViewModels;
using Assignment2.Exceptions;

namespace Assignment2.BackgroundServices.BillPaying
{


    // Using Tutorial 8 File BackgroundServices as a base to build from.
    public class BillPayBackgroundTask
    {
        private readonly McbaContext _context;

        public BillPayBackgroundTask(McbaContext context)
        {
            _context = context;

        }

        public async Task ProcessBills(ILogger<BillPayBackgroundService> logger, CancellationToken cancellationToken)
        {


            // 1. Get a list of transactions that are due to be paid, skipping any failed bills.
            var bills = await _context.BillPay.ToListAsync(cancellationToken);

            foreach (var b in bills)
            {
                if (DateTime.Compare(b.ScheduleDate, DateTime.Now.ToUniversalTime()) > 0 || b.Status == BillPayStatus.Failed || b.Status == BillPayStatus.Blocked)
                {
                    // Skip these.
                }
                else
                {
                    var a = await _context.Account
                        .Where(a => a.AccountNumber == b.AccountNumber)
                        .Include(a => a.Transactions)
                        .Include(a => a.Customer)
                        .Include(a => a.BillPays)
                            .ThenInclude(bill => bill.Payee)
                        .ToListAsync(cancellationToken);
                    var account = a.SingleOrDefault(a => a.AccountNumber == b.AccountNumber);

                    LodgeTransaction(account, b, cancellationToken);
                }

            }
            await _context.SaveChangesAsync(cancellationToken);

            logger.LogInformation("People Background Service is complete.");
        }

        private void LodgeTransaction(Account account, BillPay b, CancellationToken cancellationToken)
        {
            var bill = account.BillPays.SingleOrDefault(bill => bill.BillPayID == b.BillPayID);

            // If the bill is S = Single, then lodge a transaction, and catch any exceptions.
            if (b.Period == BillPayPeriod.OnceOff)
            {
                LodgeSingleTransaction(account, bill);
            }
            // Period is recurring. Lodge the transaction, catch any exceptions. Change the end date.
            else
            {
                LodgeRecurringTransaction(account, bill);
            }

        }

        private static void LodgeRecurringTransaction(Account a, BillPay b)
        {
            try
            {
                // 1. Lodge a transaction.
                TransactionExtensionFactory.BillPay(b.Account, b.Amount, "Paid a recurring bill of $" + b.Amount + " to " + b.Payee.PayeeName + " via BillPay.");
                // 2. Catch error, create new bill pay with failed status.
            }
            catch (Exception ex)
            {
                if (ex is InsufficientFundsException || ex is NegativeValueException)
                {
                    // Add failed bill.
                    BillPayExtension.AddBillPay(a.BillPays, new BillPayViewModel
                    {
                        Amount = b.Amount,
                        Account = b.Account,
                        Payee = b.Payee,
                        ScheduleDate = b.ScheduleDate,
                        Period = BillPayPeriod.OnceOff,
                        Status = BillPayStatus.Failed

                    });
                }
                else
                {
                    throw;
                }
            }
            finally
            {
                // 3. Delay BillPay until the next period.
                BillPayExtension.ModifyBillPay(a.BillPays, new BillPayViewModel
                {
                    AccountNumber = b.AccountNumber,
                    BillPayID = b.BillPayID,
                    PayeeID = b.PayeeID,
                    Amount = b.Amount,
                    Account = b.Account,
                    Payee = b.Payee,
                    ScheduleDate = b.ScheduleDate.AddDays(Delay(b.Period)),
                    Period = b.Period,
                    Status = BillPayStatus.Unblocked
                });
            }
        }

        private static int Delay(BillPayPeriod period)
        {
            switch (period)
            {
                case BillPayPeriod.Monthly:
                    return 30;

                case BillPayPeriod.Quarterly:
                    return 90;

                case BillPayPeriod.Annually:
                    return 365;
            }
            // If Single or Failed will come here.
            return 0;
        }

        private static void LodgeSingleTransaction(Account a, BillPay b)
        {
            try
            {
                // 1. Lodge a transaction.
                TransactionExtensionFactory.BillPay(b.Account, b.Amount, "Paid a single bill of $" + b.Amount + " to " + b.Payee.PayeeName + " via BillPay.");

                // 2. Remove BillPay.
                a.BillPays.Remove(b);
                // 3. Catch error, change bill status to fail.
            }
            catch (Exception ex)
            {
                if (ex is InsufficientFundsException || ex is NegativeValueException)
                {
                    b.Status = BillPayStatus.Failed;
                }
                else
                {
                    throw;
                }
            }
        }

    }
}
