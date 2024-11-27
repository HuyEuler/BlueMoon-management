package com.example.bluemoonmanagement.models;
public class Vehicle {
    private int vehicleId;         // ID phương tiện, tự tăng
    private int residentId;        // ID của Resident sở hữu phương tiện
    private String type;           // Loại phương tiện (car, bike, etc.)
    private String licensePlate;   // Biển số xe


    // Constructor đầy đủ tham số (ngoại trừ ID tự tăng)
    public Vehicle(int residentId, String type, String licensePlate) {
        this.residentId = residentId;
        this.type = type;
        this.licensePlate = licensePlate;
    }

    // Constructor đầy đủ (bao gồm vehicleId)
    public Vehicle(int vehicleId, int residentId, String type, String licensePlate) {
        this.vehicleId = vehicleId;
        this.residentId = residentId;
        this.type = type;
        this.licensePlate = licensePlate;
    }

    // Getters and Setters
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    // toString() method để in thông tin Vehicle
    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", residentId=" + residentId +
                ", type='" + type + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }
}