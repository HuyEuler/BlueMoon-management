package com.example.bluemoonmanagement.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.ImageView;

import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


public class Home_Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ImageView imageView;


    public void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/GUILogin.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
