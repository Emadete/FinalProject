package com.example.demo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CaptchaMaker {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static String CAPTCHA ;
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

        CAPTCHA = captcha.toString() ;

        try {
            ImageIO.write(image, "jpg", new File("src\\main\\resources\\com\\example\\demo\\images\\captcha.jpg"));
        } catch (IOException e) {
        }

    }
}