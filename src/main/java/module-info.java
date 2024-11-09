module com.example.bluemoonmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;

    opens com.example.bluemoonmanagement to javafx.fxml;
    exports com.example.bluemoonmanagement;

    opens com.example.bluemoonmanagement.controllers to javafx.fxml;
    exports com.example.bluemoonmanagement.controllers;
    exports com.example.bluemoonmanagement.controllers.Home_management;
    opens com.example.bluemoonmanagement.controllers.Home_management to javafx.fxml;
}