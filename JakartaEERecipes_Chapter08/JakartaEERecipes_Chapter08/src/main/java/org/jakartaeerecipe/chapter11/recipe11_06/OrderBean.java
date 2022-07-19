package org.jakartaeerecipe.chapter11.recipe11_06;

import org.jakartaeerecipe.chapter11.recipe11_03.IBook;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;

import java.util.List;


public class OrderBean {
    @Produces
    List<IBook> books;
    public OrderBean() { }
    void fetchBookStock(@Disposes List<IBook> books){ }
}
