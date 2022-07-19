
package org.jakartaeerecipe.chapter03.recipe03_04;

import java.util.Date;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named
@SessionScoped
public class MessageController implements java.io.Serializable {
    int hitCounter = 0;
    private String javaText;

    /**
     * Creates a new instance of MessageController
     */
    public MessageController() {
        javaText = null;
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Managed Bean Initialized", null);
        
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    public void newMessage(){
        String hitMessage = null;
        hitCounter++;
        if(hitCounter > 1){
            hitMessage = hitCounter + " times";
        } else {
            hitMessage = hitCounter + " time";
        }
        
        Date currDate = new Date();
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "You've pressed that button " + hitMessage + "!  The current date and time: " 
                + currDate, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        
        if (getJavaText().equalsIgnoreCase("java")){
            FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Good Job, that is the correct text!", null);
            FacesContext.getCurrentInstance().addMessage("componentForm:javaText", javaTextMsg);
        } else {
            FacesMessage javaTextMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Sorry, that is NOT the correct text!", null);
            FacesContext.getCurrentInstance().addMessage("componentForm:javaText", javaTextMsg);
        }
    }

    /**
     * @return the javaText
     */
    public String getJavaText() {
        return javaText;
    }

    /**
     * @param javaText the javaText to set
     */
    public void setJavaText(String javaText) {
        this.javaText = javaText;
    }
}
