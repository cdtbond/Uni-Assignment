using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Http;

namespace Assignment2.ViewComponent 

{
    [ViewComponent(Name = "AdminNavBar")]
    public class AdminNavBarViewComponent : Microsoft.AspNetCore.Mvc.ViewComponent
    {
        public IViewComponentResult Invoke()
        {
            string custname = HttpContext.Session.GetString("customername");
            ViewBag.CustomerName = custname;
            return View();
        }

    }
}
