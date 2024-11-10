package com.example.bluemoonmanagement.controllers.Fee_management;
import com.example.bluemoonmanagement.api.FeeAPI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.sql.Date;

public class AddFeeController {
    @FXML
    private TextField tfTenPhi;
    @FXML
    private TextField tfSoTien;
    @FXML
    private CheckBox cbBatBuoc;
    @FXML
    private ChoiceBox cbChuKi;
    @FXML
    private DatePicker dpHanNop;
    @FXML
    private Button btHuy;
    @FXML
    private Button btThem;
    private ShowFeeController showFeeController;
    @FXML
    public void initialize() {
        ObservableList<Integer> cycles = FXCollections.observableArrayList(0, 1, 2, 3, 6, 9, 12, 24); //0: phí một lần
        cbChuKi.setItems(cycles);
        cbChuKi.setValue(1);
        btHuy.setOnAction(event -> clearFields());
        btThem.setOnAction(event -> addFeeToDatabase());
    }
    private void addFeeToDatabase() {
        String name = tfTenPhi.getText();
        int cost = Integer.parseInt(tfSoTien.getText());
        boolean mandatory = cbBatBuoc.isSelected();
        int cycle = Integer.parseInt(cbChuKi.getValue().toString());
        LocalDate expirationDate = dpHanNop.getValue();
        Date expiration = Date.valueOf(expirationDate);

        boolean success = FeeAPI.addFee(name, cost, mandatory, cycle, expiration, 1);
        if (success) {
            if (showFeeController != null) {
                showFeeController.refreshData();
            }
            showAlert("Thêm phí thành công!", "Thông tin khoản phí đã được thêm vào danh sách.");
        }
        else {
            showAlert("Lỗi!!", "Thêm khoản phí thất bại. Vui lòng kiểm tra lại");
        }
    }
    private void clearFields() {
        tfTenPhi.setText(null);
        tfSoTien.clear();
        cbBatBuoc.setSelected(false);
        cbChuKi.setValue(null);
        dpHanNop.setValue(null);
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}