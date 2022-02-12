using ExpenseTrackerCore.Interfaces;
using ExpenseTrackerCore.Models;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace ExpenseTrackerCore1
{
    public class RecordsCtrl : IRecordsCtrl
    {
        public bool Insert(Record record)
        {
            SqlConnectionStringBuilder builder = new SqlConnectionStringBuilder();
            builder.DataSource = "expensetrackerserver.database.windows.net";
            builder.UserID = "admin_azure";
            builder.Password = "asdxsw123321A!";
            builder.InitialCatalog = "ExpenseTrackerDB1";
            using (SqlConnection connection = new SqlConnection(builder.ConnectionString))
            {
                String sql = "INSERT INTO Records (username, description, amount) VALUES ('" + record.username + "','" + record.description + "'," + record.amount + ");";
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

        public bool Delete(int id)
        {
            SqlConnectionStringBuilder builder = new SqlConnectionStringBuilder();
            builder.DataSource = "expensetrackerserver.database.windows.net";
            builder.UserID = "admin_azure";
            builder.Password = "asdxsw123321A!";
            builder.InitialCatalog = "ExpenseTrackerDB1";
            using (SqlConnection connection = new SqlConnection(builder.ConnectionString))
            {
                String sql = "DELETE FROM Records WHERE id = '" + id + "'";
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

        public IEnumerable<Record> GetRecords(string username)
        {
            try
            {
                SqlConnectionStringBuilder builder = new SqlConnectionStringBuilder();
                builder.DataSource = "expensetrackerserver.database.windows.net";
                builder.UserID = "admin_azure";
                builder.Password = "asdxsw123321A!";
                builder.InitialCatalog = "ExpenseTrackerDB1";
                using (SqlConnection connection = new SqlConnection(builder.ConnectionString))
                {
                    String sql = "SELECT * FROM Records WHERE username = '" + username + "'";
                    using (SqlCommand command = new SqlCommand(sql, connection))
                    {
                        connection.Open();
                        using (SqlDataReader reader = command.ExecuteReader())
                        {
                            IEnumerable<Record> records = Enumerable.Empty<Record>();
                            while (reader.Read())
                            {
                                Record record = new Record { username = reader.GetString(0), id = (int)reader.GetInt64(1), description = reader.GetString(2), amount = (float)reader.GetDouble(3) };
                                records = records.Concat(new[] { record });
                            }
                            connection.Close();
                            return records;
                        }
                    }
                }
            }
            catch (Exception)
            {
                return null;
            }
        }

        public bool Edit(Record record)
        {
            SqlConnectionStringBuilder builder = new SqlConnectionStringBuilder();
            builder.DataSource = "expensetrackerserver.database.windows.net";
            builder.UserID = "admin_azure";
            builder.Password = "asdxsw123321A!";
            builder.InitialCatalog = "ExpenseTrackerDB1";
            using (SqlConnection connection = new SqlConnection(builder.ConnectionString))
            {
                String sql = "UPDATE Records SET description = '" + record.description + "', amount = " + record.amount + " WHERE id = " + record.id;
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
    }
}
