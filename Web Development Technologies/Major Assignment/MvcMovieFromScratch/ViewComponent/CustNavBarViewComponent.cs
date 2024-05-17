using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Http;

namespace Assignment2.ViewComponent 

{
    [ViewComponent(Name = "CustNavBar")]
    public class CustNavBarViewComponent : Microsoft.AspNetCore.Mvc.ViewComponent
    {
        public IViewComponentResult Invoke()
        {
            string custname = HttpContext.Session.GetString("customername");
            ViewBag.CustomerName = custname;
            return View();
        }

    }
}
