package org.jakartaeerecipe.chapter15.recipe15_03;

import jakarta.ejb.Remove;
import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;

@Stateless
public class AuthenticationBean implements Serializable {

        @PersistenceContext(unitName = "JakartaEERecipes_PU")
        private EntityManager em;

        private boolean authenticated = false;
        private String username = null;
        private String password = null;
        HttpSession session = null;
        User user;

        public void findUser() {
                try {
                        em.flush();
                        getUser();
                        Query userQry = em.createQuery(
                                "select object(u) from User u "
                                        + "where u.username = :username").setParameter("username", getUser().getUsername().toUpperCase());

                        // Enable forced database query
                        userQry.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
                        setUser((User) userQry.getSingleResult());

                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Authenticated", ""));
                } catch (Exception e) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username/password", ""));
                        setUser(null);
                }

        }

        public HttpSession getSession() {
                FacesContext context = FacesContext.getCurrentInstance();
                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
                session = request.getSession(false);
                return session;
        }

        public boolean login() {

                HttpSession session = getSession();
                HttpServletRequest request = null;
                Query userQry = null;
                System.out.println("In the login method..." + getUser().getUsername());
                try {
                        FacesContext context = FacesContext.getCurrentInstance();
                        request = (HttpServletRequest) context.getExternalContext().getRequest();
                        request.login(getUser().getUsername(), this.password);

                        session.setMaxInactiveInterval(1800);
                        session.setAttribute("authenticated", new Boolean(true));

                        em.flush();

                        userQry = em.createQuery(
                                "select count(u) from User u "
                                        + "where u.username = :username").setParameter("username", getUser().getUsername().toUpperCase());
                        userQry.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
                        Long count = (Long)userQry.getSingleResult();
                        if (count > 0){

                                userQry = em.createQuery(
                                        "select object(u) from User u "
                                                + "where u.username = :username").setParameter("username", getUser().getUsername().toUpperCase());

                                // Enable forced database query
                                userQry.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
                                setUser((User) userQry.getSingleResult());
                                System.out.println("Setting  User, user exists in database with role ->" + user.getSecurityRole());
                                setAuthenticated(true);
                        } else {
                                // User cannot authenticate successfully...do something
                        }



                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Authenticated", ""));



                        return authenticated;
                } catch (NoResultException | ServletException ex) {
                        setUser(null);
                        setAuthenticated(false);
                        session = getSession();
                        session.setAttribute("authenticated", new Boolean(false));
                        if(request != null){
                                try {
                                        request.logout();
                                } catch (ServletException ex1) {
                                        System.out.println("AuthBean#login Error: " + ex);
                                }
                        }
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username/password", ""));
                        return false;

                } finally {
                        setPassword(null);
                }
        }

        public EntityManager getEm() {
                return em;
        }

        public void setEm(EntityManager em) {
                this.em = em;
        }

        /**
         * @return the isAuthenticated
         */
        public boolean isAuthenticated() {

                if (getSession().getAttribute("authenticated") != null) {
                        boolean auth = (Boolean) getSession().getAttribute("authenticated");
                        if (auth) {
                                authenticated = true;
                        }
                } else {
                        authenticated = false;
                }
                return authenticated;
        }

        public void setAuthenticated(boolean authenticated) {
                this.authenticated = authenticated;
        }

        public String getUsername() {
                try {
                        System.out.println("The current username is: " + user.getUsername());
                        username = getUser().getUsername();
                } catch (NullPointerException ex) {
                }
                return username;
        }

        public void setUsername(String username) {
                getUser().setUsername(username);
                System.out.println("Just set the username to : " + getUser().getUsername());
                this.username = null;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public void setSession(HttpSession session) {
                this.session = session;
        }

        public User getUser() {
                if (this.user == null) {
                        user = new User();
                }
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        @Remove
        public void remove() {
                System.out.println("Being removed from session...");
                setUser(null);
        }
}
