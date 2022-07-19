package org.jakartaeerecipe.chapter12.recipe12_04;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.jms.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value="recipe12_04")
@ApplicationScoped
public class Recipe12_04 {

    @Resource(name = "jms/jakartaEERecipesConnectionFactory")
    private ConnectionFactory connectionFactory;

    // @Resource(name = "jms/jakartaEERecipeQueue")
    private Queue myQueue;
    private Connection connection;
    private String displayMessage = "No messages as yet...";

    public void createConnection() {
        try {
            connection = connectionFactory.createConnection();

        } catch (JMSException e) {
            Logger.getLogger(Recipe12_04.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(Recipe12_04.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Context getContext() throws NamingException {
        Context jndiContext = new InitialContext();
        return jndiContext;
    }

    /**
     * Receive message using the standard JMS API
     */
    public void receiveMessage() {
        boolean stopReceivingMessages = false;
        if (connection == null) {
            createConnection();
        }
        try (Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
            createConnection();
            System.out.println("Creating session to receive message");

            myQueue = (Queue) getContext().lookup("jms/jakartaEERecipeQueue");
            System.out.println("Setting up consumer");
            try (MessageConsumer consumer = session.createConsumer(myQueue)) {
                connection.start();

                while (!stopReceivingMessages) {
                    System.out.println("Receiving message");
                    Message inMessage = consumer.receive();
                    if (inMessage != null) {
                        System.out.println(inMessage);
                        if (inMessage instanceof TextMessage) {
                            String messageStr = ((TextMessage) inMessage).getText();
                            System.out.println(messageStr);
                            setDisplayMessage(messageStr);
                        } else {
                            System.out.println("Message was of another type");
                            setDisplayMessage("Message was of another type");
                        }
                    } else {
                        stopReceivingMessages = true;
                    }
                }
                connection.stop();
            }

        } catch (NamingException | JMSException e) {
            Logger.getLogger(Recipe12_04.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (connection != null) {
                closeConnection();
            }
        }
    }

    /**
     * Receive a message using the new JMS API
     */
    public String receiveMessageNew() {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSConsumer consumer = context.createConsumer(myQueue);
            return consumer.receiveBody(String.class);
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
