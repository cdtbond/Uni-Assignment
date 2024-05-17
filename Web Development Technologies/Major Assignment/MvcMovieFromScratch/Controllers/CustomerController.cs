using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using DatabaseContext.Data;
using DatabaseContext.Models;
using DatabaseContext.ViewModels;
using Assignment2.Filters;
using Assignment2.ExtensionMethods;
using Microsoft.EntityFrameworkCore;
using X.PagedList;
using Assignment2.Exceptions;
using Assignment2.Utilities;
using SimpleHashing;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;

namespace Assignment2.Controllers
{
    [Authorize(Roles = "Customer")]
    public class CustomerController : Controller
    {
        private readonly McbaContext _context;
        private readonly UserManager<ApplicationUser> _userManager;

        public CustomerController(McbaContext ctxt, UserManager<ApplicationUser> userManager)
        {
            _context = ctxt;
            _userManager = userManager;
        }


        public IActionResult Index()
        {
            int ? custId = HttpContext.Session.GetInt32("customerid");
            var acclist = _context.Account.Where(acc => acc.CustomerID == custId).Include(acclist => acclist.Transactions).ToList();
            string adminpasshash = SimpleHashing.PBKDF2.Hash("admin");
            //Dictionary to store list of balances
            Dictionary<int, decimal> balances = new Dictionary<int, decimal>();
            foreach (Account account in acclist)
            {
                var translist = _context.Transaction.Where(trans => trans.Account.AccountNumber == account.AccountNumber).ToList();
                balances.Add(account.AccountNumber, AccountExt.GetBalance(account)); //Gets a balance based on account

            }
            CustIndexViewModel mdl = new CustIndexViewModel
            {
                Accounts = acclist,
                Balances =  balances
            };
            return View(mdl);
        }

        public IActionResult Statement (int accNum, int ? page) { //Gets statement based on account number

            var pageNum = 0;
            pageNum = page == null ? pageNum = 1 : pageNum = (int) page;
            const int pageSize = 4;
            var transactions = _context.Transaction.Where(t => t.AccountNumber == accNum).ToPagedList(pageNum,pageSize);
            StatementViewModel mdl = new StatementViewModel
            {
                AccountNumber = accNum,
                Transactions = transactions
            };

            return View(mdl);
        }
        public IActionResult CustNavBar()
        {
            string custname = HttpContext.Session.GetString("name");
            ViewData["customername"] = custname;
            return PartialView("custnavbar");
        }

        public async Task<IActionResult> Transaction(AtmViewModel mdl)
        {
            // Call verification checks on Amount.
            var feedback = ViewChecks.AmountChecks(mdl.Amount);
            if (!feedback.Equals(""))
            {
                ModelState.AddModelError(nameof(mdl.Amount), feedback);
                return View("ATM",mdl);
            }

            // Checks if model state is valid.
            if (!ModelState.IsValid) 
            {
                return View("ATM", mdl);
            }
            
            // If model is valid, then find account based on accnum.
            var acc = _context.Account.Include(m=> m.Transactions).SingleOrDefault(m=> m.AccountNumber==mdl.AccountNumber);
            
            try
            {
                // Checks transaction type selection.
                if (mdl.TransactionType == TransactionType.Withdraw) 
                {
                    TransactionExtensionFactory.Withdraw(acc, mdl.Amount, "Withdrew " + mdl.Amount + "Comment: " + mdl.Comment);

                }
                else if (mdl.TransactionType==TransactionType.Deposit)
                {
                    TransactionExtensionFactory.Deposit(acc, mdl.Amount, "Deposited " + mdl.Amount + "Comment: " + mdl.Comment);
                }

                await _context.SaveChangesAsync();
                return RedirectToAction("Index", "Customer");
            } catch(Exception e)
            {
                // Returns model error based on exception.
                if (e is InsufficientFundsException) 
                {
                    ModelState.AddModelError("Amount", "Out of sufficient funds for the transaction.");
                    return View("ATM",mdl);
                } else if (e is NegativeValueException)
                {
                    ModelState.AddModelError("Amount", "Amount cannot be negative.");
                    return View("ATM",mdl);
                }
                else
                {
                    throw;
                }
            }
        }

        public IActionResult Transfer(int accnum, decimal balance)
        {
            // Returns all accounts.
            List<Account> accounts = _context.Account.ToList();
            TransferViewModel mdl = new TransferViewModel
            {
                Accounts = accounts,
                FromAccountNum =accnum,
                Balance =balance
            };
            return View(mdl);
        }

        public async Task<IActionResult> TransferTransaction(TransferViewModel mdl)
        {
            // Checks if model is valid.
            if (!ModelState.IsValid)
            {
                return View("Transfer", mdl);
            }
            else
            {
                Account toAccount = _context.Account.Include(m => m.Transactions).SingleOrDefault(m => m.AccountNumber == mdl.ToAccountNum); //Finds both accounts
                Account fromAccount = _context.Account.Include(m => m.Transactions).SingleOrDefault(m => m.AccountNumber == mdl.FromAccountNum);
                // If account doesn't exist in DB, throws an error. 
                if (toAccount == null) 
                {
                    ModelState.AddModelError("ToAccountNum", "The Account Does not Exist in the Database");
                    return View("Transfer", mdl);
                }
                else
                {
                    // Call verification checks on Amount.
                    var feedback = ViewChecks.AmountChecks(mdl.Amount);
                    if (!feedback.Equals(""))
                    {
                        ModelState.AddModelError(nameof(mdl.Amount), feedback);
                        return View("Transfer", mdl);
                    }

                    // Performs Transactions.
                    try
                    {
                        TransactionExtensionFactory.Transfer(fromAccount, toAccount, mdl.Amount, "Transferred to account: " + toAccount.AccountNumber + "Comment: " + mdl.Comment);
                        await _context.SaveChangesAsync();
                        return RedirectToAction("Index", "Customer");
                    } catch (Exception e)
                    {
                        // If there are insufficient funds or a negative value, returns a view with the appropiate error.
                        if (e is NegativeValueException) 
                        {
                            ModelState.AddModelError("amount", "The Account Does not Exist in the Database");
                            return View("Transfer", mdl);

                        } else if ( e is InsufficientFundsException)
                        {
                            ModelState.AddModelError("amount", "Insufficient Funds for Transfer");
                            return View("Transfer", mdl);
                        }
                        else
                        {
                            throw;
                        }
                    }
                }
                
            }
           
        }
        public IActionResult ATM(int accnum)
        {
            Account acc = _context.Account.Include(acc=>acc.Transactions).SingleOrDefault(acc => acc.AccountNumber == accnum);
            // Gets the balance. 
            decimal balance = AccountExt.GetBalance(acc); 
            AtmViewModel mdl = new AtmViewModel 
            {
                AccountNumber = accnum,
                Balance = balance
            };
            return View("ATM",mdl);

        }
    }
}
