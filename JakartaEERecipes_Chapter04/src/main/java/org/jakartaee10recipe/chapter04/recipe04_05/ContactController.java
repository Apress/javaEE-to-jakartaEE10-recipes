package org.jakartaee10recipe.chapter04.recipe04_05;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequestScoped
@Named(value="contactController4")
public class ContactController {
    private Contact current;
    private Subscription subscription;
    private String newsletterDescription;

    // Declaration of the managed bean properties
    private List<String> occupationList;

    public Map<String, Object> getAllNewsletters() {
        return allNewsletters;
    }

    public void setAllNewsletters(Map<String, Object> allNewsletters) {
        this.allNewsletters = allNewsletters;
    }

    private Map<String, Object> allNewsletters;

    public String getNewsletterDescription() {
        return newsletterDescription;
    }
    public void setNewsletterDescription(String newsletterDescription) {
        this.newsletterDescription = newsletterDescription;
    }

    public Subscription getSubscription() {
        if (subscription == null) {
            subscription = new Subscription();
        }
        return subscription;
    }

    // Example of populating the object
    private void populateOccupationList(){
        occupationList = new ArrayList();
        occupationList.add("Author");
        occupationList.add("IT Professional");
    }
    // Example of populating the object
    private void populateNewsletterList(){
        allNewsletters = new LinkedHashMap<String,Object>();
        allNewsletters.put("Introducing Java 17 Fundamentals", "Java");
        allNewsletters.put("Jakarta EE Fundamentals", "Jakarta EE");
        allNewsletters.put("Building High Performing Java 17 Applications", "GraalVM");
        allNewsletters.put("New Books Weekly", "New Books");
    }

    /**
     * @return the occupationList
     */
    public List<String> getOccupationList() {
        return occupationList;
    }
    /**
     * @param occupationList the occupationList to set
     */
    public void setOccupationList(List<String> occupationList) {
        this.occupationList = occupationList;
    }

    public Contact getCurrent() {
        if (current == null) {
            current = new Contact();
        }
        return current;
    }

    public ContactController() {
        current = null;
        newsletterDescription = "Enter your information below in order to be " +
                "added to the Acme Bookstore newsletter.";
        subscription = new Subscription();
        populateNewsletterList();
        populateOccupationList();
    }

    /**
     * Custom validator to ensure that password field contents match
     * @param context
     * @param component
     * @param value
     */
    public void validatePassword(FacesContext context,
                                 UIComponent component,
                                 Object value){
        Map map = context.getExternalContext().getRequestParameterMap();
        String passwordText = (String) map.get(("contactForm:password"));
        String confirmPassword = value.toString();
        if (!passwordText.equals(confirmPassword)) {
            throw new ValidatorException(new FacesMessage("Passwords do not match"));
        }
    }


    public String subscribe(){
        // Using a list implementation for now,
        // but will add to a database table in Chapter 7
        // Add the current contact to the subscription list
        subscription.getSubscriptionList().add(current);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Successfully Subscribed to Newsletter for " + getCurrent().getEmail(), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        return "SUBSCRIBE";
    }

    /**
     * This method will allow a user to navigate to the manageAccount view.
     * This method will be moved into another managed bean that focuses on
     * authentication later on.
     * @return
     */
    public String manage(){
        return "./manageAccount.xhtml";
    }

    /**
     * Navigational method
     * @return String
     */
    public String add(){
        return "ADD_SUBSCRIBER";
    }
}

