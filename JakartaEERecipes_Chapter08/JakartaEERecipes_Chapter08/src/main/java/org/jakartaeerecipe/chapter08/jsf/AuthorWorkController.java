package org.jakartaeerecipe.chapter08.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.jakartaeerecipe.chapter08.session.AuthorWorkFacade;
import org.jakartaeerecipe.entity.AuthorWork;
import org.jakartaeerecipe.entity.Book;

import java.io.Serializable;
import java.util.List;

@Named(value = "authorWorkController")
@SessionScoped
public class AuthorWorkController implements Serializable {

    @EJB
    AuthorWorkFacade ejbFacade;

    private Long authorCount;
    /**
     * Creates a new instance of AuthorWorkController
     */
    public AuthorWorkController() {
    }

    /**
     * Invokes the AuthorWorkFacade's performFind method utilizing the
     * business interface.
     * @param book
     * @return
     */
    public List<AuthorWork> findByBookId(Book book){
        return ejbFacade.performFind(book.getId());
    }

    public String obtainAuthorCount(Book book){
        setAuthorCount(ejbFacade.findAuthorCount(book));
        return "/chapter09/recipe9_6b.xhtml";
    }

    /**
     * @return the authorCount
     */
    public Long getAuthorCount() {
        return authorCount;
    }

    /**
     * @param authorCount the authorCount to set
     */
    public void setAuthorCount(Long authorCount) {
        this.authorCount = authorCount;
    }


}
