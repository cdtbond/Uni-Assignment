using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using System.Net.Http.Headers;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using DatabaseContext.Data;
using Microsoft.EntityFrameworkCore;
using Assignment2.BackgroundServices;
using Microsoft.AspNetCore.Mvc;
using Assignment2.BackgroundServices.BillPaying;
using Assignment2.BackgroundServices.Emailing;
using Assignment2.BackgroundServices.Emailing.Entities;
using Microsoft.AspNetCore.Identity;
using DatabaseContext.Models;

namespace Assignment2
{
    public class Startup
    {
        readonly string AllowSpecificOrigins = "_AllowSpecificOrigins";
        readonly string _otherAppPort = "44317";
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {

            services.Configure<SmtpSettings>(Configuration.GetSection("SmtpSettings"));
            services.AddSingleton<IMailer, Mailer>();
            // Follows online identity tutotrial at: https://www.youtube.com/watch?v=egITMrwMOPU
            services.AddIdentity<ApplicationUser, IdentityRole>()
                .AddEntityFrameworkStores<McbaContext>();
            // Please note this relaxes password rules, which is required for this assignment but not advisable in real world setting.
            services.Configure<IdentityOptions>(options =>
            {
                // Needs to be 5 characters to allow "admin" to work.
                options.Password.RequiredLength = 5;
                // This removes the need to add special characters.
                options.Password.RequireNonAlphanumeric = false;
                // The removes the need to have letters
                options.Password.RequireLowercase = false;
                // Allows "admin" to work.
                options.Password.RequireDigit = false;
                // Allows all passwords to work.
                options.Password.RequireUppercase = false;
                // All passwords meet this criteria.
                options.Password.RequiredUniqueChars = 4;

            });
            services.AddHttpClient("api", client =>
            {
                client.BaseAddress = new System.Uri("http://localhost:61270");
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            });

            services.AddDistributedMemoryCache();
            services.AddSession(options =>
            {
                // Make the session cookie essential.
                options.Cookie.IsEssential = true;
            });

            services.AddDbContext<McbaContext>(options =>
            options.UseSqlServer(
                Configuration.GetConnectionString("McbaContext"),
                providerOptions => providerOptions.EnableRetryOnFailure()));
            services.AddHostedService<BillPayBackgroundService>();


            services.AddHostedService<EmailBackgroundService>();
            services.AddControllersWithViews(options =>
                    options.Filters.Add(new AutoValidateAntiforgeryTokenAttribute()));
            services.AddCors(options => options.AddPolicy(name: AllowSpecificOrigins, builder =>
            {
                builder.WithOrigins("www.localhost:" + _otherAppPort);

            }));
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env, UserManager<ApplicationUser> userManager)
        {
            if (env.IsDevelopment())
            {
                //app.UseExceptionHandler("/Home/Error");
                app.UseDeveloperExceptionPage();
            }
            else
            {
                app.UseExceptionHandler("/Home/Error");
                // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                app.UseHsts();
            }
            app.UseHttpsRedirection();
            app.UseStaticFiles();
            app.UseCors(AllowSpecificOrigins);
            app.UseAuthentication();
            app.UseRouting();
            app.UseAuthorization();
            app.UseStatusCodePagesWithRedirects("/Home/HttpErrors?statuscode={0}");
            app.UseSession();
            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllerRoute(
                    name: "default",
                    pattern: "{controller=Home}/{action=Index}/{id?}");
            });
        }
    }
}
