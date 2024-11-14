package com.example.bluemoonmanagement.models;

public class Apartment {
    private int apartmentId;
    private int ownerId;
    private double area;        // Diện tích căn hộ
    private int floor;
    private String roomNumber;

    public Apartment() {
    }

    public Apartment(int apartmentId, int ownerId, double area, int floor, String roomNumber) {
        this.apartmentId = apartmentId;
        this.ownerId = ownerId;
        this.area = area;
        this.floor = floor;
        this.roomNumber = roomNumber;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentId=" + apartmentId +
                ", ownerId=" + ownerId +
                ", area=" + area +
                ", floor=" + floor +
                ", roomNumber='" + roomNumber + '\'' +
                '}';
    }


}
