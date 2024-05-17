using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;
using DatabaseContext.IAttributes;

namespace DatabaseContext.ViewModels
{
    public class SessionViewModel:ICustomerID
    {
        public string CustomerName { get; set; }


        [Required(ErrorMessage = "A customer ID is required")]
        public int CustomerID { get; set; }


    }
}
