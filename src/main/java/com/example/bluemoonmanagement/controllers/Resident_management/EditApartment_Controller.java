//package com.example.bluemoonmanagement.controllers.Resident_management;
//
//import com.example.bluemoonmanagement.models.Apartment;
//import com.example.bluemoonmanagement.api.ApartmentAPI;
//import com.example.bluemoonmanagement.models.Resident;
//import com.example.bluemoonmanagement.api.ResidentAPI;
//
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//import java.util.List;
//
//public class EditApartment_Controller {
//
//    @FXML private TextField newOwnerName;
//    @FXML private ChoiceBox<String> newOwnerId;
//
//    private List<String> existingResidentID; // To check for duplicates
//
//    public void setExistingResidentID(List<String> existingResidentID) {
//        this.existingResidentID = existingResidentID;
//        newOwnerId.getItems().addAll(existingResidentID);
//    }
//
//
//    private Apartment apartmentToEdit;
//    private boolean saveClicked = false;
//
//
//    public void setApartmentToEdit(Apartment apartment) {
//        this.apartmentToEdit = apartment;
//        int ownerId = Integer.parseInt(newOwnerId.getValue());
//        newOwnerName.setText(ResidentAPI.getResidentById(ownerId).getName());
//        newOwnerName.setEditable(false);
//    }
//
//    @FXML
//    public void initialize() {
//        newOwnerId.valueProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                try {
//                    int ownerId = Integer.parseInt(newValue);
//                    Resident resident = ResidentAPI.getResidentById(ownerId);
//                    if (resident != null) {
//                        newOwnerName.setText(resident.getName());
//                    } else {
//                        newOwnerName.setText("Unknown Resident");
//                    }
//                } catch (NumberFormatException e) {
//                    newOwnerName.setText("");
//                }
//            } else {
//                newOwnerName.setText("");
//            }
//        });
//    }
//
//
//    public boolean isSaveClicked() {
//        return saveClicked;
//    }
//
//    @FXML private void handleSave() {
//        try {
//            String selectedValue = newOwnerId.getValue();
//            if (selectedValue == null) {
//                showAlert("Invalid Input", "Please select an Owner ID.");
//                return;
//            }
//
//            int ownerId = Integer.parseInt(selectedValue);
//            apartmentToEdit.setOwnerId(ownerId);
//
//            ApartmentAPI.updateOwnerApartment(apartmentToEdit.getApartmentId(), ownerId);
//
//            saveClicked = true;
//            closeWindow();
//
//        } catch (NumberFormatException e) {
//            showAlert("Invalid Input", "Owner ID must be a number.");
//        }
//    }
//
//
//    @FXML private void handleCancel() {
//        saveClicked = false;
//        closeWindow();
//    }
//
//    private void closeWindow() {
//        Stage stage = (Stage) newOwnerName.getScene().getWindow();
//        stage.close();
//    }
//
//    private void showAlert(String title, String content) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(content);
//        alert.showAndWait();
//    }
//}

package com.example.bluemoonmanagement.controllers.Resident_management;
import com.example.bluemoonmanagement.models.Apartment;
import com.example.bluemoonmanagement.api.ApartmentAPI;
import com.example.bluemoonmanagement.api.ResidentAPI;
import com.example.bluemoonmanagement.models.Resident;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class EditApartment_Controller {

    @FXML private ChoiceBox<String> roomOwnerID;
    @FXML private TextField roomOwnerName;
    @FXML private TextField roomArea;
    @FXML private TextField roomFloor;
    @FXML private TextField roomLabel;

    private Apartment updatedApartment; // Holds the updated resident
    private int apartmentId;

//    private List<String> availableRooms; // Rooms from apartmentList
//    private List<Resident> residentList;

    @FXML private void initialize(){
        roomOwnerID.getItems().addAll("0");
        for (Resident resident : ResidentAPI.getAllResidents()) {
            roomOwnerID.getItems().add(String.valueOf(resident.getResidentId()));
        }

        roomOwnerID.setOnAction(event -> {
            String selectedId = roomOwnerID.getValue();
            if (!selectedId.equals("0")) {
                Resident resident = ResidentAPI.getResidentById(Integer.parseInt(selectedId));
                if (resident != null) {
                    roomOwnerName.setText(resident.getName());
                }
            }
            roomOwnerName.setEditable(false);
        });
    }

//    public void setAvailableRooms(List<String> availableRooms) {
//        this.availableRooms = availableRooms;
//        residentRoom.getItems().addAll(availableRooms);
//    }
//
//    public void setResidentList(List<Resident> residentList) {
//        this.residentList = residentList;
//    }

    public Apartment getUpdatedApartment() {
        return updatedApartment;
    }

    public void setApartmentData(Apartment apartment, int apartmentId) {
        this.apartmentId = apartmentId;

        if (apartment.getOwnerId() != null) {
            roomOwnerID.setValue(String.valueOf(apartment.getOwnerId()));
        } else {
            roomOwnerID.setValue(null); // Or set to some default value if needed
        }

        if (apartment.getOwnerId() == null){
            roomOwnerName.setText(null);
        }
        else{
            roomOwnerName.setText(
                    ResidentAPI.getResidentById(apartment.getOwnerId()).getName()
            );
        }

        roomArea.setText(String.valueOf(apartment.getArea()));

        roomFloor.setText(String.valueOf(apartment.getFloor()));
        roomFloor.setEditable(false);

        roomLabel.setText(apartment.getRoom());
        roomLabel.setEditable(false);
    }


    @FXML
    private void handleSave() {
        // Retrieve values from the fields
        int ownerID = Integer.parseInt(roomOwnerID.getValue());
        String ownerName = roomOwnerName.getText();
        float area = Float.parseFloat(roomArea.getText());
        int floor = Integer.parseInt(roomFloor.getText());
        String room = roomLabel.getText();


        // Validate inputs
        if (room == null || room.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Room cannot be empty.");
            return;
        }
        if (ownerName == null || ownerName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Name cannot be empty.");
            return;
        }

        updatedApartment = new Apartment(apartmentId, ownerID, area, floor, room);

        ApartmentAPI.updateOwnerApartment(apartmentId, ownerID);

        // Close the window
        Stage stage = (Stage) roomOwnerID.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        updatedApartment = null;
        Stage stage = (Stage) roomOwnerID.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
