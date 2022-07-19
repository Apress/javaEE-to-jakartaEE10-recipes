package org.jakartaeerecipe.chapter08.session;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.Singleton;

import static jakarta.ejb.ConcurrencyManagementType.CONTAINER;

@Singleton
@ConcurrencyManagement(CONTAINER)
public class BookstoreSessionCounter {

    private int numberOfSessions;

    /**
     * Initialize the Bean
     */
    @PostConstruct
    public void init() {
        // Initialize bean here
        System.out.println("Initalizing bean...");
    }

    public BookstoreSessionCounter() {
        numberOfSessions = 0;
    }

    /**
     * @return the numberOfSessions
     */
    public int getNumberOfSessions() {
        numberOfSessions++;
        return numberOfSessions;
    }

    /**
     * @param numberOfSessions the numberOfSessions to set
     */
    public void setNumberOfSessions(int numberOfSessions) {
        this.numberOfSessions = numberOfSessions;
    }
}
