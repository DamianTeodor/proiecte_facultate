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
    [RoutePrefix("/api")]
    public class LoginController : ApiController
    {
        private readonly ILoginCtrl iloginctrl = new LoginCtrl();
        public LoginController()
        {
        }
        [HttpPost]
        public BackendResponse Create([FromBody]User user)
        {
            bool result = iloginctrl.Insert(user);
            if (result == true)
            {

                return new BackendResponse { reposnseCode = 0, responseMessage = "User registered"};
            }
            else
            {
                return new BackendResponse { reposnseCode = -1, responseMessage = "User already exists" };

            }
        }
        [HttpPut]
        public BackendResponse Validate([FromBody]User user)
        {
            bool result = iloginctrl.Validate(user);
            if (result == true)
            {
                return new BackendResponse { reposnseCode = 0, responseMessage = user.username };
            }
            else
            {
                return new BackendResponse { reposnseCode = -2, responseMessage = "Invalid login data" };
            }
        }
    }
}
