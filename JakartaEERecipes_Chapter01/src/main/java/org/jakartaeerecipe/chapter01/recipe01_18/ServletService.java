package org.jakartaeerecipe.chapter01.recipe01_18;

import jakarta.servlet.AsyncContext;

public class ServletService implements Runnable {
    AsyncContext ac;

    public ServletService(AsyncContext ac) {
        this.ac = ac;
        System.out.println("Dispatched to " + "\"ServletService\"");
    }

    @Override
    public void run() {
        System.out.println("Some long running process in \"ServletService\"");
        ac.complete();
    }
}
