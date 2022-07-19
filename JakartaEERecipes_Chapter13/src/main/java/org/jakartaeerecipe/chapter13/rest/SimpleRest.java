package org.jakartaeerecipe.chapter13.rest;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
// Set the PATH to http://host:port/<application>/rest/simplerest/
@Path("/simplerest")
public class SimpleRest {
    @GET
    // Produces plain text message
    @Produces("text/plain")
    public String getPlainMessage() {
        return "Hello from a simple rest service";
    }
    @GET
    // Produces plain text message
    @Produces("text/html")
    public String getHTMLMessage() {
        return "<P>Hello from a <b>simple</b> rest service</P>";
    }
}

