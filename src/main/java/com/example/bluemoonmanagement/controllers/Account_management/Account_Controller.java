package com.example.bluemoonmanagement.controllers.Account_management;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import com.example.bluemoonmanagement.models.User;
import static com.example.bluemoonmanagement.api.UserAPI.*;
import static com.example.bluemoonmanagement.common.GlobalVariable.*;

public class Account_Controller {
    private Stage stage;
    private Parent root;

    /*=====================================*/
    @FXML
    private TextField TenBQL;
    @FXML
    private TextField NgaySinh;
    @FXML
    private TextField SDT;
    @FXML
    private TextField DiaChi;

    @FXML
    public void initialize() {
        System.out.println("TenBQL is " + (TenBQL == null));
    }

    /*========================================================*/
    public void editInformation(ActionEvent event) throws IOException {
        //System.out.println("Edit Information");
        openWindow("Edit Information","/views/account/editInformation.fxml");
    }

    public void editAccount(ActionEvent event) throws IOException {
        //System.out.println("Edit Account");
        openWindow("Edit Account","/views/account/editAccount.fxml");
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Warning!");
        confirmationAlert.setHeaderText("Thoát chương trình?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/GUILogin.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
            stage.setScene(scene);
            stage.show();
        }
    }

    /*========================================================*/
    private void openWindow(String title, String url) {
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource(url)));
            root = loader.load();

            stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

            Platform.runLater(this::setTextField);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTextField() {
        if (TenBQL == null) {
            System.out.println("TextField 'TenBQL' is null. Check FXML bindings.");
        } else {
            assert USER != null : "Name is null";
            TenBQL.setText(USER.getName());
        }
        if (NgaySinh == null) {
            System.out.println("TextField 'NgaySinh' is null. Check FXML bindings.");
        } else {
            assert USER != null : "Birthday is null";
            NgaySinh.setText(USER.getBirthday());
        }
        if (SDT == null) {
            System.out.println("TextField 'SDT' is null. Check FXML bindings.");
        } else {
            assert USER != null : "Phone Number is null";
            SDT.setText(USER.getPhoneNumber());
        }
        if (DiaChi == null) {
            System.out.println("TextField 'DiaChi' is null. Check FXML bindings.");
        } else {
            assert USER != null : "Address is null";
            DiaChi.setText(USER.getAddress());
        }


    }
    /*========================================================*/
}
