//package fr.imta;

//import fr.imta.UserService;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

public class ClientApp {

  public static void main(String [] args) {

    try {
      TTransport transport;
     
      transport = new TSocket("localhost", 9090);
      transport.open();

      TProtocol protocol = new  TBinaryProtocol(transport);
      UserService.Client client = new UserService.Client(protocol);

      perform(client);

      transport.close();
    } catch (TException x) {
      x.printStackTrace();
    }
  } 
 

  private static void perform(UserService.Client client) throws TException
  {
      //client.sayHello("jean", 25);

       //client.ping();
       System.out.println("ping");

       UserDetails details =  new UserDetails();
       details.first_name = "John";
       details.last_name = "Do";
       details.email_address = "john.do@gmail.com";
       details.city = "Bamenda";

       client.createUser("johnny", details); 
  }
}
