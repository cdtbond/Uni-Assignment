using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace CourseEnrollment.Models
{
    public class Students
    {
        [Key, StringLength(8, MinimumLength = 8), RegularExpression(@"^[s]+[0-9]*$")]
        public string StudentID { get; set; }
        [Required, StringLength(30), RegularExpression(@"^[A-Z]+[A-Za-z]*$")]
        public string FirstName { get; set; }
        [Required, StringLength(30), RegularExpression(@"^[A-Z]+[A-Za-z]*$")]
        public string LastName { get; set; }
        // Regex requires letters (upper or lower) or numbers then the '@' sign, then letters or numbers, then '.' sign then letters.
        [Required, StringLength(320), RegularExpression(@"^[A-Za-z0-9]+[@]+[A-Za-z0-9]+[.]+[A-Za-z.]*$")]
        public string Email { get; set; }
        [StringLength(10, MinimumLength = 10), RegularExpression(@"^[0]+[4]+[0-9]*$")]
        public string MobilePhone { get; set; }

        public virtual List<Enrolled> Enrolled { get; set; }

        [NotMapped]
        public string FullName
        {
            get { return FirstName + " " + LastName; }
        }

    }
}
