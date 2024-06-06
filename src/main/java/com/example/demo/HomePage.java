package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class HomePage implements Initializable {
    @FXML
    private TableView<Currency> MarketTable;

    @FXML
    private Label TimeLabel;

    @FXML
    private TableColumn<Currency, Double> changesColumn;

    @FXML
    private TableColumn<Currency, String> nameColumn;

    @FXML
    private TableColumn<Currency, Double> maxColumn;

    @FXML
    private TableColumn<Currency, Double> minColumn;

    @FXML
    private TableColumn<Currency, Double> priceColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory( new PropertyValueFactory<Currency, String>("name"));
        priceColumn.setCellValueFactory( new PropertyValueFactory<Currency, Double>("price"));
        changesColumn.setCellValueFactory( new PropertyValueFactory<Currency, Double>("priceChange1m"));
        maxColumn.setCellValueFactory( new PropertyValueFactory<Currency, Double>("maxPrice24h"));
        minColumn.setCellValueFactory( new PropertyValueFactory<Currency, Double>("minPrice24h"));

        MarketTable.getItems().add( new Currency("BitCoin", 120));
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MarketTable.refresh();
            }
        });

        Timer timer1 = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date() ;
                TimeLabel.setText(DateFormat.getDateTimeInstance().format(date));
            }
        }) ;

        timer1.setRepeats(true);
        timer1.setCoalesce(true);
        timer1.setInitialDelay(0);
        timer1.start();

        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
    }
}
