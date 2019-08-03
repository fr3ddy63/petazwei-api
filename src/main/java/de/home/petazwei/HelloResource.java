package de.home.javaee.petazwei;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/hello")
public class HelloResource {

    @GET
    public Response doGet() {
        return Response.ok("Hello.").build();
    }
}