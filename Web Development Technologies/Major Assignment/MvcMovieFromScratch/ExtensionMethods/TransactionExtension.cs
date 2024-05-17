

using System;
using System.Threading.Tasks;
using Assignment2.Exceptions;
using DatabaseContext.Models;

namespace Assignment2.ExtensionMethods
{
    public static class TransactionExtensionFactory
    {

        public static void Deposit(Account account, decimal value,string comment)
        {
            // Use to check whether it is a negative value, not reused.
            _ = new TransactionExtension(account, value);
            AddTransaction(account, TransactionType.Deposit, value, comment);
        }
        public static void BillPay(Account fromAccount, decimal value,string comment)
        {
            // No service charges identified on BillPay, so use free transaction, not reused.
            _ = new ChargeableTransactionExtension(fromAccount, value);
            // Bill Pay.
            AddTransaction(fromAccount, TransactionType.Bill, value, comment);
        }
        public static void Withdraw(Account fromAccount, decimal value, string comment)
        {

            if (AccountExt.GetFreeTransactions(fromAccount) > 0)
            {
                // Fee free transaction, not reused.
                _ = new ChargeableTransactionExtension(fromAccount, value);
            }
            else
            {
                decimal chargeValue = 0.10m;
                // Charged Transactions.
                var trans = new ChargeableTransactionExtension(fromAccount, value, chargeValue, TransactionType.Withdraw);
                var serviceCharge = trans.ServiceTransaction;
                // Add Service Fee Transaction.
                AddTransaction(fromAccount, TransactionType.ServiceCharge, chargeValue, "Service Charge of " + serviceCharge.Value + " for " + serviceCharge.ChargeType + ".");
                value += chargeValue;
            }
            // Withdraw.
            AddTransaction(fromAccount, TransactionType.Withdraw, value, comment);
        }

        public static void Transfer(Account fromAccount,Account toAccount, decimal value, string comment)
        {
            if (AccountExt.GetFreeTransactions(fromAccount) > 0)
            {
                // Fee free transaction.
                _ = new ChargeableTransactionExtension(fromAccount, value);
            }
            else
            {
                decimal chargeValue = 0.20m;
                // Charged Transactions.
                ChargeableTransactionExtension trans = new ChargeableTransactionExtension(fromAccount, value, chargeValue, TransactionType.Transfer);
                var serviceCharge = trans.ServiceTransaction;
                AddTransaction(toAccount, TransactionType.Transfer, value, comment);
                AddTransaction(fromAccount, TransactionType.ServiceCharge, chargeValue, "Service Charge of " + serviceCharge.Value + " for " + serviceCharge.ChargeType + ".");
                value += chargeValue;
            }

            // Add Transfer transaction.
            AddTransaction(fromAccount, toAccount, TransactionType.Transfer, value, comment);
        }


        private static void AddTransaction(Account fromAccount, TransactionType type, decimal value, string comment)
        {
            fromAccount.Transactions.Add(
                new Transaction
                {
                    Account = fromAccount,
                    AccountNumber = fromAccount.AccountNumber,
                    TransactionType = type,
                    Amount = value,
                    Comment = comment,
                    ModifyDate = DateTime.UtcNow
                });
        }
        // Transfer from Transacation.
        private static void AddTransaction(Account fromAccount, Account destAccount, TransactionType type, decimal value, string comment)
        {
            fromAccount.Transactions.Add(
                new Transaction
                {
                    Account = fromAccount,
                    AccountNumber = fromAccount.AccountNumber,
                    DestinationAccount = destAccount,
                    DestAccount = destAccount.AccountNumber,
                    TransactionType = type,
                    Amount = value,
                    Comment = comment,
                    ModifyDate = DateTime.UtcNow
                });
        }


    }
    // Loosely based on a simplified version of Chris Bond's Assignment 1 Transaction Classes.
    public record TransactionExtension
    {
        public decimal Value { get; protected set; }
        protected Account Account;


        // All transactions have Value.
        public TransactionExtension(Account FromAccount, decimal Value)
        {
            Account = FromAccount;
            // Check value is positive.
            if (Value > 0)
            {
                this.Value = Value;
            }
            else
            {
                throw new NegativeValueException();
            }
        }

    }

    public record ChargeableTransactionExtension : TransactionExtension
    {
        public STransactionExtension ServiceTransaction { get; private set; }
        // Free Transaction.
        public ChargeableTransactionExtension(Account fromAccount, decimal value) : base(fromAccount, value)
        {
            // If not enough ongoing funds after this transaction, then throw a custom exception.
            if (AccountExt.InsufficientOngoingFunds(value,fromAccount))
            {
                throw new InsufficientFundsException();
            }
        }

        // Charged transaction.
        public ChargeableTransactionExtension(Account fromAccount, decimal value, decimal fee,TransactionType type) : base(fromAccount, value + fee)
        {
            ServiceTransaction = new STransactionExtension(fromAccount, fee, type);
            if (AccountExt.InsufficientOngoingFunds(value+fee, fromAccount))
            {
                throw new InsufficientFundsException();
            }
        }
    }

    // Service Fee Comment is automated, based on information from the transaction that triggered the service charge.
    public record STransactionExtension : TransactionExtension
    {
        public TransactionType ChargeType { get; init; }
        public String Comment { get; init; }
        public decimal Fee {get; init;}
        public STransactionExtension(Account fromAccount, decimal value,TransactionType ChargeType) : base(fromAccount, value) 
        {
            this.ChargeType = ChargeType;
            this.Fee = value;
            this.Comment = "Service fee for " + ChargeString(ChargeType);
        }

        private static string ChargeString(TransactionType type)
        {
            if (type == TransactionType.Transfer)
            {
                return "Transfer ";
            }
            else if (type == TransactionType.Withdraw)
            {
                return "Withdraw ";
            }
            return "Error ";
        }
    }




}
