package com.example.bluemoonmanagement.controllers.Resident_management;

import com.example.bluemoonmanagement.models.Apartment;
import com.example.bluemoonmanagement.api.ApartmentAPI;
import com.example.bluemoonmanagement.models.Resident;
import com.example.bluemoonmanagement.api.ResidentAPI;
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
//    private List<Integer> existingApartmentsID; // To check for duplicates
//
//    public void setExistingEntries(List<Integer> existingApartmentsID) {
//        this.existingApartmentsID = existingApartmentsID;
//    }

    public Apartment getNewApartment(){
        return newApartment;
    }

    @FXML
    private void initialize() {
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

//        int currentIndex;
//
//        if (existingApartmentsID.isEmpty()){
//            currentIndex = 0;
//        } else {
//            currentIndex = existingApartmentsID.getLast();
//        }

        //newApartment = new Apartment(currentIndex+1, ownerId, area, floor, room);
        newApartment = ApartmentAPI.addApartment(ownerId, area, floor, room);

        Stage stage = (Stage) roomOwnerName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) roomOwnerName.getScene().getWindow();
        stage.close();
    }
}

