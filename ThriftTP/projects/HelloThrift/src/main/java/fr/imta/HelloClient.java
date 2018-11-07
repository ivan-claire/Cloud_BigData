package fr.imta;

import fr.imta.hello.*;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class HelloClient {
  public static void main(String [] args) {

    try {
      TTransport transport;
     
      transport = new TSocket("localhost", 9090);
      transport.open();

      TProtocol protocol = new  TBinaryProtocol(transport);
      Hello.Client client = new Hello.Client(protocol);

      perform(client);

      transport.close();
    } catch (TException x) {
      x.printStackTrace();
    } 
  }

  private static void perform(Hello.Client client) throws TException
  {
      client.sayHello("jean", 25);
  }
}
