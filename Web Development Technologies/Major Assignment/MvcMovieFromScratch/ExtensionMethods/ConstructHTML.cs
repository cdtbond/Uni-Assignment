using DatabaseContext.Models;
using DatabaseContext.ViewModels;
using System;
using System.Collections.Generic;

namespace Assignment2.ExtensionMethods
{
    public static class ConstructHTML
    {
        public static string FirstTimeReport(EmailViewModel emailVM)
        {

            var html = ProvideStyle();
            html += "<h3> Welcome to the Main Commercial Bank of Australia, the most trusted bank in Australia.</h3>";
            html += "<h3> We're proud of our reputation of keeping our customers in the know, below is your first transaction statement, expect to receive these every 3 minutes when you've been paying transactions.</h3>";
            // Don't add balance change.
            return ReportBody(emailVM, html,false);
        }

        private static string ReportBody(EmailViewModel emailVM, string documentHead, bool addBalanceChange)
        {
            var html = documentHead;
            // Customer Name.
            html += "<h2> Customer Report for " + emailVM.Customer.CustomerName + "</h2>" + Environment.NewLine;
            for (var i = 0; i < emailVM.Customer.Accounts.Count; i++)
            {
                // Account Numbers.
                html += "<h3>Account " + emailVM.Customer.Accounts[i].AccountNumber + "</h3>" + Environment.NewLine;
                // Current Balance.
                html += "<h3>Balance " + emailVM.Helpers[i].Balance + "</h3>" + Environment.NewLine;

                if(addBalanceChange)
                {
                    html += "<h2> Balance Changed </h2> $" + GetBalance(emailVM.Helpers[i].Transactions) + Environment.NewLine;
                }


                html += "<h2>Transactions</h2>" + Environment.NewLine;
                // Go through all transactions.
                html += LoopThroughTransactions(emailVM.Helpers[i].Transactions) + Environment.NewLine;
            }

            // Footer
            html += "</html>";
            return html;
        }

        private static decimal GetBalance(List<Transaction> transactions)
        {
            decimal balanceChange = 0.00m;
            foreach(var t in transactions)
            {
                // Positive Transaction Types.
                if(t.TransactionType == TransactionType.Deposit || (t.TransactionType == TransactionType.Transfer && t.DestAccount == null))
                {
                    balanceChange += t.Amount; 
                } else
                {
                    balanceChange -= t.Amount;
                }
            }
            return balanceChange;
        }


        public static string OngoingReport(EmailViewModel emailVM)
        {
            var html = ProvideStyle();
            html += "<p> Below is your 3-minute activity status in MCBA.</p>";
            return ReportBody(emailVM, html,true);
        }


        private static string LoopThroughTransactions(List<Transaction> transactions)
        {
            var html = "<table style=\"width:100%\">";
                html +=@"    <tr>
        <th>Transaction Type</th>
        <th>Amount</th>
        <th>Last Modify Date</th>
        <th>Comment</th>
    </tr>";
            foreach (Transaction t in transactions)
            {
                html += "  <tr>";
                html += "<td>" + @Enum.ToObject(typeof(TransactionType), t.TransactionType) + "</td>" + Environment.NewLine;
                html += "<td>" + t.Amount + "</td>" + Environment.NewLine;
                html += "<td>" + t.ModifyDate.ToLocalTime() + "</td>" + Environment.NewLine;
                html += "<td>" + t.Comment + "</td>" + Environment.NewLine;
                html += "  </tr>";
            }
            html += "</table>";
            return html;
        }

        private static string ProvideStyle()
        {
            string s = @"<!DOCTYPE html>
<html>
<head>
<style>
table {
  font - family: arial, sans - serif;
  border - collapse: collapse;
  width: 100 %;
}

td, th {
  border: 1px solid #dddddd;
  text - align: left;
  padding: 8px;
}

tr: nth - child(even) {
  background - color: #dddddd;
}
</style>
</head>
<body>";
        return s;
        }

    }
}
