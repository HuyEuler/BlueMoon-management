package com.example.bluemoonmanagement.controllers.Resident_management;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

// ============================== Added code

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

// ============================== Added code


public class ButtonPanel_Controller {

    // =================================================================
    // BẢNG QUẢN LÝ CĂN HỘ

    @FXML private TextField searchField;
    @FXML private TableView<Owner> tableAddOwner;
    @FXML private TableColumn<Owner, Number> counterColumn;
    @FXML private TableColumn<Owner, String> tangColumn, phongColumn, dienTichColumn, tenChuSoHuuColumn, soDienThoaiColumn;
    @FXML private Button themChuCanHoButton;

    private ObservableList<Owner> ownerList = FXCollections.observableArrayList();
    private FilteredList<Owner> filteredOwnerList;

    // ==================================== Xóa chủ căn hộ
    @FXML private Button xoaChuCanHoButton;
    private boolean isDeleteButtonClicked = false;
    // ==================================== Xóa chủ căn hộ


    // ==================================== Sửa chủ căn hộ
    @FXML private Button editOwnerButton;
    // ==================================== Sửa chủ căn hộ

    @FXML public void initialize() {
        // ================================================================
        // BẮT ĐẦU BẢNG QUẢN LÝ CĂN HỘ

        filteredOwnerList = new FilteredList<>(ownerList, p -> true);
        tableAddOwner.setItems(filteredOwnerList);


        counterColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(tableAddOwner.getItems().indexOf(cellData.getValue()) + 1)
        );
        tangColumn.setCellValueFactory(cellData -> cellData.getValue().tangProperty());
        phongColumn.setCellValueFactory(cellData -> cellData.getValue().phongProperty());
        dienTichColumn.setCellValueFactory(cellData -> cellData.getValue().dienTichProperty());
        tenChuSoHuuColumn.setCellValueFactory(cellData -> cellData.getValue().tenChuSoHuuProperty());
        soDienThoaiColumn.setCellValueFactory(cellData -> cellData.getValue().soDienThoaiProperty());
        tableAddOwner.setItems(ownerList);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredOwnerList.setPredicate(owner -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return owner.phongProperty().get().toLowerCase().contains(lowerCaseFilter); // Match found
            });
            tableAddOwner.refresh();
        });


        // ============================ Added to highlight the table

        tableAddOwner.setRowFactory(tv -> new TableRow<Owner>() {
            @Override
            protected void updateItem(Owner owner, boolean empty) {
                super.updateItem(owner, empty);

                if (empty || owner == null) {
                    setStyle(""); // Reset style if no item
                } else {
                    // Highlight row if it matches the search term in "phong"
                    String searchText = searchField.getText().toLowerCase();
                    if (!searchText.isEmpty() && owner.phongProperty().get().toLowerCase().equals(searchText)) {
                        setStyle("-fx-background-color: lightblue;"); // Highlight color
                    } else {
                        setStyle(""); // Clear highlight if it doesn't match
                    }
                }
            }
        });
        // ============================ Added to highlight the table


        // ============================================================== Xóa chủ căn hộ

//        tableAddOwner.setRowFactory(tv -> {
//            TableRow<Owner> row = new TableRow<>();
//            row.setOnContextMenuRequested(event -> {
//                if (!row.isEmpty()) {
//                    tableAddOwner.getSelectionModel().select(row.getItem());
//                }
//            });
//            return row;
//        });

//        tableAddOwner.focusedProperty().addListener((observable, oldValue, newValue) -> {
//            if (!newValue && !isDeleteButtonClicked) {
//                tableAddOwner.getSelectionModel().clearSelection();
//            }
//        });
//
//        // Assign action handlers for buttons
//        xoaChuCanHoButton.setOnAction(event -> {
//            isDeleteButtonClicked = true;  // Set flag before handling delete
//            deleteSelectedOwner();
//            isDeleteButtonClicked = false; // Reset flag after handling delete
//        });

        xoaChuCanHoButton.setOnAction(event -> deleteSelectedOwner());

        // ============================================================== Xóa chủ căn hộ

        themChuCanHoButton.setOnAction(event -> openAddOwnerWindow());

        // ========================================== Sửa chủ căn hộ
        editOwnerButton.setOnAction(event -> openEditOwnerDialog());
        // ========================================== Sửa chủ căn hộ

    }

    private void openEditOwnerDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/resident_management/edit_owner.fxml"));
            AnchorPane root = loader.load();

            // Get the controller to pass the TableView
            EditOwner_Controller controller = loader.getController();
            controller.setTableView(tableAddOwner);

            Stage stage = new Stage();
            stage.setTitle("Edit Owner");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //=================================================== Xóa chủ căn hộ

    private void deleteSelectedOwner() {
        Owner selectedOwner = tableAddOwner.getSelectionModel().getSelectedItem();
        if (selectedOwner != null) {
            // Show confirmation dialog
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Confirm Deletion");
            confirmDialog.setHeaderText("Are you sure you want to delete this entry?");
            confirmDialog.setContentText("Owner: " + selectedOwner.tenChuSoHuuProperty().get());

            // Wait for user confirmation
            confirmDialog.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Remove owner from list
                    ownerList.remove(selectedOwner);
                }
            });
        } else {
            // Show warning if no row is selected
            Alert warningDialog = new Alert(Alert.AlertType.WARNING);
            warningDialog.setTitle("No Selection");
            warningDialog.setHeaderText("No Owner Selected");
            warningDialog.setContentText("Please select an owner to delete.");
            warningDialog.showAndWait();
        }
    }
    // ========================================= Xóa chủ căn hộ

    @FXML
    private void openAddOwnerWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/resident_management/add_owner.fxml"));
            AnchorPane ownerDialog = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Thêm chủ căn hộ");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(themChuCanHoButton.getScene().getWindow());
            dialogStage.setScene(new Scene(ownerDialog));

            AddOwner_Controller dialogController = loader.getController();
            dialogStage.showAndWait();  // Wait for the dialog to close

            Owner newOwner = dialogController.getOwner();
            if (newOwner != null) {
                ownerList.add(newOwner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================================== Added code

    @FXML
    private void openAddResidentWindow() {
        try {
            // Load the add_resident.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/resident_management/add_resident.fxml"));
            Parent root = loader.load();

            // Create a new stage for the pop-up
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);  // Block interactions with other windows until this is closed
            stage.setTitle("Thêm dân cư");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openEditResidentWindow() {
        try {
            // Load the add_resident.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/resident_management/edit_resident.fxml"));
            Parent root = loader.load();

            // Create a new stage for the pop-up
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);  // Block interactions with other windows until this is closed
            stage.setTitle("Sửa thông tin cư dân");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
