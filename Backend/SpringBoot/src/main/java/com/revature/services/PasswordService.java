package com.revature.services;

import com.revature.config.Configuration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Zimi Li
 */
public class PasswordService {

    public static String generatePassword() {
        return new Random().ints(Configuration.PASSWORD_LENGTH, 33, 122)
                .mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
    }

    public static void sendMail(String to, String username, String password) {
        String from = Configuration.EMAIL_USERNAME;
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Configuration.EMAIL_USERNAME, Configuration.EMAIL_PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Password Has Been Changed");
            message.setText(String.format("Your username is %s. Your new password is %s", username, password));
            Transport.send(message);
            Configuration.LOGGER.info("Sent message successfully to " + to);
            Configuration.LOGGER.info("Username: " + username);
            Configuration.LOGGER.info("Password: " + password);
        } catch (MessagingException e) {
            Configuration.LOGGER.error(e, e.fillInStackTrace());
        }
    }
}