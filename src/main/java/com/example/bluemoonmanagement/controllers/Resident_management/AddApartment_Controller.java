//package com.example.bluemoonmanagement.controllers.Resident_management;
//
//import com.example.bluemoonmanagement.api.ResidentAPI;
//import com.example.bluemoonmanagement.api.VehicleAPI;
//import com.example.bluemoonmanagement.models.Apartment;
//import com.example.bluemoonmanagement.models.Resident;
//import com.example.bluemoonmanagement.api.ApartmentAPI;
//
//import com.example.bluemoonmanagement.models.Vehicle;
//import javafx.collections.FXCollections;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//import java.util.List;
//
//public class AddApartment_Controller {
//
//    @FXML private TextField roomOwnerName;
//    @FXML private TextField roomArea;
//    @FXML private TextField roomFloor;
//    @FXML private TextField roomLabel;
//
//    private Apartment newApartment;
//    private boolean isSaveClicked = false;
//
//    @FXML
//    public void initialize() {
//        // Any necessary initialization can go here.
//    }
//
//    @FXML
//    private void handleSave(ActionEvent event) {
//        try {
//            // Parse input fields
//            String ownerName = roomOwnerName.getText();
//            float area = Float.parseFloat(roomArea.getText());
//            int floor = Integer.parseInt(roomFloor.getText());
//            String room = roomLabel.getText();
//
//            // Create a new Apartment object
//            newApartment = new Apartment(ownerName, area, floor, room);
//
//            ApartmentAPI.addApartment(null, area, floor, room);
//
//            isSaveClicked = true;
//
//            // Close the dialog
//            closeWindow();
//        } catch (NumberFormatException e) {
//            // Show an alert for invalid input
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Input Error");
//            alert.setHeaderText("Invalid Input");
//            alert.setContentText("Please enter valid values for all fields.");
//            alert.showAndWait();
//        }
//    }
//
//    @FXML
//    private void handleCancel(ActionEvent event) {
//        isSaveClicked = false;
//        closeWindow();
//    }
//
//    public Apartment getNewApartment() {
//        return newApartment;
//    }
//
//    public boolean isSaveClicked() {
//        return isSaveClicked;
//    }
//
//    private void closeWindow() {
//        Stage stage = (Stage) roomOwnerName.getScene().getWindow();
//        stage.close();
//    }
//}

package com.example.bluemoonmanagement.controllers.Resident_management;

import com.example.bluemoonmanagement.models.Apartment;
import com.example.bluemoonmanagement.api.ApartmentAPI;
import com.example.bluemoonmanagement.models.Resident;
import com.example.bluemoonmanagement.api.ResidentAPI;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class AddApartment_Controller {

    @FXML private ChoiceBox<String> roomOwnerID;
    @FXML private TextField roomOwnerName;
    @FXML private TextField roomArea;
    @FXML private TextField roomFloor;
    @FXML private TextField roomLabel;

    private Apartment newApartment;
    private List<Integer> existingApartmentsID; // To check for duplicates

    public void setExistingEntries(List<Integer> existingApartmentsID) {
        this.existingApartmentsID = existingApartmentsID;
    }

    public Apartment getNewApartment(){
        return newApartment;
    }

    @FXML
    private void initialize() {
        // Update owner name based on selected resident ID
        roomOwnerID.getItems().addAll("N: Chưa có chủ");
        for (Resident resident : ResidentAPI.getAllResidents()) {
            roomOwnerID.getItems().add(resident.getResidentId() + ": " + resident.getName());
        }

        roomOwnerID.setOnAction(event -> {
            String selectedId = roomOwnerID.getValue().substring(0, 1);
            if (!selectedId.equals("N")) {
                Resident resident = ResidentAPI.getResidentById(Integer.parseInt(selectedId));
                if (resident != null) {
                    roomOwnerName.setText(resident.getName());
                }
            } else {
                roomOwnerName.setText("");
            }
            roomOwnerName.setEditable(false);
        });
    }

    @FXML
    private void handleSave() {
        String ownerID_str = roomOwnerID.getValue().substring(0, 1);
        Integer ownerId = !(ownerID_str.equals("N")) ? Integer.parseInt(ownerID_str) : null;
        float area = Float.parseFloat(roomArea.getText());
        int floor = Integer.parseInt(roomFloor.getText());
        String room = roomLabel.getText();

        newApartment = new Apartment(existingApartmentsID.get(existingApartmentsID.size()-1)+1, ownerId, area, floor, room);
        ApartmentAPI.addApartment(ownerId, area, floor, room);

        Stage stage = (Stage) roomOwnerName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        // Close the dialog without saving
        Stage stage = (Stage) roomOwnerName.getScene().getWindow();
        stage.close();
    }
}

