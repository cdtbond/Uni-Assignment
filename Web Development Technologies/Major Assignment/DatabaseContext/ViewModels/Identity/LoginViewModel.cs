using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DatabaseContext.ViewModels
{
    public class LoginViewModel
    {
        // Approach from https://www.youtube.com/watch?v=sPbDrqpme_w&list=PL6n9fhu94yhVkdrusLaQsfERmL_Jh4XmU&index=66 used for this ViewModel.

        [StringLength(8, MinimumLength = 5, ErrorMessage = "LoginID must be 5-8 characters")]
        [Required]
        [Column(TypeName = "nchar")]
        [Display(Name = "User ID")]
        public string LoginID { get; set; }

        [Required]
        [DataType(DataType.Password)]
        public string Password { get; set; }

        [Display(Name = "Remember me")]
        public bool RememberMe { get; set; }

    }
}
