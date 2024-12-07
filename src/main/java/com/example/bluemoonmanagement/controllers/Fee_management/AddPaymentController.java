package com.example.bluemoonmanagement.controllers.Fee_management;

import com.example.bluemoonmanagement.api.ApartmentAPI;
import com.example.bluemoonmanagement.api.FeeAPI;
import com.example.bluemoonmanagement.api.PaymentAPI;
import com.example.bluemoonmanagement.api.VehicleAPI;
import com.example.bluemoonmanagement.models.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddPaymentController {
    @FXML
    private ChoiceBox<String> cbTenPhi;
    @FXML
    private Button btTao;
    @FXML
    private TableView<ShowPayment> tableView;
    @FXML
    private TableColumn<ShowPayment, String> cSoPhong, cSoTien;

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
            Fee selectedFee = findFeeByName(selectedFeeName);
            if (selectedFee != null) {
                selectedFeeId = selectedFee.getFeeId();
                ratePerSquareMeter = selectedFee.getRatePerSquareMeter();

                ObservableList<ShowPayment> showPayments = generatePaymentPreview(selectedFee);
                tableView.setItems(showPayments);
                setupEditableColumns(selectedFeeName);
            }
        }
    }
    private Fee findFeeByName(String feeName) {
        List<Fee> feeList = FeeAPI.getAllFees();
        for (Fee fee : feeList) {
            if (fee.getName().equals(feeName)) {
                return fee;
            }
        }
        return null;
    }

    private ObservableList<ShowPayment> generatePaymentPreview(Fee fee) {
        List<Apartment> apartmentList = ApartmentAPI.getAllApartment();
        ObservableList<ShowPayment> showPayments = FXCollections.observableArrayList();

        for (Apartment apartment : apartmentList) {
            double amountDue = calculateAmountDue(fee, apartment);
            showPayments.add(new ShowPayment(apartment.getRoom(), amountDue));
        }
        return showPayments;
    }

    private double calculateAmountDue(Fee fee, Apartment apartment) {
        double amountDue = 0.0;
        if (fee.getFeeType() == FeeType.VEHICLE_FEE) {
            List<Vehicle> vehicles = VehicleAPI.getAllVehiclesByApartmentId(apartment.getApartmentId());
            int motorbikes = (int) vehicles.stream().filter(v -> v.getType().equals("Xe máy")).count();
            int cars = (int) vehicles.stream().filter(v -> v.getType().equals("Ô tô")).count();
            int bicycles = (int) vehicles.stream().filter(v -> v.getType().equals("Xe đạp")).count();

            if (fee.getName().equals("Phí gửi ô tô")) {
                amountDue = ratePerSquareMeter * cars;
            } else if (fee.getName().equals("Phí gửi xe máy")) {
                amountDue = ratePerSquareMeter * motorbikes;
            } else {
                amountDue = ratePerSquareMeter * bicycles;
            }
        } else if (fee.getFeeType() == FeeType.SERVICE_FEE) {
            amountDue = ratePerSquareMeter;
        } else {
            amountDue = ratePerSquareMeter * apartment.getArea();
        }
        return amountDue;
    }

    private void setupEditableColumns(String selectedFeeName) {
        cSoPhong.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApartmentName()));
        cSoTien.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAmountDue()).asString());

        if (selectedFeeName.equals("Phí điện") || selectedFeeName.equals("Phí nước")) {
            cSoTien.setCellFactory(TextFieldTableCell.forTableColumn());
            cSoTien.setOnEditCommit(event -> {
                ShowPayment payment = event.getRowValue();
                payment.setAmountDue(Double.valueOf(event.getNewValue()));
            });
        }
        tableView.setEditable(true);
    }

    private void addPayment(ActionEvent event) {
        if (selectedFeeId != -1) {
            List<Apartment> apartmentList = ApartmentAPI.getAllApartment();
            ObservableList<ShowPayment> payments = tableView.getItems();

            for (ShowPayment showPayment : payments) {
                if (showPayment.getAmountDue() == null || showPayment.getAmountDue() == 0) {
                    continue;
                }

                Apartment apartment = apartmentList.stream()
                        .filter(a -> a.getRoom().equals(showPayment.getApartmentName()))
                        .findFirst()
                        .orElse(null);

                if (apartment != null) {
                    createPaymentRecord(apartment, showPayment.getAmountDue());
                }
            }
            showAlert("Thành công", "Tạo thanh toán thành công cho các hộ cư dân!");
        }
    }

    private void createPaymentRecord(Apartment apartment, Double amountDue) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        Fee fee = FeeAPI.getFeeById(selectedFeeId);
        PaymentStatus status = (fee.getName().equals("Phí thiện nguyện")) ? PaymentStatus.PAID : PaymentStatus.PENDING;

        Payment payment = new Payment(selectedFeeId, apartment.getApartmentId(), amountDue, 0, currentDate, month, year, status);
        boolean success = PaymentAPI.addPayment(payment);

        if (!success) {
            showAlert("Lỗi", "Không thể tạo thanh toán cho phòng: " + apartment.getRoom());
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

class ShowPayment {
    private String apartmentName;
    private Double amountDue;

    public ShowPayment(String apartmentName, Double amountDue) {
        this.apartmentName = apartmentName;
        this.amountDue = amountDue;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public Double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }
}
