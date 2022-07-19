package org.jakartaeerecipe.chapter02.recipe02_12;

import java.io.Serializable;

public class Author implements Serializable {
    private int id;
    private String first;
    private String last;

    public Author(){
        id = -1;
        first = null;
        last = null;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the first
     */
    public String getFirst() {
        return first;
    }
    /**
     * @param first the first to set
     */
    public void setFirst(String first) {
        this.first = first;
    }
    /**
     * @return the last
     */
    public String getLast() {
        return last;
    }
    /**
     * @param last the last to set
     */
    public void setLast(String last) {
        this.last = last;
    }

}
