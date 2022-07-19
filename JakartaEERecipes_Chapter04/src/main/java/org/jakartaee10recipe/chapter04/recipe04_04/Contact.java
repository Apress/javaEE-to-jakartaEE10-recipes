package org.jakartaee10recipe.chapter04.recipe04_04;

import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public class Contact implements Serializable {

    private String first;
    private String last;
    private String password;
    private String description;

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

}

