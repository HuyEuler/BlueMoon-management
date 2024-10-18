module com.example.bluemoonmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bluemoonmanagement to javafx.fxml;
    exports com.example.bluemoonmanagement;
}