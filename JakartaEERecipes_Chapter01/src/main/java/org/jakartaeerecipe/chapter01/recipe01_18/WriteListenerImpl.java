package org.jakartaeerecipe.chapter01.recipe01_18;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebListener
public class WriteListenerImpl implements WriteListener {
    ServletOutputStream os;
    AsyncContext context;

    public WriteListenerImpl(ServletOutputStream out, AsyncContext ctx){
        this.os = out;
        this.context = ctx;
        System.out.println("Write Listener Initialized");
    }
    @Override
    public void onWritePossible() {
        System.out.println("Now possible to write...");
        // Write implementation here...
    }
    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error occurred");
        context.complete();
    }

}
