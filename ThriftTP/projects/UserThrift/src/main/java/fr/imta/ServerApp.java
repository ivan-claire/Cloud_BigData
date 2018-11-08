package fr.imta;

import fr.imta.*;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;

public class ServerApp 
{
  public static UserServiceHandler handler;

  public static UserService.Processor processor;

  public static void main(String [] args) {
    try {
      handler = new UserServiceHandler();
      processor = new UserService.Processor(handler);

      Runnable simple = new Runnable() {
        public void run() {
          simple(processor);
        }
      };      
      new Thread(simple).start();
    } catch (Exception x) {
      x.printStackTrace();
    }
  }

  public static void simple(UserService.Processor processor) {
    try {
      TServerTransport serverTransport = new TServerSocket(9090);
      TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

      System.out.println("Starting the Hello server...");
      server.serve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
