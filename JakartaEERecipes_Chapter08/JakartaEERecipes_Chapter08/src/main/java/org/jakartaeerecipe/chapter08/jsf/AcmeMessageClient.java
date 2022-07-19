package org.jakartaeerecipe.chapter08.jsf;

import jakarta.annotation.Resource;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.jms.*;

@Named
public class AcmeMessageClient implements java.io.Serializable {
    // Uncomment after the jms/javaeeRecipesConnectionFactory JMS resource has
    // been created within the application server container (Recipe 14-1)

    @Resource(mappedName = "jms/jakartaEERecipeConnectionFactory")
    private ConnectionFactory connectionFactory;
    // Uncomment after the jms/Queue JMS resource has been created within the
    // application server container (Recipe 14-1)

    @Resource(mappedName = "jms/jakartaEERecipeQueue")
    private Queue queue;

    public void sendMessage() {

        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();

            message.setText("This is a test message");
            System.out.println("Sending message: " + message.getText());
            messageProducer.send(message);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Sent", null);

            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (jakarta.jms.JMSException ex) {
            System.out.println(ex);
        }
    }
}
