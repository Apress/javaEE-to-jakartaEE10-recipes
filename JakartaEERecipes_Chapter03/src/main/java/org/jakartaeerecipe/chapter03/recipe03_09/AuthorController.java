package org.jakartaeerecipe.chapter03.recipe03_09;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.jakartaeerecipe.chapter03.recipe03_03.Author;

@Named(value = "authorTableController")
@SessionScoped
public class AuthorController implements Serializable {
    private List<Author> authorList = null;
    private final String juneauBio = "This is Josh Juneau's Bio";
    private final String tarunBio = "This is Tarun Telang's Bio";
    private Author current;
    private String authorLast;

    /**
     * Creates a new instance of RecipeController
     */
    public AuthorController() {
        super();
        authorLast = null;
        populateAuthorList();
    }

    private void populateAuthorList() {
        if (authorList == null) {
            System.out.println("initializing authors list");
            authorList = new ArrayList<>();
            authorList.add(new Author("Josh", "Juneau", juneauBio));
            authorList.add(new Author("Tarun", "Telang", tarunBio));
        }
    }

    public String displayAuthor(String last) {
        for (Author author : authorList) {
            if (author.getLast().equals(last)) {
                current = author;
                break;
            }
        }
        return "recipe03_09b";
    }

    /**
     * @return the authorList
     */
    public List getAuthorList() {
        System.out.println("Getting the authorlist =>" + authorList.size());
        return authorList;
    }

    /**
     * @return the current
     */
    public Author getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(Author current) {
        this.current = current;
    }

    /**
     * @return the authorLast
     */
    public String getAuthorLast() {
        return authorLast;
    }

    /**
     * @param authorLast the authorLast to set
     */
    public void setAuthorLast(String authorLast) {
        displayAuthor(authorLast);
    }
}


