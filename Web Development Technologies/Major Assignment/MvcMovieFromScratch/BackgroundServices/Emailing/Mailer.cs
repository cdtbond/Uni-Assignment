using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MimeKit;
using MailKit.Net.Smtp;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Options;
using Assignment2.BackgroundServices.Emailing.Entities;
using System.Threading;

namespace Assignment2.BackgroundServices.Emailing
{
    public class Mailer : IMailer
    {
        private readonly SmtpSettings _smtpSettings;
        private readonly IWebHostEnvironment _environment;

        public Mailer(IOptions<SmtpSettings> smtpSettings, IWebHostEnvironment env)
        {
            _smtpSettings = smtpSettings.Value;
            _environment = env;
        }

        public async Task SendEmailAsync(string name, string email, string subject, string body,CancellationToken cancellationToken)
        {
            try
            {
                //Add to and from address and add subject to the email.
                var message = new MimeMessage();
                message.From.Add(new MailboxAddress(_smtpSettings.SenderName, _smtpSettings.SenderEmail));
                message.To.Add(new MailboxAddress(name, email));
                message.Subject = subject;
                var builder = new BodyBuilder();
                //Build the email and put body in html format
                builder.HtmlBody = body;
                message.Body = builder.ToMessageBody();
                //Send email to smtp server
                using (var client = new SmtpClient())
                {
                    client.ServerCertificateValidationCallback = (s, c, h, e) => true;
                    if (_environment.IsDevelopment())
                    {
                        await client.ConnectAsync(_smtpSettings.Server, _smtpSettings.Port, true, cancellationToken);
                    }
                    else
                    {
                        await client.ConnectAsync(_smtpSettings.Server, cancellationToken: cancellationToken);
                    }
                    await client.AuthenticateAsync(_smtpSettings.Username, _smtpSettings.Password,cancellationToken);
                    await client.SendAsync(message, cancellationToken);
                    await client.DisconnectAsync(true, cancellationToken);
                }
            }
            catch (Exception e)
            {
                throw new InvalidOperationException(e.Message);
            }
        }

    }
}
