package com.example.bluemoonmanagement.controllers.Account_management;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.*;
import java.util.Objects;

import static com.example.bluemoonmanagement.api.LoginAPI.*;
import static com.example.bluemoonmanagement.common.GlobalVariable.*;

public class EditAccountController {
    @FXML
    private PasswordField OldPassword;
    @FXML
    private PasswordField NewPassword;
    @FXML
    private PasswordField RewritePassword;
    @FXML
    private Button okButton;

    public void onOk() {
        try {
            String password = OldPassword.getText();
            String newPassword = NewPassword.getText();
            String rewritePassword = RewritePassword.getText();

            if (!Objects.equals(getPassword(USER.getId()), password)) {
                showAlert("Error", "Sai mật khẩu!");
            } else if (newPassword == "" || rewritePassword == "") {
                showAlert("Error", "Mật khẩu mới không được để trống!");
            } else if (!Objects.equals(newPassword, rewritePassword)) {
                showAlert("Error", "Mật khẩu nhập lại không trùng!");
            } else if (Objects.equals(password, newPassword)) {
                showAlert("Error", "Mật khẩu mới không được trùng mật khẩu cũ!");
            } else if (updateLogin(USER.getId(), getUsername(USER.getId()), newPassword)) {
                showAlert("Successful", "Cập nhập mật khẩu mới thành công!");
                closeWindow();
            }
        }
        catch (Exception e) {
            showAlert("Error", "Không được để trống");
        }
    }

    public void onCancel() {
        System.out.println("Cancel");
        closeWindow();
    }

    /*======================================================*/
    private void closeWindow() {
        // Lấy Stage hiện tại từ nút
        Stage stage = (Stage) okButton.getScene().getWindow();
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