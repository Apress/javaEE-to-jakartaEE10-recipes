package org.jakartaeerecipe.chapter15.recipe15_04;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStoreHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@Named
@RequestScoped
public class StandardizedAuthenticationController {
    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @NotNull
    private String username;

    @NotNull
    private String password;

    public void login() {
        System.out.println("in the login");
        FacesContext context = FacesContext.getCurrentInstance();
        Credential credential = new UsernamePasswordCredential(username, new Password("abcd1234"));

        CredentialValidationResult cres = identityStoreHandler.validate(credential);
        System.out.println("login status: " + cres.getStatus());
        System.out.println("user:" + username);
        System.out.println("password: " + password);
        if (cres.getStatus().equals(CredentialValidationResult.Status.VALID)) {
            // Authentication mechanism has send a redirect, should not
            // send anything to response from JSF now.
            context.responseComplete();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Authentication Successful", null));
        } else if (cres.getStatus().equals(CredentialValidationResult.Status.INVALID)) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentication Failure", null));
        }
    }

    private static HttpServletResponse getResponse(FacesContext context) {
        return (HttpServletResponse) context
                .getExternalContext()
                .getResponse();
    }

    private static HttpServletRequest getRequest(FacesContext context) {
        return (HttpServletRequest) context
                .getExternalContext()
                .getRequest();
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
