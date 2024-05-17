using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseContext.IAttributes
{
    public interface IModifyDate
    {
        [DataType(DataType.DateTime, ErrorMessage = "Must be a DateTime.")]
        [Column(TypeName = "datetime2")]
        public DateTime ModifyDate { get; set; }

    }
    public interface IAmount
    {
        [Required(ErrorMessage = "An amount is required")]
        [DataType(DataType.Currency)]
        [Column(TypeName = "money")]
        [RegularExpression(@"^[0-9]*[.][0-9]{2})$", ErrorMessage = "Amount must have 2 decimal places.")]
        [Range(0.0, Double.MaxValue, ErrorMessage = "Amount must be greater than 0.")]
        public decimal Amount { get; set; }
    }
}
