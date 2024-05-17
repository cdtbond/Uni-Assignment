using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CourseEnrollment.Models;

namespace CourseEnrollment.Data
{
    public class SeedData
    {
        public static void Initialize(IServiceProvider services)
        {
            using var context = new TestContext(services.GetRequiredService<DbContextOptions<TestContext>>());
            if (context.Courses.Any())
            {
                // DB has been seeded.
                return;
            }

            var cosc2276 = new Courses
            {
                CourseID = "COSC2276",
                Coordinator = "Jennifer Jones",
                CreditPoints = 6,
                Title = "Programming Fundamentals",
                Career = Career.Undergraduate,
                Enrolled = new List<Enrolled>()
            };
            var cosc2277 = new Courses
            {
                CourseID = "COSC2277",
                Coordinator = "Terry Jones",
                CreditPoints = 12,
                Title = "Web Development Technologies",
                Career = Career.Postgraduate,
                Enrolled = new List<Enrolled>()
            };
            var chris = new Students
            {
                StudentID = "s1234567",
                FirstName = "Christopher",
                LastName = "Bond",
                Email = "s1234567@student.rmit.edu.au",
                MobilePhone = "0407999999",
                Enrolled = new List<Enrolled>()
            };
            var mike = new Students
            {
                StudentID = "s7654321",
                FirstName = "Michael",
                LastName = "Bond",
                Email = "s7654321@student.rmit.edu.au",
                MobilePhone = "0407111111",
                Enrolled = new List<Enrolled>()
            };
            var mikeEnroll = new Enrolled
            {
                CourseID = cosc2276.CourseID,
                Course = cosc2276,
                StudentID = mike.StudentID,
                Student = mike
            };
            var chrisEnroll = new Enrolled
            {
                CourseID = cosc2277.CourseID,
                Course = cosc2277,
                StudentID = chris.StudentID,
                Student = chris
            };

            context.Courses.AddRange(
                cosc2276,
                cosc2277
            );
            context.Students.AddRange(
                chris,
                mike
            );
            context.Enrolled.AddRange(
                mikeEnroll,
                chrisEnroll
            );

            context.SaveChanges();

        }
    }
}
