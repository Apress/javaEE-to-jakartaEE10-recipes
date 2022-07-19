/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jakartaeerecipe.chapter13.rest.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.sse.SseEventSource;

/**
 *
 * @author Juneau
 */
@Named
@RequestScoped
public class SseClient {

    private Client client;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
    }

    public void listen() {
        WebTarget target = client.target("http://localhost:8080/JavaEERecipes/rest/ssebroadcaster/register");
        try (SseEventSource source = SseEventSource.target(target).build()) {
            source.register(System.out::println);
            source.open();
            Thread.sleep(250); // Consume events for 250 ms
            source.close();
        } catch (InterruptedException e) {
            
        }
    }
}
