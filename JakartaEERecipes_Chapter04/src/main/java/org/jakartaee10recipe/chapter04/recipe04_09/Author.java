package org.jakartaee10recipe.chapter04.recipe04_09;
import org.jakartaee10recipe.chapter04.recipe04_06.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Chapter 4
 * @author juneau
 */
public class Author implements java.io.Serializable {
    private String first;
    private String last;
    private String bio;
    private List <Book> books;

    public Author(){
        this.first = null;
        this.last = null;
        this.bio = null;
        this.books = new ArrayList();
    }

    public Author(String first, String last, String bio){
        this.first = first;
        this.last = last;
        this.bio = bio;
        this.books = new ArrayList();
    }
    /**
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * @return the bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio the bio to set
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * @return the books
     */
    public List <Book> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(List <Book> books) {
        this.books = books;
    }

    public void addBook(Book book){
        books.add(book);
    }

}
