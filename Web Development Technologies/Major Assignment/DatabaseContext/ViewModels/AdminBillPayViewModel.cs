using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DatabaseContext.Models;
using X.PagedList;

namespace DatabaseContext.ViewModels
{
    public class AdminBillPayViewModel
    {
        public IPagedList<BillPay> BillPays { get; set; }
        public int CustomerID { get; set; }
    }
}
