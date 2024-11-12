package com.example.bluemoonmanagement;
import static com.example.bluemoonmanagement.common.globalVariable.*;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/views/GUILogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),screenBounds.getWidth(), screenBounds.getHeight());
        stage.setTitle("Apartment Management System");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
