package org.jakartaee10recipe.chapter04.recipe04_17;


import org.jakartaee10recipe.chapter04.recipe04_06.Book;

/**
 * Object to hold a single cart item
 * @author juneau
 */
public class Item implements java.io.Serializable {
    private Book book = null;
    private int quantity = 0;

    public Item(Book book, int qty){
        this.book = book;
        this.quantity = qty;
    }

    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
