package com.example.bluemoonmanagement.controllers.Account_management;

import com.example.bluemoonmanagement.controllers.DataManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static com.example.bluemoonmanagement.common.GlobalVariable.*;

public class Account_content_controller {
    /*=====================================*/
    @FXML
    private TextField TenBQL;
    @FXML
    private TextField NgaySinh;
    @FXML
    private TextField SDT;
    @FXML
    private TextField DiaChi;

    public void initialize() {
        DataManager dataManager = DataManager.getInstance();
        System.out.println("Call initialize of content_Account");
        setTextField();

        dataManager.getUserName().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                TenBQL.setText(newValue);
            }
        });

        dataManager.getUserBirthday().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                NgaySinh.setText(newValue);
            }
        });

        dataManager.getPhoneNumber().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                SDT.setText(newValue);
            }
        });

        dataManager.getAddress().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                DiaChi.setText(newValue);
            }
        });

    }

    public void setTextField() {
        TenBQL.setText(USER.getName());
        NgaySinh.setText(USER.getBirthday());
        SDT.setText(USER.getPhoneNumber());
        DiaChi.setText(USER.getAddress());
    }
    /*========================================================*/
}
