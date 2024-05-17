using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.AspNetCore.Mvc;
using System;

namespace Assignment2.Filters
{
    public class AdminLoginAttribute : Attribute, IAuthorizationFilter
    {
        public void OnAuthorization(AuthorizationFilterContext context)
        {
            var customerstring = context.HttpContext.Session.GetString("customername");
            // Checks if string is empty.
            if (String.IsNullOrEmpty(customerstring)) 
            {
                context.Result = new RedirectToActionResult("Index", "Login", null);
            }
            // Checks if the user is an admin.
            else if (customerstring!= "Administrator") 
            {
                context.Result = new RedirectToActionResult("Index", "Login", null);
            }
        }
    }
}
