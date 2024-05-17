using System.Collections.Generic;
using DatabaseContext.Models;

namespace DatabaseContext.ViewModels
{
    public class CustIndexViewModel
    {
        public List<Account> Accounts { get; set; }
        public Dictionary<int,decimal> Balances { get; set; }
    }
}
