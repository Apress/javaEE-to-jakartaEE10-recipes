package org.jakartaeerecipe.chapter11.recipe11_12;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import org.jakartaeerecipe.chapter11.recipe11_02.Calculator;

public class BootstrapExample {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        try (SeContainer container = initializer.initialize()) {
            /**
             * work with Jakarta CDI
             */
            Calculator calculator = container.select(Calculator.class).get();
            int sum = calculator.addNumbers(new int[]{1, 2, 3, 4});
            System.out.println(sum);
        }
    }
}

