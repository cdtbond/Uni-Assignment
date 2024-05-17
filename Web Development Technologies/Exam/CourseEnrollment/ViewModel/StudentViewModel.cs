using CourseEnrollment.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using X.PagedList;

namespace CourseEnrollment.ViewModel
{
    public class StudentViewModel
    {
        public IPagedList <Students> Students { get; set; }
    }
}
