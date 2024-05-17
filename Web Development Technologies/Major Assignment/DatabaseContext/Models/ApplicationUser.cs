using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DatabaseContext.Models
{
    public class ApplicationUser : IdentityUser
    {

        // Override the standard PhoneNumber field in Identity User.
        [StringLength(15, MinimumLength = 12)]
        [Required]
        public override string PhoneNumber { get; set; }

        // No navigation properties.

    }
}
