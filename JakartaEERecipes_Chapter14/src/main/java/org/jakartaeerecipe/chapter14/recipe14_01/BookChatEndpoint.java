package org.jakartaeerecipe.chapter14.recipe14_01;

import jakarta.websocket.OnMessage;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/chat")
public class BookChatEndpoint {

    @OnMessage
    public String messageReceiver(String message) {
        return "Message Received: " + message;
    }
}
