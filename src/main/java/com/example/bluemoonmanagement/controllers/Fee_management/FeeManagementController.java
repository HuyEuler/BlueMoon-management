package com.example.bluemoonmanagement.controllers.Fee_management;


import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FeeManagementController {

    @FXML
    private Button addFee;
    @FXML
    private Button editFee;
    @FXML
    private Button deleteFee;
    @FXML
    private Button payFee;
    @FXML
    private Button listPaid;

    @FXML
    public void initialize() {
        if (addFee != null) {
            addFee.setOnAction(event -> openWindow("add_Fee"));
        }
        if (editFee != null){
            editFee.setOnAction(event -> openWindow("editFee"));
        }
        if (payFee != null){
            payFee.setOnAction(event -> openWindow("payFee"));
        }
        if (deleteFee != null){
            deleteFee.setOnAction(event -> openWindow("deleteFee"));
        }
        if (listPaid != null){
            listPaid.setOnAction(event -> openWindow("listPaid"));
        }
    }
    private void openWindow(String fileName) {
//        System.out.println("OKE");
        try {
            String path_file_fxml = "/views/fee_management/" + fileName + ".fxml";
            FXMLLoader loader = new FXMLLoader((getClass().getResource(path_file_fxml)));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Thêm loại phí mới");
            stage.setScene(new Scene(root));
            // modal mode: not allow return before close this window
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


