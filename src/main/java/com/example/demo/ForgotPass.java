package com.example.demo;
import javax.mail.*;
import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.Properties;
import java.util.Random;

public class ForgotPass {
        static final String host = "smtp.gmail.com";
        static final String username = "mywallet.exchanger@gmail.com";
        static final String password = "licmwgwyuunjovnz";

    public static String generateRandomPassword() {
        String characters = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {

            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));

        }

        return sb.toString();

    }

    public static void send (String emailAdress, String code){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAdress));
            message.setSubject("New Password");
            message.setText("Your new password is: " + code);

            Transport.send(message);
        }
        catch(MessagingException e){

            e.printStackTrace();

        }

    }

}
