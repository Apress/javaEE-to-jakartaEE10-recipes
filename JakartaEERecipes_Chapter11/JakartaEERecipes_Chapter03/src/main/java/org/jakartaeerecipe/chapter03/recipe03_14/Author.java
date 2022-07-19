package org.jakartaeerecipe.chapter03.recipe03_14;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record Author(String getFirst, String getLast, String getBio, List<Book> getBooks) implements Serializable {

    Author(String getFirst, String getLast, String getBio){
        this(getFirst, getLast, getBio, new ArrayList<>());
    }

    public void addBook(Book book) {
        getBooks.add(book);
    }
}
