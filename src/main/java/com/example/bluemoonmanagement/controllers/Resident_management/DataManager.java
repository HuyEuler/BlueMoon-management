package com.example.bluemoonmanagement.controllers.Resident_management;

public class DataManager {
    private static final DataManager instance = new DataManager();
    private DataManager() {}

    private int ApartmentID = 0;
    private int ResidentID = 0;


    public static DataManager getInstance() {
        return instance;
    }

    public void setApartmentID(int id) {
        this.ApartmentID = id;
    }
    public int getApartmentID() {
        return ApartmentID;
    }

    public void setResidentID(int id) {
        this.ResidentID = id;
    }
    public int getResidentID() {
        return ResidentID;
    }
}