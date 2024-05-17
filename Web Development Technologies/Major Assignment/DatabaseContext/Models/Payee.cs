using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace DatabaseContext.Models
{
    public record Payee:Person
    {
        //Inspired by tutorial 5.
        [Required]
        public int PayeeID { get; set; }

        [StringLength(50)]
        [Required]
        public string PayeeName { get; set; }

        [RegularExpression(@"^[+]+[ ]+[6]+[1]+[ ]+[0-9]{4}+[ ]+[0-9]{4}$", ErrorMessage = "Must be in following format +61 XXXX XXXX")]
        [StringLength(15)]
        [Required]
        public string Phone { get; set; }

        public virtual List<BillPay> BillPays { get; set; }

    }
}