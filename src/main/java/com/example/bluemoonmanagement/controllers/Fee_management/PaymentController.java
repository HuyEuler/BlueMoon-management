package com.example.bluemoonmanagement.controllers.Fee_management;

import com.example.bluemoonmanagement.api.ApartmentAPI;
import com.example.bluemoonmanagement.api.FeeAPI;
import com.example.bluemoonmanagement.api.PaymentAPI;
import com.example.bluemoonmanagement.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PaymentController {
    @FXML
    private ChoiceBox<String> cbTenPhi, cbSoPhong;
    @FXML
    private Label lbHienThi, lbPhaiNop;
    @FXML
    private TextField tfTienNop;
    @FXML
    private Button btHuy, btNop;
    List<Fee> feeList;
    List<Apartment> apartmentList;
    private Integer selectPaymentID = -1;
    private Integer selectedFeeID = -1;
    private Integer selectedApartmentID = -1;
    @FXML
    public void initialize() {
        loadPayment();  // check payment có month < month(current), status: Pending --> Overdue
        loadDatas();
        cbSoPhong.setOnAction(event -> loadDetails());
        cbTenPhi.setOnAction(event -> loadDetails());
        btNop.setOnAction(this::updatePayment);
        btHuy.setOnAction(event -> clearFields());
    }
    private void loadDatas() {
        feeList = FeeAPI.getAllFees();
        ObservableList<String> feeNames = FXCollections.observableArrayList();
        for (Fee fee : feeList) {
            feeNames.add(fee.getName());
        }
        cbTenPhi.setItems(feeNames);
        apartmentList =ApartmentAPI.getAllApartment();
        ObservableList<String> apartmentNames = FXCollections.observableArrayList();
        for (Apartment apartment: apartmentList){
            apartmentNames.add(apartment.getRoom());
        }
        cbSoPhong.setItems(apartmentNames);
    }
    private void updatePayment(ActionEvent event) {
        Boolean updated=null;
        if (selectPaymentID != -1) {
            Payment payment = PaymentAPI.getPaymentById(selectPaymentID);
            payment.setAmountPaid(payment.getAmountPaid() + Integer.parseInt(tfTienNop.getText()));
            if (payment.getAmountPaid() >= payment.getAmountDue()){
                payment.setStatus(PaymentStatus.PAID);
            }
            updated =PaymentAPI.updatePayment(payment);
        }
        else{
            Date currentDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);
            Payment payment = new Payment(selectedFeeID, selectedApartmentID, 0, Integer.parseInt(tfTienNop.getText()) ,currentDate, month, year, PaymentStatus.PAID);
            updated =PaymentAPI.addPayment(payment);
        }
        if (updated) {
            showAlert("Nộp phí thành công!", "Đã nộp thành công khoản phí.");
        } else {
            showAlert("Lỗi!!","Có lỗi xảy ra.");
        }
    }
    private void loadPayment(){
        List<Payment> paymentList=PaymentAPI.getAllPayments();
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        for (Payment payment: paymentList){
            if (payment.getStatus() == PaymentStatus.PENDING){
                if (payment.getPayForYear() != year || payment.getPayForMonth() != month){
                    payment.setStatus(PaymentStatus.OVERDUE);
                    PaymentAPI.updatePayment(payment);
                }
            }
        }
    }
    private void clearFields() {
        cbTenPhi.setValue(null);
        cbSoPhong.setValue(null);
        lbHienThi.setText("Không còn khoản phí nào cần đóng");
        tfTienNop.setText(null);
        lbPhaiNop.setText("None");
        selectPaymentID= -1;
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void loadDetails() {
        String selectedFeeName = cbTenPhi.getValue();
        String selectedRoom = cbSoPhong.getValue();
        if (selectedFeeName != null && selectedRoom != null) {
            for (Fee fee : feeList) {
                if (fee.getName().equals(selectedFeeName)) {
                    String typeFee =String.valueOf(fee.getFeeType());
                    selectedFeeID = fee.getFeeId();
                    for (Apartment apartment: apartmentList){
                        if (apartment.getRoom().equals(selectedRoom)) {
                            selectedApartmentID = apartment.getApartmentId();
                            if (typeFee.equals("SERVICE_FEE") || typeFee.equals("MANAGEMENT_FEE") || typeFee.equals("VEHICLE_FEE")) {
                                List<Payment> paymentList=PaymentAPI.getPaymentsByApartmentId(selectedApartmentID);
                                for (Payment payment : paymentList) {
                                    if (payment.getFeeId() == selectedFeeID) {
                                        String status=String.valueOf(payment.getStatus());
                                        if (status.equals("PAID")) {
                                            continue;
                                        }
                                        if (status.equals("OVERDUE")) {
                                            lbHienThi.setText("Còn nợ phí tháng " + payment.getPayForMonth() + ", năm " + payment.getPayForYear());
                                        } else {
                                            lbHienThi.setText("Thanh toán phí cho tháng (hiện tại) " + payment.getPayForMonth() + ", năm " + payment.getPayForYear());
                                        }
                                        selectPaymentID= payment.getPaymentId();
                                        Double amount = payment.getAmountDue() - payment.getAmountPaid();
                                        DecimalFormat decimalFormat = new DecimalFormat("#,###");
                                        String formattedAmount = decimalFormat.format(amount);
                                        lbPhaiNop.setText(formattedAmount + " VNĐ");
                                        break;
                                    } else {
                                        lbHienThi.setText("Đã đóng xong phí này!");
                                        lbPhaiNop.setText("Không");
                                    }
                                }
                            } else {  //CONTRIBUTION_FEE
                                selectPaymentID= -1;
                                lbHienThi.setText("Phí thiện nguyện, ủng hộ, đóng góp!");
                                lbPhaiNop.setText("Không yêu cầu");
                            }
                        }
                    }
                }
            }
        }
    }

}
