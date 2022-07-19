package org.jakartaeerecipe.chapter11.recipe11_02;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("myBean")
@RequestScoped
public class CalculationBean implements Serializable {

    private int num1 = 0;
    private int num2 = 1;
    private int sum;
    public CalculationBean() { }

    public int getNum1() {
        return num1;
    }
    public void setNum1(int num1) {
        this.num1 = num1;
    }
    public int getNum2() {
        return num2;
    }
    public void setNum2(int num2) {
        this.num2 = num2;
    }
    public int getSum() {
        return sum;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }

    public void addNumbers() {
        System.out.println("addNumbers() called...");
        setSum(getNum1() + getNum2());
    }
}
