package edu.epam.auth.util;

import edu.epam.auth.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;


public class MailSender{

    private static final Logger logger = LogManager.getLogger(MailSender.class);

    private static final String ACTIVATION_MESSAGE = "Hello. You registered on site. Please, go to this link for ending registration.";

    private static final MailSender instance = new MailSender();

    private final Properties properties;
    private String userName;
    private String password;

    private MailSender() {
        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("mail.properties"));

        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
        userName = properties.getProperty("mail.user.name");
        password = properties.getProperty("mail.user.password");
    }

    public static MailSender getInstance() {
        return instance;
    }

    public void send(String to, String subject, String text) {
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendActivationMail(User user) {
        String message = ACTIVATION_MESSAGE + "\n" +
                "http://localhost:8080/auth/main?action=activation&" + "login=" + user.getLogin() +
                "&activationKey=" + user.getActivationKey();
        String subject = "Registration on site";
        send(user.getEmail(), subject, message);
    }

}
