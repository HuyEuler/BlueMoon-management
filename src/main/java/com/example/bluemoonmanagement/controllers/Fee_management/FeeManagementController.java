package com.example.bluemoonmanagement.controllers.Fee_management;


import com.example.bluemoonmanagement.api.PaymentAPI;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FeeManagementController {

    @FXML
    private Button addFee, editFee, deleteFee;
    @FXML
    private Button addPayment, payFee;
    @FXML
    private Button listPaid, listPending, listOverDue;
    private Stage stage;
    private Parent root;
    String title = new String();

    @FXML
    public void initialize() {
        if (addFee != null) {
            addFee.setOnAction(event -> openWindow("add_Fee", ""));
        }
        if (editFee != null){
            editFee.setOnAction(event -> openWindow("editFee", ""));
        }
        if (payFee != null){
            payFee.setOnAction(event -> openWindow("payFee", ""));
        }
        if (deleteFee != null){
            deleteFee.setOnAction(event -> openWindow("deleteFee",""));
        }
        if (listPaid != null){
            listPaid.setOnAction(event -> openWindow("listPaid", "paid"));
        }
        if (addPayment != null){
            addPayment.setOnAction(event -> openWindow("addPayment",""));
        }
        if (listPending != null){
            listPending.setOnAction(event -> openWindow("listPaid", "pending"));
        }
        if (listOverDue != null){
            listOverDue.setOnAction(event -> openWindow("listPaid", "overdue"));
        }
    }
    private void openWindow(String fileName, String listType) {
//        System.out.println("OKE");
        try {
            String path_file_fxml = "/views/fee_management/" + fileName + ".fxml";
            FXMLLoader loader = new FXMLLoader((getClass().getResource(path_file_fxml)));
            root = loader.load();
            if (fileName.equals("add_Fee")){
                title = "Thêm loại phí mới";
            }
            if (fileName.equals("editFee")){
                title = "Sửa phí";
            }
            if (fileName.equals("deleteFee")){
                title = "Xoá phí";
            }
            if (fileName.equals("payFee")){
                title = "Thanh toán phí";
            }
            if (fileName.equals("listPaid")){
                if (listType == null){
                    System.out.println("ENDDD!");
                }
                else {
                    System.out.println("Giá trị listType: " + listType);
                    ListPaidController controller=loader.getController();
                    controller.setListType(listType);
                    if ("paid".equals(listType)) {
                        title="Danh sách đã nộp phí";
                    }
                    else{
                        if ("pending".equals(listType)) {
                            title="Danh sách chưa nộp phí";
                        }
                        else{
                            title="Danh sách đã qua kì hạn";
                        }
                    }
                }
            }
            if (fileName.equals("addPayment")){
                title = "Tạo thanh toán cho phí quản lý và phí dịch vụ hàng tháng";
            }
            stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            // modal mode: not allow return before close this window
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


