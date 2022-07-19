package org.jakartaeerecipe.chapter11.interceptor;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

@Interceptor
@Notified
public class NotificationIntercepter {

    @AroundInvoke
    public Object emailNotification(InvocationContext ctx) throws Exception {
        String smtpServer = "mysmtpserver.com";
        String email = "publisherEmail@publisher.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpServer);
        Session session = Session.getInstance(props, null);
        sendEmail(session,
                email,
                "Method invocation",
                "Entering method: " + ctx.getMethod().getName());

        return ctx.proceed();
    }

    protected void sendEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@javaee8recipes.com", "NoReply"));

            msg.setReplyTo(InternetAddress.parse("no_reply@javaee8recipes.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            Transport.send(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
