package org.jakartaeerecipe.chapter15.recipe15_02;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("recipe15_02")
@SessionScoped
@DeclareRoles("users")
public class Recipe15_02 implements Serializable {

    public Recipe15_02() {
    }

    public String nextPage() {
        return "recipe15_02b.xhtml";
    }
}
