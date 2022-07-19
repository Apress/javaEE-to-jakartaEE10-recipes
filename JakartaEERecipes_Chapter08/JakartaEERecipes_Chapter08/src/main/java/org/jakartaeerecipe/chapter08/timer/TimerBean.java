package org.jakartaeerecipe.chapter08.timer;

import jakarta.inject.Singleton;
import jakarta.ejb.Schedule;

@Singleton
public class TimerBean {

    @Schedule(minute="*/5", hour="*")
    public void automaticTimer(){
        System.out.println("*** Automatic Timeout Occurred ***");
    }
}
