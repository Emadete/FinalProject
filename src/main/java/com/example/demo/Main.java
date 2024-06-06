package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        CaptchaMaker.captcha() ;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Loginpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image icon = new Image(Main.class.getResourceAsStream("/com/example/demo/images/icon.png"));
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setTitle("My Wallet");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {launch();}
}
