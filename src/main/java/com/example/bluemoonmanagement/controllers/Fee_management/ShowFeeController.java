package com.example.bluemoonmanagement.controllers.Fee_management;
import com.example.bluemoonmanagement.api.FeeAPI;


import com.example.bluemoonmanagement.models.Fee;
import com.example.bluemoonmanagement.models.FeeType;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;



// Thiếu chức năng update table after add, edit or delete fee
public class ShowFeeController {
    @FXML
    private TableColumn<Fee, Integer> cMaPhi;
    @FXML
    private TableColumn<Fee, String> cTenPhi;
    @FXML
    private TableColumn<Fee, Double> cHoaDon;
    @FXML
    private TableColumn<Fee, Boolean> cBatBuoc;
    @FXML
    private TableColumn<Fee, FeeType> cLoaiPhi;
    @FXML
    private TableView<Fee> tableView;
    @FXML
    private Label lbPDichVu;
    @FXML
    private Label lbPQuanLy;
    @FXML
    private Label lbPTuThien;
    @FXML
    private Button btSearch;
    @FXML
    private TextField tfSearch;
    private ObservableList<Fee> feeList;
    private final NumberFormat currencyFormat = NumberFormat.getNumberInstance(new Locale("vi", "VN"));

    @FXML
    public void initialize() {
        configureTableColumns();
        loadFeeData();
        btSearch.setOnAction(event -> searchFees());
        feeList.addListener((ListChangeListener<Fee>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    updateFeeCounts();
                }
            }
        });
    }
    private void configureTableColumns() {
        cMaPhi.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFeeId()));
        cTenPhi.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        cHoaDon.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRatePerSquareMeter()));
        cHoaDon.setCellFactory(column -> new TableCell<Fee, Double>() {
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
        cBatBuoc.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().isMandatory()));
        cLoaiPhi.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFeeType()));
        cBatBuoc.setCellFactory(column -> new TableCell<Fee, Boolean>() {
            @Override
            protected void updateItem(Boolean isMandatory, boolean empty) {
                super.updateItem(isMandatory, empty);
                if (empty || isMandatory == null) {
                    setText(null);
                } else {
                    setText(isMandatory ? "Có" : "Không");
                }
            }
        });
        cLoaiPhi.setCellFactory(column -> new TableCell<Fee, FeeType>() {
            @Override
            protected void updateItem(FeeType feeType, boolean empty) {
                super.updateItem(feeType, empty);
                if (empty || feeType == null) {
                    setText(null);
                } else {
                    setText(getFeeTypeInVietnamese(feeType));
                }
            }
        });

    }
    private void loadFeeData() {
        feeList = Fee_Controller.getInstance().getFeeList();
        tableView.setItems(feeList);
        updateFeeCounts();
        long cntDichVu = feeList.stream().filter(fee -> fee.getFeeType() == (FeeType.SERVICE_FEE)).count();
        long cntQuanLy = feeList.stream().filter(fee -> fee.getFeeType() == (FeeType.MANAGEMENT_FEE)).count();
        long cntTuThien = feeList.stream().filter(fee -> fee.getFeeType() == (FeeType.CONTRIBUTION_FEE)).count();
        lbPDichVu.setText(String.valueOf(cntDichVu));
        lbPQuanLy.setText(String.valueOf(cntQuanLy));
        lbPTuThien.setText(String.valueOf(cntTuThien));
    }
        private void searchFees(){
            String searchFee = tfSearch.getText().trim();
            if (searchFee.isEmpty()) {
                tableView.setItems(feeList);
            } else {
                ObservableList<Fee> searchResults = feeList.filtered(fee -> fee.getName().contains(searchFee));
                tableView.setItems(searchResults);
            }
            tableView.refresh();
    }
    private String getFeeTypeInVietnamese(FeeType feeType) {
        if (feeType == null) return "";
        switch (feeType) {
            case SERVICE_FEE:
                return "Dịch vụ";
            case MANAGEMENT_FEE:
                return "Quản lý";
            case CONTRIBUTION_FEE:
                return "Thiện nguyện";
            default:
                return "Khác";
        }
    }
    private void refreshTableData() {
        List<Fee> fees = FeeAPI.getAllFees();
        feeList.setAll(fees);
        tableView.refresh();
        updateFeeCounts();
    }
    private void updateFeeCounts() {
        long cntDichVu = feeList.stream().filter(fee -> fee.getFeeType() == FeeType.SERVICE_FEE).count();
        long cntQuanLy = feeList.stream().filter(fee -> fee.getFeeType() == FeeType.MANAGEMENT_FEE).count();
        long cntTuThien = feeList.stream().filter(fee -> fee.getFeeType() == FeeType.CONTRIBUTION_FEE).count();
        lbPDichVu.setText(String.valueOf(cntDichVu));
        lbPQuanLy.setText(String.valueOf(cntQuanLy));
        lbPTuThien.setText(String.valueOf(cntTuThien));
    }
}