package org.jakartaeerecipe.chapter11.recipe11_02;

import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class Calculator {

    @Inject
    CalculationBean calcBean;

    public void performCalculation(){
        calcBean.addNumbers();
    }

    public int addNumbers(int[] arr) {
        int sum = 0;
        for (int i: arr) sum += i;
        return sum;
    }

}
