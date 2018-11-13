package fr.imta.rest;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

@Path("/hello")
public class RestHelloService extends Application {
   //@Context
    //private UriInfo context;

    @Context
    private HttpServletRequest httpRequest;

   /**
     * Retrieves representation of an instance of helloWorld.HelloWorld
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/sayHello")
    @Produces("text/plain")
    public String sayHello(@QueryParam("name") String name) {

        return name; 
        //String strContentType = httpRequest.getContentType();
        
       /*/ if (!"text/plain".equals(strContentType)){
              return "hello";
        }else{
              return " 406 HTTP status ";
        }*/
    }
   
    @GET
    @Path("/sayJson")
    @Produces("application/json")
    public HelloJsonResponse sayJson() {
        return new HelloJsonResponse();
    	
      }

}
