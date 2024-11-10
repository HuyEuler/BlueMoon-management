package com.example.bluemoonmanagement.controllers;

import com.example.bluemoonmanagement.api.FeeAPI;
import com.example.bluemoonmanagement.models.Fee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ShowFeeController {
    @FXML
    private Tab tTatCa;
    @FXML
    private Tab tMotLan;
    @FXML
    private Tab tHangThang;
    @FXML
    private Tab tThuongNien;
    @FXML
    private TableColumn<Fee, Integer> maPhiColumn;
    @FXML
    private TableColumn<Fee, String> tenPhiColumn;
    @FXML
    private TableColumn<Fee, Integer> soTienColumn;
    @FXML
    private TableColumn<Fee, Date> hanNopColumn;
    @FXML
    private TableColumn<Fee, Integer> chuKiColumn;
    @FXML
    private TableColumn<Fee, Boolean> batBuocColumn;
    @FXML
    private TableColumn<Fee, Integer> maPhiColumn1;
    @FXML
    private TableColumn<Fee, String> tenPhiColumn1;
    @FXML
    private TableColumn<Fee, Integer> soTienColumn1;
    @FXML
    private TableColumn<Fee, Integer> chuKiColumn1;
    @FXML
    private TableColumn<Fee, Date> hanNopColumn1;
    @FXML
    private TableColumn<Fee, Boolean> batBuocColumn1;
    @FXML
    private TableColumn<Fee, Integer> maPhiColumn2;
    @FXML
    private TableColumn<Fee, String> tenPhiColumn2;
    @FXML
    private TableColumn<Fee, Integer> soTienColumn2;
    @FXML
    private TableColumn<Fee, Date> hanNopColumn2;
    @FXML
    private TableColumn<Fee, Integer> chuKiColumn2;
    @FXML
    private TableColumn<Fee, Boolean> batBuocColumn2;
    @FXML
    private TableColumn<Fee, Integer> maPhiColumn3;
    @FXML
    private TableColumn<Fee, String> tenPhiColumn3;
    @FXML
    private TableColumn<Fee, Integer> soTienColumn3;
    @FXML
    private TableColumn<Fee, Date> hanNopColumn3;
    @FXML
    private TableColumn<Fee, Integer> chuKiColumn3;
    @FXML
    private TableColumn<Fee, Boolean> batBuocColumn3;
    @FXML
    private TableView<Fee> TableTatCa;
    @FXML
    private TableView<Fee> TableMotLan;
    @FXML
    private TableView<Fee> TableHangThang;
    @FXML
    private TableView<Fee> TableThuongNien;
    @FXML
    private Label lbBatBuoc;
    @FXML
    private Label lbKhongBatBuoc;
    @FXML
    private Label lbSapToi;
    @FXML
    private Button btSearch;
    @FXML
    private TextField tfSearch;
    private Map<TableView<Fee>, Map<String, TableColumn<Fee, ?>>> tableColumnMapping;
    private ObservableList<Fee> feeList;
    public void initialize() {
        tableColumnMapping = Map.of(
                TableTatCa, Map.of(
                        "maPhi", maPhiColumn,
                        "tenPhi", tenPhiColumn,
                        "soTien", soTienColumn,
                        "hanNop", hanNopColumn,
                        "chuKi", chuKiColumn,
                        "batBuoc", batBuocColumn
                ),
                TableMotLan, Map.of(
                        "maPhi", maPhiColumn1,
                        "tenPhi", tenPhiColumn1,
                        "soTien", soTienColumn1,
                        "hanNop", hanNopColumn1,
                        "chuKi", chuKiColumn1,
                        "batBuoc", batBuocColumn1
                ),
                TableHangThang, Map.of(
                        "maPhi", maPhiColumn2,
                        "tenPhi", tenPhiColumn2,
                        "soTien", soTienColumn2,
                        "hanNop", hanNopColumn2,
                        "chuKi", chuKiColumn2,
                        "batBuoc", batBuocColumn2
                ),
                TableThuongNien, Map.of(
                        "maPhi", maPhiColumn3,
                        "tenPhi", tenPhiColumn3,
                        "soTien", soTienColumn3,
                        "hanNop", hanNopColumn3,
                        "chuKi", chuKiColumn3,
                        "batBuoc", batBuocColumn3
                )
        );
        setupTableColumns(TableTatCa);
        setupTableColumns(TableMotLan);
        setupTableColumns(TableHangThang);
        setupTableColumns(TableThuongNien);

        loadFeeData();
        tTatCa.setOnSelectionChanged(event -> {
            if (tTatCa.isSelected()) {
                TableTatCa.setItems(feeList);
                countGradientFees(feeList);
            }
        });

        tMotLan.setOnSelectionChanged(event -> {
            if (tMotLan.isSelected()) {
                ObservableList<Fee> motLanFees = feeList.filtered(fee -> fee.getCycle() == 0);
                TableMotLan.setItems(motLanFees);
                countGradientFees(motLanFees);
            }
        });

        tHangThang.setOnSelectionChanged(event -> {
            if (tHangThang.isSelected()) {
                ObservableList<Fee> hangThangFees = feeList.filtered(fee -> fee.getCycle() == 1);
                TableHangThang.setItems(hangThangFees);
                countGradientFees(hangThangFees);
            }
        });
        tThuongNien.setOnSelectionChanged(event -> {
            if (tThuongNien.isSelected()) {
                ObservableList<Fee> thuongNienFees = feeList.filtered(fee -> fee.getCycle() > 1);
                TableThuongNien.setItems(thuongNienFees);
                countGradientFees(thuongNienFees);
            }
        });
        btSearch.setOnAction(event -> searchFee());
    }

    private void setupTableColumns(TableView<Fee> table) {
        Map<String, TableColumn<Fee, ?>> columns = tableColumnMapping.get(table);
        if (columns != null) {
            columns.get("maPhi").setCellValueFactory(new PropertyValueFactory<>("feeId"));
            columns.get("tenPhi").setCellValueFactory(new PropertyValueFactory<>("name"));
            columns.get("soTien").setCellValueFactory(new PropertyValueFactory<>("cost"));
            columns.get("hanNop").setCellValueFactory(new PropertyValueFactory<>("expiration"));
            columns.get("chuKi").setCellValueFactory(new PropertyValueFactory<>("cycle"));
            TableColumn<Fee, Boolean> batBuocColumn = (TableColumn<Fee, Boolean>) columns.get("batBuoc");
            batBuocColumn.setCellValueFactory(new PropertyValueFactory<>("mandatory"));
            batBuocColumn.setCellFactory(column -> new TableCell<>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null : item ? "Có" : "Không");
                }
            });
        }
    }

    private void loadFeeData() {
        List<Fee> fees = FeeAPI.getFeeList();
        if (fees != null) {
            feeList = FXCollections.observableArrayList(fees);
            TableTatCa.setItems(feeList);
            countGradientFees(feeList);
//            TableMotLan.setItems(feeList.filtered(fee -> fee.getCycle() == 0));
//            TableHangThang.setItems(feeList.filtered(fee -> fee.getCycle() == 1));
//            TableThuongNien.setItems(feeList.filtered(fee -> fee.getCycle() >= 1));
        }
    }
    public void refreshData() {
        loadFeeData();
    }
    private void countGradientFees(ObservableList<Fee> fees) {
        long mandatoryCount = fees.stream().filter(Fee::isMandatory).count();
        long optionalCount = fees.size() - mandatoryCount;
        LocalDate currentDate = LocalDate.now();
        long dueInThreeMonths = fees.stream()
                .filter(fee -> fee.getExpiration() != null)
                .filter(fee -> ((java.sql.Date) fee.getExpiration()).toLocalDate().isBefore(currentDate.plusMonths(3)))
                .filter(fee -> {
                    LocalDate expirationDate = ((java.sql.Date) fee.getExpiration()).toLocalDate();
                    return expirationDate.isAfter(currentDate) || expirationDate.isEqual(currentDate);
                })
                .count();
//        System.out.println("Số phí thời gian tới không quá 3 tháng: " + dueInThreeMonths);
        lbSapToi.setText(String.valueOf(dueInThreeMonths));
        lbBatBuoc.setText(String.valueOf(mandatoryCount));
        lbKhongBatBuoc.setText(String.valueOf(optionalCount));
//        System.out.println("bắt buộc: " + mandatoryCount);
//        System.out.println("không bắt buộc: " + nonMandatoryCount);
    }
    private void searchFee(){
        String search_fee = tfSearch.getText();
//        System.out.println(search_fee);
        ObservableList<Fee> search = feeList.filtered(fee -> fee.getName().equals(search_fee));
        if (Objects.equals(search_fee, "")){
            loadFeeData();
        }
        TableTatCa.setItems(search);
        countGradientFees(search);
    }
}
