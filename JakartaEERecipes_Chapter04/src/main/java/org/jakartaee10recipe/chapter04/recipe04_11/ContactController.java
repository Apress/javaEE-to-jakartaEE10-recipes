package org.jakartaee10recipe.chapter04.recipe04_11;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.*;

@RequestScoped
@Named(value= "ch3contactController")
public class ContactController {

    @Inject
    private SubscriptionController subscriptionController;

    private Contact current;
    private Subscription subscription;
    private String newsletterDescription;
    private String passwordConfirm;
    private List<String> occupationList;
    private Map<String, Object> allNewsletters;
    private Map<String, Object> notificationTypes;
    private String passwordStrengthMessage;

    public String getPasswordStrengthMessage() {
        return passwordStrengthMessage;
    }


    public ContactController() {
        current = null;
        passwordConfirm = null;
        newsletterDescription = "Enter your information below in order to be " +
                "added to the Acme Bookstore newsletter.";

        populateNewsletterList();
        populateOccupationList();
        populateNotificationTypes();
    }

    /**
     * This method will allow a user to navigate to the manageAccount view.
     * This method will be moved into another managed bean that focuses on
     * authentication later on.
     * @return
     */
    public String manage(){
        return "/chatper04/manageAccount";
    }

    /**
     * Navigational method
     * @return String
     */
    public String add(){
        return "/chapter04/subscribe";
    }

    public String getNewsletterDescription() {
        return newsletterDescription;
    }

    public void setNewsletterDescription(String newsletterDescription) {
        this.newsletterDescription = newsletterDescription;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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

    public void passwordStrength(AjaxBehaviorEvent event){
        UIInput password = (UIInput) event.getComponent();
        boolean isStrong = false;
        String input = password.getValue().toString();

        if(input.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,})")) {
            isStrong = true;
        }

        if(isStrong == true){
            setPasswordStrengthMessage("Password is strong");
        } else {
            setPasswordStrengthMessage("Password is weak");
        }
    }

    private void setPasswordStrengthMessage(String passwordStrengthMessage) {
        this.passwordStrengthMessage = passwordStrengthMessage;
    }

    public Map<String, Object> getNotificationTypes() {
        return notificationTypes;
    }

    public void setNotificationTypes(Map<String, Object> notificationTypes) {
        this.notificationTypes = notificationTypes;
    }

    public Map<String, Object> getAllNewsletters() {
        return allNewsletters;
    }

    public void setAllNewsletters(Map<String, Object> allNewsletters) {
        this.allNewsletters = allNewsletters;
    }

    public void newsletterBoxListener(ValueChangeEvent event) {
        try {
            Boolean isChecked = (Boolean) event.getNewValue();
            if (isChecked) {
                System.out.println("Checked");
            } else {
                System.out.println("Not Checked");
            }
        } catch(Exception e){
            System.err.println(e);
        }
    }

    private void populateNotificationTypes() {
        notificationTypes = new HashMap<>();
        notificationTypes.put("Product Updates", "1");
        notificationTypes.put("Best Seller Alerts","2");
        notificationTypes.put("Spam", "3");
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

    public String subscribe(){
        // Using a list implementation for now,
        // but will add to a database table in Chapter 7
        // Add the current contact to the subscription list
        System.out.println("Here....submitting...");
        subscription.getSubscriptionList().add(current);

        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Successfully Subscribed to Newsletter for " + getCurrent().getEmail(), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        return "/chapter04/subscribe";
    }

    public SubscriptionController getSubscriptionController() {
        return subscriptionController;
    }

    public void setSubscriptionController(SubscriptionController subscriptionController) {
        this.subscriptionController = subscriptionController;
    }
}



