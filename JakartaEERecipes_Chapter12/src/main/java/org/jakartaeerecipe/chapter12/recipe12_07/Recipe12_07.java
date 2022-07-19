package org.jakartaeerecipe.chapter12.recipe12_07;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.jms.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value="recipe12_07")
@ApplicationScoped
public class Recipe12_07 {
    @Resource(name = "jms/jakartaEERecipeConnectionFactory")
    private TopicConnectionFactory connectionFactory;
    private Topic myTopic;
    private TopicConnection connection;
    private String displayMessage = "No messages as yet...";

    public Recipe12_07(){

    }

    public void createConnection() {
        try {
            connection = (TopicConnection) connectionFactory.createConnection();
            connection.setClientID("durable");
        } catch (JMSException e) {
            Logger.getLogger(Recipe12_07.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(Recipe12_07.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Context getContext() throws NamingException {
        Context jndiContext = new InitialContext();
        return jndiContext;

    }

    public void createTopicSubscriber(){
        try {
            createConnection();
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            myTopic = (Topic) getContext().lookup("jms/MyTopic");
            TopicSubscriber subscriber = session.createDurableSubscriber(myTopic, "jakartaEERecipesSub");
            connection.close();
        } catch (NamingException | JMSException ex) {
            Logger.getLogger(Recipe12_07.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveMessage() {
        boolean stopReceivingMessages = false;
        try {
            createConnection();
            System.out.println("Creating session to receive message");
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            myTopic = (Topic) getContext().lookup("jms/myTopic");
            System.out.println("Setting up consumer");

            String selector = "TYPE = 'JAKARTAEE'";
            TopicSubscriber subscriber = session.createDurableSubscriber(myTopic, "jakartaEERecipesSub");
            connection.start();

            while (!stopReceivingMessages) {
                System.out.println("Receiving message");
                Message inMessage = subscriber.receive();
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
            subscriber.close();

            session.close();
            closeConnection();
        } catch (NamingException | JMSException ex) {
            Logger.getLogger(Recipe12_07.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage() {
        try {
            createConnection();
            System.out.println("Creating session");
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            System.out.println("Creating message");
            TextMessage message  = session.createTextMessage();
            message.setText("Java EE 7 Is the Best!");
            message.setStringProperty("TYPE", "JAKARTAEE");

            System.out.println("Creating producer");
            myTopic = (Topic) getContext().lookup("jms/jakartaEERecipesTopic");
            TopicPublisher publisher = session.createPublisher(myTopic);
            publisher.setDeliveryDelay(1000);
            System.out.println("Sending message");
            publisher.publish(message);

            System.out.println("Message sent, closing session");
            publisher.close();
            session.close();
            connection.close();

        } catch (NamingException | JMSException ex) {
            Logger.getLogger(Recipe12_07.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void unsubscribe(){
        try {
            createConnection();
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            session.unsubscribe("jakartaEERecipesSub");
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(Recipe12_07.class.getName()).log(Level.SEVERE, null, ex);
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
