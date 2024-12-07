package com.example.bluemoonmanagement;
import static com.example.bluemoonmanagement.common.GlobalVariable.*;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;



public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/views/GUILogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),screenWidth, screenHeight);
        stage.setTitle("Apartment Management System");
//        stage.setMaximized(true);
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/images/hotel-icon.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
