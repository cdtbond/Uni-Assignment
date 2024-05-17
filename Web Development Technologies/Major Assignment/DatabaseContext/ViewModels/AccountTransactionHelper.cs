using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DatabaseContext.Models;

namespace DatabaseContext.ViewModels
{
    public class AccountTransactionHelper
    {
        public List<Transaction> Transactions { get; set; }
        public decimal Balance { get; set; }
    }
}
