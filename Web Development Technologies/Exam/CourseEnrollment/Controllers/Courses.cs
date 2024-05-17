using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using CourseEnrollment.Data;
using CourseEnrollment.Models;
using CourseEnrollment.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using X.PagedList;
using Microsoft.Extensions.Logging;

namespace CourseEnrollment.Controllers
{
    public class CoursesController : Controller
    {
        const int pageSize = 4;
        // Follows Day 5 Tutorial approach.
        private readonly TestContext _context;
        private readonly ILogger<CoursesController> _logger;
        public CoursesController(TestContext context, ILogger<CoursesController> logger)
        {
            _context = context;
            _logger = logger;
        }

        // GET: Courses
        public ActionResult Index(string id, int ? page)
        {
            var pageNum = 0;
            pageNum = page == null ? pageNum = 1 : pageNum = (int)page;
            var coursesQuery = _context.Courses
                .Include(c => c.Enrolled);
            var courses = coursesQuery.ToList();
            CourseViewModel viewModel = new();
            viewModel.Courses = courses;
     
            if (id != null)
            {


                var enrolledQuery = _context.Enrolled
                    .Include(c => c.Student)
                    .Include(c => c.Course)
                    .Where(c => c.CourseID == id);
                var enrolled = enrolledQuery.ToPagedList(pageNum, pageSize);
                viewModel.SelectedEnrollment = enrolled;
                viewModel.SelectedCourseID = id;
            }
     
            return View(viewModel);
        }

        // GET: Courses/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Courses/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(CreateCourseViewModel viewModel)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    // Check ID not already taken.
                    var courseBool = _context.Courses.Select(c => c.CourseID == viewModel.CourseID).Any();
                    if(courseBool)
                    {
                        ModelState.AddModelError("CourseID", "Course ID already taken");
                        return View(viewModel);
                    }
                    //ModelState.AddModelError("ToDate", "From Date needs to be before todate");
                    // Add to Database.
                    Courses newCourse = new Courses
                    {
                        CourseID = viewModel.CourseID,
                        Title = viewModel.Title,
                        Career = viewModel.Career,
                        Coordinator = viewModel.Coordinator,
                        CreditPoints = viewModel.CreditPoints,
                        Enrolled = new List<Enrolled>()
                    };

                    _context.Courses.Add(newCourse);
                    _context.SaveChangesAsync();
                    return RedirectToAction(nameof(Index));
                }
            } catch(Exception)
            {
                // Proceed with model error.
            }
            return View(viewModel);

        }

        // GET: Courses/Create
        public ActionResult Enroll()
        {
            var students = _context.Students.ToList();
            var courses = _context.Courses.ToList();

            var enrollVM = new EnrollViewModel
            {
                Students = students,
                Courses = courses
            };
            
            return View(enrollVM);
        }

        // POST: Courses/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Enroll(EnrollViewModel viewModel)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    // Check whether the course and student combo already exists.
                    // Example

                    if(AlreadyEnrolled(viewModel))
                    {
                        ModelState.AddModelError("SelectedCourseID", "Student Already Enrolled in Course");
                        var students = _context.Students.ToList();
                        var courses = _context.Courses.ToList();

                        var enrollVM = new EnrollViewModel
                        {
                            Students = students,
                            Courses = courses
                        };

                        return View(enrollVM);
                    }

                    var student = _context.Students.SingleOrDefault(s => s.StudentID == viewModel.SelectedStudentID);
                    var course = _context.Courses.SingleOrDefault(c => c.CourseID == viewModel.SelectedCourseID);

                    // Add to Database.
                    Enrolled newEnrollment = new Enrolled
                    {
                        StudentID = viewModel.SelectedStudentID,
                        CourseID = viewModel.SelectedCourseID,
                        Student = student,
                        Course = course
                    };

                    _context.Enrolled.Add(newEnrollment);
                    _context.SaveChangesAsync();
                    return RedirectToAction(nameof(Index));
                }
            }
            catch (Exception)
            {
                // Proceed with model error.
            }
            return View(viewModel);

        }

        public bool AlreadyEnrolled(EnrollViewModel viewModel)
        {
            var studentEnrollments = _context.Enrolled.Where(e => e.StudentID == viewModel.SelectedStudentID);
            var studentEnrolledInCourse = studentEnrollments.Where(e => e.CourseID == viewModel.SelectedCourseID);

            return studentEnrolledInCourse.Any();
        }
    }
}
