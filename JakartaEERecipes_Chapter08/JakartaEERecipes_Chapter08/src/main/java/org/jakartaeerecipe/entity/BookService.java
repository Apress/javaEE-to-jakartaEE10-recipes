package org.jakartaeerecipe.entity;

import jakarta.ws.rs.*;

@Path("/hello-world")
public class BookService {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}