package com.example.bluemoonmanagement.controllers.Home_management;

import javafx.scene.control.*;

public class EditAccountController {

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}