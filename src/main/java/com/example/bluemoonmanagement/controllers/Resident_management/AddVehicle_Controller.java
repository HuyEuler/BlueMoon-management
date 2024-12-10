package com.example.bluemoonmanagement.controllers.Resident_management;

import com.example.bluemoonmanagement.api.ApartmentAPI;
import com.example.bluemoonmanagement.api.VehicleAPI;
import com.example.bluemoonmanagement.models.Resident;
import com.example.bluemoonmanagement.models.Vehicle;
import com.example.bluemoonmanagement.api.ResidentAPI;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class AddVehicle_Controller {

    @FXML private ChoiceBox<String> vehicleOwnerID;
    @FXML private TextField vehicleOwnerName;
    @FXML private ChoiceBox<String> vehicleType;
    @FXML private TextField vehicleLicense;

    private Vehicle vehicle;
    private List<Vehicle> existingVehicles; // To check for duplicates


    public void setExistingVehicles(List<Vehicle> existingVehicles) {
        this.existingVehicles = existingVehicles;
    }

    public Vehicle getNewVehicle(){
        return vehicle;
    }

    @FXML
    private void initialize() {

        for (Resident resident : ResidentAPI.getAllResidents()) {
            vehicleOwnerID.getItems().add(resident.getResidentId() + ": " + resident.getName());
        }

        vehicleType.setItems(FXCollections.observableArrayList("Ô tô", "Xe máy", "Xe đạp", "Khác"));

        vehicleOwnerID.setOnAction(event -> {
            int selectedId = parseNumber(vehicleOwnerID.getValue());

            Resident resident = ResidentAPI.getResidentById(selectedId);
            if (resident != null) {
                vehicleOwnerName.setText(resident.getName());
                vehicleOwnerName.setEditable(false);
            }
        });
    }

    @FXML
    private void handleSave() {

        String resID = vehicleOwnerID.getValue();
        String type = vehicleType.getValue();
        String licensePlate = vehicleLicense.getText();

        if (resID == null || type == null || licensePlate == null){
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Không được bỏ trống thông tin nào khi thêm.");
            return;
        }

        int residentId = parseNumber(resID);

        if (existingVehicles.isEmpty()) {
            VehicleAPI.addVehicle(residentId, type, licensePlate);
            List<Vehicle> veh = VehicleAPI.getAllVehicles();
            int idOfNewAddedVehicle = veh.getLast().getVehicleId();
            vehicle = new Vehicle(idOfNewAddedVehicle, residentId, type, licensePlate);
        } else {
            boolean vehicleExists = existingVehicles.stream()
                    .anyMatch(vehicle -> vehicle.getLicensePlate().equalsIgnoreCase(licensePlate));

            if (vehicleExists) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Phương tiện đang muốn thêm đã tồn tại. Hãy thêm một phương tiện khác!");
                return;
            } else {
                VehicleAPI.addVehicle(residentId, type, licensePlate);
                List<Vehicle> veh = VehicleAPI.getAllVehicles();
                int idOfNewAddedVehicle = veh.getLast().getVehicleId();
                vehicle = new Vehicle(idOfNewAddedVehicle, residentId, type, licensePlate);
            }
        }

        Stage stage = (Stage) vehicleOwnerName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) vehicleOwnerName.getScene().getWindow();
        stage.close();
    }

    private int parseNumber(String input){
        String[] parts = input.split(": ");
        return Integer.parseInt(parts[0]);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
