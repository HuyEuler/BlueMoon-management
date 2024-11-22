package com.example.bluemoonmanagement.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import static com.example.bluemoonmanagement.common.GlobalVariable.USER;

public class DataManager {
    private static final DataManager instance = new DataManager();
    private DataManager() {}

    private final StringProperty userName = new SimpleStringProperty(USER.getName());
    private final StringProperty userBirthday = new SimpleStringProperty(USER.getBirthday());
    private final StringProperty phoneNumber = new SimpleStringProperty(USER.getPhoneNumber());
    private final StringProperty address = new SimpleStringProperty(USER.getAddress());

    public static DataManager getInstance() {
        return instance;
    }

    public StringProperty getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public StringProperty getUserBirthday() {
        return userBirthday;
    }
    public void setUserBirthday(String birthday) {
        this.userBirthday.set(birthday);
    }

    public StringProperty getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
    public StringProperty getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address.set(address);
    }

}
