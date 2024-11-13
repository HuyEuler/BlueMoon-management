package com.example.bluemoonmanagement.controllers.Resident_management;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddOwner_Controller {

    // ================================================= Added code


    // ================================================= Added code

    @FXML
    private TextField txtRoom_OwnerTable, txtOwnerName_OwnerTable, txtPhoneNumber_OwnerTable;

    @FXML
    private ChoiceBox<String> floorChoiceBox_OwnerTable;
    private Owner owner;

    @FXML
    private void initialize() {
        // Initialize fields and choice boxes

        floorChoiceBox_OwnerTable.getItems().clear();
        for (int i = 1; i <= 10; i++) {
            floorChoiceBox_OwnerTable.getItems().add("Tầng " + i);
        }

        //roomSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
    }

    @FXML
    private void handleSave() {
        // Retrieve data and process it
        String phoneNumer = txtPhoneNumber_OwnerTable.getText();
        String floor = floorChoiceBox_OwnerTable.getValue();
        String room = txtRoom_OwnerTable.getText();
        String ownerName = txtOwnerName_OwnerTable.getText();

        owner = new Owner(floor, room, "30m^2", ownerName, phoneNumer);


        // Save data to the database or perform further actions
        System.out.println("New value: " + floor + ", " + room + ", " + ownerName + ", " + phoneNumer);

        //closeWindow();

        Stage stage = (Stage) txtOwnerName_OwnerTable.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        // Close the window without saving
        Stage stage = (Stage) txtOwnerName_OwnerTable.getScene().getWindow();
        stage.close();
    }

    public Owner getOwner(){
        return owner;
    }
}
