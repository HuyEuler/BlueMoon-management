package com.example.bluemoonmanagement.controllers.Resident_management;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditResident_Controller {
    @FXML
    private TextArea dang_ky_tam_vang;

    @FXML
    private void handleSave(){
        String dktv = dang_ky_tam_vang.getText();
        Stage stage = (Stage) dang_ky_tam_vang.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel(){
        Stage stage = (Stage) dang_ky_tam_vang.getScene().getWindow();
        stage.close();
    }
}
