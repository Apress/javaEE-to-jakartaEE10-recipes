package org.jakartaeerecipe.chapter08.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.jakartaeerecipe.chapter08.session.ContactFacade;
import org.jakartaeerecipe.entity.Contact;

import java.io.Serializable;
import java.util.List;

@Named(value = "subscriptionController1")
@SessionScoped
public class SubscriptionController implements Serializable {

    private List<Contact> subscriptionList;


    @EJB
    private ContactFacade contactFacade;
    /**
     * Creates a new instance of SubscriptionController
     */
    public SubscriptionController() {
        subscriptionList = contactFacade.findAll();
    }

    /**
     * @return the subscriptionList
     */
    public List <Contact> getSubscriptionList() {
        subscriptionList = contactFacade.findAll();
        return subscriptionList;
    }

    /**
     * @param subscriptionList the subscriptionList to set
     */
    public void setSubscriptionList(List <Contact> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }
}
