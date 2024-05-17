using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using DatabaseContext.Data;
using Microsoft.AspNetCore.Identity;
using DatabaseContext.Models;

namespace Assignment2.BackgroundServices.Emailing
{


    // Using Tutorial 8 File BackgroundServices as a base to build from.
    public class EmailBackgroundService : BackgroundService
    {
        private readonly IServiceProvider _services;
        private readonly ILogger<EmailBackgroundService> _logger;
        private readonly IMailer _mailer;

        public EmailBackgroundService(IServiceProvider services, ILogger<EmailBackgroundService> logger, IMailer mailer)
        {
            _services = services;
            _logger = logger;
            _mailer = mailer;
        }

        // This is the looping Service.
        protected override async Task ExecuteAsync(CancellationToken stoppingToken)
        {
            using var scope = _services.CreateScope();
            var context = scope.ServiceProvider.GetRequiredService<McbaContext>();
            
            var userManager = scope.ServiceProvider.GetRequiredService<UserManager<ApplicationUser>>();
            var users = userManager.Users.ToList();
            var dictionary = users.ToDictionary(x => x.Id, x => x.Email);


            _logger.LogInformation("Email Background Service is running.");
            while (!stoppingToken.IsCancellationRequested)
            {
                EmailBackgroundTask background = new EmailBackgroundTask(_mailer,context, dictionary);
                await background.ProcessEmails(stoppingToken);
                _logger.LogInformation("Email Background Service is waiting 3 minutes.");
                await Task.Delay(TimeSpan.FromMinutes(3), stoppingToken);
            }
        }
    }
}
