package org.jakartaee10recipe.chapter04.recipe04_17;

import org.jakartaee10recipe.chapter04.recipe04_06.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private List<Item> books = null;

    public Cart(){
        books = null;
    }
    /**
     * @return the books
     */
    public List <Item> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(List books) {
        this.books = books;
    }

    /**
     * Utility method to add a book and quantity
     * @param title
     * @param qty
     */
    public void addBook(Book title, int qty){
        if (books == null){
            books = new ArrayList();
        }
        books.add(new Item(title, qty));
    }
}
