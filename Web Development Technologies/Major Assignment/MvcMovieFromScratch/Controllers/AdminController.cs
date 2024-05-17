using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Net.Http;
using Newtonsoft.Json;
using Assignment2.Filters;
using DatabaseContext.Models;
using DatabaseContext.ViewModels;
using X.PagedList;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Authorization;

namespace Assignment2.Controllers


{

    [Authorize(Roles ="Admin")]
    public class AdminController : Controller
    {
        private readonly UserManager<ApplicationUser> _userManager;
        private readonly RoleManager<IdentityRole> _roleManager;
        private readonly IHttpClientFactory _clientfactory;
        private HttpClient Client => _clientfactory.CreateClient("api");

        public AdminController(IHttpClientFactory clientfact, RoleManager<IdentityRole> roleManager, UserManager<ApplicationUser> userManager) {
            _clientfactory = clientfact;
            _roleManager = roleManager;
            _userManager = userManager;
        }

        public async Task<IActionResult> Statement(int custNum, int? page, string fromdate, string todate)
        {
            // Checks if the page is null.
            int pagenum = page == null ? pagenum = 1 : pagenum = (int)page;
            var response = await Client.GetAsync("api/Admin/transactions?custnum=" + custNum + "");
            if (!response.IsSuccessStatusCode)
            {
                throw new Exception();
            }


            var objString =  await response.Content.ReadAsStringAsync(); //Code for getting JSOn and deserilizing into a list
           var transactionlist = JsonConvert.DeserializeObject<List<Transaction>>(objString);
           transactionlist = transactionlist.OrderByDescending(m => m.ModifyDate).ToList();
           var pagablelist = transactionlist.ToPagedList(pagenum, 4); 
            AdminStatementViewModel defmodel = new AdminStatementViewModel //Default Unfiltered model if no dates are entered


            {
                Transactions = pagablelist,
                CustNum = custNum,
                FromDate = "",
                ToDate = "",
            };
            // If dates are entered, code runs below.
            if (!String.IsNullOrEmpty(fromdate) && !String.IsNullOrEmpty(todate))
            {
                var frdate = DateTime.Parse(fromdate);
                var tdate = DateTime.Parse(todate);
                // If dates are not between, then returns the unfiltred view with an error. 
                if (frdate > tdate)
                {
                    ModelState.AddModelError("ToDate", "From Date needs to be before todate");



                    return View(defmodel);

                }
                else
                {

                   var filteredlist = transactionlist.Where(m => m.ModifyDate >= frdate).Where(m=> m.ModifyDate<=tdate).OrderByDescending(m=>m.ModifyDate).ToList(); //Filters list between the dates
                    if (filteredlist.Count() == 0) //If no dates, returns no transactions. 



                    {
                        ModelState.AddModelError("ToDate", "No Transactions found between these dates");
                        return View(defmodel);
                    }
                    // Filtered transactionlist is return if dates are found.
                    var pagedfilteredlist = filteredlist.ToPagedList(pagenum, 4);
                    AdminStatementViewModel filteredmodel = new AdminStatementViewModel
                    {
                        Transactions = pagedfilteredlist,
                        CustNum = custNum,
                        FromDate = fromdate,
                        ToDate = todate,
                    };

                    return View(filteredmodel);
                }
            }


            return View(defmodel);

        }

        public async Task<IActionResult> Index()
        {


            var response = await Client.GetAsync("api/Admin");
            if (!response.IsSuccessStatusCode)
            {
                throw new Exception();
            }

            var objstring = await response.Content.ReadAsStringAsync();
            var custlist = JsonConvert.DeserializeObject<List<ApplicationUser>>(objstring);
            AdminViewModel mdl = new AdminViewModel
            {
                Users = custlist

            };
            return View(mdl);
        }

