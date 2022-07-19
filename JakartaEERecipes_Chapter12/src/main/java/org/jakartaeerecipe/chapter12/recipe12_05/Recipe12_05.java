package org.jakartaeerecipe.chapter12.recipe12_05;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.jms.*;
import org.jakartaeerecipe.chapter12.recipe12_02.Recipe12_02;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "recipe12_05")
@ApplicationScoped
public class Recipe12_05 {
    @Resource(name = "jms/jakartaEERecipeConnectionFactory")
    private ConnectionFactory connectionFactory;

    private Queue myQueue;
    private Connection connection;
    private String displayMessage = "No messages as yet...";

    /**
     * Creates a new instance of Example12_06
     */
    public Recipe12_05() {
    }

    public void createConnection() {
        try {
            connection = connectionFactory.createConnection();

        } catch (JMSException ex) {
            Logger.getLogger(Recipe12_05.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException e) {
            Logger.getLogger(Recipe12_05.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private Context getContext() throws NamingException {
        Context jndiContext = new InitialContext();
        return jndiContext;

    }

    public void sendMessage1() {
        if (connection != null) {

            try {
                myQueue = (Queue) getContext().lookup("jms/jakartaEERecipeQueue");
            } catch (NamingException e) {
                Logger.getLogger(Recipe12_05.class.getName()).log(Level.SEVERE, null, e);
            }

            try (Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                 MessageProducer producer = session.createProducer(myQueue)) {

                System.out.println("Creating message");
                TextMessage message = session.createTextMessage();
                message.setText("Jakarta EE 10 Is the Best!");
                message.setStringProperty("TYPE", "JAKARTAEE");
                System.out.println("Sending message");
                producer.send(message);
            } catch (JMSException e) {
                Logger.getLogger(Recipe12_05.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void sendMessage2() {
        if (connection != null) {
            try (Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                 MessageProducer producer = session.createProducer(myQueue);) {

                System.out.println("Creating message");
                TextMessage message = session.createTextMessage();
                message.setText("Java SE 17 Is Great!");
                message.setStringProperty("TYPE", "JAVASE");
                System.out.println("Sending message");
                producer.send(message);

            } catch (JMSException e) {
                Logger.getLogger(Recipe12_05.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void receiveMessage() {
        boolean stopReceivingMessages = false;
        String selector = "TYPE = 'JAKARTAEE'";
        try(Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(myQueue, selector)) {

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

        } catch (JMSException e) {
            Logger.getLogger(Recipe12_05.class.getName()).log(Level.SEVERE, null, e);
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
