package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Loginpage.captcha();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Loginpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("My Wallet");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {launch();}
}