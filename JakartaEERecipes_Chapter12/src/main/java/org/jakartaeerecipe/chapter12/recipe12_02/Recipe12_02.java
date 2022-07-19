package org.jakartaeerecipe.chapter12.recipe12_02;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Session;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "recipe12_02")
@SessionScoped
public class Recipe12_02 implements Serializable {

    @Resource(name = "jms/jakartaEERecipesConnectionFactory")
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private String connectionString;

    @PostConstruct
    public void createConnection() {
        try {
            connection = connectionFactory.createConnection();

        } catch (JMSException ex) {
            Logger.getLogger(Recipe12_02.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(Recipe12_02.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createSession() {
        try {
            if (connection != null) {
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                session.close();
                setConnectionString("Session successfully created");
            } else {
                setConnectionString("No connection available");
            }

        } catch (JMSException ex) {
            Logger.getLogger(Recipe12_02.class.getName()).log(Level.SEVERE, null, ex);
            setConnectionString("Session not created");
        }

    }

    /**
     * @return the connectionString
     */
    public String getConnectionString() {
        createSession();
        return connectionString;
    }

    /**
     * @param connectionString the connectionString to set
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

}

