package com.example.bluemoonmanagement;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.bluemoonmanagement.common.GlobalVariable.*;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/views/GUILogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),screenWidth, screenHeight);
        stage.setTitle("Apartment Management System");

        stage.setScene(scene);
        stage.show();
}

    public static void main(String[] args) {
        launch();
    }
}
