package com.example.bluemoonmanagement.controllers.Fee_management;

import com.example.bluemoonmanagement.api.FeeAPI;
import com.example.bluemoonmanagement.models.Fee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Fee_Controller {
    private static Fee_Controller instance;
    private ObservableList<Fee> feeList;

    private Fee_Controller() {
        feeList = FXCollections.observableArrayList(FeeAPI.getAllFees());
    }

    public static Fee_Controller getInstance() {
        if (instance == null) {
            instance = new Fee_Controller();
        }
        return instance;
    }

    public ObservableList<Fee> getFeeList() {
        return feeList;
    }
    public void refreshFeeList() {
        feeList.setAll(FeeAPI.getAllFees());
    }
}
