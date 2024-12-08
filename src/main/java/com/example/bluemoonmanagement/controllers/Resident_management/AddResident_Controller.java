package com.example.bluemoonmanagement.controllers.Resident_management;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.bluemoonmanagement.models.Resident;
import com.example.bluemoonmanagement.models.Apartment;
import com.example.bluemoonmanagement.api.ApartmentAPI;
import com.example.bluemoonmanagement.api.ResidentAPI;

import java.util.List;

public class AddResident_Controller {

    @FXML private ChoiceBox<String> residentRoom;
    @FXML private TextField residentName;
    @FXML private DatePicker residentDOB;
    @FXML private ChoiceBox<String> residentGender;
    @FXML private TextField residentPhoneNumber;
    @FXML private TextField residentNation;
    @FXML private TextField residentRelationship;
    @FXML private ChoiceBox<String> residentIsOwner;
    @FXML private ChoiceBox<String> residentStatus;
    @FXML private TextArea residentNote;

    //private List<String> existingEntries; // To check for duplicates
    private List<String> availableRooms; // Rooms from apartmentList
    private int currentCount;

    private List<String> roomNameUniqueList;
    private List<String> roomOwnerUniqueList;

    private Resident newResident;

//    public void setExistingEntries(List<String> existingEntries) {
//        this.existingEntries = existingEntries;
//    }
    public void setCurrentCount(int currentCount){
        this.currentCount = currentCount;
    }

    public void setAvailableRooms(List<String> availableRooms) {
        this.availableRooms = availableRooms;
        residentRoom.getItems().addAll(availableRooms);
    }

    public void setRoomNameUniqueList(List<String> roomNameUniqueList){
        this.roomNameUniqueList = roomNameUniqueList;
    }

    public void setRoomOwnerUniqueList(List<String> roomOwnerUniqueList){
        this.roomOwnerUniqueList = roomOwnerUniqueList;
    }

    public Resident getNewResident() {
        return newResident;
    }

    @FXML private void initialize(){
        residentGender.getItems().addAll("Nam", "Nữ");
        residentIsOwner.getItems().addAll("Có", "Không");
        residentStatus.getItems().addAll("Thường trú", "Tạm trú", "Tạm vắng");
    }

    @FXML
    private void handleSave() {
        String room = residentRoom.getValue();
        String name = residentName.getText();
        String dob = residentDOB.getValue() != null ? residentDOB.getValue().toString() : null;
        String gender = residentGender.getValue();
        String phoneNumber = residentPhoneNumber.getText();
        String nationality = residentNation.getText();
        String relationship = residentRelationship.getText();
        String isOwner = residentIsOwner.getValue();
        String status = residentStatus.getValue();
        String note = residentNote.getText();

        int statusInt = switch (status){
            case "Thường trú" -> 1;
            case "Tạm trú" -> 2;
            case "Tạm vắng" -> 3;
            default -> -1;
        };
//        if (status.equals("Thường trú")){
//            statusInt = 1;
//        } else {
//            statusInt = 2;
//        }

        int roomId = ApartmentAPI.getApartmentIdByRoom(room);

        if (room == null || room.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Room cannot be empty.");
            return;
        }
        if (name == null || name.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Name cannot be empty.");
            return;
        }

        // Check for duplicates (room + residentName must be unique)
        String roomNameUniqueCheck = roomId + "|" + name;
        String roomOwnerUniqueCheck = roomId + "|" + true;

        if (roomNameUniqueList.contains(roomNameUniqueCheck)) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "This resident already exists for the specified room.");
            return;
        }

        if (isOwner.equals("Có") && roomOwnerUniqueList.contains(roomOwnerUniqueCheck)){
            showAlert(Alert.AlertType.ERROR, "Validation Error", "The room is already owned.");
            return;
        }

        //newResident = new Resident(existingEntries.toArray().length+1, roomId, name, dob, gender.equals("Nam"), phoneNumber, nationality, relationship, isOwner.equals("Có"), statusInt);
        newResident = new Resident(currentCount+1, roomId, name, dob, gender.equals("Nam"), phoneNumber,
                nationality, relationship, isOwner.equals("Có"), statusInt);
        ResidentAPI.addResident(room, name, dob, gender.equals("Nam"), phoneNumber,
                nationality, relationship, isOwner.equals("Có"),
                statusInt,
                note);

        // Close the window
        Stage stage = (Stage) residentRoom.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        newResident = null;
        Stage stage = (Stage) residentRoom.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
