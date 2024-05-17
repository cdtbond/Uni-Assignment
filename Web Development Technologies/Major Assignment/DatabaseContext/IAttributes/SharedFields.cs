using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseContext.IAttributes
{
    public interface ILoginID
    {
        [Display(Name = "LoginID")]
        [Required(ErrorMessage = "Please Supply a Login Name")]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        [Column(TypeName = "nchar")]
        [StringLength(8, MinimumLength = 8, ErrorMessage = "LoginID must be 8 characters")]
        public string LoginID { get; set; }
    }
    public interface ICustomerID
    {
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        [Required]
        public int CustomerID { get; set; }
    }
    public interface IScheduleDate
    {
        [DataType(DataType.DateTime, ErrorMessage = "Must be DateTime")]
        [Column(TypeName = "datetime2")]
        public DateTime ScheduleDate { get; set; }
    }

    public interface IAccountNumber
    {
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int AccountNumber { get; set; }
    }
}
