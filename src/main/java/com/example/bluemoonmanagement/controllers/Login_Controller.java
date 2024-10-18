package com.example.bluemoonmanagement.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class Login_Controller {
    @FXML
    ImageView imageView;
    public void click(ActionEvent event) throws IOException {
        imageView.setVisible(true);
    }
}
