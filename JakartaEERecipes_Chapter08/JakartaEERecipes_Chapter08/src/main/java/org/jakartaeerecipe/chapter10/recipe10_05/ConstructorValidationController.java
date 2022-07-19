package org.jakartaeerecipe.chapter10.recipe10_05;

import jakarta.validation.constraints.NotNull;

public class ConstructorValidationController {

    private String p1;
    private String p2;

    @NotNull
    public ConstructorValidationController(String parameterOne,
                                           String parameterTwo){
        this.p1 = parameterOne;
        this.p2 = parameterTwo;
    }

    /**
     * @return the p1
     */
    public String getP1() {
        return p1;
    }

    /**
     * @param p1 the p1 to set
     */
    public void setP1(String p1) {
        this.p1 = p1;
    }

    /**
     * @return the p2
     */
    public String getP2() {
        return p2;
    }

    /**
     * @param p2 the p2 to set
     */
    public void setP2(String p2) {
        this.p2 = p2;
    }
}
