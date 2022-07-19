package org.jakartaeerecipe.chapter03.recipe03_02;

import java.io.Serializable;
import java.util.*;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.inject.Named;

@Named
@SessionScoped
public class CalculationController implements Serializable {

    private int num1, num2;
    private int result;

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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(String calculationType) {
        this.calculationType = calculationType;
    }

    public List<SelectItem> getCalculationList() {
        return calculationList;
    }

    private String calculationType;

    private static final String ADDITION = "Addition";
    private static final String SUBTRACTION = "Subtraction";
    private static final String MULTIPLICATION = "Multiplication";
    private static final String DIVISION = "Division";

    List<SelectItem> calculationList;

    /**
     * Creates a new instance of CalculationController
     */
    public CalculationController() {

        // Initialize variables
        num1 = num2 = result = 0;
        calculationType = null;

        // Initialize the list of values for the SelectOneMenu
        populateCalculationList();
        System.out.println("initialized the bean!");
    }

    private void populateCalculationList(){
        calculationList = new ArrayList<>();
        calculationList.add(new SelectItem(ADDITION));
        calculationList.add(new SelectItem(SUBTRACTION));
        calculationList.add(new SelectItem(MULTIPLICATION));
        calculationList.add(new SelectItem(DIVISION));
    }

    public void performCalculation() {
        switch (getCalculationType()) {
            case ADDITION -> setResult(num1 + num2);
            case SUBTRACTION -> setResult(num1 - num2);
            case MULTIPLICATION -> setResult(num1 * num2);
            case DIVISION -> {
                try {
                    setResult(num1 / num2);
                } catch (Exception ex) {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Calculation", "Invalid Calculation");
                    FacesContext.getCurrentInstance().addMessage(null, facesMsg);
                }
            }
        }
    }
}


