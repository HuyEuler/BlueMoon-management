package com.example.bluemoonmanagement.controllers.Fee_management;

import com.example.bluemoonmanagement.api.FeeAPI;
import com.example.bluemoonmanagement.models.Fee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class DeleteFeeController {
    @FXML
    private ChoiceBox cbTenPhi;
    @FXML
    private Label lbTenPhi;
    @FXML
    private Label lbSoTien;
    @FXML
    private Label lbBatBuoc;
    @FXML
    private Label lbChuKi;
    @FXML
    private Label lbHanNop;
    @FXML
    private Button btHuy;
    @FXML
    private Button btXoa;
    private int selectedFeeId = -1;
    @FXML
    public void initialize() {
        loadFeeNames();
        cbTenPhi.setOnAction(event -> loadFeeDetails());
        btXoa.setOnAction(this::deleteFee);
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
        String selectedFeeName =(String) cbTenPhi.getValue();
        if (selectedFeeName != null) {
            List<Fee> feeList = FeeAPI.getFeeList();
            for (Fee fee : feeList) {
                if (fee.getName().equals(selectedFeeName)) {
                    selectedFeeId = fee.getFeeId();
                    cbTenPhi.setValue(selectedFeeName);
                    lbTenPhi.setText("Thông tin khoản phí xoá: " + selectedFeeName);
                    lbSoTien.setText("Số tiền: " + (fee.getCost()));
                    if (fee.isMandatory()){
                        lbBatBuoc.setText("Phí bắt buộc!");
                    }
                    else{
                        lbBatBuoc.setText("Phí không bắt buộc!");
                    }
                    lbChuKi.setText("Chu kì: "+fee.getCycle()+" Tháng");
                    lbHanNop.setText("Hạn nộp: " + fee.getExpiration());
                    break;
                }
            }
        }
    }
    private void deleteFee(ActionEvent event) {
        if (selectedFeeId == -1) {
            showAlert("Lỗi!!!", "Vui lòng chọn khoản phí cần xoá.");
            return;
        }
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Xác nhận xóa");
        confirmationAlert.setHeaderText("Xoá khoản phí này?");
        confirmationAlert.setContentText("Bạn có chắc chắn muốn xóa khoản phí này?");
        if (confirmationAlert.showAndWait().orElse(null) != ButtonType.OK) {
            return;
        }
        boolean isDeleted = FeeAPI.deleteFee(selectedFeeId);
        if (isDeleted) {
            showAlert("Xoá Thành công!", "Khoản phí đã được xóa thành công.");
            clearFields();
            loadFeeNames();
            selectedFeeId = -1;
        } else {
            showAlert("Lỗi!!!", "Không thể xóa khoản phí. Vui lòng thử lại.");
        }
    }

    private void clearFields() {
        cbTenPhi.setValue(null);
        lbSoTien.setText(null);
        lbHanNop.setText(null);
        lbBatBuoc.setText(null);
        lbTenPhi.setText("Thông tin khoản phí xoá");
        lbChuKi.setText(null);
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
