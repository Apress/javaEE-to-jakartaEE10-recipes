package org.jakartaeerecipe.chapter03.recipe03_05;

import java.util.ResourceBundle;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named(value="exampleController")
@RequestScoped
public class ExampleController {
    private String exampleProperty;
    /**
     * Creates a new instance of ExampleController
     */
    public ExampleController() {
        exampleProperty = "Used to instantiate the bean.";
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                ResourceBundle.getBundle("Bundle").getString("ExampleMessage"), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    /**
     * @return the exampleProperty
     */
    public String getExampleProperty() {
        return exampleProperty;
    }
    /**
     * @param exampleProperty the exampleProperty to set
     */
    public void setExampleProperty(String exampleProperty) {
        this.exampleProperty = exampleProperty;
    }
}

