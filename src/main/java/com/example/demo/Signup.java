package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.awt.*;
import java.io.*;
import java.util.ResourceBundle;

public class Signup implements Initializable {

    @FXML
    private TextField captcha;

    @FXML
    private ImageView captchaPhoto;

    @FXML
    private TextField email;

    @FXML
    private TextField lastname;

    @FXML
    private TextField name;

    @FXML
    private TextField pass;

    @FXML
    private TextField phonenumber;

    @FXML
    private TextField repass;

    @FXML
    private TextField Username;

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

    public void goTOLoginPage(ActionEvent actionEvent) {
        try {
            Stage stage1 = (Stage) captchaPhoto.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));

            Scene signupScene = new Scene(root ) ;

            stage1.setScene(signupScene);
            stage1.centerOnScreen();
            stage1.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUser(ActionEvent actionEvent){

        if(User.checkEmail(email.getText())){
            if(User.checkUserName(Username.getText())){
                if(User.checkName(name.getText())){
                    if(User.checkPhoneNum(phonenumber.getText())){
                        if(User.checkPass(pass.getText())){
                            if(pass.getText().equals(repass.getText())){

                                if (captcha.getText().equals(CaptchaMaker.CAPTCHA)) {
                                    String url = "jdbc:mysql://localhost:3306/exchangedb";
                                    String username = "root";
                                    String password = "123456";

                                    try (Connection conn = DriverManager.getConnection(url, username, password)) {

                                        String sql = "INSERT INTO user (firstname, lastname, username, email, phonenumber, password) VALUES (?, ?, ?, ?, ?, ?)";
                                        PreparedStatement pstmt = conn.prepareStatement(sql);
                                        pstmt.setString(1, name.getText());
                                        pstmt.setString(2, lastname.getText());
                                        pstmt.setString(3, Username.getText());
                                        pstmt.setString(4, email.getText());
                                        pstmt.setString(5, phonenumber.getText());
                                        pstmt.setString(6, pass.getText());

                                        try {
                                            Stage stage1 = (Stage) captchaPhoto.getScene().getWindow();
                                            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));

                                            Scene signupScene = new Scene(root ) ;

                                            stage1.setScene(signupScene);
                                            stage1.centerOnScreen();
                                            stage1.show();
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }

                                        int affectedRows = pstmt.executeUpdate();

                                        if (affectedRows > 0) {
                                            System.out.println("Data inserted successfully.");
                                        } else {
                                            System.out.println("Failed to insert data.");
                                        }

                                    } catch (SQLException e) {
                                        System.err.println("Error: " + e.getMessage());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reCaptchaMake( null );
    }
}