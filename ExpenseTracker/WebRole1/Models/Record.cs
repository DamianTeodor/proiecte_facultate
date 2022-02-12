using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ExpenseTrackerCore.Models
{
    public class Record
    {
        public string username { get; set; }
        public int id { get; set; }
        public string description { get; set; }

        public float amount { get; set; }
    }
}