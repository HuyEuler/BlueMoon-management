package com.example.bluemoonmanagement.controllers.Account_management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.example.bluemoonmanagement.api.LoginAPI.*;
import static com.example.bluemoonmanagement.api.UserAPI.*;
import static com.example.bluemoonmanagement.common.GlobalVariable.*;

public class EditInformationController {
    @FXML
    private TextField TenBQL;
    @FXML
    private TextField NgaySinh;
    @FXML
    private TextField SDT;
    @FXML
    private TextField DiaChi;
    @FXML
    private PasswordField Password;

    public void onOk(ActionEvent event) throws IOException {
        System.out.println("OK");
        String tenbql = TenBQL.getText();
        String ngaysinh = NgaySinh.getText();
        String sdt = SDT.getText();
        String diachi = DiaChi.getText();
        String password = Password.getText();

        if (!Objects.equals(getPassword(USER.getId()), password)) {
            showAlert("Error","Sai mật khẩu!");
        } else if (updateUser(USER.getId(), tenbql, ngaysinh, sdt, diachi)){
            showAlert("Successful", "Cập nhập thông tin thành công!");
            closeWindow();
        }
    }

    public void onCancel(ActionEvent event) throws IOException {
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