package fr.imta;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApplicationPath("/hello")
public class RestHelloService {
   //@Context
    //private UriInfo context;

    @Context
    private HttpServletRequest httpRequest;

   /**
     * Retrieves representation of an instance of helloWorld.HelloWorld
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/hello/sayHello")
    @Produces("text/plain")
    public String sayHello() {

        String strContentType = httpRequest.getContentType();

        if (!"text/plain".equals(strContentType)){
              return "hello";
        }else{
              return " 406 HTTP status ";
        }
  }
}
