using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.AspNetCore.Mvc;
using DatabaseContext.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Assignment2.Filters
{
    // Based on McbaExampleWithLogin from Week 6.
    public class AuthorizeLoginAttribute : Attribute , IAuthorizationFilter
    {
        public void OnAuthorization(AuthorizationFilterContext context)
        {
            var customerID = context.HttpContext.Session.GetInt32("customerid"); //Checks if there is a session and redirects if user is not logged in
            if(!customerID.HasValue)
            {
                context.Result = new RedirectToActionResult("Index", "Login", null);
            }
        }
    }
}
