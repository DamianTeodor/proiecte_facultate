using ExpenseTrackerCore.Interfaces;
using ExpenseTrackerCore.Models;
using ExpenseTrackerCore1;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Cors;

namespace ExpenseTrackerCore.Controllers
{
    [EnableCors(origins: "*", headers: "*", methods: "*")] // tune to your needs
    [RoutePrefix("api")]
    public class RecordsController : ApiController
    {
        private readonly IRecordsCtrl irecordsctrl = new RecordsCtrl();
        public RecordsController()
        {
        }

        [HttpGet]
        [Route("records")]
        public BackendResponse GetRecords([FromUri] string username)
        {
            IEnumerable<Record> result = irecordsctrl.GetRecords(username);
            if (result != null)
            {
                return new BackendResponse { reposnseCode = 0, responseMessage = "Records fetched" , responseBody = result};
            }
            else
            {
                return new BackendResponse { reposnseCode = -1, responseMessage = "Could not fetch records" };
            }
        }

        [HttpPost]
        [Route("records/insert")]
        public BackendResponse Create([FromBody] Record record)
        {
            bool result = irecordsctrl.Insert(record);
            if (result == true)
            {

                return new BackendResponse { reposnseCode = 0, responseMessage = "Record Added" };
            }
            else
            {
                return new BackendResponse { reposnseCode = -1, responseMessage = "Could not add new record" };

            }
        }

        [HttpPost]
        [Route("records/edit")]
        public BackendResponse Edit([FromBody] Record record)
        {
            bool result = irecordsctrl.Edit(record);
            if (result == true)
            {

                return new BackendResponse { reposnseCode = 0, responseMessage = "Record edited" };
            }
            else
            {
                return new BackendResponse { reposnseCode = -1, responseMessage = "Could not edit record" };

            }
        }

        [HttpDelete]
        [Route("records/delete")]
        public BackendResponse Delete([FromUri] int recordId)
        {
            bool result = irecordsctrl.Delete(recordId);
            if (result == true)
            {

                return new BackendResponse { reposnseCode = 0, responseMessage = "Record deleted" };
            }
            else
            {
                return new BackendResponse { reposnseCode = -1, responseMessage = "Could not delete record" };

            }
        }
    }
}