        public async Task<IActionResult> LockAccount(int id)
        {

            var response = await Client.GetAsync("api/Admin/LockCustomer?custnum=" + id + "");

            if (response.StatusCode == System.Net.HttpStatusCode.BadRequest)
            {
                
                // Add an error here.
            }

            return RedirectToAction(nameof(Index));
        }
        public async Task<IActionResult> BillPay(int custNum, int? page)
        {
            // Checks if the page is null.
            int pagenum = page == null ? pagenum = 1 : pagenum = (int)page;
            var response = await Client.GetAsync("api/Admin/billpays?custNum=" + custNum);
            if (!response.IsSuccessStatusCode)
            {
                throw new Exception();
            }
            // Code for getting JSON and deserilizing into a list.
            var objString = await response.Content.ReadAsStringAsync();
            List<BillPay> bills = JsonConvert.DeserializeObject<List<BillPay>>(objString);
            var pagableList = bills.ToPagedList(pagenum, 4);
            // Default Unfiltered model if no dates are entered.
            var defmodel = new AdminBillPayViewModel
            {
                BillPays = pagableList,
                CustomerID = custNum,
            };

            return View(defmodel);
        }

        public async Task<IActionResult> UnblockBill(int billPayID)
        {
            var response = await Client.GetAsync("api/Admin/UnblockBillPay?id=" + billPayID);

            if (response.StatusCode == System.Net.HttpStatusCode.BadRequest)
            {
                ModelState.AddModelError("BillPay", "Invalid action");
            }

            // Return to the Main view.
            return RedirectToAction("Index", "Admin");
        }

        public async Task<IActionResult> BlockBill(int billPayID)
        {
            var response = await Client.GetAsync("api/Admin/BlockBillPay?id=" + billPayID);

            if (response.StatusCode == System.Net.HttpStatusCode.BadRequest)
            {
                ModelState.AddModelError("BillPay", "Invalid action");
            }
            // Return to the Main view.
            return RedirectToAction("Index", "Admin");
        }

        [HttpGet]
        public IActionResult CreateRole()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> CreateRole(CreateRoleViewModel model)
        {
            if (ModelState.IsValid)
            {
                IdentityRole identityRole = new IdentityRole
                {
                    Name = model.RoleName
                };
                var result = await _roleManager.CreateAsync(identityRole);
                if (result.Succeeded)
                {
                    return RedirectToAction("ListRoles", "Admin");
                }
                foreach (var error in result.Errors)
                {
                    ModelState.AddModelError("", error.Description);
                }
            }
            return View(model);
        }

        [HttpGet]
        public IActionResult ListRoles()
        {
            var roles = _roleManager.Roles;
            return View(roles);
        }

        [HttpGet]
        public async Task<IActionResult> EditUsersInRole(string roleId)
        {
            
            var role = await _roleManager.FindByIdAsync(roleId);

            if (role == null) //If role is not found, returns error
            {
                ViewBag.ErrorMessage = $"Role {roleId} can't be found";
                return View("NotFound");
            }
            var model = new List<UserRoleViewModel>();
            foreach (var user in await _userManager.Users.ToListAsync()) //Gets user list and selects userroleviewmodel
            {
                var userRoleViewModel = new UserRoleViewModel
                {
                    UserId = user.Id,
                    UserName = user.UserName,
                    RoleId = roleId
                    
                };
                if(await _userManager.IsInRoleAsync(user,role.Name))
                {
                    userRoleViewModel.IsSelected = true;
                } 
                else
                {
                    userRoleViewModel.IsSelected = false;
                }

                model.Add(userRoleViewModel);
            };
            return View(model);

        }

        [HttpPost]
        public async Task<IActionResult> EditUsersInRole(List<UserRoleViewModel> model, string roleId)
        {
            var role = await _roleManager.FindByIdAsync(roleId);
            if(role==null)
            {
                ViewBag.ErrorMessage = $"Role with Id = {roleId} can't be found.";
                return View("Not Found");
            }
            for (var i=0; i< model.Count; i++)
            {
                var user = await _userManager.FindByIdAsync(model[i].UserId);
                IdentityResult result = null;

                // If selected and not already a member of the role, add the user to the role.
                if(model[i].IsSelected && !(await _userManager.IsInRoleAsync(user,role.Name)))
                {

                    result = await _userManager.AddToRoleAsync(user,role.Name);
                }
                // If not selected and a member of the role, remove the user to the role.
                else if (!model[i].IsSelected && (await _userManager.IsInRoleAsync(user, role.Name)))
                {
                    result = await _userManager.RemoveFromRoleAsync(user, role.Name);
                } else
                {
                    continue;
                }

                if (result.Succeeded)
                {
                    if(i < (model.Count -1))
                    {
                        continue;
                    }
                    else
                    {
                        return RedirectToAction("ListRoles");
                    }
                }
            }

            return RedirectToAction("ListRoles");
        }
    }
}
