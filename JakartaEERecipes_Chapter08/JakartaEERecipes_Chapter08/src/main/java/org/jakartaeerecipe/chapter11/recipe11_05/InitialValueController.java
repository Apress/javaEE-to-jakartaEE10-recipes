package org.jakartaeerecipe.chapter11.recipe11_05;

import jakarta.enterprise.inject.Produces;

public class InitialValueController {
    @Produces
    @InitValue
    public int initialValue = 1000;
}
