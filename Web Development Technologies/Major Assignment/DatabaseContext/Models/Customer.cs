using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseContext.Models
{
    public record Customer:Person
    {
        // Inspired by Tutorial 5.
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        [Required]
        public int CustomerID { get; set; }

        [StringLength(50)]
        [Required]
        public string CustomerName { get; set; }
        [StringLength(11, MinimumLength = 11)]
        [RegularExpression(@"^[0-9]{11}$")]
        public string TFN { get; set; }
        
        public virtual List<Account> Accounts { get; set; }
    }
}