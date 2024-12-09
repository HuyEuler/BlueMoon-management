package com.example.bluemoonmanagement.controllers.Fee_management;

import com.example.bluemoonmanagement.api.FeeAPI;
import com.example.bluemoonmanagement.models.Fee;
import com.example.bluemoonmanagement.models.FeeType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.text.DecimalFormat;
import java.util.List;

public class DeleteFeeController {
    @FXML
    private ChoiceBox<String> cbTenPhi;
    @FXML
    private Label lbTenPhi;
    @FXML
    private Label lbDonGia;
    @FXML
    private Label lbBatBuoc;
    @FXML
    private Label lbLoaiPhi;
    @FXML
    private Button btHuy;
    @FXML
    private Button btXoa;
    private List<Fee> feeList;
    private int selectedFeeId = -1;

    @FXML
    public void initialize() {
        loadFeeList();
        cbTenPhi.setOnAction(event -> loadFeeDetails());
        btXoa.setOnAction(this::deleteFee);
        btHuy.setOnAction(event -> clearFields());
    }
    private void loadFeeList() {
        feeList = FeeAPI.getAllFees();
        if (feeList == null || feeList.isEmpty()) {
            showAlert("Thông báo", "Không có khoản phí nào để xóa.");
            cbTenPhi.setItems(FXCollections.observableArrayList());
            return;
        }
        ObservableList<String> feeNames = FXCollections.observableArrayList();

        for (Fee fee : feeList) {
            feeNames.add(fee.getName());
        }
        cbTenPhi.setItems(feeNames);
    }
    private void loadFeeDetails() {
        String selectedFeeName =(String) cbTenPhi.getValue();
        if (selectedFeeName != null) {
            List<Fee> fees = FeeAPI.getAllFees();
            for (Fee fee : fees) {
                if (fee.getName().equals(selectedFeeName)) {
                    selectedFeeId = fee.getFeeId();
                    cbTenPhi.setValue(selectedFeeName);
                    lbTenPhi.setText("Thông tin khoản phí xoá: " + selectedFeeName);
                    Double amount = fee.getRatePerSquareMeter();
                    DecimalFormat decimalFormat = new DecimalFormat("#,###");
                    String formattedAmount = decimalFormat.format(amount);
                    lbDonGia.setText("Số tiền: " + formattedAmount + " VNĐ");
                    if (fee.isMandatory()){
                        lbBatBuoc.setText("Phí bắt buộc!");
                    }
                    else{
                        lbBatBuoc.setText("Phí không bắt buộc!");
                    }
                    String s = null;
                    if (fee.getFeeType() == FeeType.CONTRIBUTION_FEE){
                        s = "Thiện nguyện";
                    }
                    else {
                        if (fee.getFeeType() == FeeType.SERVICE_FEE){
                            s = "Dịch vụ";
                        }
                        else{
                            if (fee.getFeeType() == FeeType.VEHICLE_FEE){
                                s = "Phương tiện";
                            }
                            else {
                                s="Quản lý";
                            }
                        }
                    }
                    lbLoaiPhi.setText("Loại phí: " +  s);

                    break;
                }
            }
        }
    }
    private void deleteFee(ActionEvent event) {
        try {
            if (selectedFeeId == -1) {
                showAlert("Lỗi!!!", "Vui lòng chọn khoản phí cần xoá.");
                return;
            }
            Alert confirmationAlert=new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Xác nhận xóa");
            confirmationAlert.setHeaderText("Xoá khoản phí này?");
            confirmationAlert.setContentText("Bạn có chắc chắn muốn xóa khoản phí này?");
            if (confirmationAlert.showAndWait().orElse(null) != ButtonType.OK) {
                return;
            }
            boolean isDeleted=FeeAPI.deleteFee(selectedFeeId);
            if (isDeleted) {
                Fee_Controller.getInstance().refreshFeeList();
                showAlert("Xoá Thành công!", "Khoản phí đã được xóa thành công.");
                clearFields();
                loadFeeList();
                selectedFeeId=-1;
            } else {
                showAlert("Lỗi!!!", "Không thể xóa khoản phí. Vui lòng thử lại.");
            }
        }
        catch (Exception e){
            showAlert("Lỗi", "Vui lòng chọn đủ thông tin");
        }
    }

    private void clearFields() {
        cbTenPhi.setValue(null);
        lbDonGia.setText(null);
        lbLoaiPhi.setText(null);
        lbBatBuoc.setText(null);
        lbTenPhi.setText("Thông tin khoản phí xoá");
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
