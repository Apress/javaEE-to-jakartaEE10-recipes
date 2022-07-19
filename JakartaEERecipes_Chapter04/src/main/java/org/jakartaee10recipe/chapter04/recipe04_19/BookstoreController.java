package org.jakartaee10recipe.chapter04.recipe04_19;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Date;

@Named("bookstoreController")
@ApplicationScoped
public class BookstoreController {
    private Date dayAndTime = null;

    private int counter;

    @Inject
    @Push(channel="messagePusher")
    private PushContext push;

    private String messageText;

    /**
     * Creates a new instance of BookstoreController
     */
    public BookstoreController() {
        init();
    }

    public void init(){
        setCounter(5);
    }

    public void increaseCounter(){
        System.out.println("increasing the counter by 1 to " + counter);
        counter++;
    }

    /**
     * Initiates a notification to all Websocket clients.
     */
    public void sendMessage(){
        System.out.println("sending message: " + messageText);
        push.send(messageText);
        messageText = null;
    }

    /**
     * @return the dayAndTime
     */
    public Date getDayAndTime() {
        dayAndTime = new Date();
        return dayAndTime;
    }

    /**
     * @param dayAndTime the dayAndTime to set
     */
    public void setDayAndTime(Date dayAndTime) {
        this.dayAndTime = dayAndTime;
    }

    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * @return the messageText
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * @param messageText the messageText to set
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
