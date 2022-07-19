package org.jakartaeerecipe.chapter15.recipe15_02;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("recipe15_02b")
@SessionScoped
public class Recipe15_02b implements Serializable {
    public Recipe15_02b() {
    }

    public String unsecuredProcess(){
        return "recipe15_02_1.xhtml";
    }

    @RolesAllowed("users")
    public String securedProcess(){
        return "recipe15_02_2.xhtml";
    }
}
