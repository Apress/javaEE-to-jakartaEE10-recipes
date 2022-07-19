
package org.jakartaeerecipe.chapter13.rest.service;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;

@Path("sse")
public class SSEEventResource {

    @Resource(name = "DefaultManagedExecutorService")
    ManagedExecutorService executor;

    public SSEEventResource() {

    }

    @GET
    @Path("send")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void send(@Context SseEventSink eventSink,
            @Context Sse sse) {
        executor.execute(() -> {
            try (SseEventSink sink = eventSink) {
                eventSink.send(sse.newEvent("Welcome to the List!"));
                eventSink.send(sse.newEvent("Message One!"));
                eventSink.send(sse.newEvent("SERVER-NOTIFICATION", "Message Two!"));
                eventSink.send(sse.newEventBuilder()
                        .comment("Nice Test")
                        .name("SERVER-TEST")
                        .data("Some data...could be an object")
                        .build());
                eventSink.close();
            }
        });
    }

}
