package org.jakartaeerecipe.chapter03.recipe03_06;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import java.io.Serializable;

@Named(value = "navigationController")
@RequestScoped
public class NavigationController implements Serializable{

    private boolean authenticated = false;

    public String pageOne(){
        return "PAGE_1";
    }

    public String pageTwo(){
        return "PAGE_2";
    }

    /**
     * Utilizing implicit navigation, a page name can be returned from an
     * action method rather than listing a navigation-rule within faces-config.xml
     * @return the page name
     */
    public String nextPage(){
        // Perform some task, then implicitly list a page to render
        return "recipe03_06c";
    }

    /**
     * Demonstrates the use of conditional navigation
     */
    public void login(){
        // Perform some tasks, if needed, and then return boolean
        setAuthenticated(true);
        System.out.println("Here");
    }

    /**
     * @return the authenticated
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * @param authenticated the authenticated to set
     */
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
