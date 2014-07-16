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
        public string GetUserData(string ID)
        {
            SqlConnection con = new SqlConnection(System.Configuration.ConfigurationManager.AppSettings["connString"]);
            con.Open();
            SqlDataAdapter adapter = new SqlDataAdapter("select * from Customers where CTM_NBR="+ID, con);
            DataSet customers = new DataSet();
            adapter.Fill(customers);
            return customers.GetXml();
        }

        public string GetAllUsers()
        {
            SqlConnection con = new SqlConnection(System.Configuration.ConfigurationManager.AppSettings["connString"]);
            con.Open();
            SqlDataAdapter adapter = new SqlDataAdapter("select * from Customers", con);
            DataSet customers = new DataSet();
             adapter.Fill(customers);
            return customers.GetXml();
        }
    }
}
