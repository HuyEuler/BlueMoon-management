package com.example.bluemoonmanagement.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.prefs.Preferences;

import static com.example.bluemoonmanagement.api.LoginAPI.*;
import static com.example.bluemoonmanagement.common.GlobalVariable.*;

public class Login_Controller {
    @FXML
    private CheckBox remember;
    @FXML
    private TextField login_user;
    @FXML
    private TextField login_password;


    private final Preferences prefs = Preferences.userNodeForPackage(Login_Controller.class);
    private static final String CHECKBOX_KEY = "checkboxChecked";
    private static final String PASSWORD_KEY = "password";

    public void initialize() {
        boolean isChecked = prefs.getBoolean(CHECKBOX_KEY, false);
        remember.setSelected(isChecked);

        if (isChecked) {
            login_user.setText(getUsername(1));
            login_password.setText(prefs.get(PASSWORD_KEY, ""));

        } else {
            System.out.println("No");
        }

        remember.setOnAction(event -> {
            prefs.putBoolean(CHECKBOX_KEY, remember.isSelected());
        });

        login_password.textProperty().addListener((observable, oldValue, newValue) -> {
            prefs.put(PASSWORD_KEY, newValue);
        });
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        String username = login_user.getText();
        String password = login_password.getText();

        System.out.printf("Username: %s%n", username);
        System.out.printf("Password: %s%n", password);

        if (authenticate(username, password)) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/GUIMain.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, screenWidth, screenHeight);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Sai thông tin!");
            Alert confirmationAlert = new Alert(Alert.AlertType.ERROR);
            confirmationAlert.setTitle("Error");
            confirmationAlert.setHeaderText("Sai thông tin đăng nhập, vui lòng kiểm tra lại!");
            confirmationAlert.showAndWait();
        }
    }
}
