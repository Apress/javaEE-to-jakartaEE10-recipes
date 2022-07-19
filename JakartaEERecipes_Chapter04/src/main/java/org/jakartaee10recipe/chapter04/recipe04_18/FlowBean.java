package org.jakartaee10recipe.chapter04.recipe04_18;

import jakarta.faces.flow.FlowScoped;
import jakarta.inject.Named;

@Named
@FlowScoped("exampleFlow")
public class FlowBean implements java.io.Serializable {

    private String flowValue;
    private String parameter1;
    /**
     * Creates a new instance of FlowBean
     */
    public FlowBean() {
    }

    public void initializeIt(){
        System.out.println("Initialize the flow...");
    }

    public void finalizeIt(){
        System.out.println("Finalize the flow...");
    }


    public String navMethod(){
        return "intermediateFlow";
    }

    public String testMethod(){
        return "intermediate";
    }

    public String endFlow(){
        return "endingFlow";
    }

    /**
     * @return the flowValue
     */
    public String getFlowValue() {
        return flowValue;
    }

    /**
     * @param flowValue the flowValue to set
     */
    public void setFlowValue(String flowValue) {
        this.flowValue = flowValue;
    }

    /**
     * @return the parameter1
     */
    public String getParameter1() {
        return parameter1;
    }

    /**
     * @param parameter1 the parameter1 to set
     */
    public void setParameter1(String parameter1) {
        this.parameter1 = parameter1;
    }
}
