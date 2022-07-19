package org.jakartaeerecipe.chapter12.recipe12_06;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.jms.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "recipe12_06")
@ApplicationScoped
public class Recipe12_06 {

    @Resource(name = "jms/jakartaEERecipeConnectionFactory")
    private ConnectionFactory connectionFactory;

    private Queue myQueue;
    private Connection connection;
    private String displayMessage = "No messages as yet...";

    /**
     * Creates a new instance of Example12_06
     */
    public Recipe12_06() {
    }

    public void createConnection() {
        try {
            connection = connectionFactory.createConnection();

        } catch (JMSException e) {
            Logger.getLogger(Recipe12_06.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException e) {
            Logger.getLogger(Recipe12_06.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private Context getContext() throws NamingException {
        Context jndiContext = new InitialContext();
        return jndiContext;

    }

    public void browseMessages() {

        try(Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueBrowser browser = session.createBrowser(myQueue);) {

            Enumeration msgs = browser.getEnumeration();

            if(!msgs.hasMoreElements()){
                System.out.println("No more messages within the queue...");
            } else {
                while(msgs.hasMoreElements()){
                    Message currMsg = (Message)msgs.nextElement();
                    setDisplayMessage(currMsg.getJMSMessageID());
                    System.out.println("Message ID: " + currMsg.getJMSMessageID());
                }
            }

        } catch (JMSException e) {
            Logger.getLogger(Recipe12_06.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * @return the displayMessage
     */
    public String getDisplayMessage() {
        return displayMessage;
    }

    /**
     * @param displayMessage the displayMessage to set
     */
    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}
