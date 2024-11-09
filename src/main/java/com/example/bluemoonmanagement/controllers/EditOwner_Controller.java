// EditOwnerController.java
package com.example.bluemoonmanagement.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class EditOwner_Controller {

    @FXML
    private TextField roomField;
    @FXML
    private TextField ownerNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private TableView<Owner> tableView;

    // Method to initialize the TableView and set up initial values
    public void setTableView(TableView<Owner> tableView) {
        this.tableView = tableView;
    }

    @FXML
    public void initialize() {
        saveButton.setOnAction(event -> onSave());
        cancelButton.setOnAction(event -> onCancel());
    }

    private void onSave() {
        String phong = roomField.getText();
        String tenChuSoHuu = ownerNameField.getText();
        String soDienThoai = phoneField.getText();

        // Check if the entered room exists in the table
        Owner ownerToEdit = tableView.getItems().stream()
                .filter(owner -> owner.phongProperty().get().equals(phong))
                .findFirst()
                .orElse(null);

        if (ownerToEdit != null) {
            ownerToEdit.tenChuSoHuuProperty().set(tenChuSoHuu);
            ownerToEdit.soDienThoaiProperty().set(soDienThoai);
            tableView.refresh(); // Refresh the table to show updated data
            closeWindow();
        } else {
            showAlert("Error", "The specified room does not exist in the table.");
        }
    }

    private void onCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
