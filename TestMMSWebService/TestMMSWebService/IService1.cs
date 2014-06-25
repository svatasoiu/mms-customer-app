using System;
using System.Collections.Generic;
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
        string GetUserName(int ID);

        [OperationContract]
        List<User> GetAllUsers();
    }

    [DataContract]
    public class User
    {
        [DataMember]
        public int UserID;
        [DataMember]
        public string Name;
        [DataMember]
        public DateTime ExpirationDate;
    }
}
