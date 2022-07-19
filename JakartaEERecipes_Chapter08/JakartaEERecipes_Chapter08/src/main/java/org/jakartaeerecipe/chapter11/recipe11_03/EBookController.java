package org.jakartaeerecipe.chapter11.recipe11_03;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named(value = "ebookController")
@SessionScoped
@EBook
public class EBookController implements Serializable, IBook {

    /**
     * Creates a new instance of EbookController
     */
    public EBookController() {
    }

    @Override
    public String getTitle() {
        return null;
    }
}
