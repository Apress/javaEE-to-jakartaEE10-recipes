package org.jakartaee10recipe.chapter04.recipe04_11;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Chapter 3
 * @author juneau
 *
 * Session-scoped bean that is used to hold the list of subscribers.  Uses an
 * ArrayList in the early book chapters, but will evolve to make use of a database
 * later in the book.
 */
@Named(value = "ch3SubscriptionController")
@SessionScoped
public class SubscriptionController implements Serializable {
    private List<Contact> subscriptionList;

    /**
     * Creates a new instance of SubscriptionController
     */
    public SubscriptionController() {
        subscriptionList = new ArrayList();
    }

    /**
     * @return the subscriptionList
     */
    public List <Contact> getSubscriptionList() {
        return subscriptionList;
    }

    /**
     * @param subscriptionList the subscriptionList to set
     */
    public void setSubscriptionList(List <Contact> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }
}
