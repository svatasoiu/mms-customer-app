using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace TestMMSWebService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface TestService
    {
        [OperationContract]
        string GetUserData(string ID);

        [OperationContract]
        string GetAllUsers();
    }

    [DataContract]
    public class Customer
    {
        [DataMember]
        public string CustomerID;
        [DataMember]
        public string Product;
        [DataMember]
        public DateTime ExpirationDate;
    }
}
