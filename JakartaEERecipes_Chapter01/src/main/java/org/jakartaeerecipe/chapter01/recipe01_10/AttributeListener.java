package org.jakartaeerecipe.chapter01.recipe01_10;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebListener
public class AttributeListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    private ServletContext context = null;

    public AttributeListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        this.context = event.getServletContext();
        log("contextInitialized()");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
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
    public void attributeAdded(HttpSessionBindingEvent event) {
        /* This method is called when an attribute is added to a session. */
        HttpSession session = event.getSession();
        String id = session.getId();
        String name = event.getName();
        String value = (String) event.getValue();
        String message = new StringBuffer("New attribute has been added to session: ")
                .append(id).append("\n")
                .append("Attribute Name: ").append(name).append("\n")
                .append("Attribute Value:").append(value).toString();
        log(message);
    }

    private void log(String message) {
        if (context != null) {
            context.log("SessionListener: " + message);
        } else {
            System.out.println("SessionListener: " + message);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        /* This method is called when an attribute is removed from a session. */
        HttpSession session = event.getSession();
		String id = session.getId();
		String name = event.getName();
		if (name == null) {
			name = "Unknown";
		}
		String value = (String) event.getValue();
		String message = new StringBuffer("Attribute has been removed from session: ").append(id).append("\n")
				.append("Attribute Name: ").append(name).append("\n").append("Attribute Value: ").append(value)
				.toString();
		log(message);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        /* This method is called when an attribute is replaced in a session. */
        String name = event.getName();
		if (name == null) {
			name = "Unknown";
		}
		String message = new StringBuffer("Attribute has been replaced: ").append(name).toString();
		log(message);
    }
}
