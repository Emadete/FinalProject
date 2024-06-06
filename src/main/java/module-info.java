module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javax.mail.api;
    requires activation;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}