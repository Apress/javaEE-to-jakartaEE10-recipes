package org.jakartaeerecipe.chapter10.recipe10_04;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Named
@RequestScoped
public class ParameterValidationController {


    private String email;


    public void submit(){
        submitEmailAddress(email);
    }

    public void submitEmailAddress(@NotNull @Email String emailAddress){
        System.out.println("Do something with the address: " + emailAddress);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
