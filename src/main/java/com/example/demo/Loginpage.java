package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Loginpage implements Initializable{

    public static User user ;

    @FXML private PasswordField passField;

    @FXML private TextField usernameField ,captchaTxtField;

    @FXML private Button submitBtn;

    @FXML
    private Hyperlink signupBtn ;

    @FXML private ImageView captchaPhoto ;
    @Override
    public void initialize(URL location, ResourceBundle resources){

        reCaptchaMake(null);

        submitBtn.setOnMouseEntered(e -> submitBtn.setStyle("-fx-background-color: #6e038c; -fx-background-radius: 10;"));
        submitBtn.setOnMouseExited(e -> submitBtn.setStyle("-fx-background-color:  #7707b8; -fx-background-radius: 10;"));

    }

    public void reCaptchaMake(MouseEvent mouseEvent) {
        CaptchaMaker.captcha();

        File img = new File("src\\main\\resources\\com\\example\\demo\\images\\captcha.jpg");
        InputStream isImage = null;
        try {
            isImage = (InputStream) new FileInputStream(img);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        captchaPhoto.setImage(new Image(isImage));
    }

    public void goToSignUpPage(ActionEvent actionEvent) {
        try {
            Stage stage1 = (Stage) signupBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));

            Scene signupScene = new Scene(root ) ;

            stage1.setScene(signupScene);
            stage1.centerOnScreen();
            stage1.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void forgotPassAction (ActionEvent actionEvent) {
        try {
            Stage stage1 = (Stage) signupBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("ForgotPass.fxml"));

            Scene signupScene = new Scene(root ) ;

            stage1.setScene(signupScene);
            stage1.centerOnScreen();
            stage1.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkuser(ActionEvent actionEvent){

        String url = "jdbc:mysql://localhost:3306/exchangedb";
        String Username = "root";
        String Password = "123456";

        try (Connection conn = DriverManager.getConnection(url, Username, Password)){
            String query = "SELECT * FROM user WHERE username = '" + usernameField.getText() + "' OR password = '" + passField.getText() + "'";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String phonenumber = resultSet.getString("phonenumber");
                String password = resultSet.getString("password");

                user =new User(firstname , lastname , username , email , phonenumber , password);
            }
            if( user != null && captchaTxtField.getText().equals(CaptchaMaker.CAPTCHA)) {
                try {
                    Stage stage1 = (Stage) signupBtn.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

                    Scene dasboardScene = new Scene(root);

                    stage1.setScene(dasboardScene);
                    stage1.centerOnScreen();
                    stage1.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

}