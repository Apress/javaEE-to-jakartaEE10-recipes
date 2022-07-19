package org.jakartaee10recipe.chapter04.recipe04_16;


import jakarta.faces.application.Application;
import jakarta.faces.event.*;

public class BookstoreAppListener implements SystemEventListener {
    @Override
    public void processEvent(SystemEvent systemEvent) throws AbortProcessingException {
        if (systemEvent instanceof PostConstructApplicationEvent){
            System.out.println("The application has been constructed...");
        }
        if (systemEvent instanceof PreDestroyApplicationEvent){
            System.out.println("The application is being destroyed...");
        }
    }

    @Override
    public boolean isListenerForSource(Object o) {
        return (o instanceof Application);
    }
}
