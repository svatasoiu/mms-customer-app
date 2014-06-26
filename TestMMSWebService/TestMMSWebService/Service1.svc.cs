using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using System.Data;
using System.Data.SqlClient;

namespace TestMMSWebService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select Service1.svc or Service1.svc.cs at the Solution Explorer and start debugging.
    public class Service1 : TestService
    {
        public string GetUserName(int ID)
        {
            return "Sorin";
        }

        public DataSet GetAllUsers()
        {
            
            try
            {
                SqlConnection con = new SqlConnection("Server=localhost\\sqlexpress; Database=CustomerDB; Integrated Security=True;");
                con.Open();
                SqlDataAdapter adapter = new SqlDataAdapter("select * from Customers", con);
                DataSet customers = new DataSet();
                adapter.Fill(customers);
                return customers;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
            }
            return null;
            //return new List<Customer>() { new Customer {CustomerID="0010", Product="SV", ExpirationDate=new DateTime(1990,05,05)}};
        }
    }
}
