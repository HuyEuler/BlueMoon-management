package com.example.bluemoonmanagement.controllers.Resident_management;

import com.example.bluemoonmanagement.models.Activity;
import com.example.bluemoonmanagement.models.Resident;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Objects;

import static com.example.bluemoonmanagement.api.ActivityAPI.getAllActivityOfResident;
import static com.example.bluemoonmanagement.api.ApartmentAPI.getApartmentById;
import static com.example.bluemoonmanagement.api.ResidentAPI.getResidentById;

public class showActivity_controller {
    @FXML
    private TableView<Activity> tableView;
    @FXML
    private TableColumn<Activity, String> cMa;
    @FXML
    private TableColumn<Activity, String> cTrangThai;
    @FXML
    private TableColumn<Activity, String> cNgayVao;
    @FXML
    private TableColumn<Activity, String> cNgayRa;
    @FXML
    private TableColumn<Activity, String> cGhiChu;
    @FXML
    private TextField tName;
    @FXML
    private TextField tNumberPhone;
    @FXML
    private TextField tBirthday;
    @FXML
    private TextField tGender;
    @FXML
    private TextField tNumberApartment;
    @FXML
    private TextField tRelationship;

    private int id;

    public void initialize() {
        DataManager dataManager = DataManager.getInstance();
        id = 1;//dataManager.getApartmentID();
        setInfomationResident();
        setTable();
    }

    private void setInfomationResident() {
        Resident resident = getResidentById(id);
        assert resident != null;
        tName.setText(resident.getName());
        tNumberPhone.setText(resident.getPhoneNumber());
        tBirthday.setText(resident.getBirthday());
        tGender.setText(!Objects.equals(resident.getGender(), "Male") ? "Nam" : "Nữ");
        tNumberApartment.setText(Objects.requireNonNull(getApartmentById(id)).getRoom());
        tRelationship.setText(resident.getRelationshipWithOwner());

    }

    private void setTable() {
        List<Activity> activityList = getAllActivityOfResident(id);

        cMa.setCellValueFactory(new PropertyValueFactory<>("activityId"));
        cTrangThai.setCellValueFactory(new PropertyValueFactory<>("status"));

        cTrangThai.setCellValueFactory(cellData -> {
            int status = cellData.getValue().getStatus();
            String statusText = switch (status) {
                case 0 -> "Đã rời";
                case 1 -> "Thường trú";
                case 2 -> "Tạm trú";
                case 3 -> "Tạm vắng";
                default -> "Không xác định";
            };
            return new SimpleStringProperty(statusText);
        });

        cNgayVao.setCellValueFactory(new PropertyValueFactory<>("timeIn"));
        cNgayRa.setCellValueFactory(new PropertyValueFactory<>("timeOut"));
        cGhiChu.setCellValueFactory(new PropertyValueFactory<>("note"));

        tableView.setItems(FXCollections.observableArrayList(activityList));
    }
}
