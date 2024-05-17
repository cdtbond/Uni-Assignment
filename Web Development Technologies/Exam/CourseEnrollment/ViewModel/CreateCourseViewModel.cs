using CourseEnrollment.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;
using X.PagedList;

namespace CourseEnrollment.ViewModel
{
    public class CreateCourseViewModel
    {
        [Key, StringLength(8, MinimumLength = 8), RegularExpression(@"^[C]+[O]+[S]+[C]+[0-9]*$")]
        public string CourseID { get; set; }

        [Required, StringLength(50)]
        public string Title { get; set; }

        [Required, Range(1, 12)]
        public int CreditPoints { get; set; }

        [Required, StringLength(30), RegularExpression(@"^[A-Z]+[a-z]+[g]+[r]+[a]+[d]+[u]+[a]+[t]+[e]")]
        public string Career { get; set; }

        [Required, StringLength(50), RegularExpression(@"^[A-Z]+[A-Za-z ]*$")]
        public string Coordinator { get; set; }
    }
}
