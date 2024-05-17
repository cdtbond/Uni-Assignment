using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using DatabaseContext.IAttributes;

namespace DatabaseContext.Models
{
    public enum TransactionType
    {
        Deposit = 1,
        Withdraw = 2,
        Transfer = 3,
        ServiceCharge = 4,
        Bill = 5
    }

    public record Transaction : IAmount,IModifyDate
    {
        // Inspired by Tutorial 5.
        public int TransactionID { get; set; }

        [Required]
        public TransactionType TransactionType { get; set; }

        [ForeignKey("Account")]
        [Required]
        public int AccountNumber { get; set; }
        public virtual Account Account { get; set; }

        [ForeignKey("DestinationAccount")]
        public int? DestAccount { get; set; }
        public virtual Account DestinationAccount { get; set; }

        [Required(ErrorMessage = "An amount is required")]
        [DataType(DataType.Currency)]
        [Column(TypeName = "money")]
        [RegularExpression(@"^[0-9]*[.][0-9]{2})$", ErrorMessage = "Amount must have 2 decimal places.")]
        [Range(0.0, Double.MaxValue, ErrorMessage = "Amount must be greater than 0.")]
        public decimal Amount { get; set; }

        [StringLength(255)]
        public string Comment { get; set; }

        [DataType(DataType.DateTime, ErrorMessage = "Must be a DateTime.")]
        [Column(TypeName = "datetime2")]
        public DateTime ModifyDate { get; set; }
    }
}