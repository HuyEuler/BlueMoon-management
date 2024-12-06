package com.example.bluemoonmanagement.controllers.Resident_management;
import com.example.bluemoonmanagement.api.ApartmentAPI;
import com.example.bluemoonmanagement.api.ResidentAPI;
import com.example.bluemoonmanagement.models.Resident;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class EditResident_Controller {

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

    private Resident updatedResident; // Holds the updated resident
    private int residentId;

    private List<String> availableRooms; // Rooms from apartmentList
    private List<Resident> residentList;

    @FXML private void initialize(){
        residentGender.getItems().addAll("Nam", "Nữ");
        residentIsOwner.getItems().addAll("Có", "Không");
        residentStatus.getItems().addAll("Thường trú", "Tạm trú", "Tạm vắng", "Đã rời đi");
    }

    public void setAvailableRooms(List<String> availableRooms) {
        this.availableRooms = availableRooms;
        residentRoom.getItems().addAll(availableRooms);
    }

    public void setResidentList(List<Resident> residentList) {
        this.residentList = residentList;
    }

    public Resident getUpdatedResident() {
        return updatedResident;
    }

    public void setResidentData(Resident resident, int residentId) {
        this.residentId = residentId;

        residentRoom.setValue(
                ApartmentAPI.getApartmentById(resident.getApartmentId()).getRoom()
        );
        residentName.setText(resident.getName());
        residentDOB.setValue(resident.getBirthday() != null ? LocalDate.parse(resident.getBirthday()) : null);
        residentGender.setValue(
                resident.getGender() ? "Nam" : "Nữ"
        );
        residentPhoneNumber.setText(resident.getPhoneNumber());
        residentNation.setText(resident.getNationality());
        residentRelationship.setText(resident.getRelationshipWithOwner());
        residentIsOwner.setValue(resident.getIsOwner() ? "Có" : "Không");
        residentStatus.setValue(
                resident.getStatusDescription(resident.getStatus())
        );
    }


    @FXML
    private void handleSave() {
        // Retrieve values from the fields
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

        int statusInt = switch (status) {
            case "Đã rời đi" -> 0;
            case "Thường trú" -> 1;
            case "Tạm trú" -> 2;
            case "Tạm vắng" -> 3;
            default -> -1;
        };


        // Validate inputs
        if (room == null || room.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Room cannot be empty.");
            return;
        }
        if (name == null || name.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Name cannot be empty.");
            return;
        }

        updatedResident = new Resident(residentId, ApartmentAPI.getApartmentIdByRoom(room), name, dob, gender.equals("Nam"), phoneNumber,
                nationality, relationship, isOwner.equals("Có"), statusInt);

        ResidentAPI.updateResidentById(residentId, ApartmentAPI.getApartmentIdByRoom(room), name, dob, gender.equals("Nam"),
        phoneNumber, nationality, relationship, isOwner.equals("Có"), statusInt, note);

        // Close the window
        Stage stage = (Stage) residentRoom.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        updatedResident = null;
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
