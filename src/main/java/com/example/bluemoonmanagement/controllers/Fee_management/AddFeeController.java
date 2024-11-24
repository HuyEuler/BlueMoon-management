package com.example.bluemoonmanagement.controllers.Fee_management;
import com.example.bluemoonmanagement.api.FeeAPI;

import com.example.bluemoonmanagement.models.Fee;
import com.example.bluemoonmanagement.models.FeeType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddFeeController {
    @FXML
    private TextField tfTenPhi;
    @FXML
    private TextField tfDonGia;
    @FXML
    private CheckBox cbBatBuoc;
    @FXML
    private ChoiceBox<String> cbLoaiPhi;
    @FXML
    private Button btHuy;
    @FXML
    private Button btThem;
    private ObservableList<Fee> feeList;

    @FXML
    public void initialize() {
        ObservableList<String> feeTypeNames = FXCollections.observableArrayList(
                getFeeTypeInVietnamese(FeeType.CONTRIBUTION_FEE),
                getFeeTypeInVietnamese(FeeType.SERVICE_FEE),
                getFeeTypeInVietnamese(FeeType.MANAGEMENT_FEE)
        );
        cbLoaiPhi.setItems(feeTypeNames);
        cbLoaiPhi.setValue(null);
        btHuy.setOnAction(event -> clearFields());
        btThem.setOnAction(event -> addFeeToDatabase());
    }

    private void addFeeToDatabase() {
        try {
            String name = tfTenPhi.getText();
            double ratePerSquareMeter = Double.parseDouble(tfDonGia.getText());
            boolean isMandatory = cbBatBuoc.isSelected();

            String selectedTypeName = cbLoaiPhi.getValue();
            FeeType feeType = getFeeTypeFromVietnamese(selectedTypeName);
            Fee fee = new Fee();
            fee.setName(name);
            fee.setRatePerSquareMeter(ratePerSquareMeter);
            fee.setMandatory(isMandatory);
            fee.setFeeType(feeType);

            boolean success = FeeAPI.addFee(fee);

            if (success) {
                Fee_Controller.getInstance().refreshFeeList();
                showAlert("Thêm phí thành công!", "Thông tin khoản phí đã được thêm vào danh sách.");
                clearFields();
            } else {
                showAlert("Lỗi!!", "Thêm khoản phí thất bại. Vui lòng kiểm tra lại.");
            }
        } catch (NumberFormatException e) {
            showAlert("Lỗi nhập liệu", "Vui lòng nhập đúng định dạng cho đơn giá.");
        } catch (Exception e) {
            showAlert("Lỗi không xác định", "Đã xảy ra lỗi: " + e.getMessage());
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
            default:
                throw new IllegalArgumentException("Loại phí không hợp lệ: " + feeTypeName);
        }
    }
    private void clearFields() {
        tfTenPhi.clear();
        tfDonGia.clear();
        cbBatBuoc.setSelected(false);
        cbLoaiPhi.setValue("Không xác định");
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}