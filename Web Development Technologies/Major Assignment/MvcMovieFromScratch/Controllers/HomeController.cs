using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using DatabaseContext.ViewModels;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;

namespace Assignment2.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;

        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
        }

        public IActionResult Index()
        {

            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }


        //Controller for handling HttpError Code exceptions
        public IActionResult HttpErrors( int statusCode)
        {

            string httperrormessage = "";
            string errormessage = "";
            switch (statusCode) //Switch statements for printing appropiate error messages. 
            {
                case 404:
                    httperrormessage = "Resource not found";
                    errormessage = "The requested page doesn't exist";
                    break;
                case 400:
                    httperrormessage = "Bad Request";
                    errormessage = "A server error has occured in processing this trsnaction";
                    break;
                case 401:
                    httperrormessage = "Unauthorised Access";
                    errormessage = "You are not Authorised to Access this page";
                    break;
                case 403:
                    httperrormessage = "Forbidden";
                    errormessage = "You do not have access to this page or a bad request has occured";
                    break;
                default:
                    httperrormessage = "Error has occured in the server";
                    errormessage = "A server error has occured.";
                    break;

            }

            HttpErrorViewModel mdl = new HttpErrorViewModel
            {
                HttpErrorMessage = httperrormessage,
                Message = errormessage
            };
            return View(mdl);
        }
        //Custom exception error page
        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View("Error",new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
