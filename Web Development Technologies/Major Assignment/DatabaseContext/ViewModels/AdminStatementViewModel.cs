using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using X.PagedList;
using DatabaseContext.Models;

namespace DatabaseContext.ViewModels
{
    public class AdminStatementViewModel
    {
        public IPagedList<Transaction> Transactions { get; set; }
        public int CustNum { get; set; }
        public string FromDate { get; set; }
        public string ToDate { get; set; }
    }
}
