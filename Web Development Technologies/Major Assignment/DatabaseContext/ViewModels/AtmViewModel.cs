using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using DatabaseContext.IAttributes;
using DatabaseContext.Models;

namespace DatabaseContext.ViewModels
{
    public class AtmViewModel : IAccountNumber,IAmount
    {
        [RegularExpression(@"^[0-9]*$", ErrorMessage = "Must be an integer.")]
        public int AccountNumber { get; set; }
       public TransactionType TransactionType { get; set; }

       public decimal Balance { get; set; }

        [Required(ErrorMessage = "An amount is required")]
        [DataType(DataType.Currency)]
        [Range(0.01, Double.MaxValue, ErrorMessage = "Amount must be greater than 0.")]
        public decimal Amount { get; set; }

       public string Comment { get; set; }
    }
}
