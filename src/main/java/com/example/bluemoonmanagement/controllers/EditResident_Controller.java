package com.example.bluemoonmanagement.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDate;

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
