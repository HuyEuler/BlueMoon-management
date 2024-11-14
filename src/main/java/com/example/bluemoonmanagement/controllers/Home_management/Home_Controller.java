package com.example.bluemoonmanagement.controllers.Home_management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static com.example.bluemoonmanagement.common.GlobalVariable.screenBounds;


public class Home_Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ImageView imageView;


    public void switchToLogin(ActionEvent event) throws IOException {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Warning!");
        confirmationAlert.setHeaderText("Thoát chương trình?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/GUILogin.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private Button editInformation;
    @FXML
    private Button changeAccount;

    @FXML
    private TextField idBQL;

    @FXML
    private TextField idNgaySinh;

    @FXML
    private TextField idSDT;

    @FXML
    private TextField idDiaChi;

    @FXML
    public void initialize() {
        if (editInformation != null) {
            editInformation.setOnAction(event -> openWindow("editInformation"));
        }
        if (changeAccount != null){
            changeAccount.setOnAction(event -> openWindow("changeAccount"));
        }
    }

    private void openWindow(String fileName) {
        System.out.println("OKE");
        try {
            String path_file_fxml = "/views/home/" + fileName + ".fxml";
            FXMLLoader loader = new FXMLLoader((getClass().getResource(path_file_fxml)));
            root = loader.load();
            stage = new Stage();

            if (fileName.equals("changeAccount")) {
                stage.setTitle("Change password");
            } else if (fileName.equals("editInformation")) {
                stage.setTitle("Change information");
            }
            stage.setScene(new Scene(root));
            // modal mode: not allow return before close this window
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
