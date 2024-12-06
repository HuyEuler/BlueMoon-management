package com.example.bluemoonmanagement.controllers.Resident_management;

import com.example.bluemoonmanagement.api.VehicleAPI;
import com.example.bluemoonmanagement.models.Resident;
import com.example.bluemoonmanagement.models.Vehicle;
import com.example.bluemoonmanagement.api.ResidentAPI;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
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
    private List<Integer> existingVehicles; // To check for duplicates


    public void setExistingEntries(List<Integer> existingVehicles) {
        this.existingVehicles = existingVehicles;
    }

    public Vehicle getNewVehicle(){
        return vehicle;
    }

    @FXML
    private void initialize() {
        // Update owner name based on selected resident ID
        for (Resident resident : ResidentAPI.getAllResidents()) {
            vehicleOwnerID.getItems().add(String.valueOf(resident.getResidentId()));
        }

        vehicleType.setItems(FXCollections.observableArrayList("Ô tô", "Xe máy", "Xe đạp", "Khác"));

        vehicleOwnerID.setOnAction(event -> {
            String selectedId = vehicleOwnerID.getValue();
            if (selectedId != null) {
                Resident resident = ResidentAPI.getResidentById(Integer.parseInt(selectedId));
                if (resident != null) {
                    vehicleOwnerName.setText(resident.getName());
                    vehicleOwnerName.setEditable(false);
                }
            }
        });
    }

    @FXML
    private void handleSave() {

        int residentId = Integer.parseInt(vehicleOwnerID.getValue());
        String type = vehicleType.getValue();
        String licensePlate = vehicleLicense.getText();

        vehicle = new Vehicle(existingVehicles.get(existingVehicles.size()-1)+1, residentId, type, licensePlate);
        VehicleAPI.addVehicle(residentId, type, licensePlate);

        // Close the dialog
        Stage stage = (Stage) vehicleOwnerName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        // Close the dialog without saving
        Stage stage = (Stage) vehicleOwnerName.getScene().getWindow();
        stage.close();
    }
}
