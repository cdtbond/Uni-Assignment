using Microsoft.AspNetCore.Mvc;
using DatabaseContext.Models;
using DatabaseContext.Data;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Assignment2.Filters;
using Microsoft.AspNetCore.Http;
using Assignment2.ExtensionMethods;
using DatabaseContext.ViewModels;
using Assignment2.Utilities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;

namespace Assignment2.Controllers
{
    [Authorize(Roles = "Customer")]
    public class BillPayController : Controller
    {
        private readonly UserManager<ApplicationUser> _userMan;


        // Follows Day 5 Tutorial approach.
        private readonly McbaContext _context;
        public BillPayController(McbaContext context, UserManager<ApplicationUser> userMan, SignInManager<ApplicationUser> signInMan) {
            _context = context;
            _userMan = userMan;
        }

        public async Task<IActionResult> Index()
        {
            int ? custId = HttpContext.Session.GetInt32("customerid");

            var custs = _context.Customer
                .Include(c => c.Accounts.Where(c => c.CustomerID == custId))
                    .ThenInclude(a => a.BillPays)
                        .ThenInclude(b => b.Payee);
            var cust = await custs.SingleOrDefaultAsync(c => c.CustomerID == custId);
            return View(cust);
        }

        public async Task<IActionResult> Modify(int id)
        {

            // Get the correct Bill.
            BillPay billPay = await _context.BillPay
                .Include(b => b.Account)
                .Include(b => b.Payee)
                .SingleOrDefaultAsync(b => b.BillPayID == id);


            return View(
                new BillPayViewModel
                {
                    AccountNumber = billPay.AccountNumber,
                    BillPayID = billPay.BillPayID,
                    Account = billPay.Account,
                    Payee = billPay.Payee,
                    PayeeID = billPay.PayeeID,
                    Amount = billPay.Amount,
                    ScheduleDate = billPay.ScheduleDate,
                    Period = billPay.Period,
                    Payees = await _context.Payee.ToListAsync(),
                    Status = BillPayStatus.Unblocked
                });
        }

        [HttpPost]
        public async Task<IActionResult> Modify(BillPayViewModel bill)
        {            
            var accounts = _context.Account.Where(c => c.AccountNumber == bill.AccountNumber)
                    .Include(a => a.BillPays)
                        .ThenInclude(b => b.Payee);
            Account account = await accounts.SingleOrDefaultAsync(a => a.AccountNumber == bill.AccountNumber);
            bill.Account = account;

            // Call verification checks on billAmount.
            var feedback = ViewChecks.AmountChecks(bill.Amount);
            if (!feedback.Equals(""))
            {
                ModelState.AddModelError(nameof(bill.Amount), feedback);
                return View(bill);
            }

            // Return Payee from DB.
            bill.Payee = await _context.Payee.FindAsync(bill.PayeeID);

            BillPayExtension.ModifyBillPay(bill.Account.BillPays, bill);
            await _context.SaveChangesAsync();

            return RedirectToAction(nameof(Index));
        }

        // Approach based on Tutorial 5 code.
        public async Task<IActionResult> Create(int accountNumber)
        {    
            return View(
                new BillPayViewModel
                {
                    AccountNumber = accountNumber,
                    Account = await _context.Account.FindAsync(accountNumber),
                    Payees = await _context.Payee.ToListAsync()
                });
        }

        [HttpPost]
        public async Task<IActionResult> Create(BillPayViewModel bill)
        {

            var accounts = _context.Account.Where(c => c.AccountNumber == bill.AccountNumber)
                    .Include(a => a.BillPays)
                        .ThenInclude(b => b.Payee);
            Account account = await accounts.SingleOrDefaultAsync(a => a.AccountNumber == bill.AccountNumber);
            bill.Account = account;
            bill.Payees = await _context.Payee.ToListAsync();
            // Call verification checks on billAmount.
            var feedback = ViewChecks.AmountChecks(bill.Amount);
            if (!feedback.Equals(""))
            {
                ModelState.AddModelError(nameof(bill.Amount), feedback);
                return View(bill);
            }

            if(ViewChecks.EmptyDate(bill.ScheduleDate))
            {
                ModelState.AddModelError(nameof(bill.ScheduleDate), "Date must be in the future.");
                return View(bill);
            }

            // Return Payee from DB.
            bill.Payee = await _context.Payee.FindAsync(bill.PayeeID);
            
            BillPayExtension.AddBillPay(bill.Account.BillPays,bill);
            await _context.SaveChangesAsync();

            return RedirectToAction(nameof(Index));
        }

        public async Task<IActionResult> Remove(int id)
        {
            // Get the correct Bill.
            BillPay billPay = await _context.BillPay
                .Include(b=>b.Account)
                .SingleOrDefaultAsync(b => b.BillPayID == id);

            // Get the Account with all the Bills.
            Account account = await _context.Account
                .Include(a=>a.BillPays)
                .SingleOrDefaultAsync(a=>a.AccountNumber==billPay.AccountNumber);


            // Get the position in the list of bills.
            List<BillPay> billPays = account.BillPays;
            int index = billPays.IndexOf(billPay);

            // Remove at specified position.
            BillPayExtension.RemoveBillPay(account, index);
            await _context.SaveChangesAsync();

            return RedirectToAction(nameof(Index));
        }


        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
