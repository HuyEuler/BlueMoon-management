package com.example.bluemoonmanagement.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDate;


public class AddResident_Controller {

    @FXML
    private TextField txtName, txtAddress, txtCountry, txtRegion, txtRoom, txtMQH;

    @FXML
    private ChoiceBox<String> genderChoiceBox, floorChoiceBox;

    @FXML
    private DatePicker birthSelectionBox;

    @FXML
    private TextArea txtLyDo;

    @FXML
    private void initialize() {
        // Initialize fields and choice boxes
        genderChoiceBox.getItems().addAll("Nam", "Nữ");

        floorChoiceBox.getItems().clear();
        for (int i = 1; i <= 10; i++) {
            floorChoiceBox.getItems().add("Tầng " + i);
        }

        //roomSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
    }

    @FXML
    private void handleSave() {
        // Retrieve data and process it
        String name = txtName.getText();
        String gender = genderChoiceBox.getValue();
        String dob = (birthSelectionBox.getValue() != null) ? birthSelectionBox.getValue().toString() : "";
        String personID = txtAddress.getText();
        String country = txtCountry.getText();
        String region = txtRegion.getText();
        String floor = floorChoiceBox.getValue();
        String room = txtRoom.getText();
        String mqh = txtMQH.getText();
        String lyDo = txtLyDo.getText();

        // Save data to the database or perform further actions
        System.out.println("Resident Information Saved: " + name + ", " + gender + ", " + dob + ", " + personID + ", " + country + ", " + region + ", " + floor + ", " + room);

        //closeWindow();

        // Close the window after saving
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        // Close the window without saving
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }
}
