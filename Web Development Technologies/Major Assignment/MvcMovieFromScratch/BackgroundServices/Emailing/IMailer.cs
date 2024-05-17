using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace Assignment2.BackgroundServices.Emailing
{
    public interface IMailer
    {
        Task SendEmailAsync(string name, string email, string subject, string body,CancellationToken cancellationToken);
    }
}
