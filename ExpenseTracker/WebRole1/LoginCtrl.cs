using ExpenseTrackerCore.Interfaces;
using ExpenseTrackerCore.Models;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace ExpenseTrackerCore1
{
    public class LoginCtrl : ILoginCtrl
    {
        public bool Insert(User user)
        {
            SqlConnectionStringBuilder builder = new SqlConnectionStringBuilder();
            builder.DataSource = "expensetrackerserver.database.windows.net";
            builder.UserID = "admin_azure";
            builder.Password = "asdxsw123321A!";
            builder.InitialCatalog = "ExpenseTrackerDB1";
            using (SqlConnection connection = new SqlConnection(builder.ConnectionString))
            {
                String sql = "INSERT INTO Account (username, passwordd) VALUES ('" + user.username + "','" + user.password + "');";
                using (SqlCommand command = new SqlCommand(sql, connection))
                {
                    int result = 0;
                    connection.Open();
                    try
                    {
                        result = command.ExecuteNonQuery();
                    }
                    catch (Exception)
                    {
                        return false;
                    }
                    if (result == 1)
                    {
                        connection.Close();
                        return true;
                    }
                    else
                    {
                        connection.Close();
                        return false;
                    }
                }
            }
        }

        public bool Validate(User user)
        {
            SqlConnectionStringBuilder builder = new SqlConnectionStringBuilder();
            builder.DataSource = "expensetrackerserver.database.windows.net";
            builder.UserID = "admin_azure";
            builder.Password = "asdxsw123321A!";
            builder.InitialCatalog = "ExpenseTrackerDB1";
            using (SqlConnection connection = new SqlConnection(builder.ConnectionString))
            {
                String sql = "SELECT COUNT(*) FROM Account WHERE (username= '" + user.username + "' and passwordd='" + user.password + "')";
                using (SqlCommand command = new SqlCommand(sql, connection))
                {
                    connection.Open();
                    int userExists = (int)command.ExecuteScalar();
                    if (userExists > 0)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }
    }
}
