using Assignment2.ExtensionMethods;
using DatabaseContext.Data;
using DatabaseContext.Models;
using DatabaseContext.ViewModels;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Assignment2.Controllers
{
    public class AccountController : Controller
    {
        private readonly UserManager<ApplicationUser> _userMan;
        private readonly SignInManager<ApplicationUser> _signInMan;
        private readonly McbaContext _context;

        public AccountController(UserManager<ApplicationUser> userMan, SignInManager<ApplicationUser> signInMan, McbaContext context)
        {
            _userMan = userMan;
            _signInMan = signInMan;
            _context = context;
        }
        
        // GET: /<controller>/
        [HttpGet]
        public IActionResult Register()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Register(RegisterViewModel regModel)
        {

            if (ModelState.IsValid)
            {
                // Get the indentity ids as an int list.
                var identityIds = AccountExt.ListToInt(_userMan.Users.ToList());


                // Increment the counter for the new ID.
                var newId = identityIds.Max() + 1;
                var newStringId = newId + "";

                var existingId = await _userMan.FindByIdAsync(newStringId);
                if(existingId != null)

                {
                    ModelState.AddModelError(string.Empty, "Id already used");
                    return View(regModel);
                } 
                var user = new ApplicationUser //Otherwise create a new user
                {
                    Id = newStringId,
                    UserName = regModel.LoginID,
                    Email = regModel.Email,
                    PhoneNumber = regModel.PhoneNumber
                };
                var result = await _userMan.CreateAsync(user, regModel.Password); //Creates user
                if (result.Succeeded)
                {
                    await _userMan.AddToRoleAsync(user, "Customer");
                    await _signInMan.SignInAsync(user, isPersistent: false); //Signs in user
 
                    return RedirectToAction("OpenAccount","Account", newId);
                } else
                {
                    foreach(var error in result.Errors)
                    {
                        ModelState.AddModelError(string.Empty, error.Description); //Adds and displays error if not successful
                    }
                }
            }
            // If not valid then re-render with validation.
            return View(regModel);
        }



        [HttpPost]
        public async Task<IActionResult> Logout() 
        {
            await _signInMan.SignOutAsync();
            return RedirectToAction("Index", "Home");
        }

        [HttpGet]
        public IActionResult Login()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Login(LoginViewModel loginModel)
        {

            if (ModelState.IsValid)
            {
                // The last parameter here if set to true will Lock out a user upon unsuccessful attempts.
                var result = await _signInMan.PasswordSignInAsync(loginModel.LoginID, loginModel.Password,loginModel.RememberMe,false);


                var user = _userMan.Users.FirstOrDefault(x=>x.UserName.Equals(loginModel.LoginID));




                if (result.Succeeded)
                {
                    HttpContext.Session.SetInt32("customerid", int.Parse(user.Id));
                    var customer = _context.Customer.Find(int.Parse(user.Id));
                    HttpContext.Session.SetString("customername", customer.CustomerName);
                    return RedirectToAction("Index", "Home");
                }
                else
                {
                     ModelState.AddModelError(string.Empty,"Invalid Login Attempt");
                }
            }
            // If not valid then re-render with validation.
            return View(loginModel);
        }
        public IActionResult AccessDenied()
        {
            return View();
        }



        [HttpGet]
        public IActionResult OpenAccount(int id)
        {
            // Set the Customer Id to the Login Id to allow navigation.
            return View( new OpenAccountViewModel
            {
                Id = id

            });

        }

        [HttpPost]
        public async Task<IActionResult> OpenAccount(OpenAccountViewModel model)
        {

            if (ModelState.IsValid)
            {
                HttpContext.Session.SetString("customername", model.CustomerName);
                // Get the indentity ids as an int list.
                var identityIds = AccountExt.ListToInt(_userMan.Users.ToList());

                // Get the lasrgest number, that is the current ID.
                var newId = identityIds.Max();
               


                // Get customer details.  
                Customer cust = new Customer
                {
                    CustomerName = model.CustomerName,
                    City = model.City,
                    Address = model.Address,
                    CustomerID = newId,
                    PostCode = model.PostCode,
                    State = model.State,
                    TFN = model.TFN,
                    Accounts = new List<Account>()
                };

                var accountId = _context.Account.Max(a => a.AccountNumber);
                accountId += 1;
                
                // Open an account.
                Account acc = new Account
                {
                    Customer = cust,
                    CustomerID = model.Id,
                    AccountType = AccountType.Savings,
                    ModifyDate = DateTime.UtcNow,
                    AccountNumber = accountId,
                    BillPays = new List<BillPay>(),
                    Transactions = new List<Transaction>()
                    
                };

                // Lodge initial deposit.
                var trans =  new Transaction 
                    {
                        Account = acc,
                        AccountNumber = acc.AccountNumber,
                        TransactionType = TransactionType.Deposit,
                        Amount = model.Deposit,
                        Comment = "Initial Deposit of " + model.Deposit,
                        ModifyDate = DateTime.UtcNow
                    };
                acc.Transactions.Add(trans);

                cust.Accounts.Add(acc);
                _context.Customer.Add(cust);
                _context.Account.Add(acc);
                _context.Transaction.Add(trans);
                await _context.SaveChangesAsync();

                // Go to general page.
                return RedirectToAction("Index", "Home");
            }

            // If errors, return to View.
            return View(model);

        }

    }
}
