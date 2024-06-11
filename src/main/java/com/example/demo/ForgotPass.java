package com.example.demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Random;

public class ForgotPass {
        static final String host = "smtp.gmail.com";
        static final String username = "mywallet.exchanger@gmail.com";
        static final String password = "licmwgwyuunjovnz";

        private static String recoveryCode ;

    public static String generateRandomPassword() {
        String characters = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {

            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));

        }

        recoveryCode = sb.toString();
        return recoveryCode ;
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
            message.setSubject("change Password");
            message.setText("Your verification code : " + code);

            Transport.send(message);
        }
        catch(MessagingException e){

            e.printStackTrace();

        }

    }
    @FXML
    private TextField EmailField;
    @FXML
    private AnchorPane panel1;

    @FXML
    private AnchorPane panel2;
    @FXML
    private AnchorPane panel3;
    @FXML
    private AnchorPane panel4;

    @FXML
    void backLoginAction(ActionEvent event) {
        try {
            Stage stage1 = (Stage) EmailField.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Loginpage.fxml"));

            Scene signupScene = new Scene(root) ;

            stage1.setScene(signupScene);
            stage1.centerOnScreen();
            stage1.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkEmailValid () {
        if (User.checkEmail(EmailField.getText())){
            EmailField.setStyle("-fx-background-radius: 5;");
            return true ;
        }
        else {
            EmailField.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: red;");
            return false ;
        }
    }

    @FXML
    private TextField recoveryField;

    @FXML
    void changeEmail(ActionEvent event) {
        panel2.setVisible(false);
        panel1.setVisible(true);
    }

    @FXML
    void cheakCode(ActionEvent event) {
        if (recoveryCode.equals(recoveryField.getText())){
            panel2.setVisible(false);
            panel3.setVisible(true);
            recoveryField.setStyle("-fx-background-color: white; -fx-background-radius: 5;");
        }
        else {
            recoveryField.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-border-color: red ; -fx-border-radius: 5");
        }
    }

    @FXML
    void resentMassage(ActionEvent event) {
        send(EmailField.getText(),generateRandomPassword());
    }

    public void sentMailBtn(ActionEvent actionEvent) {
        String url = "jdbc:mysql://localhost:3306/exchangedb";
        String Username = "root";
        String Password = "123456";

        if (!checkEmailValid()) return ;
        try (Connection connection = DriverManager.getConnection(url, Username, Password)) {
            String sql = "SELECT * FROM user WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, EmailField.getText());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
//                    txtName.setText(resultSet.getString("firstname"));
//                    txtLastName.setText(resultSet.getString("lastname"));
//                    txtPhoneNumber.setText(resultSet.getString("phonenumber"));
//                    txtEmail.setText(resultSet.getString("email"));
//                    txtPassword.setText(resultSet.getString("password"));
//                    txtWallet_id.setText(resultSet.getString("walletid"));
//                    txtUsername.setText(thisUsername);
                    send(EmailField.getText(),generateRandomPassword());
                    panel1.setVisible(false);
                    panel2.setVisible(true);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private PasswordField conPassFlied;

    @FXML
    private PasswordField passFlied;

    public void resPassBtn(ActionEvent actionEvent) {
        System.out.println(passFlied.getText());
        System.out.println(conPassFlied.getText());
        if (User.checkPass(passFlied.getText())) {
            passFlied.setStyle("-fx-background-color: white ; -fx-background-radius: 5 ;");
            if (passFlied.getText().equals(conPassFlied.getText())) {
                panel3.setVisible(false);
                panel4.setVisible(true);
            }
        } else {
            passFlied.setStyle("-fx-background-color: white ; -fx-background-radius: 5 ; -fx-border-color: red ; -fx-border-radius: 5");
        }
    }
}
