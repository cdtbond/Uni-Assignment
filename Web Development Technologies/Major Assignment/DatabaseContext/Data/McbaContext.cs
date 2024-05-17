using Microsoft.EntityFrameworkCore;
using DatabaseContext.Models;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;

namespace DatabaseContext.Data
{
    public class McbaContext : IdentityDbContext<ApplicationUser>
    {
        public McbaContext(DbContextOptions<McbaContext> options) : base(options)
        {
        }

        public DbSet<Customer> Customer { get; set; }
        public DbSet<Account> Account { get; set; }
        public DbSet<BillPay> BillPay { get; set; }
        public DbSet<Payee> Payee { get; set; }
        public DbSet<Transaction> Transaction { get; set; }
        public DbSet<Email> Email { get; set; }

       // Structure based on Tutorial 5.
        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);
            builder.Entity<Transaction>().HasCheckConstraint("CH_Transaction_Amount", "Amount >=0");
            builder.Entity<Transaction>().HasOne(x => x.Account).WithMany(x => x.Transactions).HasForeignKey(x => x.AccountNumber);
            builder.Entity<BillPay>().HasOne(x => x.Account).WithMany(x => x.BillPays).HasForeignKey(x => x.AccountNumber);
            builder.Entity<BillPay>().HasOne(x => x.Payee).WithMany(x => x.BillPays).HasForeignKey(x => x.PayeeID);
            builder.Entity<Account>().HasOne(x => x.Customer).WithMany(x => x.Accounts).HasForeignKey(x => x.CustomerID);

        }
    }
}
