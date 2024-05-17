using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;
using CourseEnrollment.Models;

namespace CourseEnrollment.ViewModel
{
    public class EnrollViewModel
    {
        public List<Students> Students { get; set; }
        public List<Courses> Courses { get; set; }
        [Display(Name = "Select Course")]
        public string SelectedCourseID { get; set; }
        [Display(Name = "Select Student")]
        public string SelectedStudentID { get; set; }
    }
}
