package org.jakartaeerecipe.chapter01.recipe01_07;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Properties;

@WebListener
public class StartupShutdownListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    private final String TAG = StartupShutdownListener.class.getSimpleName() + ": ";
    public StartupShutdownListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        System.out.println(TAG +  "Servlet startup...");
        System.out.println(TAG + event.getServletContext().getServerInfo());
        System.out.println(TAG + System.currentTimeMillis());

        sendEmail("Servlet context has initialized");
    }

    private void sendEmail(String servlet_context_has_initialized) {
        String smtpHost = "mail.smtp2go.com"; // for e.g smtp.smtp2go.com
        String smtpUsername = "tarun";
        String smtpPassword = "abcd1234";
        String from = "hello@taruntelang.me";
        String to = "tarun.telang@gmail.com";
        int smtpPort = 2525;

        System.out.println(TAG +  "sending email...");
        try {
            // Send email here

            //Set the host smtp address
            Properties props = new Properties();
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "false");

            // create some properties and get the default Session
            Session session = Session.getInstance(props);

            // create a message
            Message msg = new MimeMessage(session);

            // set the from and to address
            InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);
            InternetAddress[] address = new InternetAddress[1];
            address[0] = new InternetAddress(to);
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("Servlet container shutting down");

            // Append Footer
            msg.setContent(servlet_context_has_initialized, "text/plain");
            Transport transport = session.getTransport("smtp");
            transport.connect(smtpHost, smtpPort, smtpUsername, smtpPassword);
            Transport.send(msg, smtpUsername, smtpPassword);
        } catch (MessagingException e) {
            System.out.println(TAG +  e);
        }
        System.out.println(TAG +  "email sent successfully...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        System.out.println(TAG +  "Servlet shutdown...");
        System.out.println(TAG +  event.getServletContext().getServerInfo());
        System.out.println(TAG + System.currentTimeMillis());

        // See error in server.log if mail is unsuccessful
        sendEmail("Servlet context has been destroyed...");

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
