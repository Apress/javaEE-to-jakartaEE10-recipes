package org.jakartaeerecipe.chapter11.observer;

import org.jakartaeerecipe.chapter11.event.BookEvent;
import org.jakartaeerecipe.chapter11.qualifier.OnlineSale;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

public class BookEventHandler<BookController> {
//    @Inject
//    private BookController bookController;

    public BookEventHandler(){
    }

    public void  notifyPublisherOnline (@Observes @OnlineSale BookEvent event) {
        for (String s : event.getNotifyList()) {
            System.out.println("Sending Notification to Publisher: " + s + " purchase of book online: "
                    /* + bookController.findById(event.getBook()).getTitle() */ + " from store: " + event.getStoreName()
                    + " purchase price: $" + event.getPrice()
                    + " on: " + event.getDate());
        }
    }

    public void notifyPublisherInStore (@Observes @OnlineSale BookEvent event) {
        for (String s : event.getNotifyList()) {
            System.out.println("Sending Notification to Publisher: " + s + " purchase of book in store: "
                    /* + bookController.findById(event.getBook()).getTitle() */ + " from store: " + event.getStoreName()
                    + " purchase price: $" + event.getPrice()
                    + " on: " + event.getDate());
        }
    }

}
