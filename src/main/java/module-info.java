module com.example.finaltour {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.finaltour to javafx.fxml;
    exports com.example.finaltour;
}