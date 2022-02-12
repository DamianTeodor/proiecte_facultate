using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ExpenseTrackerCore.Models
{
    public class BackendResponse
    {
        public int reposnseCode { get; set; }
        public string responseMessage { get; set; }
        public IEnumerable<Models.Record> responseBody { get; set; }
    }
}