using Assignment2.ExtensionMethods;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Assignment2.Utilities
{
    public static class ViewChecks
    {
        public static string AmountChecks(decimal amount)
        {
            if(IsNegativeOrZero(amount))
            {
                return "Must be positive.";
            }
            else if(HasExcessDecimalPlaces(amount))
            {
                return "Must have a max of 2 decimal places.";
            } else
            {
                return "";
            }
        }


        private static bool IsNegativeOrZero(decimal value)
        {
            return value <= 0;
        }
        private static bool HasExcessDecimalPlaces(decimal value)
        {
            return decimal.Round(value, 2) != value;
        }


        public static bool EmptyDate(DateTime date)
        {
            return date.Year.CompareTo(DateTime.UtcNow.Year) < 0;
        }

    }
}
