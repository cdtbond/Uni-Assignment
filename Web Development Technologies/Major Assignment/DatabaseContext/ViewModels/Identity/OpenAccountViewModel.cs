using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DatabaseContext.ViewModels
{
    public class OpenAccountViewModel
    {
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        [Required]
        public int Id { get; set; }

        [Required]
        public decimal Deposit { get; set; }

        [StringLength(50)]
        [Display(Name = "Customer Name")]
        [Required]
        public string CustomerName { get; set; }

        [StringLength(11, MinimumLength = 11)]
        [RegularExpression(@"^[0-9]{11}$")]
        public string TFN { get; set; }


        [StringLength(50)]
        public string Address { get; set; }

        [StringLength(40)]
        public string City { get; set; }

        [StringLength(20)]
        [RegularExpression(@"^[A-Z]*$")]
        public string State { get; set; }

        [RegularExpression(@"^[0-9]{4}$", ErrorMessage = "Must be a 4 digit number")]
        [StringLength(10)]
        public string PostCode { get; set; }

    }
}
