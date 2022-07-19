package org.jakartaeerecipe.chapter03.recipe03_10;

import jakarta.enterprise.context.RequestScoped;
import jdk.jfr.Name;

@Name(value = "evaluationController")
@RequestScoped
public class EvaluationController {
    private boolean field1 = true;
    /**
     * Creates a new instance of EvaluationController
     */
    public EvaluationController() {
    }
    public boolean expr1(){
        return true;
    }
    public boolean expr2(){
        return false;
    }
    /**
     * @return the field1
     */
    public boolean isField1() {
        return field1;
    }
    /**
     * @param field1 the field1 to set
     */
    public void setField1(boolean field1) {
        this.field1 = field1;
    }

}
