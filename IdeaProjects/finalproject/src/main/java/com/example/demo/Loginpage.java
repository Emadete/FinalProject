package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;

public class Loginpage implements Initializable{

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @FXML private PasswordField Password;

    @FXML private TextField Username , CAPTCHA;

    @FXML private Button forgotpass , signup , submit;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        submit.setOnMouseEntered(e -> submit.setStyle("-fx-background-color: #6e038c; -fx-background-radius: 10;"));
        submit.setOnMouseExited(e -> submit.setStyle("-fx-background-color:  #7707b8; -fx-background-radius: 10;"));

        signup.setOnMouseEntered(e -> signup.setStyle("-fx-background-color: #025922; -fx-background-radius: 10;"));
        signup.setOnMouseExited(e -> signup.setStyle("-fx-background-color:  #0c7039; -fx-background-radius: 10;"));

        forgotpass.setOnMouseEntered(e -> forgotpass.setStyle("-fx-background-color:  #474747; -fx-background-radius: 10;"));
        forgotpass.setOnMouseExited(e -> forgotpass.setStyle("-fx-background-color:   #737a76; -fx-background-radius: 10;"));

    }
    public static void captcha(){

        int width = 90;
        int height = 42;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);

        Random random = new Random();
        StringBuilder captcha = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            char ch = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            captcha.append(ch);
            graphics.setColor(new Color(random.nextInt(25 ,255), random.nextInt(25 , 255), random.nextInt(25 , 255)));
            graphics.setFont(new Font("Arial", Font.BOLD, 20));
            graphics.rotate(Math.toRadians(random.nextInt(-5, 5)), 5 + i * 17, 25);
            graphics.drawString(String.valueOf(ch), 5 + i * 17, 25);
        }

        try {
            ImageIO.write(image, "jpg", new File("C:\\Users\\User\\IdeaProjects\\finalproject\\src\\main\\resources\\com\\example\\demo\\images\\captcha.jpg"));
        } catch (IOException e) {
        }

    }

}
