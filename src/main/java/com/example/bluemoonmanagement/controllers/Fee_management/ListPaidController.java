package com.example.bluemoonmanagement.controllers.Fee_management;

import com.example.bluemoonmanagement.api.ApartmentAPI;
import com.example.bluemoonmanagement.api.FeeAPI;
import com.example.bluemoonmanagement.api.PaymentAPI;
import com.example.bluemoonmanagement.models.Apartment;
import com.example.bluemoonmanagement.models.Fee;
import com.example.bluemoonmanagement.models.FeeType;
import com.example.bluemoonmanagement.models.Payment;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ListPaidController {
    @FXML
    private TableColumn<Show, String> cTenPhi;
    @FXML
    private TableColumn<Show, String> cCanHo;
    @FXML
    private TableColumn<Show, Integer> cThang;
    @FXML
    private TableColumn<Show, Integer> cNam;
    @FXML
    private TableColumn<Show, String> cLoaiPhi;
    @FXML
    private TableColumn<Show, Double> cSoTien;
    @FXML
    private TableView<Show> tableView;
    private final NumberFormat currencyFormat = NumberFormat.getNumberInstance(new Locale("vi", "VN"));

    @FXML
    public void initialize() {
        configureTableColumns();
        loadTableData();
    }

    private void configureTableColumns() {
        cTenPhi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLoaiPhi()));
        cCanHo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCanHo()));
        cThang.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getcThang()));
        cNam.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getcNam()));
        cLoaiPhi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getcLoaiPhi()));
        cSoTien.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSoTien()));
        cSoTien.setCellFactory(column -> new TableCell<Show, Double>() {
            @Override
            protected void updateItem(Double amount, boolean empty) {
                super.updateItem(amount, empty);
                if (empty || amount == null) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(amount) + " VNĐ"); // Định dạng số tiền
                }
            }
        });
    }

    private void loadTableData() {
        List<Payment> paidPayments = PaymentAPI.getPaidPayments();
        List<Show> showTables = FXCollections.observableArrayList();

        for (Payment payment : paidPayments) {
            int feeId = payment.getFeeId();
            Fee fee = FeeAPI.getFeeById(feeId);
            String feeType;

            if (fee.getFeeType() == FeeType.CONTRIBUTION_FEE) {
                feeType = "Thiện nguyện";
            } else if (fee.getFeeType() == FeeType.SERVICE_FEE) {
                feeType = "Dịch vụ";
            } else {
                feeType = "Quản lý";
            }

            int apartmentId = payment.getApartmentId();
            Apartment apartment = ApartmentAPI.getApartmentById(apartmentId);

            Show show = new Show(
                    fee.getName(),
                    apartment.getRoom(),
                    payment.getPayForMonth(),
                    payment.getPayForYear(),
                    feeType,
                    payment.getAmountPaid()
            );
            showTables.add(show);
        }
        ObservableList<Show> shows = FXCollections.observableArrayList(showTables);
        tableView.setItems(shows);
    }

}

class Show {
    private final String LoaiPhi;
    private final String CanHo;
    private final Integer cThang;
    private final Integer cNam;
    private final String cLoaiPhi;
    private final Double SoTien;

    public Show(String LoaiPhi, String CanHo, Integer cThang, Integer cNam, String cLoaiPhi, Double SoTien) {
        this.LoaiPhi = LoaiPhi;
        this.CanHo = CanHo;
        this.cThang = cThang;
        this.cNam = cNam;
        this.cLoaiPhi = cLoaiPhi;
        this.SoTien = SoTien;
    }
    public String getLoaiPhi() {
        return LoaiPhi;
    }
    public String getCanHo() {
        return CanHo;
    }
    public Integer getcThang() {
        return cThang;
    }
    public Integer getcNam() {
        return cNam;
    }
    public String getcLoaiPhi() {
        return cLoaiPhi;
    }
    public Double getSoTien() {
        return SoTien;
    }
}
