package com.example.bluemoonmanagement.controllers.Resident_management;

import com.example.bluemoonmanagement.models.Apartment;
import com.example.bluemoonmanagement.models.Resident;
import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static com.example.bluemoonmanagement.api.ApartmentAPI.getApartmentById;
import static com.example.bluemoonmanagement.api.ApartmentAPI.getResidentsFromApartmentId;

public class showOwner_controller {
    @FXML
    private Label lbNumberRoom;
    @FXML
    private TableView<Resident> tableView;
    @FXML
    private TableColumn<Resident, String> cHoTen;
    @FXML
    private TableColumn<Resident, String> cNgaySinh;
    @FXML
    private TableColumn<Resident, String> cSDT;
    @FXML
    private TableColumn<Resident, String> cMQH;

    private int id;

    @FXML
    public void initialize() {
        DataManager dataManager = DataManager.getInstance();
        id = dataManager.getApartmentID();
        Apartment apartment = getApartmentById(id);
        assert apartment != null;
        lbNumberRoom.setText("Danh sách cư dân phòng %s".formatted(apartment.getRoom()));
        setTable();
        System.out.println("zookeeper");
    }

    private void setTable() {
        List<Resident> residentList = getResidentsFromApartmentId(id);
        System.out.println(residentList);
        System.out.println(FXCollections.observableArrayList(residentList));

        cHoTen.setCellValueFactory(new PropertyValueFactory<>("name"));
        cNgaySinh.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        cSDT.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        cMQH.setCellValueFactory(new PropertyValueFactory<>("relationshipWithOwner"));

        tableView.setItems(FXCollections.observableArrayList(residentList));

    }
}
