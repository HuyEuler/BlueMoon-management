package com.example.bluemoonmanagement.controllers.Fee_management;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TabController {
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab feeTab;
    @FXML
    private AnchorPane button_panel;
    @FXML
    private AnchorPane content;
    @FXML
    public void initialize() {
        feeTab.setOnSelectionChanged(event -> {
            if (feeTab.isSelected()) {
                loadFeeManagementContent();
            }
        });
    }

    private void loadFeeManagementContent() {
        try {
            // Load button_panel.fxml
            FXMLLoader buttonLoader = new FXMLLoader(getClass().getResource("/views/fee_management/button_panel.fxml"));
            AnchorPane buttonPane = buttonLoader.load();
            button_panel.getChildren().setAll(buttonPane);
            // Load content.fxml
            FXMLLoader contentLoader = new FXMLLoader(getClass().getResource("/views/fee_management/content.fxml"));
            AnchorPane contentPane = contentLoader.load();
            content.getChildren().setAll(contentPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
