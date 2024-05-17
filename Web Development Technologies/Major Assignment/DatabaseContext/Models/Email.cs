using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DatabaseContext.Models
{
    public class Email
    {

        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Key]
        public int EmailID { get; set; }

        [Required]
        [DataType(DataType.DateTime, ErrorMessage = "Must be a DateTime.")]
        [Column(TypeName = "datetime2")]
        public DateTime ModifyDate { get; set; }


    }
}
