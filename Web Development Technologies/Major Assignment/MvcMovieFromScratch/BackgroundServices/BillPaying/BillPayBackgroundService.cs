using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using DatabaseContext.Data;

namespace Assignment2.BackgroundServices.BillPaying
{


    // Using Tutorial 8 File BackgroundServices as a base to build from.
    public class BillPayBackgroundService : BackgroundService
    {
        private readonly IServiceProvider _services;
        private readonly ILogger<BillPayBackgroundService> _logger;

        public BillPayBackgroundService(IServiceProvider services, ILogger<BillPayBackgroundService> logger)
        {
            _services = services;
            _logger = logger;
        }

        // This is the looping Service.
        protected override async Task ExecuteAsync(CancellationToken stoppingToken)
        {
            using var scope = _services.CreateScope();
            var context = scope.ServiceProvider.GetRequiredService<McbaContext>();
            _logger.LogInformation("People Background Service is running.");
            while (!stoppingToken.IsCancellationRequested)
            {
                BillPayBackgroundTask background = new BillPayBackgroundTask(context);
                await background.ProcessBills(_logger,stoppingToken);
                _logger.LogInformation("BillPay Background Service is waiting 30 seconds.");
                await Task.Delay(TimeSpan.FromSeconds(30), stoppingToken);
            }
        }
    }
}
