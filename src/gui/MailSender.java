/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static java.lang.ProcessBuilder.Redirect.to;
import java.util.Properties;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender extends Application {
    public static void sendMail(String toEmail) { 
        System.out.println("toEmail  "+toEmail);
        String host = "soumaya.abderahmen@esprit.tn";  
        final String user = "soumaya.abderahmen@esprit.tn";
        final String password = "223JFT2119";

        String to = toEmail;//→ the EMAIL i want to send TO →

        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(" Nouveau Article ");
            message.setContent(
                    "<h1 style =\"color:red\" >Un nouveau article a été ajouée!  </h1> <br/> <img width=\"50%\" height=\"50%\" src=C:/Users/Islem/Documents/NetBeansProjects/Dawinii/src/images/logo-dawini.png>",
                    "text/html");

            Transport.send(message);
            System.out.println("message envoyé via mail ");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
