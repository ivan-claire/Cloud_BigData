package fr.imta;

import fr.imta.hello.Hello;

import org.apache.thrift.TException;

public class HelloHandler implements Hello.Iface {
    @Override
    public void sayHello (String s, int i) throws TException {
	System.out.println ("Hello " + s + " !"+"NUMBER "+i);
    }
}
