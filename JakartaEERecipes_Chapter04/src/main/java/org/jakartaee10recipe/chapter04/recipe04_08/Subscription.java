package org.jakartaee10recipe.chapter04.recipe04_08;

import java.util.ArrayList;
import java.util.List;

public class Subscription {
    private List<Contact> subscriptionList;

    public Subscription() {
        subscriptionList = new ArrayList<>();
    }

    public List<Contact> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Contact> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }
}

