using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DatabaseContext.ViewModels
{
    public class RegisterViewModel
    {
        // Approach from https://www.youtube.com/watch?v=sPbDrqpme_w&list=PL6n9fhu94yhVkdrusLaQsfERmL_Jh4XmU&index=66 used for this ViewModel.

        [Required]
        [EmailAddress]
        public string Email { get; set; }


        [StringLength(8, MinimumLength = 8, ErrorMessage = "LoginID must be 8 characters")]
        [Required]
        [Column(TypeName = "nchar")]
        [Display(Name = "User ID")]
        public string LoginID { get; set; }

        [Required]
        [DataType(DataType.Password)]
        public string Password { get; set; }

        [DataType(DataType.Password)]
        [Display(Name = "Confirm password")]
        [Compare("Password",ErrorMessage = "Passwords do not match")]
        public string ConfirmPassword { get; set; }

        // Override the standard PhoneNumber field in Identity User.
        [StringLength(15)]
        [Display(Name = "Phone Number")]
        [Required]
        public string PhoneNumber { get; set; }

    }
}
