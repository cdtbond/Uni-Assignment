using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace CourseEnrollment.Models
{
    public class Enrolled
    {
        [ForeignKey("Course"), StringLength(8, MinimumLength = 8), RegularExpression(@"^[s]+[0-9]*$")]
        public string CourseID { get; set; }
        [ForeignKey("Student"), StringLength(8, MinimumLength = 8), RegularExpression(@"^[s]+[0-9]*$")]
        public string StudentID { get; set; }

        public virtual Courses Course { get; set; }
        public virtual Students Student { get; set; }

    }
}
