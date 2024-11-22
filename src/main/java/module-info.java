module com.example.bluemoonmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.prefs;

    opens com.example.bluemoonmanagement to javafx.fxml;
    exports com.example.bluemoonmanagement;

    opens com.example.bluemoonmanagement.controllers to javafx.fxml;
    exports com.example.bluemoonmanagement.controllers;

//    opens com.example.bluemoonmanagement.controllers.Home_management to javafx.fxml;
//    exports com.example.bluemoonmanagement.controllers.Home_management;

//    opens com.example.bluemoonmanagement.controllers.Fee_management to javafx.fxml;
//    exports com.example.bluemoonmanagement.controllers.Fee_management;

    opens com.example.bluemoonmanagement.models to javafx.base;
    exports com.example.bluemoonmanagement.models;

    exports com.example.bluemoonmanagement.controllers.Fee_management;
    opens com.example.bluemoonmanagement.controllers.Fee_management to javafx.fxml;

    exports com.example.bluemoonmanagement.common;
    opens com.example.bluemoonmanagement.common to javafx.fxml;

    exports com.example.bluemoonmanagement.controllers.Resident_management;
    opens com.example.bluemoonmanagement.controllers.Resident_management to javafx.fxml;
    exports com.example.bluemoonmanagement.controllers.Account_management;
    opens com.example.bluemoonmanagement.controllers.Account_management to javafx.fxml;
}