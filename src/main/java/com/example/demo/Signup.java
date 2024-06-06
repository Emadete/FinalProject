package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;

public class Signup {

    @FXML private ImageView captchaPhoto ;
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
}