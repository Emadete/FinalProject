package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    @FXML
    private Button fullName;

    @FXML
    private AnchorPane prof;

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
    private AnchorPane ProfilePane;

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

    @FXML
    private Button logoutBtn;

    @FXML
    private Button profBtn;

    @FXML
    private Button imageLink;

    @FXML
    private ImageView picture;

    @FXML
    private ImageView picture1;

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
            case 7:
                swapId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
                prof.setVisible(false);
            case 8:
                swapId.setStyle("-fx-background-color: #31297f; -fx-background-radius: 7;");
                ProfilePane.setVisible(false);
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
    void profBtn(ActionEvent event) {
        Button b = (Button) event.getSource();

        closeMenu();


        ProfilePane.setVisible(true);
    }

    @FXML
    void fullnameBtn(ActionEvent event){

        prof.setVisible(true);

    }

    @FXML
    void LogoutBtn(ActionEvent event){

        try {
            Stage stage1 = (Stage) logoutBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));

            Scene signupScene = new Scene(root ) ;

            stage1.setScene(signupScene);
            stage1.centerOnScreen();
            stage1.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");

        // Set file filter to only allow image files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            Circle clip = new Circle(picture.getFitWidth() / 2, picture.getFitHeight() / 2, picture.getFitWidth() / 2);
            Circle clip1 = new Circle(picture1.getFitWidth() / 2, picture1.getFitHeight() / 2, picture1.getFitWidth() / 2);

            picture.setClip(clip);
            picture.setClip(clip);
            picture.fitWidthProperty().bind(clip.radiusProperty().multiply(2));
            picture.fitHeightProperty().bind(clip.radiusProperty().multiply(2));
            clip.centerXProperty().bind(picture.fitWidthProperty().divide(2));
            clip.centerYProperty().bind(picture.fitHeightProperty().divide(2));
            picture.setImage(image);
            picture1.setClip(clip1);
            picture1.setClip(clip1);
            picture1.fitWidthProperty().bind(clip1.radiusProperty().multiply(2));
            picture1.fitHeightProperty().bind(clip1.radiusProperty().multiply(2));
            clip1.centerXProperty().bind(picture1.fitWidthProperty().divide(2));
            clip1.centerYProperty().bind(picture1.fitHeightProperty().divide(2));
            picture1.setImage(image);
            picture1.setImage(image);
        }
    }

}
