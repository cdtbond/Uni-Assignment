using DatabaseContext.ViewModels;
using DatabaseContext.Models;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Assignment2.ExtensionMethods
{
    public static class BillPayExtension
    {
        public static void AddBillPay(List<BillPay> bills,BillPayViewModel bill)
        {
            bills.Add(
                 new BillPay
                 {
                     Account = bill.Account,
                     Amount = bill.Amount,
                     AccountNumber = bill.Account.AccountNumber,
                     Payee = bill.Payee,
                     PayeeID = bill.Payee.PayeeID,
                     ScheduleDate = bill.ScheduleDate,
                     Period = bill.Period,
                     Status = bill.Status
                 }) ;
        }

        public static void RemoveBillPay(Account account, int index)
        {
            account.BillPays.RemoveAt(index);
        }

        internal static void ModifyBillPay(List<BillPay> billPays, BillPayViewModel newBill)
        {
            // Find the index of the current Bill.
            int index = billPays.FindIndex(x => x.BillPayID == newBill.BillPayID);
            // Remove the old Bill.
            billPays.RemoveAt(index);


            // Add the new Bill.
            billPays.Add(
                 new BillPay
                 {
                     Account = newBill.Account,
                     Amount = newBill.Amount,
                     AccountNumber = newBill.Account.AccountNumber,
                     Payee = newBill.Payee,
                     PayeeID = newBill.Payee.PayeeID,
                     ScheduleDate = newBill.ScheduleDate,
                     Period = newBill.Period,
                     Status = newBill.Status
                 });

        }
    }


}
