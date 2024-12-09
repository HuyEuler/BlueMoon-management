package com.example.bluemoonmanagement.controllers.Resident_management;

import com.example.bluemoonmanagement.models.Apartment;
import com.example.bluemoonmanagement.api.ApartmentAPI;
import com.example.bluemoonmanagement.models.Resident;
import com.example.bluemoonmanagement.api.ResidentAPI;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private List<Apartment> existingApartments; // To check for duplicates

    public void setExistingApartments(List<Apartment> existingApartments) {
        this.existingApartments = existingApartments;
    }

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

        String roomOwnerID_str = roomOwnerID.getValue();
        String area_str = roomArea.getText();
        String floor_str = roomFloor.getText();
        String room = roomLabel.getText();

        if (roomOwnerID_str == null || roomOwnerID_str.isEmpty() || area_str == null || area_str.isEmpty() ||
                floor_str == null || floor_str.isEmpty() || room == null || room.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không được bỏ trống thông tin cần điền.");
            return;
        }

        String ownerID_str = roomOwnerID_str.substring(0, 1);
        Integer ownerId = !(ownerID_str.equals("N")) ? Integer.parseInt(ownerID_str) : null;
        float area = Float.parseFloat(area_str);
        int floor = Integer.parseInt(floor_str);

        if (existingApartments.isEmpty()){
            newApartment = ApartmentAPI.addApartment(ownerId, area, floor, room);
        } else {
            boolean roomExists = existingApartments.stream()
                    .anyMatch(apartment -> apartment.getRoom().equalsIgnoreCase(room));

            if (roomExists) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Phòng đang muốn thêm đã tồn tại. Hãy thêm một phòng khác!");
                return;
            } else {
                newApartment = ApartmentAPI.addApartment(ownerId, area, floor, room);
            }
        }

        Stage stage = (Stage) roomOwnerName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) roomOwnerName.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

