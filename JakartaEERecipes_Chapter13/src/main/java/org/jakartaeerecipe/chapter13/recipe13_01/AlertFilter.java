package org.jakartaeerecipe.chapter13.recipe13_01;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
//import org.jakartaeerecipe.chapter13.rest.interfaces.Alerter;


@Provider
//@Alerter
public class AlertFilter implements ContainerRequestFilter,
        ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext)
            throws IOException {
        alert(requestContext);
    }
    @Override
    public void filter(ContainerRequestContext crc, ContainerResponseContext crc1) throws IOException {
        alert(crc);
    }
    public void alert(ContainerRequestContext context) {
        try(InputStream in = context.getEntityStream();) {
            if (in != null) {
                InputStreamReader inreader = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(inreader);
                String text = "";
                while ((text = reader.readLine()) != null) {
                    System.out.println(text);
                }
            }
        } catch (IOException ex) {
            // Error handling
        }
    }
}

