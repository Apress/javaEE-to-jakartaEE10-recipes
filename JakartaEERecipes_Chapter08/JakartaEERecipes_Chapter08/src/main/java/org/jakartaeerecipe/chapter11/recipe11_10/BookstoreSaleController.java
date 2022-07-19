package org.jakartaeerecipe.chapter11.recipe11_10;

import org.jakartaeerecipe.chapter11.event.BookEvent;
import org.jakartaeerecipe.chapter11.qualifier.OnlineSale;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Event;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class BookstoreSaleController implements Serializable {
    @Inject
    @OnlineSale
    private Event<BookEvent> onlineSaleEvent;

    private BookEvent currentEvent;

    public BookstoreSaleController() {

    }

    /**
     * Fires synchronous CDI event BookEvent.
     */
    public void onlineSaleAction() {
        List notifyList = new ArrayList();
        currentEvent.setNotifyList(notifyList);
        onlineSaleEvent.fire(currentEvent);
        currentEvent = null;
    }

    /**
     * Fires asynchronous CDI event BookEvent.
     */
    public void storeSaleAction() {
        List notifyList = new ArrayList();
        currentEvent.setNotifyList(notifyList);
        onlineSaleEvent.fireAsync(currentEvent)
                .whenComplete((event, throwable) -> {
                    if (throwable != null) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "FAIL", "Error has occurred " + throwable.getMessage()));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                                FacesMessage.SEVERITY_INFO, "SUCCESS", "Successful Brick-and-Mortor Store Sale Processing..."));
                        currentEvent = null;
                    }
                });
    }

    /**
     * @return the currentEvent
     */
    public BookEvent getCurrentEvent() {
        if(currentEvent == null){
            currentEvent = new BookEvent();
        }
        return currentEvent;
    }

    /**
     * @param currentEvent the currentEvent to set
     */
    public void setCurrentEvent(BookEvent currentEvent) {
        this.currentEvent = currentEvent;
    }
}
