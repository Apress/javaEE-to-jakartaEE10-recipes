package org.jakartaeerecipe.chapter11.recipe11_13;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@Dependent
public class BookstoreRegistrationController implements BookstoreRegistration, Serializable {
    @Inject
    private Registration current;

    public BookstoreRegistrationController(){

    }

    /**
     * @return the current
     */
    public Registration getCurrent() {
        if(current == null){
            current = new Registration();
        }
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(Registration current) {
        this.current = current;
    }

    public String register(){
        System.out.println("Executing Registration");
        return register(current);
    }

    @Override
    public String register(Registration registration) {
        // Persist current registration
        return null;
    }
}
