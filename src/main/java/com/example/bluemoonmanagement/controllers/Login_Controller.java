package com.example.bluemoonmanagement.controllers;
import  com.example.bluemoonmanagement.api.LoginAPI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.example.bluemoonmanagement.api.LoginAPI.authenticate;

public class Login_Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ImageView imageView;

    @FXML
    private TextField login_user;
    @FXML
    private TextField login_password;

    public void switchToMainScene(ActionEvent event) throws IOException {
        String username = login_user.getText();
        String password = login_password.getText();

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        if (authenticate(username, password)) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/GUIMain.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Sai thông tin!");
            Alert confirmationAlert = new Alert(Alert.AlertType.ERROR);
            confirmationAlert.setTitle("Erorr");
            confirmationAlert.setHeaderText("Sai thông tin đăng nhập, vui lòng kiểm tra lại!");
        }
    }
}
