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
import java.util.Optional;

public class ButtonPanel_Controller {
    // ==================================================================
    // BẢNG QUẢN LÝ CĂN HỘ ==============================================

    @FXML
    private TextField apartmentSearchField;
    @FXML
    private TableView<Apartment> tableAddApartment;
    @FXML
    private TableColumn<Apartment, Number> roomAreaColumn, roomFloorColumn;
    @FXML
    private TableColumn<Apartment, String> roomOwnerNameColumn, roomLabelColumn;
    @FXML
    private Button addApartmentButton, editApartmentButton, deleteApartmentButton, showOwnerButton;

    private List<Apartment> apartmentList = ApartmentAPI.getAllApartment();
    private FilteredList<Apartment> filteredApartmentList;
    ObservableList<Apartment> observableApartmentList = FXCollections.observableArrayList(apartmentList);

    // ==================================================================
    // BẢNG QUẢN LÝ DÂN CƯ ==============================================
    @FXML
    private TextField residentSearchField;
    @FXML
    private TableView<Resident> tableAddResident;
    @FXML
    private TableColumn<Resident, String> residentRoomLabelColumn, residentNameColumn,
            residentDOBColumn, residentGenderColumn, residentPhoneNumberColumn,
            residentNationColumn, residentRelationshipColumn, residentStatusColumn, residentIsOwnerColumn;
    @FXML
    private Button addResidentButton, editResidentButton, deleteResidentButton, showActivityButton;

    @FXML
    private Label absentCountLabel, permanentCountLabel, temporaryCountLabel, sumOfResidentLabel;
    @FXML
    private Label carCountLabel, motorbikeCountLabel, bicycleCountLabel, otherTransportCountLabel;

    private List<Resident> residentList = ResidentAPI.getAllResidents();
    private List<Resident> residentListToGetIndex = ResidentAPI.getAllDataFromResidentTableInDB();//new ArrayList<>();
    private FilteredList<Resident> filteredResidentList;
    ObservableList<Resident> observableResidentList = FXCollections.observableArrayList(residentList);

    // ==================================================================
    // BẢNG QUẢN LÝ PHƯƠNG TIỆN ==============================================
    @FXML
    private TextField vehicleSearchField;
    @FXML
    private TableView<Vehicle> tableAddVehicle;
    @FXML
    private TableColumn<Vehicle, String> vehicleOwnerNameColumn, vehicleType, vehicleLicense;
    @FXML
    private Button addVehicleButton, deleteVehicleButton;

    private List<Vehicle> vehicleList = VehicleAPI.getAllVehicles();
    private FilteredList<Vehicle> filteredVehicleList;
    ObservableList<Vehicle> observableVehicleList = FXCollections.observableArrayList(vehicleList);


