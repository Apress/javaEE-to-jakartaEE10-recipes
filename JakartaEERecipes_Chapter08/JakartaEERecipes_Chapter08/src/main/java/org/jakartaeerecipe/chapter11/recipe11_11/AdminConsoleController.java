package org.jakartaeerecipe.chapter11.recipe11_11;

import org.jakartaeerecipe.chapter11.interceptor.Notified;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Notified
@Named
@RequestScoped
public class AdminConsoleController {

    public AdminConsoleController(){

    }

    public void login(){
        System.out.println("This is an action method which would allow one to log into an"
                + "administrative console");
    }

}
