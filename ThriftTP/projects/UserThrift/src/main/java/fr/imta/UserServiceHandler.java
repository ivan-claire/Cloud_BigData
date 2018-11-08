//package fr.imta;

//import fr.imta.*;
import org.apache.thrift.TException;
import java.util.HashMap;

public class UserServiceHandler implements UserService.Iface {
    
    private HashMap<Long,String> users = new HashMap<Long, String>();
    private int user_ids;

    @Override
    public long createUser(java.lang.String user_name, UserDetails user_details) throws AlreadyExist, org.apache.thrift.TException{
	System.out.println ("User Created with user name" + user_name);
	System.out.println ("\nUser Details....\n" );
        System.out.println ("First Name:"+user_details.first_name+"\nCity:"+user_details.city );
      return 1;
    }

    @Override
    public void deleteUser(long user_id) throws UnknownId, org.apache.thrift.TException {
	System.out.println ("User to be deleted" + user_id);
    }

    @Override
    public boolean checkUser(java.lang.String user_name) throws UnknownId, org.apache.thrift.TException {
	System.out.println ("User to be checked" + user_name);
         return false;
    }

    @Override
    public UserInfo getUserInfoById(long user_id) throws UnknownId, org.apache.thrift.TException{
	System.out.println ("User to be checked" + user_id);
        UserInfo info = new UserInfo();
        return info;
    }

    @Override
    public UserInfo getUserInfoByName(java.lang.String user_name) throws UnknownName, org.apache.thrift.TException{
	System.out.println ("User to be checked" + user_name);
       UserInfo info = new UserInfo();
        return info;
    }

    @Override
    public void updateDetails(long user_id, UserDetails user_details) throws UnknownId, org.apache.thrift.TException{
	System.out.println ("User to be checked" + user_id);
        
    }

}
