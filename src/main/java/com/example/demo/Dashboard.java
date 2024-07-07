package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
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
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    private static Currency USDT ;
    private static Currency LTC ;
    private static Currency BTC ;
    private static Currency ETH ;
    private static Currency AVAX ;

    ObservableList<String> coinlist = FXCollections.observableArrayList("USDT","LTC","BTC","ETH","AVAX");

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

    @FXML
    private TextField emailfild;

    @FXML
    private TextField firstnamefild;

    @FXML
    private TextField lastnamefild;

    @FXML
    private TextField passwordfild;

    @FXML
    private TextField phonenumberfild;

    @FXML
    private Button editBtn;

    @FXML
    private Button submitBtn;

    @FXML
    private ChoiceBox Coin;

    @FXML
    private TextField amount;

    @FXML
    private Button doneBtn;

    @FXML
    private TextField wallet_id;

    @FXML
    private Text balanceavax;

    @FXML
    private Text balancebtc;

    @FXML
    private Text balanceeth;

    @FXML
    private Text balanceltc;

    @FXML
    private Text balanceusdt;

    @FXML
    private Text currentavax;

    @FXML
    private Text currentbtc;

    @FXML
    private Text currenteth;

    @FXML
    private Text currentltc;

    @FXML
    private Text currentusdt;

    @FXML
    private Text priceavax;

    @FXML
    private Text pricebtc;

    @FXML
    private Text priceeth;

    @FXML
    private Text priceltc;

    @FXML
    private Text priceusdt;

    @FXML
    private Text walletbalance;

    @FXML
    private Text walletcard;

    @FXML
    private Text walletid;

    @FXML
    private Button setBtn;

    @FXML
    private TextField swid;

    @FXML
    private Button subBtn;

    @FXML
    private LineChart<?, ?> chart;


    private int selected = 1 ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeCurrencyTable();
        makewallet();

        fullName.setText(Loginpage.user.getFirstname() + ' ' + Loginpage.user.getLastname()) ;
        usernametext.setText(Loginpage.user.getUserName());
        firstnametext.setText(Loginpage.user.getFirstname());
        lastnametext.setText(Loginpage.user.getLastname());
        passwordtext.setText(Loginpage.user.getPassword());
        phonenumbertext.setText(Loginpage.user.getPhoneNumber());
        emailtext.setText(Loginpage.user.getEmail());
        Cardholder.setText(Loginpage.user.getFirstname() + ' ' + Loginpage.user.getLastname());
        Coin.setItems(coinlist);
        Coin.setValue("USDT");
        updateDateTime();
        startDateTimeUpdate();

    }

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

                        DecimalFormat decimalFormat = new DecimalFormat("#.##");

                        if (USDT == null) {
                            Image image = new Image(Main.class.getResourceAsStream("/com/example/demo/images/tether1.png"));
                            ImageView imageView = new ImageView(image);
                            USDT = new Currency("USDT", USDTpr, imageView);
                            currencyTable.getItems().add(USDT);
                        }else {
                            String formattedNumber = decimalFormat.format(Math.ceil(( (USDTpr-USDT.getPrice()) / USDT.getPrice() * 100)*100)/100);
                            double result = Double.parseDouble(formattedNumber);
                            USDT.setPriceChange1m(result);
                            USDT.setPrice(USDTpr);
                        }
                        if (LTC == null) {
                            Image image = new Image(Main.class.getResourceAsStream("/com/example/demo/images/lightcoin1.png"));
                            ImageView imageView = new ImageView(image);
                            LTC = new Currency("LTC",LTCpr,imageView);
                            currencyTable.getItems().add(LTC) ;
                        }else {
                            String formattedNumber = decimalFormat.format(Math.ceil(( (LTCpr-LTC.getPrice()) / LTC.getPrice() * 100)*100)/100);
                            double result = Double.parseDouble(formattedNumber);
                            LTC.setPriceChange1m(result);
                            LTC.setPrice(LTCpr);
                        }
                        if (BTC == null) {
                            Image image = new Image(Main.class.getResourceAsStream("/com/example/demo/images/Bitcoin1.png"));
                            ImageView imageView = new ImageView(image);
                            BTC = new Currency("BTC",BTCpr,imageView);
                            currencyTable.getItems().add(BTC) ;
                        }else {
                            String formattedNumber = decimalFormat.format(( (BTCpr-BTC.getPrice()) / BTC.getPrice() * 100));
                            double result = Double.parseDouble(formattedNumber);
                            BTC.setPriceChange1m(result);
                            BTC.setPrice(BTCpr);
                        }
                        if (ETH == null) {
                            Image image = new Image(Main.class.getResourceAsStream("/com/example/demo/images/Eterium1.png"));
                            ImageView imageView = new ImageView(image);
                            ETH = new Currency("ETH",ETHpr,imageView);
                            currencyTable.getItems().add(ETH) ;
                        }else {
                            String formattedNumber = decimalFormat.format(( (ETHpr-ETH.getPrice()) / ETH.getPrice() * 100));
                            double result = Double.parseDouble(formattedNumber);
                            ETH.setPriceChange1m(result);
                            ETH.setPrice(ETHpr);
                        }
                        if (AVAX == null) {
                            Image image = new Image(Main.class.getResourceAsStream("/com/example/demo/images/Avalanch1.png"));
                            ImageView imageView = new ImageView(image);
                            AVAX = new Currency("AVAX",AVAXpr,imageView);
                            currencyTable.getItems().add(AVAX) ;
                        }else {
                            String formattedNumber = decimalFormat.format(( (AVAXpr -AVAX.getPrice()) / AVAX.getPrice() * 100));
                            double result = Double.parseDouble(formattedNumber);
                            AVAX.setPriceChange1m(result);
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


    public void makechart(){}

    public void setBtn(ActionEvent event){

        setBtn.setVisible(false);
        swid.setVisible(true);
        subBtn.setVisible(true);

    }

    public void subBtn(ActionEvent event){

        setBtn.setVisible(true);
        swid.setVisible(false);
        subBtn.setVisible(false);

        String url = "jdbc:mysql://localhost:3306/exchangedb";
        String username = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "INSERT INTO wallet (id , username , USDT , LTC , BTC , ETH , AVAX) VALUES (?, ?, 0, 0, 0, 0, 0)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, swid.getText());
            pstmt.setString(2, Loginpage.user.getUserName());
            pstmt.executeUpdate();
            makewallet();

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void doneBtn(ActionEvent event){

        String url = "jdbc:mysql://localhost:3306/exchangedb";
        String username = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "UPDATE wallet SET " + Coin.getValue() + " = " + Coin.getValue() + " + ? WHERE id = '" + wallet_id.getText() + "'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1, Float.parseFloat(amount.getText()));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void makewallet(){

        float wbtc = 0, weth = 0 , wusdt = 0 , wltc = 0 , wavax = 0;
        double USDTpr = 0 , LTCpr = 0 , BTCpr = 0 , ETHpr = 0 , AVAXpr = 0;

        String url = "jdbc:mysql://localhost:3306/exchangedb";
        String username = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, username, password)){

            String query = "SELECT * FROM wallet WHERE username = '" + Loginpage.user.getUserName() + "'";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                String wid = resultSet.getString("id");
                walletid.setText(wid);
                idcard.setText(wid);
                wbtc = resultSet.getFloat("BTC");
                pricebtc.setText(Float.toString(wbtc));
                weth = resultSet.getFloat("ETH");
                priceeth.setText(Float.toString(weth));
                wusdt = resultSet.getFloat("USDT");
                priceusdt.setText(Float.toString(wusdt));
                wltc = resultSet.getFloat("LTC");
                priceltc.setText(Float.toString(wltc));
                wavax = resultSet.getFloat("AVAX");
                priceavax.setText(Float.toString(wavax));

            }

            walletcard.setText(Loginpage.user.getFirstname() + ' ' + Loginpage.user.getLastname());
            Time currentTime = new Time(new java.util.Date().getTime());
            String sql = "SELECT * FROM currency WHERE Time BETWEEN '"+new Time(currentTime.getTime()- 60 * 1000)+"' AND '"+currentTime+"'";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()){

                Date date = resultSet1.getDate(1);
                Time time = resultSet1.getTime(2);
                USDTpr = resultSet1.getDouble(3);
                currentusdt.setText(Double.toString(USDTpr));
                LTCpr = resultSet1.getDouble(4);
                currentltc.setText(Double.toString(LTCpr));
                BTCpr = resultSet1.getDouble(5);
                currentbtc.setText(Double.toString(BTCpr));
                ETHpr = resultSet1.getDouble(6);
                currenteth.setText(Double.toString(ETHpr));
                AVAXpr = resultSet1.getDouble(7);
                currentavax.setText(Double.toString(AVAXpr));

            }

            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            balancebtc.setText(decimalFormat.format(BTCpr * wbtc));
            balanceusdt.setText(decimalFormat.format(USDTpr * wusdt));
            balanceltc.setText(decimalFormat.format(LTCpr * wltc));
            balanceeth.setText(decimalFormat.format(ETHpr * weth));
            balanceavax.setText(decimalFormat.format(AVAXpr * wavax));
            walletbalance.setText(decimalFormat.format((BTCpr * wbtc) + (USDTpr * wusdt) + (LTCpr * wltc) + (ETHpr * weth) + (AVAXpr * wavax)));

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void editBtn(ActionEvent event){
        firstnametext.setVisible(false);
        lastnametext.setVisible(false);
        passwordtext.setVisible(false);
        phonenumbertext.setVisible(false);
        emailtext.setVisible(false);
        editBtn.setVisible(false);
        firstnamefild.setVisible(true);
        lastnamefild.setVisible(true);
        passwordfild.setVisible(true);
        phonenumberfild.setVisible(true);
        emailfild.setVisible(true);
        submitBtn.setVisible(true);
    }

    private boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }

    public void submitBtn(ActionEvent event){

        String url = "jdbc:mysql://localhost:3306/exchangedb";
        String username = "root";
        String password = "123456";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            if (isNotEmpty(firstnamefild.getText())){
                String sql = "UPDATE user SET firstname = ? WHERE username = '" + Loginpage.user.getUserName() + "'";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, firstnamefild.getText());
                Loginpage.user.setFirstname(firstnamefild.getText());
                firstnametext.setText(Loginpage.user.getFirstname());
                pstmt.executeUpdate();
            }

            if (isNotEmpty(lastnamefild.getText())){
                String sql = "UPDATE user SET lastname = ? WHERE username = '" + Loginpage.user.getUserName() + "'";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, lastnamefild.getText());
                Loginpage.user.setLastname(lastnamefild.getText());
                lastnametext.setText(Loginpage.user.getLastname());
                pstmt.executeUpdate();
            }

            if (isNotEmpty(emailfild.getText())){
                String sql = "UPDATE user SET email = ? WHERE username = '" + Loginpage.user.getUserName() + "'";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, emailfild.getText());
                Loginpage.user.setEmail(emailfild.getText());
                emailtext.setText(Loginpage.user.getEmail());
                pstmt.executeUpdate();
            }

            if (isNotEmpty(phonenumberfild.getText())){
                String sql = "UPDATE user SET phonenumber = ? WHERE username = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, phonenumberfild.getText());
                pstmt.setString(2, Loginpage.user.getUserName());
                Loginpage.user.setPhoneNumber(phonenumberfild.getText());
                phonenumbertext.setText(Loginpage.user.getPhoneNumber());
                pstmt.executeUpdate();
            }

            if (isNotEmpty(passwordfild.getText())){
                String sql = "UPDATE user SET password = ? WHERE username = '" + Loginpage.user.getUserName() + "'";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, passwordfild.getText());
                Loginpage.user.setPassword(passwordfild.getText());
                passwordtext.setText(Loginpage.user.getPassword());
                pstmt.executeUpdate();
            }

            firstnametext.setVisible(true);
            lastnametext.setVisible(true);
            passwordtext.setVisible(true);
            phonenumbertext.setVisible(true);
            emailtext.setVisible(true);
            editBtn.setVisible(true);
            firstnamefild.setVisible(false);
            lastnamefild.setVisible(false);
            passwordfild.setVisible(false);
            phonenumberfild.setVisible(false);
            emailfild.setVisible(false);
            submitBtn.setVisible(false);

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
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
        makewallet();
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
