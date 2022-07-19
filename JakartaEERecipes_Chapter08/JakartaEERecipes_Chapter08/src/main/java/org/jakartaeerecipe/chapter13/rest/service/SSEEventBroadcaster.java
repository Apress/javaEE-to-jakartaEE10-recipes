
package org.jakartaeerecipe.chapter13.rest.service;

import java.util.UUID;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseBroadcaster;
import jakarta.ws.rs.sse.SseEventSink;

/**
 *
 * @author Juneau
 */
@Path("ssebroadcaster")
@Singleton
public class SSEEventBroadcaster {

    @Context
    private Sse sse;

    private volatile SseBroadcaster sseBroadcaster;

    public SSEEventBroadcaster() {
    }

    @PostConstruct
    public void init() {
        sseBroadcaster = sse.newBroadcaster();
    }

    @GET
    @Path("register")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void register(@Context SseEventSink eventSink) {
        eventSink.send(sse.newEvent("Thanks for registering!"));
        sseBroadcaster.register(eventSink);
    }

    @POST
    @Path("send/{message}")
    public void broadcast(@PathParam("message") String message) {
        sseBroadcaster.broadcast(sse.newEventBuilder()
                .mediaType(MediaType.APPLICATION_JSON_TYPE)
                .id(UUID.randomUUID().toString())
                .name("SSEEventBroadcaster Message")
                .data(message)
                .build()
        );
    }
}
