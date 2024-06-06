package com.example.demo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Currency {
    private ArrayList<Double> pricesBy1s = new ArrayList<>();
    private String name ;
    private double price ;
    private double priceChange1m ;
    private double maxPrice24h ;
    private int maxPriceIndex ;
    private double minPrice24h ;
    private int minPriceIndex ;

    public Currency(String name, double price) {
        this.name = name;
        this.price = price;
        priceChange1m = 0 ;
        maxPrice24h = price ;
        maxPriceIndex = 0 ;
        minPriceIndex = 0 ;
        minPrice24h = price ;
        init();
    }

    private void init () {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pricesBy1s.isEmpty()) pricesBy1s.add(price) ;
                else {
                    price += new Random().nextInt(-10,10) ;
                    pricesBy1s.add(price) ;
                }
                if ( price < 0 ) price = 0 ;
                pricesBy1s.add(price) ;

                if (pricesBy1s.size() >= 60) {
                    priceChange1m = ( 100 - pricesBy1s.get(pricesBy1s.size()-60)/price*100) ;
                } else priceChange1m = ( 100 - pricesBy1s.getFirst()/price*100) ;
                if (maxPriceIndex >= pricesBy1s.size() - 60*60*24){
                    if (price >= maxPrice24h){
                        maxPriceIndex = pricesBy1s.size()-1 ;
                        maxPrice24h = pricesBy1s.getLast() ;
                    }
                } else  {
                    maxPriceIndex = (pricesBy1s.size() - 60*60*24) ;
                    maxPrice24h = pricesBy1s.get(maxPriceIndex) ;
                }
                if (minPriceIndex >= pricesBy1s.size() - 60*60*24){
                    if (price <= minPrice24h){
                        minPriceIndex = pricesBy1s.size()-1 ;
                        minPrice24h = price ;
                    }
                } else  {
                    minPriceIndex = (pricesBy1s.size() - 60*60*24) ;
                    minPrice24h = pricesBy1s.get(maxPriceIndex) ;
                }
            }

        }) ;

        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getPriceChange1m() {
        return priceChange1m;
    }

    public double getMaxPrice24h() {
        return maxPrice24h;
    }

    public double getMinPrice24h() {
        return minPrice24h;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", maxPrice24h=" + maxPrice24h +
                ", minPrice24h=" + minPrice24h +
                ", priceChange1m=" + priceChange1m+ '%' +
                '}';
    }
}
