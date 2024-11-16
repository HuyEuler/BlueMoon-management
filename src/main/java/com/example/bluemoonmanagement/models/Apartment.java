package com.example.bluemoonmanagement.models;

public class Apartment {
    private int apartmentId;
    private Integer ownerId;
    private float area;
    private int floor;
    private String room;

    // Constructor không chứa apartmentId (auto-increment)
    public Apartment(Integer ownerId, float area, int floor, String room) {
        this.ownerId = ownerId;
        this.area = area;
        this.floor = floor;
        this.room = room;
    }

    // Getters và Setters cho các thuộc tính

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}