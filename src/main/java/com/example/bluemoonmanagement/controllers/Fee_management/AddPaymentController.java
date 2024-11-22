package com.example.bluemoonmanagement.controllers.Fee_management;

import com.example.bluemoonmanagement.api.ApartmentAPI;
import com.example.bluemoonmanagement.api.FeeAPI;
import com.example.bluemoonmanagement.api.PaymentAPI;
import com.example.bluemoonmanagement.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddPaymentController {
    @FXML
    private ChoiceBox<String> cbTenPhi;
    @FXML
    private Button btTao;
    private Integer selectedFeeId = -1;
    private Double ratePerSquareMeter = 0.0;
    @FXML
    public void initialize() {
        loadFeeNames();
        cbTenPhi.setOnAction(event -> loadFeeID());
        btTao.setOnAction(this::addPayment);
    }
    private void loadFeeNames() {
        List<Fee> feeList = FeeAPI.getAllFees();
        ObservableList<String> feeNames = FXCollections.observableArrayList();
        for (Fee fee : feeList) {
            feeNames.add(fee.getName());
        }
        cbTenPhi.setItems(feeNames);
    }
    private void loadFeeID() {
        String selectedFeeName = cbTenPhi.getValue();
        if (selectedFeeName != null) {
            List<Fee> feeList = FeeAPI.getAllFees();
            for (Fee fee : feeList) {
                if (fee.getName().equals(selectedFeeName)) {
                    selectedFeeId = fee.getFeeId();
                    cbTenPhi.setValue(selectedFeeName);
                    ratePerSquareMeter = fee.getRatePerSquareMeter();
                    break;
                }
            }
        }
    }
    private void addPayment(ActionEvent event) {
        if (selectedFeeId != -1) {
            List<Apartment> apartmentList =ApartmentAPI.getAllApartment();
            for (Apartment apartment : apartmentList){
                Integer apartmentID = apartment.getApartmentId();
                Double amountDue = ratePerSquareMeter *  apartment.getArea();
                Date currentDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate);
                int month = calendar.get(Calendar.MONTH) + 1;
                int year = calendar.get(Calendar.YEAR);
                Payment payment = new Payment(selectedFeeId, apartmentID, amountDue, 0, currentDate, month, year, PaymentStatus.PENDING);
                boolean updated =PaymentAPI.addPayment(payment);
                if (updated) {
                    showAlert("Tạo thanh toán thành công!", "Đã tạo thanh toán phí tháng này cho các hộ cư dân.");
                    } else {
                    showAlert("Lỗi!!", "Có lỗi xảy ra.");
                }
            }
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
