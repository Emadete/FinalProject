package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
}
