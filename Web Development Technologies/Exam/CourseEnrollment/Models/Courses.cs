using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace CourseEnrollment.Models
{
    public static class Career
    {
        public const string Undergraduate = "Undergraduate";
        public const string Postgraduate = "Postgraduate";
    }
    public class Courses
    {
        [Key, StringLength(8,MinimumLength =8), RegularExpression(@"^[C]+[O]+[S]+[C]+[0-9]*$")]
        public string CourseID { get; set; }

        [Required, StringLength(50)]
        public string Title { get; set; }

        [Required, Range(1,12)]
        public int CreditPoints { get; set; }

        [Required, StringLength(30), RegularExpression(@"^[A-Z]+[a-z]+[g]+[r]+[a]+[d]+[u]+[a]+[t]+[e]")]
        public string Career { get; set; }

        [Required, StringLength(50), RegularExpression(@"^[A-Z]+[A-Za-z ]*$")]
        public string Coordinator { get; set; }
        public virtual List<Enrolled> Enrolled { get; set; }
    }
}
