package com.example.bluemoonmanagement.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeleteOwner_Controller {

    @FXML private Label messageLabel;
    @FXML private Button confirmButton;
    @FXML private Button cancelButton;

    private boolean confirmed = false;  // Track if deletion is confirmed

    @FXML
    private void initialize() {
        // Initialize if any customization for the dialog is needed
    }

    @FXML
    private void handleConfirm() {
        confirmed = true;
        closeDialog();
    }

    @FXML
    private void handleCancel() {
        confirmed = false;
        closeDialog();
    }

    private void closeDialog() {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}