    @FXML
    public void initialize() {
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
        showActivityButton.setOnAction(event -> showActivityByID());
        showOwnerButton.setOnAction(event -> showOwnerByID());

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
            if (cellData.getValue().getIsOwner()) {
                return new SimpleStringProperty("Có");
            } else {
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

            controller.setExistingApartments(apartmentList);

            Stage stage = new Stage();
            stage.setTitle("Thêm căn hộ");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            Apartment newApartment = controller.getNewApartment();

            if (newApartment != null) {

                apartmentList.add(newApartment);
                observableApartmentList.setAll(apartmentList);
                tableAddApartment.refresh();

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
                alert.setTitle("Lỗi");
                alert.setHeaderText("Không có phòng nào được chọn");
                alert.setContentText("Hãy chọn một phòng để cập nhật.");
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
                ResidentAPI.updateResidentById(oldOwner, null);
                //observableResidentList.addAll(residentList);
                observableResidentList.setAll(residentList);
                tableAddResident.refresh();
            }

            if (updatedApartment != null) {
                int index = apartmentList.indexOf(selectedApartment);
                apartmentList.set(index, updatedApartment);
                observableApartmentList.setAll(apartmentList);
                tableAddApartment.refresh();
            }

            if (residentNeedToBeAdded != null) {
                System.out.println("New resident need to be added");
                residentList.add(residentNeedToBeAdded);
                observableResidentList.setAll(residentList);
                residentListToGetIndex.add(residentNeedToBeAdded);
                if (currentOwnerId != null) {
                    System.out.println("Old owner id is: " + currentOwnerId);
                    Resident oldOwner = ResidentAPI.getResidentById(currentOwnerId);
                    int index = residentList.indexOf(oldOwner);
                    System.out.println("index of old owner is: " + index);
                    oldOwner.setIsOwner(false);
                    ResidentAPI.updateResidentById(oldOwner, null);
                    residentList.set(index, oldOwner);
                    observableResidentList.setAll(residentList);
                    tableAddResident.refresh();
                }

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
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không thể mở giao diện sửa căn hộ.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    private void deleteSelectedApartment() {
        Apartment selectedApartment = tableAddApartment.getSelectionModel().getSelectedItem();

        if (selectedApartment == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không có phòng nào được chọn");
            alert.setContentText("Hãy chọn một phòng để xóa.");
            alert.showAndWait();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Xác nhận xóa");
        confirmationAlert.setHeaderText("Bạn có chắc muốn xóa phòng này không?");
        confirmationAlert.setContentText("Phòng: " + selectedApartment.getRoom());

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                apartmentList.remove(selectedApartment);
                observableApartmentList.remove(selectedApartment);
                tableAddApartment.refresh();

                residentList.removeIf(resident -> resident.getApartmentId() == selectedApartment.getApartmentId());
                observableResidentList.setAll(residentList);
                tableAddResident.refresh();
                updatePermanentCount();
                updateTemporaryCount();
                updateAbsentCount();
                updateSumOfResident();

                vehicleList.removeIf(vehicle -> {
                    Resident resident = ResidentAPI.getResidentById(vehicle.getResidentId());
                    return resident != null && resident.getApartmentId() == selectedApartment.getApartmentId();
                });

                observableVehicleList.setAll(vehicleList);
                tableAddVehicle.refresh();
                updateCarCount();
                updateBicycleCount();
                updateMotorbikeCount();
                updateỌtherTransportCount();

                ApartmentAPI.deleteApartment(selectedApartment.getApartmentId());

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Xóa thành công");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Đã xóa phòng.");
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
                        if (resident.getIsOwner()) {
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

                if (newResident.getIsOwner()) {
                    residentList.add(newResident);
                    residentListToGetIndex.add(newResident);
                    observableResidentList.setAll(residentList); // Refresh observable list
                    tableAddResident.refresh();

                    Apartment roomOfResident = ApartmentAPI.getApartmentById(newResident.getApartmentId());

                    int index = apartmentList.indexOf(roomOfResident);
                    roomOfResident.setOwnerId(newResident.getResidentId());
                    apartmentList.set(index, roomOfResident);
                    observableApartmentList.setAll(apartmentList);
                    tableAddApartment.refresh();
                    ApartmentAPI.updateOwnerApartment(roomOfResident.getApartmentId(), newResident.getResidentId());
                } else {
                    residentList.add(newResident);
                    residentListToGetIndex.add(newResident);
                    observableResidentList.setAll(residentList); // Refresh observable list
                    tableAddResident.refresh();
                }
            }

//            if (newResident != null) {
//
//                if (newResident.getIsOwner()) {
//                    Apartment roomOfResident = ApartmentAPI.getApartmentById(newResident.getApartmentId());
//
//                    if (roomOfResident.getOwnerId() != null) {
//                        // Create a warning alert
//                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                        alert.setTitle("Cảnh báo");
//                        alert.setHeaderText("Phòng này đang có chủ sở hữu.");
//                        alert.setContentText("Bạn có muốn thay thế chủ sở hữu mới không?");
//
//                        // Add Yes and No buttons
//                        ButtonType buttonYes = new ButtonType("Có");
//                        ButtonType buttonNo = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//                        alert.getButtonTypes().setAll(buttonYes, buttonNo);
//
//                        Optional<ButtonType> result = alert.showAndWait();
//
//                        // If Yes is clicked, run the code outside the block
//                        if (result.isPresent() && result.get() == buttonYes) {
//                            residentList.add(newResident);
//                            residentListToGetIndex.add(newResident);
//                            observableResidentList.setAll(residentList); // Refresh observable list
//                            tableAddResident.refresh();
//
//                            ResidentAPI.addResident(
//                                    ApartmentAPI.getApartmentById(newResident.getApartmentId()).getRoom(),
//                                    newResident.getName(), newResident.getBirthday(),
//                                    newResident.getGender(), newResident.getPhoneNumber(),
//                                    newResident.getNationality(), newResident.getRelationshipWithOwner(),
//                                    true,
//                                    newResident.getStatus(),
//                                    null);
//
//                            int index = apartmentList.indexOf(roomOfResident);
//                            roomOfResident.setOwnerId(newResident.getResidentId());
//                            apartmentList.set(index, roomOfResident);
//                            observableApartmentList.setAll(apartmentList);
//                            tableAddApartment.refresh();
//                            ApartmentAPI.updateOwnerApartment(roomOfResident.getApartmentId(), newResident.getResidentId());
//                        }
//                    }
//                     else {
//                        residentList.add(newResident);
//                        residentListToGetIndex.add(newResident);
//                        observableResidentList.setAll(residentList); // Refresh observable list
//                        tableAddResident.refresh();
//                    }
//
//                    int index = apartmentList.indexOf(roomOfResident);
//                    roomOfResident.setOwnerId(newResident.getResidentId());
//                    apartmentList.set(index, roomOfResident);
//                    observableApartmentList.setAll(apartmentList);
//                    tableAddApartment.refresh();
//                    ApartmentAPI.updateOwnerApartment(roomOfResident.getApartmentId(), newResident.getResidentId());
            updatePermanentCount();
            updateTemporaryCount();
            updateAbsentCount();
            updateSumOfResident();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không thể mở giao diện thêm cư dân.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    public void openEditResidentWindow() {
        try {
            Resident selectedResident = tableAddResident.getSelectionModel().getSelectedItem();

            if (selectedResident == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Không có cư dân nào được chọn");
                alert.setContentText("Hãy chọn một cư dân để sửa thông tin.");
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

                } else {
                    if (updatedResident.getIsOwner()){
                        Apartment apartment = ApartmentAPI.getApartmentById(updatedResident.getApartmentId());
                        int indexOfApartment = apartmentList.indexOf(apartment);
                        apartment.setOwnerId(updatedResident.getResidentId());
                        apartmentList.set(indexOfApartment, apartment);
                        observableApartmentList.setAll(apartmentList);
                        tableAddApartment.refresh();
                    }
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
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không thể mở giao diện sửa cư dân.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    public void deleteSelectedResident() {
        Resident selectedResident = tableAddResident.getSelectionModel().getSelectedItem();

        if (selectedResident == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không có cư dân nào được chọn");
            alert.setContentText("Hãy chọn một cư dân để xóa.");
            alert.showAndWait();
            return;
        }

        int selectedResidentId = selectedResident.getResidentId();

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Xác nhận xóa");
        confirmAlert.setHeaderText("Bạn có chắc muốn xóa cư dân này không?");
        confirmAlert.setContentText("Hành động này không thể khôi phục.");
        ButtonType confirmButton = new ButtonType("Xóa", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Hủy", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmAlert.getButtonTypes().setAll(confirmButton, cancelButton);

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == confirmButton) {
                residentList.remove(selectedResident);
                observableResidentList.setAll(residentList);
                tableAddResident.refresh();

                List<Vehicle> allVehicleOwnedByTheDeletedResident = VehicleAPI.getAllVehiclesByResidentId(selectedResidentId);

                if (!allVehicleOwnedByTheDeletedResident.isEmpty()){
                    vehicleList.removeAll(allVehicleOwnedByTheDeletedResident);
                    observableVehicleList.setAll(vehicleList);
                    tableAddVehicle.refresh();

                    updateCarCount();
                    updateBicycleCount();
                    updateMotorbikeCount();
                    updateỌtherTransportCount();
                }


//                if (selectedResident.getIsOwner()) {
//                    //List<Apartment> apartmentsNeedToBeModfied = new ArrayList<>();
//                    for (Apartment apartment: apartmentList){
//                        if (apartment.getOwnerId() == selectedResidentId){
//                            apartment.setOwnerId(null);
//                            ApartmentAPI.updateOwnerApartment(apartment.getApartmentId(), null);
//                        }
//                    }
////                    Apartment apartment = ApartmentAPI.getApartmentById(selectedResident.getApartmentId());
////                    int index = apartmentList.indexOf(apartment);
////                    apartment.setOwnerId(null);
////                    apartmentList.set(index, apartment);
//                    observableApartmentList.setAll(apartmentList);
//                    tableAddApartment.refresh();
//                }

                for (Apartment apartment: apartmentList){
                    if (apartment.getOwnerId() != null && apartment.getOwnerId() == selectedResidentId){
                        apartment.setOwnerId(null);
                        ApartmentAPI.updateOwnerApartment(apartment.getApartmentId(), null);
                    }
                }
                observableApartmentList.setAll(apartmentList);
                tableAddApartment.refresh();

                ResidentAPI.deleteResidentById(selectedResidentId, null);

                updateAbsentCount();
                updatePermanentCount();
                updateTemporaryCount();
                updateSumOfResident();

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Xóa thành công");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Cư dân đã được xóa.");
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

            // Set up the stage for the popup
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Thêm phương tiện");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            Vehicle newVehicle = addVehicleController.getNewVehicle();
            if (newVehicle != null) {
                vehicleList.add(newVehicle);
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
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không thể mở giao diện thêm phương tiện.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteSelectedVehicle() {
        Vehicle selectedVehicle = tableAddVehicle.getSelectionModel().getSelectedItem();

        if (selectedVehicle == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không có phương tiện nào được chọn");
            alert.setContentText("Hãy chọn một phương tiện để xóa.");
            alert.showAndWait();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Xác nhận xóa");
        confirmationAlert.setHeaderText("Xóa phương tiện");
        confirmationAlert.setContentText("Bạn có chắc muốn xóa phương tiện này không?");

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

    public void showActivityByID() {
        try {
            Resident selectedResident = tableAddResident.getSelectionModel().getSelectedItem();

            if (selectedResident == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Không có cư dân nào được chọn");
                alert.setContentText("Hãy chọn một cư dân để sửa thông tin.");
                alert.showAndWait();
                return;
            }

            int id = selectedResident.getResidentId();

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
        catch (Exception e) {
            System.out.println("Error");
        }
    }


    //Gọi PopUp hiển thị thông tin của căn hộ
    private void showOwnerByID() {
        try {
            Apartment selectedApartment = tableAddApartment.getSelectionModel().getSelectedItem();

            if (selectedApartment == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Không có phòng nào được chọn");
                alert.setContentText("Hãy chọn một phòng để xóa.");
                alert.showAndWait();
                return;
            }

            int id = selectedApartment.getApartmentId();

            DataManager dataManager = DataManager.getInstance();
            dataManager.setApartmentID(id);

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/views/resident_management/show_owner.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Apartment Management System");
            stage.setResizable(false);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không thể mở giao diện thống kê nhân khẩu.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


}
