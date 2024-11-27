package com.example.bluemoonmanagement.controllers.Fee_management;

import com.example.bluemoonmanagement.api.FeeAPI;
import com.example.bluemoonmanagement.models.Fee;
import com.example.bluemoonmanagement.models.FeeType;
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
    private TextField tfDonGia;
    @FXML
    private CheckBox cbBatBuoc;
    @FXML
    private ChoiceBox<String> cbLoaiPhi;
    @FXML
    private Button btCapNhat, btHuy;
    @FXML
    private Label lbChinhSua;
    private int selectedFeeId = -1;

    @FXML
    public void initialize() {
        ObservableList<String> feeTypeNames = FXCollections.observableArrayList(
                getFeeTypeInVietnamese(FeeType.CONTRIBUTION_FEE),
                getFeeTypeInVietnamese(FeeType.SERVICE_FEE),
                getFeeTypeInVietnamese(FeeType.MANAGEMENT_FEE),
                getFeeTypeInVietnamese(FeeType.VEHICLE_FEE)
        );
        cbLoaiPhi.setItems(feeTypeNames);
        cbLoaiPhi.setValue(null);
        loadFeeNames();
        cbTenPhi.setOnAction(event -> loadFeeDetails());
        btCapNhat.setOnAction(this::updateFee);
        btHuy.setOnAction(event -> clearFields());
    }

    private void loadFeeNames() {
        List<Fee> feeList = FeeAPI.getAllFees();
        ObservableList<String> feeNames = FXCollections.observableArrayList();
        for (Fee fee : feeList) {
            feeNames.add(fee.getName());
        }
        cbTenPhi.setItems(feeNames);
    }
    private void loadFeeDetails() {
        String selectedFeeName = cbTenPhi.getValue();
        if (selectedFeeName != null) {
            List<Fee> feeList = FeeAPI.getAllFees();
            for (Fee fee : feeList) {
                if (fee.getName().equals(selectedFeeName)) {
                    selectedFeeId = fee.getFeeId();
                    cbTenPhi.setValue(selectedFeeName);
                    lbChinhSua.setText("Chỉnh sửa phí: " + selectedFeeName);
                    tfDonGia.setText(String.valueOf(fee.getRatePerSquareMeter()));
                    cbBatBuoc.setSelected(fee.isMandatory());
                    FeeType feeType = fee.getFeeType();
                    String feeTypeName = getFeeTypeInVietnamese(feeType);
                    cbLoaiPhi.setValue(feeTypeName);

                    break;
                }
            }
        }
    }

    private void updateFee(ActionEvent event) {
        if (selectedFeeId != -1) {
            String name = cbTenPhi.getValue();
            float ratePerSquareMeter = Float.parseFloat(tfDonGia.getText());
            boolean ismandatory = cbBatBuoc.isSelected();
            String selectedTypeName = cbLoaiPhi.getValue();
            FeeType feeType = getFeeTypeFromVietnamese(selectedTypeName);
            Fee fee= new Fee(selectedFeeId, name, ratePerSquareMeter, ismandatory, feeType);
            boolean updated = FeeAPI.updateFee(fee);

            if (updated) {
                Fee_Controller.getInstance().refreshFeeList();
                showAlert("Cập nhật thành công!", "Thông tin khoản phí đã được cập nhật.");
            } else {
                showAlert("Lỗi!!", "Cập nhật thất bại.");
            }
        }
    }
    private String getFeeTypeInVietnamese(FeeType feeType) {
        switch (feeType) {
            case CONTRIBUTION_FEE:
                return "Thiện nguyện";
            case SERVICE_FEE:
                return "Dịch vụ";
            case MANAGEMENT_FEE:
                return "Quản lý";
            case VEHICLE_FEE:
                return "Phương tiện";
            default:
                return "Không xác định";
        }
    }
    private FeeType getFeeTypeFromVietnamese(String feeTypeName) {
        switch (feeTypeName) {
            case "Thiện nguyện":
                return FeeType.CONTRIBUTION_FEE;
            case "Dịch vụ":
                return FeeType.SERVICE_FEE;
            case "Quản lý":
                return FeeType.MANAGEMENT_FEE;
            case "Phương tiện":
                return FeeType.VEHICLE_FEE;
            default:
                throw new IllegalArgumentException("Loại phí không hợp lệ: " + feeTypeName);
        }
    }

    private void clearFields() {
        cbTenPhi.setValue(null);
        tfDonGia.clear();
        cbBatBuoc.setSelected(false);
        cbLoaiPhi.setValue(null);
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
