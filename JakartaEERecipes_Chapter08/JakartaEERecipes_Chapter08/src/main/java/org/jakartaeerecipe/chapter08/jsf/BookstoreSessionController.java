package org.jakartaeerecipe.chapter08.jsf;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.jakartaeerecipe.chapter08.session.BookstoreSessionCounter;

@Named("bookstoreSessionController")
@SessionScoped
public class BookstoreSessionController implements java.io.Serializable {

    @EJB
    BookstoreSessionCounter bookstoreSessionCounter;

    private boolean flag = false;
    private int counter;

    /**
     * @return the counter
     */
    public int getCounter() {
        if(!flag){
            counter = bookstoreSessionCounter.getNumberOfSessions();
            flag = true;
        }
        return counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }


}
