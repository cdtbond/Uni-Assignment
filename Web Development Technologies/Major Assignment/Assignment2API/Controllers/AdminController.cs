using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DatabaseContext.Models;
using Assignment2API.Repository;
using DatabaseContext.Data;
using Microsoft.AspNetCore.Identity;

namespace Assignment2API.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class AdminController : Controller
    {
        private readonly AdminManager _mgr;
        private readonly McbaContext _context;
        private readonly UserManager<ApplicationUser> _userManager;
        public AdminController(AdminManager manager, McbaContext context, UserManager<ApplicationUser> userManager)
        {
            _mgr = manager;
            _context = context;
            _userManager = userManager;
        }

        public IActionResult Index()
        {
            return View();
        }

        // Returns all the current logins and users.
        [HttpGet]
        public IEnumerable<ApplicationUser> GetLogins()
        {
            return _mgr.GetLogins(); 

        }

        // Returns the Transactions of a Customer.
        [Route("transactions")]
        [HttpGet]
        public IEnumerable<Transaction> GetTransactions(int custNum)
        {
            return _mgr.GetTransactions(custNum);
        }


        // Locks the specified customer for 1 minute.
        [Route("LockCustomer")]
        [HttpGet]

        public async Task<IActionResult> LockCustomer(int custNum)
        {
            int success = await _mgr.LockUserAsync(custNum); 
            if (success == 0)
            {
                return BadRequest();
            }
            else
            {
                return Ok();
            }

        }

        [Route("billpays")]
        [HttpGet]
        public IEnumerable<BillPay> GetBillPays(int custNum)
        {
            return _mgr.GetBillPays(custNum);
        }

        [Route("BlockBillPay")]
        [HttpGet]

        public IActionResult BlockBillPay(int id)
        {
            // Blocks the Bill.
            int success = _mgr.BlockBillPay(id);
            if (success == 0)
            {
                return BadRequest();
            }
            else
            {
                return Ok();
            }

        }

        [Route("UnblockBillPay")]
        [HttpGet]
        public IActionResult UnblockBillPay(int id)
        {
            // Unblocks the Bill.
            int success = _mgr.UnblockBillPay(id); 
            if (success == 0)
            {
                return BadRequest();
            }
            else
            {
                return Ok();
            }

        }

        [Route("customer")]
        [HttpGet]
        public int GetCustomerFromBill(int billPay)
        {
            return _mgr.GetCustomerFromBill(billPay);
        }


    }
}
