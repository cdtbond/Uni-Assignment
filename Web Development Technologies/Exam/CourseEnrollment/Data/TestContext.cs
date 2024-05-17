using Microsoft.EntityFrameworkCore;
using CourseEnrollment.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CourseEnrollment.Data
{
    public class TestContext : DbContext
    {

        public TestContext(DbContextOptions<TestContext> options) : base(options)
        {
        }


        public DbSet<Students> Students { get; set; }
        public DbSet<Courses> Courses { get; set; }
        public DbSet<Enrolled> Enrolled { get; set; }
        protected override void OnModelCreating(ModelBuilder builder)
        {   
            base.OnModelCreating(builder);
            builder.Entity<Enrolled>().HasKey(c => new { c.CourseID, c.StudentID});
            builder.Entity<Enrolled>()
                .HasOne(x => x.Student)
                .WithMany(x => x.Enrolled)
                .OnDelete(DeleteBehavior.NoAction);
            builder.Entity<Enrolled>()
                .HasOne(x => x.Course)
                .WithMany(x => x.Enrolled)
                .OnDelete(DeleteBehavior.NoAction);
        }
    }
}
