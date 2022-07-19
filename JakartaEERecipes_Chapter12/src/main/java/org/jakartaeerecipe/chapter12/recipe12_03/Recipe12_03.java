package org.jakartaeerecipe.chapter12.recipe12_03;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.jms.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value="recipe12_03")
@SessionScoped
public class Recipe12_03 implements Serializable {

    @Resource(name = "jms/jakartaEERecipesConnectionFactory")
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private String connectionString;

    private Queue myQueue;

    // @Resource(name = "jms/myQueue")
    private Queue inboundQueue;

    @PostConstruct
    public void createConnection() {
        try {
            connection = connectionFactory.createConnection();

        } catch (JMSException ex) {
            Logger.getLogger(org.jakartaeerecipe.chapter12.recipe12_03.Recipe12_03.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException e) {
            Logger.getLogger(org.jakartaeerecipe.chapter12.recipe12_03.Recipe12_03.class.getName())
                    .log(Level.SEVERE, null, e);
        }
    }


    private Context getContext() throws NamingException {
        Context jndiContext = new InitialContext();
        return jndiContext;
    }

    /**
     * Sending a JMS Message using the Standard API
     */
    public void sendMessage() {
        if (connection != null) {
            System.out.println("Creating Session");
            try(Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            ) {
                myQueue = (Queue) getContext().lookup("jms/jakartaEERecipeQueue");
                MessageProducer producer = session.createProducer(myQueue);
                TextMessage message = session.createTextMessage();
                message.setText("Jakarta EE 10 Is the Best!");
                producer.send(message);
                producer.close();
                setConnectionString("Message Successfully Sent to Queue");
            } catch ( NamingException | JMSException e) {
                Logger.getLogger(org.jakartaeerecipe.chapter12.recipe12_03.Recipe12_03.class.getName())
                        .log(Level.SEVERE, null, e);
                setConnectionString("Session not created and message not sent");
            }
        } else {
            setConnectionString("No connection available");
        }
    }

    public String sendMessageNew() {
        String response = "Message Not Sent...";
        try (JMSContext context = connectionFactory.createContext()) {
            inboundQueue = (Queue) getContext().lookup("jms/jakartaEERecipeQueue");
            StringBuilder message = new StringBuilder();
            message.append("Jakarta EE 10 Is the Best!");
            context.createProducer().send(inboundQueue, message.toString());
            response = "Message Sent...";
        } catch (NamingException e) {
            Logger.getLogger(org.jakartaeerecipe.chapter12.recipe12_03.Recipe12_03.class.getName())
                    .log(Level.SEVERE, null, e);
        }
        return response;
    }

    /**
     * @return the connectionString
     */
    public String getConnectionString() {
        sendMessage();
        return connectionString;
    }

    /**
     * @param connectionString the connectionString to set
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
}

