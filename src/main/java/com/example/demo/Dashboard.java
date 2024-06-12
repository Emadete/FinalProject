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
    @FXML
    private AnchorPane DashboardPane;

    @FXML
    private AnchorPane exchangePane;

    @FXML
    private AnchorPane historyPane;

    @FXML
    private AnchorPane walletPane;

    @FXML
    private AnchorPane swapPane;

    @FXML
    private AnchorPane transferPane;

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
    private int selected = 1 ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fullName.setText(Loginpage.user.getFirstname() + ' ' + Loginpage.user.getLastname()) ;

    }

    private void closeMenu () {
        switch (selected) {
            case 1 :
                dashboardId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
                DashboardPane.setVisible(false);
            case 2:
                myWalletId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
                walletPane.setVisible(false);
            case 3:
                exchangeId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
                exchangePane.setVisible(false);
            case 4:
                transferId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
                transferPane.setVisible(false);
            case 5:
                historyId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
                historyPane.setVisible(false);
            case 6:
                swapId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
                swapPane.setVisible(false);
        }
    }


    public void dashboardBtn(ActionEvent event) {
        Button b = (Button) event.getSource();

        closeMenu();

        b.setStyle("-fx-background-color: #605afa; -fx-background-radius: 7;");
        selected = 1 ;

        DashboardPane.setVisible(true);
    }

    @FXML
    void myWalletBtn(ActionEvent event) {
        Button b = (Button) event.getSource();

        closeMenu();

        b.setStyle("-fx-background-color: #605afa; -fx-background-radius: 7;");
        selected = 2 ;

        walletPane.setVisible(true);
    }

    @FXML
    void exchangeBtn(ActionEvent event) {
        Button b = (Button) event.getSource();

        closeMenu();

        b.setStyle("-fx-background-color: #605afa; -fx-background-radius: 7;");
        selected = 3 ;

        exchangePane.setVisible(true);
    }

    @FXML
    void transferBtn(ActionEvent event) {
        Button b = (Button) event.getSource();

        closeMenu();

        b.setStyle("-fx-background-color: #605afa; -fx-background-radius: 7;");
        selected = 4 ;

        transferPane.setVisible(true);
    }

    @FXML
    void historyBtn(ActionEvent event) {
        Button b = (Button) event.getSource();

        closeMenu();

        b.setStyle("-fx-background-color: #605afa; -fx-background-radius: 7;");
        selected = 5 ;

        historyPane.setVisible(true);
    }

    @FXML
    void swapBtn(ActionEvent event) {
        Button b = (Button) event.getSource();

        closeMenu();

        b.setStyle("-fx-background-color: #605afa; -fx-background-radius: 7;");
        selected = 6 ;

        swapPane.setVisible(true);
    }
}
