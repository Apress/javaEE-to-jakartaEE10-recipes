package org.jakartaeerecipe.chapter08.session;

import jakarta.ejb.*;
import org.jakartaeerecipe.chapter08.object.Cart;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 30)
public class OrderFacade {
    private Cart cart;
    @SuppressWarnings("unused")
    @PrePassivate
    private void prePassivate() {
        System.out.println("In PrePassivate method");
    }
    @SuppressWarnings("unused")
    @PostActivate
    private void postActivate() {
        System.out.println("In PostActivate method");
    }
    public Cart getCart() {
        if(cart == null)
            cart = new Cart();
        return cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public void completePurchase() {
        System.out.println("Not yet implemented..");
    }
    @Remove
    public void destroy() {
        System.out.println("Destroying OrderFacade...");
    }

}
