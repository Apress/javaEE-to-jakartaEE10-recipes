/*
 * Converts text into XML
 */
package org.jakartaeerecipes.chapter13.recipe13_02;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juneau
 */
@XmlRootElement(name="message")
public class MessageWrapper {
    private String message;
    
    public MessageWrapper(){
        
    }
    
    public MessageWrapper(String message){
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
