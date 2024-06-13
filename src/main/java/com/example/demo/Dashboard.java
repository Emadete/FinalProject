package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
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
    private AnchorPane mainPane;
    @FXML
    private AnchorPane transferPane;
    @FXML
    private AnchorPane tokenPane;
    @FXML
    private AnchorPane profilePane ;

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
    private Button tokenId;
    @FXML
    private Button transferId;

    @FXML
    private LineChart lineChart ;

    @FXML
    private Circle profilePhoto;

    private int selected = 1 ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fullName.setText(Loginpage.user.getFirstname() + ' ' + Loginpage.user.getLastname()) ;
        NumberAxis xAxis = new NumberAxis(0,12,1);
        NumberAxis yAxis = new NumberAxis(0,1000,50);
        lineChart = new LineChart(xAxis,yAxis) ;

        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data<>(0,12));
        series.getData().add(new XYChart.Data<>(1,5));
        series.getData().add(new XYChart.Data<>(2,17));
        series.getData().add(new XYChart.Data<>(3,2));
        series.getData().add(new XYChart.Data<>(4,9));

        lineChart.getData().add(series);
        lineChart.autosize();
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
            case 7:
                tokenId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
                tokenPane.setVisible(false);
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
    @FXML
    void tokenBtn (ActionEvent event) {
        Button b = (Button) event.getSource();
        closeMenu();

        b.setStyle("-fx-background-color: #605afa; -fx-background-radius: 7;");
        selected = 7;

        tokenPane.setVisible(true);
    }

    @FXML
    public void profileBtn(MouseEvent mouseEvent) {
        if(selected != 8) {
            profilePane.setVisible(true);

            Lighting lighting = new Lighting();
            mainPane.setEffect(lighting);

            selected = 8 ;
        }
    }

    public void mainPaneAction(MouseEvent mouseEvent) {
        if (!mouseEvent.getTarget().equals(fullName) || !mouseEvent.getTarget().equals(profilePhoto)){
            if (selected == 8) {
                profilePane.setVisible(false);

                mainPane.setEffect(null);
            }
        }
    }
}
