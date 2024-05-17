using CourseEnrollment.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using X.PagedList;


namespace CourseEnrollment.ViewModel
{
    public class CourseViewModel
    {
        public List <Courses> Courses { get; set; }
        public IPagedList <Enrolled> SelectedEnrollment { get; set; }
        public string SelectedCourseID { get; set; }
    }
}
