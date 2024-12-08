package com.example.bluemoonmanagement.controllers.Resident_management;
import com.example.bluemoonmanagement.MainApplication;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.example.bluemoonmanagement.models.Apartment;
import com.example.bluemoonmanagement.models.Resident;
import com.example.bluemoonmanagement.models.Vehicle;
import com.example.bluemoonmanagement.api.ApartmentAPI;
import com.example.bluemoonmanagement.api.ResidentAPI;
import com.example.bluemoonmanagement.api.VehicleAPI;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ButtonPanel_Controller {
    // ==================================================================
    // BẢNG QUẢN LÝ CĂN HỘ ==============================================

    @FXML private TextField apartmentSearchField;
    @FXML private TableView<Apartment> tableAddApartment;
    @FXML private TableColumn<Apartment, Number> roomAreaColumn, roomFloorColumn;
    @FXML private TableColumn<Apartment, String> roomOwnerNameColumn, roomLabelColumn;
    @FXML private Button addApartmentButton, editApartmentButton, deleteApartmentButton, showOwnerButton;

    private List<Apartment> apartmentList = ApartmentAPI.getAllApartment();
    private List<Apartment> apartmentListToGetIndex = new ArrayList<>(apartmentList);
    private FilteredList<Apartment> filteredApartmentList;
    ObservableList<Apartment> observableApartmentList = FXCollections.observableArrayList(apartmentList);

    // ==================================================================
    // BẢNG QUẢN LÝ DÂN CƯ ==============================================
    @FXML private TextField residentSearchField;
    @FXML private TableView<Resident> tableAddResident;
    @FXML private TableColumn<Resident, String> residentRoomLabelColumn, residentNameColumn,
            residentDOBColumn, residentGenderColumn, residentPhoneNumberColumn,
            residentNationColumn, residentRelationshipColumn, residentStatusColumn, residentIsOwnerColumn;
    @FXML private Button addResidentButton, editResidentButton, deleteResidentButton, showActivityButton;

    @FXML private Label absentCountLabel, permanentCountLabel, temporaryCountLabel, sumOfResidentLabel;
    @FXML private Label carCountLabel, motorbikeCountLabel, bicycleCountLabel, otherTransportCountLabel;

    private List<Resident> residentList = ResidentAPI.getAllResidents();
    private List<Resident> residentListToGetIndex = ResidentAPI.getAllDataFromResidentTableInDB();//new ArrayList<>();
    private FilteredList<Resident> filteredResidentList;
    ObservableList<Resident> observableResidentList = FXCollections.observableArrayList(residentList);

    // ==================================================================
    // BẢNG QUẢN LÝ PHƯƠNG TIỆN ==============================================
    @FXML private TextField vehicleSearchField;
    @FXML private TableView<Vehicle> tableAddVehicle;
    @FXML private TableColumn<Vehicle, String> vehicleOwnerNameColumn, vehicleType, vehicleLicense;
    @FXML private Button addVehicleButton, deleteVehicleButton;

    private List<Vehicle> vehicleList = VehicleAPI.getAllVehicles();
    private List<Vehicle> vehicleListToGetIndex = new ArrayList<>(vehicleList);
    private FilteredList<Vehicle> filteredVehicleList;
    ObservableList<Vehicle> observableVehicleList = FXCollections.observableArrayList(vehicleList);


    @FXML public void initialize() {
        // ================================================================
        // BẮT ĐẦU BẢNG QUẢN LÝ CĂN HỘ ====================================

        filteredApartmentList = new FilteredList<>(observableApartmentList, p -> true);
        tableAddApartment.setItems(filteredApartmentList);

        roomOwnerNameColumn.setCellValueFactory(
                cellData -> {
                    Integer residentID = cellData.getValue().getOwnerId();
                    //System.out.println(residentID);
                    String ownerName = (residentID == null) ? null : ResidentAPI.getResidentById(residentID).getName();
                    return new SimpleStringProperty(ownerName);
                }
        );
        roomAreaColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getArea()));
        roomFloorColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFloor()));
        roomLabelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoom()));

        tableAddApartment.setItems(observableApartmentList);

        apartmentSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredApartmentList.setPredicate(apartment -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return apartment.getRoom().toLowerCase().contains(lowerCaseFilter); // Match found
            });
            tableAddApartment.refresh();
        });

        tableAddApartment.setRowFactory(tv -> new TableRow<Apartment>() {
            @Override
            protected void updateItem(Apartment apartment, boolean empty) {
                super.updateItem(apartment, empty);

                if (empty || apartment == null) {
                    setStyle("");
                } else {
                    String searchText = apartmentSearchField.getText().toLowerCase();
                    if (!searchText.isEmpty() && apartment.getRoom().toLowerCase().equals(searchText)) {
                        setStyle("-fx-background-color: lightblue;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        deleteApartmentButton.setOnAction(event -> deleteSelectedApartment());
        addApartmentButton.setOnAction(event -> openAddApartmentWindow());
        editApartmentButton.setOnAction(event -> openEditApartmentWindow());

        // ================================================================
        // BẮT ĐẦU BẢNG QUẢN LÝ DÂN CƯ ====================================

        filteredResidentList = new FilteredList<>(observableResidentList, p -> true);
        tableAddResident.setItems(filteredResidentList);

        residentRoomLabelColumn.setCellValueFactory(
                cellData -> {
                    int roomId = cellData.getValue().getApartmentId();
                    Apartment apartment = ApartmentAPI.getApartmentById(roomId);
                    String roomLabel = apartment.getRoom();
                    return new SimpleStringProperty(roomLabel);
                }
        );

        residentNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        residentDOBColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBirthday()));
        residentGenderColumn.setCellValueFactory(cellData -> {
                    boolean gender = cellData.getValue().getGender();
                    return new SimpleStringProperty(gender ? "Nam" : "Nữ");
        }
        );

        residentPhoneNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        residentNationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNationality()));
        residentRelationshipColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRelationshipWithOwner()));
        residentIsOwnerColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getIsOwner()){
                return new SimpleStringProperty("Có");
            }
            else{
                return new SimpleStringProperty("Không");
            }
        });
        residentStatusColumn.setCellValueFactory(cellData -> {
            String st = cellData.getValue().getStatusDescription(cellData.getValue().getStatus());
            return new SimpleStringProperty(st);
        });

        tableAddResident.setItems(observableResidentList);

        updateAbsentCount();
        updatePermanentCount();
        updateTemporaryCount();
        updateSumOfResident();


        deleteResidentButton.setOnAction(event -> deleteSelectedResident());
        addResidentButton.setOnAction(event -> openAddResidentWindow());
        editResidentButton.setOnAction(event -> openEditResidentWindow());

        // ==================================================================

        showActivityButton.setOnAction(event -> {
            Stage inputStage = new Stage();
            inputStage.initModality(Modality.APPLICATION_MODAL);
            inputStage.setTitle("Enter Apartment ID");

            ChoiceBox<Integer> idChoiceBox = new ChoiceBox<>();
            List<Integer> availableRooms = apartmentList.stream()
                    .map(Apartment::getApartmentId)
                    .toList();

            idChoiceBox.getItems().addAll(availableRooms);

            // Create buttons
            Button confirmButton = new Button("OK");
            Button cancelButton = new Button("Cancel");

            // Layout
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(10));
            layout.getChildren().addAll(new Label("Please select the Apartment ID:"), idChoiceBox, confirmButton, cancelButton);

            // Scene
            Scene scene = new Scene(layout);
            inputStage.setScene(scene);

            // Confirm button functionality
            confirmButton.setOnAction(e -> {
                Integer selectedId = idChoiceBox.getValue();
                if (selectedId == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an Apartment ID.");
                    alert.showAndWait();
                    return;
                }

                inputStage.close();
                try {
                    showActivityByID(selectedId);
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading activity window: " + ex.getMessage());
                    alert.showAndWait();
                }
            });

            cancelButton.setOnAction(e -> inputStage.close());
            inputStage.showAndWait();
        });

        showOwnerButton.setOnAction(event -> {
            Stage inputStage = new Stage();
            inputStage.initModality(Modality.APPLICATION_MODAL);
            inputStage.setTitle("Enter Apartment ID");

            ChoiceBox<Integer> idChoiceBox = new ChoiceBox<>();
            List<Integer> availableRooms = apartmentList.stream()
                    .map(Apartment::getApartmentId)
                    .toList();

            idChoiceBox.getItems().addAll(availableRooms);

            // Create buttons
            Button confirmButton = new Button("OK");
            Button cancelButton = new Button("Cancel");

            // Layout
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(10));
            layout.getChildren().addAll(new Label("Please select the Apartment ID:"), idChoiceBox, confirmButton, cancelButton);

            // Scene
            Scene scene = new Scene(layout);
            inputStage.setScene(scene);

            // Confirm button functionality
            confirmButton.setOnAction(e -> {
                Integer selectedId = idChoiceBox.getValue();
                if (selectedId == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an Apartment ID.");
                    alert.showAndWait();
                    return;
                }

                inputStage.close();
                try {
                    showOwnerByID(selectedId);
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error loading activity window: " + ex.getMessage());
                    alert.showAndWait();
                }
            });

            cancelButton.setOnAction(e -> inputStage.close());
            inputStage.showAndWait();
        });

        // ==================================================================

        residentSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredResidentList.setPredicate(resident -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return resident.getName().toLowerCase().contains(lowerCaseFilter); // Match found
            });
            tableAddResident.refresh();
        });

        tableAddResident.setRowFactory(tv -> new TableRow<Resident>() {
            @Override
            protected void updateItem(Resident resident, boolean empty) {
                super.updateItem(resident, empty);
                if (empty || resident == null) {
                    setStyle("");
                } else {
                    String searchText = residentSearchField.getText().toLowerCase();
                    if (!searchText.isEmpty() && resident.getName().toLowerCase().equals(searchText)) {
                        setStyle("-fx-background-color: lightblue;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });


        // =====================================================================
        // BẢNG QUẢN LÝ PHƯƠNG TIỆN

        filteredVehicleList = new FilteredList<>(observableVehicleList, p -> true);
        tableAddVehicle.setItems(filteredVehicleList);

        deleteVehicleButton.setOnAction(event -> deleteSelectedVehicle());
        addVehicleButton.setOnAction(event -> openAddVehicleWindow());

        vehicleOwnerNameColumn.setCellValueFactory(cellData -> {
            String ownerName = ResidentAPI.getResidentById(cellData.getValue().getResidentId()).getName();
            return new SimpleStringProperty(ownerName);
        }
        );

        vehicleType.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getType());
        });

        vehicleLicense.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getLicensePlate());
        });

        tableAddVehicle.setItems(observableVehicleList);

        vehicleSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredVehicleList.setPredicate(vehicle -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return vehicle.getLicensePlate().toLowerCase().contains(lowerCaseFilter); // Match found
            });
            tableAddVehicle.refresh();
        });

        tableAddVehicle.setRowFactory(tv -> new TableRow<Vehicle>() {
            @Override
            protected void updateItem(Vehicle vehicle, boolean empty) {
                super.updateItem(vehicle, empty);

                if (empty || vehicle == null) {
                    setStyle("");
                } else {
                    String searchText = vehicleSearchField.getText().toLowerCase();
                    if (!searchText.isEmpty() && vehicle.getLicensePlate().toLowerCase().equals(searchText)) {
                        setStyle("-fx-background-color: lightblue;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        updateCarCount();
        updateBicycleCount();
        updateMotorbikeCount();
        updateỌtherTransportCount();

    }

    private void openAddApartmentWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/resident_management/add_apartment.fxml"));
            AnchorPane root = loader.load();
            AddApartment_Controller controller = loader.getController();

            List<Integer> existingEntries = apartmentListToGetIndex.stream()
                    .map(Apartment::getApartmentId)
                    .toList();
            controller.setExistingEntries(existingEntries);

            Stage stage = new Stage();
            stage.setTitle("Add Apartment");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            Apartment newApartment = controller.getNewApartment();

            if (newApartment != null) {

                boolean roomExists = apartmentList.stream()
                        .anyMatch(apartment -> apartment.getRoom().equalsIgnoreCase(newApartment.getRoom()));
                if (roomExists) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Duplicate Room");
                    alert.setHeaderText("Room Already Exists");
                    alert.setContentText("An apartment with the same room identifier already exists. Please use a different room identifier.");
                    alert.showAndWait();
                } else {
                    apartmentList.add(newApartment);
                    apartmentListToGetIndex.add(newApartment);
                    observableApartmentList.setAll(apartmentList);
                    tableAddApartment.refresh();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openEditApartmentWindow() {
        try {
            Apartment selectedApartment = tableAddApartment.getSelectionModel().getSelectedItem();

            if (selectedApartment == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText("No Apartment Selected");
                alert.setContentText("Please select an apartment to edit.");
                alert.showAndWait();
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/resident_management/edit_apartment.fxml"));
            AnchorPane root = loader.load();

            EditApartment_Controller editController = loader.getController();

            int selectedApartmentId = selectedApartment.getApartmentId();
            Integer currentOwnerId = selectedApartment.getOwnerId();

            editController.setCurrentOwnerId(currentOwnerId);

            editController.setApartmentData(selectedApartment, selectedApartmentId);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Chỉnh sửa căn hộ");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            Apartment updatedApartment = editController.getUpdatedApartment();
            Resident residentNeedToBeAdded = editController.getResidentNeedToBeAdded();
            Resident residentNeedToBeUpdated = editController.getResidentNeedToBeUpdated();

            if (currentOwnerId != null && residentNeedToBeUpdated != null &&
                    residentNeedToBeUpdated.getResidentId() != currentOwnerId) {
                Resident oldOwner = ResidentAPI.getResidentById(selectedApartment.getOwnerId());
                int index = residentList.indexOf(oldOwner);
                oldOwner.setIsOwner(false);
                residentList.set(index, oldOwner);
                observableResidentList.addAll(residentList);
                tableAddResident.refresh();
            }

            if (updatedApartment != null) {
                int index = apartmentList.indexOf(selectedApartment);
                apartmentList.set(index, updatedApartment);
                observableApartmentList.setAll(apartmentList);
                tableAddApartment.refresh();
            }

            if (residentNeedToBeAdded != null) {
                residentList.add(residentNeedToBeAdded);
                observableResidentList.setAll(residentList);
                residentListToGetIndex.add(residentNeedToBeAdded);
            }

            if (residentNeedToBeUpdated != null) {
                int index = residentList.indexOf(residentNeedToBeUpdated);
                residentList.set(index, residentNeedToBeUpdated);
                observableResidentList.setAll(residentList);
                tableAddResident.refresh();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to open the Edit Resident window.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    private void deleteSelectedApartment() {
        Apartment selectedApartment = tableAddApartment.getSelectionModel().getSelectedItem();

        if (selectedApartment == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Apartment Selected");
            alert.setContentText("Please select an apartment to delete.");
            alert.showAndWait();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText("Are you sure you want to delete this apartment?");
        confirmationAlert.setContentText("Room: " + selectedApartment.getRoom());

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                apartmentList.remove(selectedApartment);
                observableApartmentList.remove(selectedApartment);
                tableAddApartment.refresh();

                ApartmentAPI.deleteApartment(selectedApartment.getApartmentId());

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Deletion Successful");
                successAlert.setHeaderText(null);
                successAlert.setContentText("The apartment has been successfully deleted.");
                successAlert.showAndWait();
            }
        });
    }

    public void openAddResidentWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/resident_management/add_resident.fxml"));
            AnchorPane root = loader.load();
            AddResident_Controller addResidentController = loader.getController();

            int currentCount = residentListToGetIndex.size();

            addResidentController.setCurrentCount(currentCount);
            //System.out.println(residentListToGetIndex);

            List<String> availableRooms = apartmentList.stream()
                    .map(Apartment::getRoom)
                    .toList();
            addResidentController.setAvailableRooms(availableRooms);

            List<String> roomNameUniqueList = residentList.stream()
                    .map(resident -> resident.getApartmentId() + "|" + resident.getName())
                    .toList();
            addResidentController.setRoomNameUniqueList(roomNameUniqueList);

            List<String> roomOwnerUniqueList = residentList.stream()
                    .map(resident -> {
                        if (resident.getIsOwner()){
                            return resident.getApartmentId() + "|" + resident.getIsOwner();
                        }
                        return null;
                    })
                    .toList();
            addResidentController.setRoomOwnerUniqueList(roomOwnerUniqueList);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Thêm cư dân mới");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            Resident newResident = addResidentController.getNewResident();
            //System.out.println(newResident);

            if (newResident != null) {
                residentList.add(newResident);
                residentListToGetIndex.add(newResident);
                observableResidentList.setAll(residentList); // Refresh observable list
                tableAddResident.refresh(); // Refresh table view
                updatePermanentCount();
                updateTemporaryCount();
                updateAbsentCount();
                updateSumOfResident();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to open the Add Resident window.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    public void openEditResidentWindow() {
        try {
            Resident selectedResident = tableAddResident.getSelectionModel().getSelectedItem();

            if (selectedResident == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText("No Resident Selected");
                alert.setContentText("Please select a resident to edit.");
                alert.showAndWait();
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/resident_management/edit_resident.fxml"));
            AnchorPane root = loader.load();

            EditResident_Controller editController = loader.getController();

            int selectedResidentId = selectedResident.getResidentId();

            editController.setResidentData(selectedResident, selectedResidentId);
            List<String> availableRooms = apartmentList.stream().map(Apartment::getRoom).toList();
            editController.setAvailableRooms(availableRooms);
            editController.setResidentList(residentList);

            List<String> roomNameUniqueList = residentList.stream()
                    .map(resident -> resident.getApartmentId() + "|" + resident.getName())
                    .toList();
            editController.setRoomNameUniqueList(roomNameUniqueList);

            List<String> roomOwnerUniqueList = residentList.stream()
                    .map(resident -> {
                        if (resident.getIsOwner() && resident.getResidentId() != selectedResidentId){
                            return resident.getApartmentId() + "|" + resident.getIsOwner();
                        }
                        return null;
                    })
                    .toList();
            editController.setRoomOwnerUniqueList(roomOwnerUniqueList);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Chỉnh sửa cư dân");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Get the updated resident
            Resident updatedResident = editController.getUpdatedResident();
            if (updatedResident != null) {
                int index = residentList.indexOf(selectedResident);
                residentList.set(index, updatedResident);
                observableResidentList.setAll(residentList);

                if (updatedResident.getApartmentId() == selectedResident.getApartmentId()) {

                    Apartment apartment = ApartmentAPI.getApartmentById(selectedResident.getApartmentId());
                    int apartmentID = apartment.getApartmentId();
                    int indexOfAartment = apartmentList.indexOf(apartment);
                    Apartment newApartment = null;
                    //System.out.println("Index of apartment: " + indexOfAartment);

                    if (selectedResident.getIsOwner()) {
                        if (!updatedResident.getIsOwner()){
                            newApartment = new Apartment(apartmentID, null,
                                    apartment.getArea(), apartment.getFloor(), apartment.getRoom());
                            ApartmentAPI.updateOwnerApartment(apartmentID, null);
                        }
                    } else {
                        if (updatedResident.getIsOwner()){
                            newApartment = new Apartment(apartmentID,
                                    updatedResident.getResidentId(),
                                    apartment.getArea(), apartment.getFloor(), apartment.getRoom());
                            ApartmentAPI.updateOwnerApartment(apartmentID, updatedResident.getResidentId());
                        }
                    }
                    apartmentList.set(indexOfAartment, newApartment);
                    observableApartmentList.setAll(apartmentList);
                    tableAddApartment.refresh();

                }

                tableAddResident.refresh();
                updateAbsentCount();
                updatePermanentCount();
                updateTemporaryCount();
                updateSumOfResident();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to open the Edit Resident window.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    public void deleteSelectedResident() {
        Resident selectedResident = tableAddResident.getSelectionModel().getSelectedItem();

        if (selectedResident == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Resident Selected");
            alert.setContentText("Please select a resident to delete.");
            alert.showAndWait();
            return;
        }

        int selectedResidentId = selectedResident.getResidentId();

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText("Are you sure you want to delete this resident?");
        confirmAlert.setContentText("This action cannot be undone.");
        ButtonType confirmButton = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmAlert.getButtonTypes().setAll(confirmButton, cancelButton);

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == confirmButton) {
                residentList.remove(selectedResident);
                observableResidentList.setAll(residentList);

                tableAddResident.refresh();

                ResidentAPI.deleteResidentById(selectedResidentId, null);

                updateAbsentCount();
                updatePermanentCount();
                updateTemporaryCount();
                updateSumOfResident();

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Resident Deleted");
                successAlert.setHeaderText(null);
                successAlert.setContentText("The resident has been successfully deleted.");
                successAlert.showAndWait();
            }
        });
    }

    private void updateAbsentCount() {
        long count = residentList.stream()
                .filter(resident -> "Tạm vắng".equals(resident.getStatusDescription(resident.getStatus())))
                .count();
        absentCountLabel.setText(String.valueOf(count));
    }

    private void updatePermanentCount() {
        long count = residentList.stream()
                .filter(resident -> "Thường trú".equals(resident.getStatusDescription(resident.getStatus())))
                .count();
        permanentCountLabel.setText(String.valueOf(count));
    }

    private void updateTemporaryCount() {
        long count = residentList.stream()
                .filter(resident -> "Tạm trú".equals(resident.getStatusDescription(resident.getStatus())))
                .count();
        temporaryCountLabel.setText(String.valueOf(count));
    }

    private void updateSumOfResident(){
        long count = residentList.size() - residentList.stream()
                .filter(resident -> "Đã rời đi".equals(resident.getStatusDescription(resident.getStatus())))
                .count();
        sumOfResidentLabel.setText(String.valueOf(count));
    }

    @FXML
    private void openAddVehicleWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/resident_management/add_vehicle.fxml"));
            AnchorPane root = loader.load();
            AddVehicle_Controller addVehicleController = loader.getController();

            List<Integer> existingEntries = vehicleListToGetIndex.stream()
                    .map(Vehicle::getVehicleId)
                    .toList();
            addVehicleController.setExistingEntries(existingEntries);

            // Set up the stage for the popup
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Thêm phuong tien");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            Vehicle newVehicle = addVehicleController.getNewVehicle();
            if (newVehicle != null) {
                vehicleList.add(newVehicle);
                vehicleListToGetIndex.add(newVehicle);
                observableVehicleList.setAll(vehicleList);
                tableAddVehicle.refresh();
                updateCarCount();
                updateBicycleCount();
                updateMotorbikeCount();
                updateỌtherTransportCount();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to open the Add Resident window.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteSelectedVehicle() {
        Vehicle selectedVehicle = tableAddVehicle.getSelectionModel().getSelectedItem();

        if (selectedVehicle == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Vehicle Selected");
            alert.setContentText("Please select a vehicle to delete.");
            alert.showAndWait();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Deletion");
        confirmationAlert.setHeaderText("Delete Vehicle");
        confirmationAlert.setContentText("Are you sure you want to delete this vehicle?");

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {

                vehicleList.remove(selectedVehicle);
                observableVehicleList.setAll(vehicleList);
                VehicleAPI.deleteVehicle(selectedVehicle.getVehicleId());
                tableAddVehicle.refresh();
                updateCarCount();
                updateBicycleCount();
                updateMotorbikeCount();
                updateỌtherTransportCount();

            }
        });
    }

    private void updateCarCount() {
        long count = vehicleList.stream()
                .filter(vehicle -> "Ô tô".equals(vehicle.getType()))
                .count();
        carCountLabel.setText(String.valueOf(count));
    }

    private void updateMotorbikeCount() {
        long count = vehicleList.stream()
                .filter(vehicle -> "Xe máy".equals(vehicle.getType()))
                .count();
        motorbikeCountLabel.setText(String.valueOf(count));
    }

    private void updateBicycleCount() {
        long count = vehicleList.stream()
                .filter(vehicle -> "Xe đạp".equals(vehicle.getType()))
                .count();
        bicycleCountLabel.setText(String.valueOf(count));
    }

    private void updateỌtherTransportCount() {
        long count = vehicleList.stream()
                .filter(vehicle -> "Khác".equals(vehicle.getType()))
                .count();
        otherTransportCountLabel.setText(String.valueOf(count));
    }

    private void showActivityByID(int id) throws IOException {
        DataManager dataManager = DataManager.getInstance();
        dataManager.setApartmentID(id);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/views/resident_management/show_activity.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Apartment Management System");
        stage.setResizable(false);
        stage.showAndWait();
    }


    //Gọi PopUp hiển thị thông tin của căn hộ
    public void showOwnerByID(int id) throws IOException {
        DataManager dataManager = DataManager.getInstance();
        dataManager.setApartmentID(id);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/views/resident_management/show_owner.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Apartment Management System");
        stage.setResizable(false);
        stage.showAndWait();
    }


}
