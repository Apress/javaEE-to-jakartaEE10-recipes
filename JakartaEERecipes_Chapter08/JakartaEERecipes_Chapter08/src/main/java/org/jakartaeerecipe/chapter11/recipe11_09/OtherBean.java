package org.jakartaeerecipe.chapter11.recipe11_09;

import org.jakartaeerecipe.chapter11.Order;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.inject.Named;

@Named("OtherBean")
public class OtherBean {

    // @Inject
    Bean<Order> bean;

    void testMethod() {
        bean.getName();
        bean.getBeanClass();
        bean.getInjectionPoints();
        bean.getQualifiers();
        bean.getScope();
        bean.isAlternative();
    }
}
