using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Collections.Generic;
using DatabaseContext.IAttributes;

namespace DatabaseContext.Models
{
    public enum AccountType
    {
        Checking = 1,
        Savings = 2,
    }

    public record Account:IModifyDate,ICustomerID,IAccountNumber
    {

        // Inspired by Tutorial 5.
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int AccountNumber { get; set; }

        [Required]
        public AccountType AccountType { get; set; }

        [Required]
        public int CustomerID { get; set; }
        public virtual Customer Customer { get; set; }

        [DataType(DataType.DateTime, ErrorMessage = "Must be a DateTime.")]
        [Column(TypeName = "datetime2")]
        public DateTime ModifyDate { get; set; }

        public virtual List<Transaction> Transactions { get; set; }
        public virtual List<BillPay> BillPays { get; set; }
    }
}