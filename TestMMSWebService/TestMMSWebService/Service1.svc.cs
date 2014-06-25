using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

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

        public List<User> GetAllUsers()
        {
            return new List<User>() { new User {UserID=1, Name="SV", ExpirationDate=new DateTime(1990,05,05)}};
        }
    }
}
