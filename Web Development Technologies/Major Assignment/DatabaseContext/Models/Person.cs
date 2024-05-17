using System.ComponentModel.DataAnnotations;

namespace DatabaseContext.Models
{
    public record Person
    {
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
