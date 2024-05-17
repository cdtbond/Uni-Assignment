using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using DatabaseContext.IAttributes;

namespace DatabaseContext.Models
{
    public enum BillPayPeriod
    {
        OnceOff = 1,
        Monthly = 2,
        Quarterly = 3,
        Annually = 4
    }

    public enum BillPayStatus
    {
        Failed = 1,
        Blocked = 2,
        Unblocked = 3
    }

    public class BillPay : IModifyDate, IAmount, IScheduleDate
    {
        [Required]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int BillPayID { get; set; }

        // Inspired by Tutorial 5.
        [Required]
        [StringLength(60, MinimumLength = 3)]
        public int AccountNumber { get; set; }
        public virtual Account Account { get; set; }

        [Required]
        public int PayeeID { get; set; }
        public virtual Payee Payee { get; set; }

        [Required(ErrorMessage = "An amount is required")]
        [DataType(DataType.Currency)]
        [Column(TypeName = "money")]
        [RegularExpression(@"^[0-9]*[.][0-9]{2})$", ErrorMessage = "Amount must have 2 decimal places.")]
        [Range(0.0, Double.MaxValue, ErrorMessage = "Amount must be greater than 0.")]
        public decimal Amount { get; set; }

        [Required(ErrorMessage = "A Date is required")]
        [DataType(DataType.DateTime, ErrorMessage = "Must be DateTime")]
        [Column(TypeName = "datetime2")]
        public DateTime ScheduleDate { get; set; }

        [Required]
        public BillPayPeriod Period { get; set; }

        [Required]
        [DataType(DataType.DateTime, ErrorMessage = "Must be a DateTime.")]
        [Column(TypeName = "datetime2")]
        public DateTime ModifyDate { get; set; }

        public BillPayStatus Status { get; set; }

    }
}