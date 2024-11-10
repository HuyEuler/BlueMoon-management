package com.example.bluemoonmanagement.controllers.Fee_management;

import com.example.bluemoonmanagement.api.FeeAPI;
import com.example.bluemoonmanagement.controllers.Fee_management.ShowFeeController;
import com.example.bluemoonmanagement.models.Fee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EditFeeController {

    @FXML
    private ChoiceBox<String> cbTenPhi;
    @FXML
    private TextField tfSoTien;
    @FXML
    private CheckBox cbBatBuoc;
    @FXML
    private ChoiceBox<Integer> cbChuKi;
    @FXML
    private DatePicker dpHanNop;
    @FXML
    private Button btCapNhat;
    @FXML
    private Button btHuy;
    @FXML
    private Label lbChinhSua;
    private int selectedFeeId = -1;
    private ShowFeeController showFeeController;

    @FXML
    public void initialize() {
        loadFeeNames();
        ObservableList<Integer> cycles = FXCollections.observableArrayList(0, 1, 2, 3, 6, 9, 12, 24);
        cbChuKi.setItems(cycles);
        cbTenPhi.setOnAction(event -> loadFeeDetails());
        btCapNhat.setOnAction(this::updateFee);
        btHuy.setOnAction(event -> clearFields());
    }

    private void loadFeeNames() {
        List<Fee> feeList = FeeAPI.getFeeList();
        ObservableList<String> feeNames = FXCollections.observableArrayList();
        for (Fee fee : feeList) {
            feeNames.add(fee.getName());
        }
        cbTenPhi.setItems(feeNames);
    }
    private void loadFeeDetails() {
        String selectedFeeName = cbTenPhi.getValue();
        if (selectedFeeName != null) {
            List<Fee> feeList = FeeAPI.getFeeList();
            for (Fee fee : feeList) {
                if (fee.getName().equals(selectedFeeName)) {
                    selectedFeeId = fee.getFeeId();
                    cbTenPhi.setValue(selectedFeeName);
                    lbChinhSua.setText("Chỉnh sửa phí: " + selectedFeeName);
                    tfSoTien.setText(String.valueOf(fee.getCost()));
                    cbBatBuoc.setSelected(fee.isMandatory());
                    cbChuKi.setValue(fee.getCycle());
                    if (fee.getExpiration() != null) {
                        dpHanNop.setValue(((java.sql.Date) fee.getExpiration()).toLocalDate());
//                        dpHanNop.setValue(fee.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    } else {
                        dpHanNop.setValue(null);
                    }
                    break;
                }
            }
        }
    }

    private void updateFee(ActionEvent event) {
        if (selectedFeeId != -1) {
            String name = cbTenPhi.getValue();
            int cost = Integer.parseInt(tfSoTien.getText());
            boolean mandatory = cbBatBuoc.isSelected();
            int cycle = cbChuKi.getValue();
            LocalDate expirationDate = dpHanNop.getValue();
            Date expiration = expirationDate != null ? Date.valueOf(expirationDate) : null;

            boolean updated = FeeAPI.editFee(selectedFeeId, name, cost, mandatory, cycle, expiration, 1);

            if (updated) {
                if (showFeeController != null) {
                    showFeeController.refreshData();
                }
                showAlert("Cập nhật thành công!", "Thông tin khoản phí đã được cập nhật.");
            } else {
                showAlert("Lỗi!!", "Cập nhật thất bại.");
            }
        }
    }

    private void clearFields() {
        cbTenPhi.setValue(null);
        tfSoTien.clear();
        cbBatBuoc.setSelected(false);
        cbChuKi.setValue(null);
        dpHanNop.setValue(null);
        selectedFeeId = -1;
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
