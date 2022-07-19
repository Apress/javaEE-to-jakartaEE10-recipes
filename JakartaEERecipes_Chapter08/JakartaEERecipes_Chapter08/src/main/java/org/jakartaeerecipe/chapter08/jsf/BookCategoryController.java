package org.jakartaeerecipe.chapter08.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.jakartaeerecipe.chapter08.session.BookCategoryFacade;

import java.util.List;

@Named(value = "bookCategoryController")
@RequestScoped
public class BookCategoryController {

    @EJB
    BookCategoryFacade ejbFacade;

    /**
     * Creates a new instance of BookCategoryController
     */
    public BookCategoryController() {
    }

    public List getBookCategories(){
        return ejbFacade.getBookCategories();
    }
}
