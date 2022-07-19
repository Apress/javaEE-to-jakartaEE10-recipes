package org.jakartaeerecipe.chapter11.recipe11_03;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named(value = "paperbackController")
@SessionScoped
@Paperback
public class PaperbackController implements Serializable, IBook {
    @Override
    public String getTitle() {
        return null;
    }
}
