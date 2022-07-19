package org.jakartaeerecipe.chapter13.rest;

import jakarta.ws.rs.*;
import org.jakartaeerecipe.chapter13.recipe13_02.MessageWrapper;
import org.jakartaeerecipe.chapter13.recipe13_02.MessageWrapperList;

import java.util.ArrayList;

@Path("/simplerest")
public class SimpleRest {

    private String message = "Hello from a simple REST Service";
    private String htmlMessage = "<p><b>" + message + "</b></p>";

    @GET
    // Produces plain text message
    @Produces("text/plain")
    public String getPlainMessage() {
        return message;
    }

    @GET
    // Produces plain text message
    @Produces("text/html")
    public String getHTMLMessage() {
        return htmlMessage;
    }

    @GET
    // Produces an XML message
    @Produces("application/xml")
    public MessageWrapper getXMLMessage() {
        return new MessageWrapper(message);
    }

    @GET
    // Produces an XML message
    @Path("all")
    @Produces("application/xml")
    public MessageWrapperList getXMLMessageList() {
        ArrayList<String> messageList = new ArrayList<>();
        messageList.add("String 1");
        messageList.add("String 2");
        return new MessageWrapperList(messageList);
    }

    @PUT
    @Path("add")
    @Consumes("text/plain")
    public String add(@QueryParam("text") String text){
        this.message = text;
        return message;
    }
}
