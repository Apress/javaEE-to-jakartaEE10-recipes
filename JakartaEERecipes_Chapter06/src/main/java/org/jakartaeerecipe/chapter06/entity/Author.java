package org.jakartaeerecipe.chapter06.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Chapter 5
 * @author juneau
 */
public class Author implements java.io.Serializable {
    private int id;
    private String first;
    private String last;
    private String bio;
    private List <Book> books;

    public Author(){
        super();
        this.id = -1;
        this.first = null;
        this.last = null;
        this.bio = null;
        this.books = new ArrayList();
    }

    public Author(int id, String first, String last, String bio){
        this.id = id;
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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

}
