using DatabaseContext.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DatabaseContext.ViewModels
{
    public class EmailViewModel
    {
        public Customer Customer { get; set; }
        public List<AccountTransactionHelper> Helpers { get; set; }
    }
}
