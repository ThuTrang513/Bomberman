module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens com.example.test to javafx.fxml;
    exports com.example.test;
}