package org.jakartaeerecipe.chapter02.recipe02_03;

import java.util.Random;

public class RandomBean {
    Random random = new Random();
    private int randomNumber = 0;
    /**
     * @return the randomNumber
     */
    public int getRandomNumber() {
        randomNumber = random.nextInt();
        return randomNumber;
    }

}
