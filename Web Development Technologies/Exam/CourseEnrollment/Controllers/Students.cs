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

namespace CourseEnrollment.Controllers
{
    public class StudentsController : Controller
    {
        const int pageSize = 4;
        // Follows Day 5 Tutorial approach.
        private readonly TestContext _context;
        public StudentsController(TestContext context)
        {
            _context = context;
        }

        // List all the students
        // GET: Students
        public ActionResult Index(int? page)
        {
            var pageNum = 0;
            pageNum = page == null ? pageNum = 1 : pageNum = (int)page;
            var studentQuery = _context.Students;
            var students = studentQuery.ToPagedList(pageNum, pageSize);
            StudentViewModel viewModel = new();
            viewModel.Students = students;

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
        public ActionResult Create(CreateStudentViewModel viewModel)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    // Check for unique ID and Full Name.

                    _context.Students.Add(new Students
                    {
                        StudentID = viewModel.StudentID,
                        FirstName = viewModel.FirstName,
                        LastName = viewModel.LastName,
                        Email = viewModel.Email,
                        Enrolled = new List<Enrolled>(),
                        MobilePhone = viewModel.MobilePhone
                    });
                    _context.SaveChanges();
                }
            } catch (Exception)
            {
                // Continue.
            }
            return RedirectToAction(nameof(Index));
        }
    }
}
