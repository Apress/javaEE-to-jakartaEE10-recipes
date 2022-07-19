package org.jakartaeerecipe.chapter08.jsf;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;


@MessageDriven(mappedName="jms/jakartaEERecipeQueue", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "jakarta.jms.Queue")})
public class AcmeMessageBean implements MessageListener {
    public AcmeMessageBean(){

    }

    @Override
    public void onMessage(Message msg) {
        if(msg != null){
            performExtraProcessing();
            System.out.println("Message has been received: " + msg);
        } else {
            System.out.println("No message received");
        }
    }

    public void performExtraProcessing(){
        System.out.println("This method could perform extra processing");
    }
}
