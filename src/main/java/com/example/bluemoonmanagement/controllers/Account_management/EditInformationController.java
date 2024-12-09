package com.example.bluemoonmanagement.controllers.Account_management;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Objects;

import static com.example.bluemoonmanagement.api.LoginAPI.*;
import static com.example.bluemoonmanagement.api.UserAPI.*;
import static com.example.bluemoonmanagement.common.GlobalVariable.*;

public class EditInformationController {
    @FXML
    private TextField TenBQL;
    @FXML
    private DatePicker NgaySinh;
    @FXML
    private TextField SDT;
    @FXML
    private TextField DiaChi;
    @FXML
    private PasswordField Password;

    DataManager dataManager = DataManager.getInstance();

    public void onOk() {
        try {
            String tenbql = TenBQL.getText();
            String ngaysinh = NgaySinh.getValue().toString();
            String sdt = SDT.getText();
            String diachi = DiaChi.getText();
            String password = Password.getText();

            if (tenbql == null || sdt == null || diachi == null) {
                showAlert("Error", "Phải nhập đầy đủ thông tin");
            }

            if (!Objects.equals(getPassword(USER.getId()), password)) {
                showAlert("Error", "Sai mật khẩu!");
            } else if (updateUser(USER.getId(), tenbql, ngaysinh, sdt, diachi)) {
                dataManager.setUserName(tenbql);
                dataManager.setUserBirthday(ngaysinh);
                dataManager.setPhoneNumber(sdt);
                dataManager.setAddress(diachi);
                showAlert("Successful", "Cập nhập thông tin thành công!");
                closeWindow();
            }
        } catch (Exception e) {
            showAlert("Error", "Phải nhập đầy đủ thông tin");
        }
    }

    public void onCancel() {
        System.out.println("Cancel");
        closeWindow();
    }

    /*======================================================*/
    private void closeWindow() {
        Stage stage = (Stage) Password.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}