package com.example.bluemoonmanagement.controllers.Account_management;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static com.example.bluemoonmanagement.common.GlobalVariable.*;

public class Account_button_controller {
    private Stage stage;
    private Parent root;

    /*=====================================*/

    public void initialize() {
        System.out.println("Call initialize of button_Account");
    }

    /*========================================================*/
    public void editInformation() {
        //System.out.println("Edit Information");
        openWindow("Edit Information","/views/account/editInformation.fxml");
    }

    public void editAccount() {
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
            Scene scene = new Scene(root, screenWidth, screenHeight);
            stage.setScene(scene);
            stage.show();
        }
    }

    /*========================================================*/
    public void openWindow(String title, String url) {
        try {
            FXMLLoader loader = new FXMLLoader((getClass().getResource(url)));
            root = loader.load();
            stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
