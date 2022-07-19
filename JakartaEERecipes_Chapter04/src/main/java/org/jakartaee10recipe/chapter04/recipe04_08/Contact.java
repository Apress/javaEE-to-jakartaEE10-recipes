package org.jakartaee10recipe.chapter04.recipe04_08;

import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.Map;

public class Contact implements Serializable {

    private String first;
    private String last;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String gender;
    private String password;
    private String description;
    private Map<String, Object> newsletterList;
    private Map<String, String> notificationType;
    private boolean receiveNotifications;


    public boolean isReceiveNotifications() {
        return receiveNotifications;
    }

    public void setReceiveNotifications(boolean receiveNotifications) {
        this.receiveNotifications = receiveNotifications;
    }

    public Map<String, String> getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Map<String, String> notificationType) {
        this.notificationType = notificationType;
    }


    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    private String occupation;

    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+", message = "Email format is invalid.")
    private String email;

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the newsletterList
     */
    public Map<String,Object> getNewsletterList() {
        return newsletterList;
    }
    /**
     * @param newsletterList the newsletterList to set
     */
    public void setNewsletterList(Map<String,Object> newsletterList) {
        this.newsletterList = newsletterList;
    }

}
