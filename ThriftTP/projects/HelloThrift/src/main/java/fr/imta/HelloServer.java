package fr.imta;

import fr.imta.hello.Hello;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class HelloServer {

  public static HelloHandler handler;

  public static Hello.Processor processor;

  public static void main(String [] args) {
    try {
      handler = new HelloHandler();
      processor = new Hello.Processor(handler);

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

  public static void simple(Hello.Processor processor) {
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
