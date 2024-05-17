using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using DatabaseContext.Models;
using DatabaseContext.Data;
using System;
using System.Linq;
using Microsoft.AspNetCore.Identity;

namespace Assignment2.Data
{
    public static class SeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            // Using Tutorial 4 as a starting point.
            using (var context = new McbaContext(
                serviceProvider.GetRequiredService<
                    DbContextOptions<McbaContext>>()))
            {
                // See if any customers are present.
                if (context.Customer.Any())
                {
                    return;   // DB has been seeded
                }

                context.Customer.AddRange(
                    new Customer
                    {
                        CustomerID = 2100,
                        CustomerName = "Matthew Bolger",
                        TFN = "10055668911",
                        Address = "121 Fake St",
                        City = "Tarneit",
                        PostCode = "3033",
                        State = "VIC"
                    },
                    new Customer
                    {
                        CustomerID = 2200,
                        CustomerName = "Shekhar Kalra",
                        TFN = "10055668912",
                        Address = "122 Fake St",
                        City = "Tarneit",
                        PostCode = "3033",
                        State = "VIC"
                    },
                    new Customer
                    {
                        CustomerID = 2300,
                        CustomerName = "Rodney Cocker",
                        TFN = "10055668913",
                        Address = "123 Fake St",
                        City = "Tarneit",
                        PostCode = "3033",
                        State = "VIC"
                    },
                    new Customer
                    {
                        CustomerID = 2400,
                        CustomerName = "Christopher John Bond",
                        TFN = "10055668914",
                        Address = "124 Fake St",
                        City = "Tarneit",
                        PostCode = "3033",
                        State = "VIC"
                    },
                    new Customer
                    {
                        CustomerID = 2500,
                        CustomerName = "Tuan Luong",
                        TFN = "10055668915",
                        Address = "125 Fake St",
                        City = "Tarneit",
                        PostCode = "3033",
                        State = "VIC"
                    },
                    new Customer
                    {
                        CustomerID = 1000,
                        CustomerName = "Administrator",
                        TFN = "10000000000",
                        Address = "125 Fake St",
                        City = "Tarneit",
                        PostCode = "3000",
                        State = "VIC"
                    }
                );
                context.Payee.AddRange(
                    new Payee
                    {
                        PayeeName = "Telstra",
                        Address = "300 Collins St",
                        City = "Melbourne",
                        State = "VIC",
                        PostCode = "3000",
                        Phone = "+61 3000 1000"
                    },
                    new Payee
                    {
                        PayeeName = "Optus",
                        Address = "1 Lyonpark Rd",
                        City = "Macquarie Park",
                        State = "NSW",
                        PostCode = "2113",
                        Phone = "+61 2000 1000"
                    },
                    new Payee
                    {
                        PayeeName = "TPG",
                        Address = "65 Waterloo Rd",
                        City = "Macquarie Park",
                        State = "NSW",
                        PostCode = "2113",
                        Phone = "+61 3000 2000"
                    },
                    new Payee
                    {
                        PayeeName = "Aussie Broadband",
                        Address = "3 Electra Ave",
                        City = "Morwell",
                        State = "VIC",
                        PostCode = "3840",
                        Phone = "+61 3000 1000"
                    }
                );
                context.Account.AddRange(
                   new Account
                   {
                       AccountNumber = 111,
                       AccountType = AccountType.Checking,
                       CustomerID = 2100, //Matthw Bolger
                       ModifyDate = DateTime.Parse("2021-2-15")
                   },
                   new Account
                   {
                       AccountNumber = 112,
                       AccountType = AccountType.Savings,
                       CustomerID = 2100, //Matthw Bolger
                       ModifyDate = DateTime.Parse("2021-2-15")
                   },
                   new Account
                   {
                       AccountNumber = 121,
                       AccountType = AccountType.Checking,
                       CustomerID = 2200, //Shekhar Kalra
                       ModifyDate = DateTime.Parse("2021-2-15")
                   },
                   new Account
                   {
                       AccountNumber = 122,
                       AccountType = AccountType.Savings,
                       CustomerID = 2200, //Shekhar Kalra
                       ModifyDate = DateTime.Parse("2021-2-15")
                   },
                   new Account
                   {
                       AccountNumber = 131,
                       AccountType = AccountType.Savings,
                       CustomerID = 2300, //Rodney Cocker
                       ModifyDate = DateTime.Parse("2021-2-15")
                   },
                   new Account
                   {
                       AccountNumber = 141,
                       AccountType = AccountType.Savings,
                       CustomerID = 2400, //Chris Bond
                       ModifyDate = DateTime.Parse("2021-2-15")
                   },
                   new Account
                   {
                       AccountNumber = 151,
                       AccountType = AccountType.Savings,
                       CustomerID = 2500, //Tuan Luong
                       ModifyDate = DateTime.Parse("2021-2-15")
                   }
                );
                context.Transaction.AddRange(
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 111,
                        Amount = 871,
                        Comment = "Initial Deposit",
                        ModifyDate = DateTime.Parse("2021-1-05")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 112,
                        Amount = 379,
                        Comment = "Initial Deposit",
                        ModifyDate = DateTime.Parse("2021-1-05")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 121,
                        Amount = 627,
                        Comment = "Initial Deposit",
                        ModifyDate = DateTime.Parse("2021-1-05")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 122,
                        Amount = 716,
                        Comment = "Initial Deposit",
                        ModifyDate = DateTime.Parse("2021-1-05")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 131,
                        Amount = 519,
                        Comment = "Initial Deposit",
                        ModifyDate = DateTime.Parse("2021-1-05")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 141,
                        Amount = 300,
                        Comment = "Initial Deposit",
                        ModifyDate = DateTime.Parse("2021-1-05")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 151,
                        Amount = 810,
                        Comment = "Initial Deposit",
                        ModifyDate = DateTime.Parse("2021-1-05")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 111,
                        Amount = 747,
                        Comment = "Second Transaction",
                        ModifyDate = DateTime.Parse("2021-1-06")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 112,
                        Amount = 236,
                        Comment = "Second Transaction",
                        ModifyDate = DateTime.Parse("2021-1-06")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 121,
                        Amount = 619,
                        Comment = "Second Transaction",
                        ModifyDate = DateTime.Parse("2021-1-06")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 122,
                        Amount = 191,
                        Comment = "Second Transaction",
                        ModifyDate = DateTime.Parse("2021-1-06")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 131,
                        Amount = 616,
                        Comment = "Second Transaction",
                        ModifyDate = DateTime.Parse("2021-1-06")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 141,
                        Amount = 733,
                        Comment = "Second Transaction",
                        ModifyDate = DateTime.Parse("2021-1-06")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 151,
                        Amount = 871,
                        Comment = "Second Transaction",
                        ModifyDate = DateTime.Parse("2021-1-06")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 111,
                        Amount = 59,
                        Comment = "Third Transaction",
                        ModifyDate = DateTime.Parse("2021-1-07")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 112,
                        Amount = 60,
                        Comment = "Third Transaction",
                        ModifyDate = DateTime.Parse("2021-1-07")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Bill,
                        AccountNumber = 121,
                        Amount = 954,
                        Comment = "Third Transaction",
                        ModifyDate = DateTime.Parse("2021-1-07")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 122,
                        Amount = 939,
                        Comment = "Third Transaction",
                        ModifyDate = DateTime.Parse("2021-1-07")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 131,
                        Amount = 2,
                        Comment = "Third Transaction",
                        ModifyDate = DateTime.Parse("2021-1-07")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 141,
                        Amount = 327,
                        Comment = "Third Transaction",
                        ModifyDate = DateTime.Parse("2021-1-07")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 151,
                        Amount = 950,
                        Comment = "Third Transaction",
                        ModifyDate = DateTime.Parse("2021-1-07")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 111,
                        Amount = 914,
                        Comment = "Fourth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-08")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Bill,
                        AccountNumber = 112,
                        Amount = 496,
                        Comment = "Fourth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-08")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 121,
                        Amount = 279,
                        Comment = "Fourth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-08")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 122,
                        Amount = 1200,
                        Comment = "Fourth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-08")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 131,
                        Amount = 765,
                        Comment = "Fourth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-08")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 141,
                        Amount = 13,
                        Comment = "Fourth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-08")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 151,
                        Amount = 385,
                        Comment = "Fourth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-08")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 111,
                        Amount = 478,
                        Comment = "Fifth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-09")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 112,
                        Amount = 147,
                        Comment = "Fifth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-09")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 121,
                        Amount = 34,
                        Comment = "Fifth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-09")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 122,
                        Amount = 723,
                        Comment = "Fifth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-09")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 131,
                        Amount = 1414,
                        Comment = "Fifth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-09")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 141,
                        Amount = 617,
                        Comment = "Fifth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-09")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Bill,
                        AccountNumber = 151,
                        Amount = 1400,
                        Comment = "Fifth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-09")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 111,
                        Amount = 646,
                        Comment = "Sixth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-10")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 112,
                        Amount = 746,
                        Comment = "Sixth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-10")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 121,
                        Amount = 134,
                        Comment = "Sixth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-10")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Bill,
                        AccountNumber = 122,
                        Amount = 637,
                        Comment = "Sixth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-10")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 131,
                        Amount = 385,
                        Comment = "Sixth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-10")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Bill,
                        AccountNumber = 141,
                        Amount = 1071,
                        Comment = "Sixth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-10")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 151,
                        Amount = 126,
                        Comment = "Sixth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-10")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Transfer,
                        AccountNumber = 111,
                        DestAccount = 131,
                        Amount = 2121,
                        Comment = "First Transfer",
                        ModifyDate = DateTime.Parse("2021-1-11")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 112,
                        Amount = 339,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-11")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Bill,
                        AccountNumber = 121,
                        Amount = 357,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-11")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Bill,
                        AccountNumber = 122,
                        Amount = 217,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-11")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Transfer,
                        AccountNumber = 131,
                        Amount = 2121,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-11")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 141,
                        Amount = 244,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-11")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 151,
                        Amount = 743,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-11")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 111,
                        Amount = 399,
                        Comment = "Eigth Transaction",
                        ModifyDate = DateTime.Parse("2021-1-12")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Transfer,
                        AccountNumber = 112,
                        DestAccount = 141,
                        Amount = 531,
                        Comment = "First Transfer",
                        ModifyDate = DateTime.Parse("2021-1-12")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 121,
                        Amount = 362,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-12")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 122,
                        Amount = 525,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-12")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 131,
                        Amount = 1380,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-12")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Transfer,
                        AccountNumber = 141,
                        Amount = 531,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-12")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Bill,
                        AccountNumber = 151,
                        Amount = 215,
                        Comment = "Receiving Transfer",
                        ModifyDate = DateTime.Parse("2021-1-12")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Bill,
                        AccountNumber = 111,
                        Amount = 26,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-13")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Bill,
                        AccountNumber = 112,
                        Amount = 524,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-13")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Transfer,
                        AccountNumber = 121,
                        DestAccount = 151,
                        Amount = 632,
                        Comment = "First Transfer",
                        ModifyDate = DateTime.Parse("2021-1-13")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 122,
                        Amount = 279,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-13")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 131,
                        Amount = 158,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-13")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 141,
                        Amount = 704,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-13")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Transfer,
                        AccountNumber = 151,
                        Amount = 632,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-13")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 111,
                        Amount = 1821,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-14")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 112,
                        Amount = 1055,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-14")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 121,
                        Amount = 1668,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-14")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 122,
                        Amount = 512,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-14")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 131,
                        Amount = 615,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-14")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 141,
                        Amount = 1534,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-14")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 151,
                        Amount = 733,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-14")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 111,
                        Amount = 1860,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-15")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 112,
                        Amount = 1183,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-15")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 121,
                        Amount = 1807,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-15")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 122,
                        Amount = 547,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-15")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 131,
                        Amount = 599,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-15")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 141,
                        Amount = 1615,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-15")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 151,
                        Amount = 728,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-15")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 111,
                        Amount = 1950,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-16")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 112,
                        Amount = 1154,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-16")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 121,
                        Amount = 1917,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-16")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 122,
                        Amount = 479,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-16")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 131,
                        Amount = 615,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-16")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 141,
                        Amount = 1391,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-16")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 151,
                        Amount = 629,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-16")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 111,
                        Amount = 2026,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-17")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 112,
                        Amount = 1113,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-17")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 121,
                        Amount = 1647,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-17")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 122,
                        Amount = 486,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-17")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 131,
                        Amount = 599,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-17")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 141,
                        Amount = 1467,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-17")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 151,
                        Amount = 729,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-17")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 111,
                        Amount = 834,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-18")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 112,
                        Amount = 612,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-18")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Bill,
                        AccountNumber = 121,
                        Amount = 788,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-18")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 122,
                        Amount = 188,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-18")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Bill,
                        AccountNumber = 131,
                        Amount = 279,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-18")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 141,
                        Amount = 625,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-18")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Withdraw,
                        AccountNumber = 151,
                        Amount = 338,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-18")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 111,
                        Amount = 2125,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-19")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 112,
                        Amount = 1208,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-19")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 121,
                        Amount = 1782,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-19")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 122,
                        Amount = 479,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-19")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 131,
                        Amount = 571,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-19")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 141,
                        Amount = 1400,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-19")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 151,
                        Amount = 645,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-19")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 111,
                        Amount = 9421,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-20")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 112,
                        Amount = 5163,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-20")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 121,
                        Amount = 8145,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-20")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 122,
                        Amount = 3252,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-20")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 131,
                        Amount = 4488,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-20")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 141,
                        Amount = 8038,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-20")
                    },
                    new Transaction
                    {
                        TransactionType = TransactionType.Deposit,
                        AccountNumber = 151,
                        Amount = 3772,
                        Comment = "etc",
                        ModifyDate = DateTime.Parse("2021-1-20")
                    }
                );

                context.BillPay.AddRange(
                    new BillPay
                    {
                        AccountNumber = 111,
                        PayeeID = 1,
                        Amount = 20.05M,
                        ScheduleDate = DateTime.Parse("25/01/21"),
                        Period = BillPayPeriod.OnceOff,
                        Status = BillPayStatus.Unblocked,
                        ModifyDate = DateTime.Parse("20/01/21")
                    },
                    new BillPay
                    {
                        AccountNumber = 112,
                        PayeeID = 2,
                        Amount = 10.05M,
                        ScheduleDate = DateTime.Parse("25/01/21"),
                        Period = BillPayPeriod.OnceOff,
                        Status = BillPayStatus.Unblocked,
                        ModifyDate = DateTime.Parse("20/01/21")
                    }
                    );

                context.SaveChanges();
            }
        }
    }

    public static class SeedUsers
    {





        public static void Initialize(UserManager<ApplicationUser> userManager)
        {


            if (userManager.FindByIdAsync("2100").Result == null)
            {
                var user = new ApplicationUser
                {
                    Id = "2100",
                    PhoneNumber = "+61 0000 1111",
                    UserName = "12345678",
                    // Chris Bond Email.
                    Email = "s3767838@student.rmit.edu.au"
                };

                var result = userManager.CreateAsync(user, "abc123").Result;
            }
            if (userManager.FindByIdAsync("2200").Result == null)
            {
                var user = new ApplicationUser
                {
                    Id = "2200",
                    PhoneNumber = "+61 0000 1112",
                    UserName = "38074569",
                    // Chris Bond Email.
                    Email = "s3767838@student.rmit.edu.au",
                };

                var result = userManager.CreateAsync(user, "youWill_n0tGuess-This!").Result;
            }
            if (userManager.FindByIdAsync("2300").Result == null)
            {

                var user = new ApplicationUser
                {
                    Id = "2300",
                    PhoneNumber = "+61 0000 1113",
                    UserName = "17963428",
                    // Chris Bond Email.
                    Email = "s3767838@student.rmit.edu.au",
                };

                var result = userManager.CreateAsync(user, "ilovermit2020").Result;

            }
            if (userManager.FindByIdAsync("2400").Result == null)
            {

                var user = new ApplicationUser
                {
                    Id = "2400",
                    PhoneNumber = "+61 0000 1114",
                    UserName = "20001999",
                    // Tuan Luong Email
                    Email = "s3652729@student.rmit.edu.au",
                };

                var result = userManager.CreateAsync(user, "abc123").Result;
            }
            if (userManager.FindByIdAsync("2500").Result == null)
            {
                var user = new ApplicationUser
                {
                    Id = "2500",
                    PhoneNumber = "+61 0000 1115",
                    UserName = "19980002",
                    // Tuan Luong Email
                    Email = "s3652729@student.rmit.edu.au",
                };

                var result = userManager.CreateAsync(user, "abc123").Result;
            }
            if (userManager.FindByIdAsync("1000").Result == null)
            {
                var user = new ApplicationUser
                {
                    Id = "1000",
                    PhoneNumber = "+61 0000 1116",
                    UserName = "admin",
                    // Tuan Luong Email
                    Email = "s3652729@student.rmit.edu.au",
                };

                var result = userManager.CreateAsync(user, "admin").Result;
            }
        }
        
    }
}
