package org.jakartaeerecipe.chapter11.recipe11_05;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class ProducerExample implements Serializable {
    @Inject
    @InitValue
    private int initial;
    private int orderList = -1;
    private ProducerExample() {

    }
    /**
     * @return the orderList
     */
    public int getOrderList() {
        if (orderList == -1)
            orderList = initial;
        return orderList;
    }

    public void addItem(){
        setOrderList(getOrderList() + 1);
    }
    public void removeItem(){
        setOrderList(getOrderList() - 1);
    }

    /**
     * @param orderList the orderList to set
     */
    public void setOrderList(int orderList) {
        this.orderList = orderList;
    }

}
