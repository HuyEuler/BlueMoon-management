module com.example.bluemoonmanagement {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.bluemoonmanagement to javafx.fxml;
    exports com.example.bluemoonmanagement;

    opens com.example.bluemoonmanagement.controllers to javafx.fxml;
    exports com.example.bluemoonmanagement.controllers;
}