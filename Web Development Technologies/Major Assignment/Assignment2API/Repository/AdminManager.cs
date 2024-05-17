using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using DatabaseContext.Data;
using DatabaseContext.Models;
using Microsoft.AspNetCore.Identity;

namespace Assignment2API.Repository
{
    public class AdminManager
    {
        private readonly UserManager<ApplicationUser> _userManger;
        private readonly McbaContext _context;
        public AdminManager(UserManager<ApplicationUser> userManager,McbaContext context)
        {
            _userManger = userManager;
            _context = context;
        }

        public IEnumerable<ApplicationUser> GetLogins()
        {
            // Returns all logins as a list.
            var users = _userManger.Users.ToList();

            return users;
        }

        // Returns the transactions for a specific customer.
        public IEnumerable<Transaction> GetTransactions(int custNum)
        {
            var list = _context.Account.Include(m => m.Transactions).Where(m => m.CustomerID == custNum).ToList();
            List<Transaction> trList = new List<Transaction>();

            foreach (var acc in list)
            {
                foreach (var tr in acc.Transactions)
                {
                    trList.Add(tr);
                }
            }

            return trList;
        }

        public async Task<int> LockUserAsync(int custNum)
        {
         // Finds the user to be locked.
         //var loginUser = _context.Login.SingleOrDefault(l => l.CustomerID == custNum);

         var user = await _userManger.FindByIdAsync(custNum+"");
         var statusLock = await _userManger.SetLockoutEnabledAsync(user, true);
         // Lockout of 1 minute executed.
         var statusLockDate = await _userManger.SetLockoutEndDateAsync(user, DateTime.Now.AddMinutes(1));

         if(!statusLock.Succeeded || !statusLockDate.Succeeded)
         {
             custNum = 0;
         }

            return custNum;
        }

        public int BlockBillPay(int billPayID)
        {
            return ToggleBillPay(billPayID, BillPayStatus.Blocked);
        }

        private int ToggleBillPay(int billPayID, BillPayStatus status)
        {
            try
            {
                // Finds the Bill to be un/blocked.
                var billPay = _context.BillPay.SingleOrDefault(bp => bp.BillPayID == billPayID);
                billPay.Status = status;
                _context.SaveChanges();
            }
            catch (Exception)
            {
                billPayID = 0;
            }

            return billPayID;
        }


        public int UnblockBillPay(int billPayID)
        {

            return ToggleBillPay(billPayID, BillPayStatus.Unblocked);

        }



        public IEnumerable<BillPay> GetBillPays(int custNum)
        { //Returns the transactions for a specific customer.
            var list = _context.Account.Include(m => m.BillPays).Where(m => m.CustomerID == custNum).ToList();
            var billPayList = new List<BillPay>();

            foreach (var acc in list)
            {
                foreach (var b in acc.BillPays)
                {
                    billPayList.Add(b);
                }
            }

            return billPayList;
        }

        public int GetCustomerFromBill(int billPay)
        {
            //Returns the customer id from the attached Bill.
            var bill = _context.BillPay.Where(b => b.BillPayID == billPay)
                                .Include(b => b.Account).ToList();
            return bill[0].AccountNumber;
        }



    }
}
