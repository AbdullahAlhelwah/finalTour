module com.example.finaltour {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.finaltour to javafx.fxml;
    exports com.example.finaltour;
}