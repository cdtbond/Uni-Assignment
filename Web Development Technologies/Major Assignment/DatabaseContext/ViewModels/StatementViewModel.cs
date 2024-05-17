using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using X.PagedList;
using DatabaseContext.Models;


namespace DatabaseContext.ViewModels
{
    public class StatementViewModel
    {
        public IPagedList <Transaction> Transactions;
        public int AccountNumber { get; set; }
        
    }
}
