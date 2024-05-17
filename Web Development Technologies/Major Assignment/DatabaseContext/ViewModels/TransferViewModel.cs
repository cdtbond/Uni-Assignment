using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using DatabaseContext.IAttributes;
using DatabaseContext.Models;

namespace DatabaseContext.ViewModels
{
    public class TransferViewModel:IAmount
    {
        public List<Account> Accounts { get; set; }
        public int FromAccountNum { get; set; }
        [Required(ErrorMessage ="Please Enter an Account Number")]
        public int ToAccountNum { get; set; }
        [Required(ErrorMessage = "An amount is required")]
        [DataType(DataType.Currency)]
        [Range(0.01, Double.MaxValue, ErrorMessage = "Amount must be greater than 0.")]
        public decimal Amount { get; set; }
        public decimal Balance { get; set; }
        public string Comment { get; set; }

    }
}
