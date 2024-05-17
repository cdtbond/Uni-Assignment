using System;
using System.Collections.Generic;
using DatabaseContext.Models;

namespace Assignment2.ExtensionMethods
{
    public static class AccountExt
    {
        // Loosely based on a simplified version of Chris Bond's Assignment 1 Account Classes.
        public static decimal GetBalance(Account a)
        {
            //For performance we are not checking the validity of each of the transactions
            decimal balance = 0;
            a.Transactions.ForEach(x =>
            {
                // Credit Transactions should add money to the balance.
                if (IsCredit(x))
                {
                    balance += x.Amount; ;
                }
                else
                {
                    // Debit Transactions should remove money from the balance.
                    balance -= x.Amount;
                }
            });

            return balance;
        }

        public static decimal GetBalanceFromTransactions(List<Transaction> trans)
        {
            //For performance we are not checking the validity of each of the transactions
            decimal balance = 0;
            trans.ForEach(x =>
            {
                    // Credit Transactions should add money to the balance.
                    if (IsCredit(x))
                    {
                        balance += x.Amount; ;
                    }
                    else
                    {
                        // Debit Transactions should remove money from the balance.
                        balance -= x.Amount;
                    }
            });

            return balance;
        }

        public static List<Transaction> GetTransactionsAfter(Account a,DateTime comparisonTime)
        {
            //For performance we are not checking the validity of each of the transactions
            List<Transaction> trans = new();
            a.Transactions.ForEach(x =>
            {
                // If this transaction occured after the supplied Date Time, add to list.
                if (IsAfter(x, comparisonTime))
                {
                    trans.Add(x);
                }
            });

            return trans;
        }

        private static bool IsAfter(Transaction t,DateTime comparisonTime)
        {
            return t.ModifyDate > comparisonTime;
        }

        public static int GetFreeTransactions(Account a)
        {
            //For performance we are not checking the validity of each of the transactions
            var chargedTransactions = a.Transactions.FindAll(x => !IsCredit(x)).Count;
            // 4 Transactions for free
            var freeTransactions = 4 - chargedTransactions;
            return Math.Max(freeTransactions, 0);
        }

        // Credit Transactions (Deposit Transactions or Transfer Transactions where the Destination Account is null).
        // Otherwise it is a Debit type of Withdrawal (W), Transfer (T), Service Charge (S) or BillPay (B)
        private static bool IsCredit(Transaction t)
        {
            return (t.TransactionType == TransactionType.Deposit || (t.TransactionType == TransactionType.Transfer && t.DestinationAccount == null));
        }

        public static bool InsufficientOngoingFunds(decimal withdrawal,Account a)
        {
            var remainingFunds = GetBalance(a);
            remainingFunds -= MinOngoingBalance(a);

            if (remainingFunds > withdrawal)
            {
                return false;
            }
            else
            {
                return true;
            }
        }

        private static decimal MinOngoingBalance(Account a)
        {
          // Serial, consider changing to async, no dependency between steps (however will need to remove init keyword).
          if (a.AccountType.Equals('C'))
          {
              return 100;
          } else if(a.AccountType.Equals('S'))
          {
              return 0;
          }
            return 0;
        }
        private static decimal MinStartBalance(Account a)
        {
            // Serial, consider changing to async, no dependency between steps (however will need to remove init keyword).
            if (a.AccountType.Equals('C'))
            {
                return 500;
            }
            else if (a.AccountType.Equals('S'))
            {
                return 100;
            }
            return 100;
        }


        public static bool InsufficientStartingFunds(Account a)
        {
            return MinStartBalance(a) > GetBalance(a);
        }

        public static List<int> ListToInt(List<ApplicationUser> list)
        {
            var intList = new List<int>();
            foreach (var item in list)
            {
                intList.Add(int.Parse(item.Id));
            }
            return intList;
        }

    }
}
