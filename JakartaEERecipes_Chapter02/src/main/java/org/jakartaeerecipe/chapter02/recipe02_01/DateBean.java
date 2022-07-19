package org.jakartaeerecipe.chapter02.recipe02_01;

import java.util.Date;

public class DateBean {
    private Date currentDate = new Date();

    /**
     * @return the currentDate
     */
    public Date getCurrentDate() {
        return currentDate;
    }

    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}

