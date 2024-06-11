package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    @FXML
    private Text fullName;
    private int selected ;
    @FXML
    private Button dashboardId;

    @FXML
    private Button exchangeId;

    @FXML
    private Button historyId;

    @FXML
    private Button myWalletId;

    @FXML
    private Button swapId;

    @FXML
    private Button transferId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fullName.setText(Loginpage.user.getFirstname() + ' ' + Loginpage.user.getLastname()) ;

    }


    public void dashboardBtn(ActionEvent event) {
        Button b = (Button) event.getSource();

        switch (selected) {
            case 1 :
                dashboardId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 2:
                myWalletId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 3:
                exchangeId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 4:
                transferId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 5:
                historyId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 6:
                swapId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
        }
        if (selected != 1){
            b.setStyle("-fx-background-color: #605afa; -fx-background-radius: 7;");
            selected = 1 ;
        }
    }

    @FXML
    void exchangeBtn(ActionEvent event) {
        Button b = (Button) event.getSource();

        switch (selected) {
            case 1 :
                dashboardId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 2:
                myWalletId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 3:
                exchangeId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 4:
                transferId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 5:
                historyId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 6:
                swapId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
        }

        if (selected != 3){
            b.setStyle("-fx-background-color: #605afa; -fx-background-radius: 7;");
            selected = 3 ;
        }
    }

    @FXML
    void historyBtn(ActionEvent event) {
        Button b = (Button) event.getSource();

        switch (selected) {
            case 1 :
                dashboardId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 2:
                myWalletId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 3:
                exchangeId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 4:
                transferId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 5:
                historyId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 6:
                swapId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
        }

        if (selected != 5){
            b.setStyle("-fx-background-color: #605afa; -fx-background-radius: 7;");
            selected = 5 ;
        }
    }

    @FXML
    void myWalletBtn(ActionEvent event) {
        Button b = (Button) event.getSource();

        switch (selected) {
            case 1 :
                dashboardId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 2:
                myWalletId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 3:
                exchangeId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 4:
                transferId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 5:
                historyId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 6:
                swapId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
        }

        if (selected != 2){
            b.setStyle("-fx-background-color: #605afa; -fx-background-radius: 7;");
            selected = 2 ;
        }
    }

    @FXML
    void swapBtn(ActionEvent event) {
        Button b = (Button) event.getSource();

        switch (selected) {
            case 1 :
                dashboardId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 2:
                myWalletId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 3:
                exchangeId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 4:
                transferId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 5:
                historyId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 6:
                swapId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
        }

        if (selected != 6){
            b.setStyle("-fx-background-color: #605afa; -fx-background-radius: 7;");
            selected = 6 ;
        }
    }

    @FXML
    void transferBtn(ActionEvent event) {
        Button b = (Button) event.getSource();

        switch (selected) {
            case 1 :
                dashboardId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 2:
                myWalletId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 3:
                exchangeId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 4:
                transferId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 5:
                historyId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
            case 6:
                swapId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
        }

        if (selected != 4){
            b.setStyle("-fx-background-color: #605afa; -fx-background-radius: 10;");
            selected = 4 ;
        }
    }
}
