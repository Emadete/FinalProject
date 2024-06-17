package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    private static Currency USDT ;
    private static Currency LTC ;
    private static Currency BTC ;
    private static Currency ETH ;
    private static Currency AVAX ;

    @FXML
    private TableView<Currency> currencyTable;

    @FXML
    private TableColumn<Currency, ImageView> photoColumn ;
    @FXML
    private TableColumn<Currency, String> marketColumn;
    @FXML
    private TableColumn<Currency, Double> priceColumn;
    @FXML
    private TableColumn<Currency, Double> changesColumn;
    @FXML
    private TableColumn<Currency, Double> minColumn;
    @FXML
    private TableColumn<Currency, Double> maxColumn;

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

    @FXML
    private Text emailtext;

    @FXML
    private Text firstnametext;

    @FXML
    private Text lastnametext;

    @FXML
    private Text passwordtext;

    @FXML
    private Text phonenumbertext;

    @FXML
    private Text usernametext;

    @FXML
    private Text idcard;

    @FXML
    private Text Cardholder;

    @FXML
    private Label dateTimeLabel;

    private int selected = 1 ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeCurrencyTable();

        fullName.setText(Loginpage.user.getFirstname() + ' ' + Loginpage.user.getLastname()) ;
        usernametext.setText(Loginpage.user.getUserName());
        firstnametext.setText(Loginpage.user.getFirstname());
        lastnametext.setText(Loginpage.user.getLastname());
        passwordtext.setText(Loginpage.user.getPassword());
        phonenumbertext.setText(Loginpage.user.getPhoneNumber());
        emailtext.setText(Loginpage.user.getEmail());
        Cardholder.setText(Loginpage.user.getFirstname() + ' ' + Loginpage.user.getLastname());
        idcard.setText("5892 1015 0405 8690");
        updateDateTime();
        startDateTimeUpdate();

    }

//    public Image resizeImage(Image originalImage, int targetWidth, int targetHeight) {
//        ImageView imageView = new ImageView(originalImage);
//        imageView.setFitWidth(targetWidth);
//        imageView.setFitHeight(targetHeight);
//        imageView.setPreserveRatio(true);
//
//        WritableImage resizedImage = new WritableImage(targetWidth, targetHeight);
//        PixelReader pixelReader = imageView.snapshot(null, null).getPixelReader();
//
//        for (int y = 0; y < targetHeight; y++) {
//            for (int x = 0; x < targetWidth; x++) {
//                resizedImage.getPixelWriter().setArgb(x, y, pixelReader.getArgb((int) (x), (int) (y)));
//            }
//        }
//
//        return resizedImage;
//    }

    public void makeCurrencyTable (){
        photoColumn.setCellValueFactory( new PropertyValueFactory<>("photo"));
        marketColumn.setCellValueFactory( new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        changesColumn.setCellValueFactory( new PropertyValueFactory<>("priceChange1m"));
        minColumn.setCellValueFactory( new PropertyValueFactory<>("minPrice24h"));
        maxColumn.setCellValueFactory( new PropertyValueFactory<>("maxPrice24h"));

        String url = "jdbc:mysql://localhost:3306/exchangedb";
        String Username = "root";
        String Password = "123456";

        Timer timer = new Timer(60*1000, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try (Connection conn = DriverManager.getConnection(url, Username, Password)){
                    Time currentTime = new Time(new java.util.Date().getTime());
                    String query = "SELECT * FROM currency WHERE Time BETWEEN '"+new Time(currentTime.getTime()- 60 * 1000)+"' AND '"+currentTime+"'";
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Date date = resultSet.getDate(1);
                        Time time = resultSet.getTime(2);
                        double USDTpr = resultSet.getDouble(3);
                        double LTCpr = resultSet.getDouble(4);
                        double BTCpr = resultSet.getDouble(5);
                        double ETHpr = resultSet.getDouble(6);
                        double AVAXpr = resultSet.getDouble(7);


                        if (USDT == null) {
                            Image image = new Image(Main.class.getResourceAsStream("/com/example/demo/images/tether1.png"));
                            ImageView imageView = new ImageView(image);
                            USDT = new Currency("USDT", USDTpr, imageView);
                            currencyTable.getItems().add(USDT);
                        }else {
                            USDT.setPriceChange1m(Math.ceil(( (USDTpr-USDT.getPrice()) / USDT.getPrice() * 100)*100)/100);
                            USDT.setPrice(USDTpr);
                        }
                        if (LTC == null) {
                            Image image = new Image(Main.class.getResourceAsStream("/com/example/demo/images/lightcoin1.png"));
                            ImageView imageView = new ImageView(image);
                            LTC = new Currency("LTC",LTCpr,imageView);
                            currencyTable.getItems().add(LTC) ;
                        }else {
                            LTC.setPriceChange1m(Math.ceil(( (LTCpr-LTC.getPrice()) / LTC.getPrice() * 100)*100)/100);
                            LTC.setPrice(LTCpr);
                        }
                        if (BTC == null) {
                            Image image = new Image(Main.class.getResourceAsStream("/com/example/demo/images/Bitcoin1.png"));
                            ImageView imageView = new ImageView(image);
                            BTC = new Currency("BTC",BTCpr,imageView);
                            currencyTable.getItems().add(BTC) ;
                        }else {
                            BTC.setPriceChange1m(( (BTCpr-BTC.getPrice()) / BTC.getPrice() * 100));
                            BTC.setPrice(BTCpr);
                        }
                        if (ETH == null) {
                            Image image = new Image(Main.class.getResourceAsStream("/com/example/demo/images/Eterium1.png"));
                            ImageView imageView = new ImageView(image);
                            ETH = new Currency("ETH",ETHpr,imageView);
                            currencyTable.getItems().add(ETH) ;
                        }else {
                            ETH.setPriceChange1m(( (ETHpr-ETH.getPrice()) / ETH.getPrice() * 100));
                            ETH.setPrice(ETHpr);
                        }
                        if (AVAX == null) {
                            Image image = new Image(Main.class.getResourceAsStream("/com/example/demo/images/Avalanch1.png"));
                            ImageView imageView = new ImageView(image);
                            AVAX = new Currency("AVAX",AVAXpr,imageView);
                            currencyTable.getItems().add(AVAX) ;
                        }else {
                            AVAX.setPriceChange1m(( (AVAXpr -AVAX.getPrice()) / AVAX.getPrice() * 100));
                            AVAX.setPrice(AVAXpr);
                        }
                    }
                    currencyTable.refresh();
                }catch (SQLException  ex) {
                    System.err.println("Error: " + ex.getMessage());
                }
            }
        });

        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
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

    private void updateDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        Platform.runLater(() -> dateTimeLabel.setText(formattedDateTime));
    }

    private void startDateTimeUpdate() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateDateTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
