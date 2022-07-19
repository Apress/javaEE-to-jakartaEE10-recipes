package org.jakartaeerecipe.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("jakartaee")
public class JakartaEEResource {

    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
}
