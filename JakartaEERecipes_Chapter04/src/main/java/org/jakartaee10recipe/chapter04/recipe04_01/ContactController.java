package org.jakartaee10recipe.chapter04.recipe04_01;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
@Named(value= "contactController")
public class ContactController {

    private Contact current;

    public Contact getCurrent() {
        if (current == null) {
            current = new Contact();
        }
        return current;
    }

    public String subscribe(){
        // Using a list implementation for now,
        // but will add to a database table in Chapter 7
        // Add the current contact to the subscription list
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Successfully Subscribed to Newsletter for " + getCurrent().getEmail(), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        return "SUBSCRIBE";
    }

    /**
     * Navigational method
     * @return String
     */
    public String add(){
        return "ADD_SUBSCRIBER";
    }
}
