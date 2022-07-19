package org.jakartaeerecipe.chapter03.recipe03_01;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class HelloWorldController {
    private String hello = "Hello World!";
    public String getHello() {
        return hello;
    }
}

