using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using DatabaseContext.Models;
using DatabaseContext.IAttributes;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseContext.ViewModels
{
    public class BillPayViewModel : IAccountNumber, IAmount, IScheduleDate
    {
        public Customer Customer { get; set; }
        public int BillPayID { get; set; }

        public int AccountNumber { get; set; }
        public Account Account { get; set; }
        public List<Payee> Payees { get; set; }
        public Payee Payee { get; set; }
        public int PayeeID { get; set; }
        [Required(ErrorMessage = "An amount is required")]
        [DataType(DataType.Currency)]
        [Range(0.01, Double.MaxValue, ErrorMessage = "Amount must be greater than 0.")]
        public decimal Amount { get; set; }

        [DataType(DataType.DateTime, ErrorMessage = "Must be DateTime")]
        [Column(TypeName = "datetime2")]
        public DateTime ScheduleDate { get; set; }

        [Required]
        public BillPayPeriod Period { get; set; }

        public BillPayStatus Status { get; set; }
    }


}
