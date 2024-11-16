package com.example.bluemoonmanagement.models;

public class Resident {
    private int residentId;
    private int apartmentId;
    private String name;
    private String birthday;
    private boolean gender;
    private String phoneNumber;
    private String nationality;
    private String relationshipWithOwner;
    private int status;
    private boolean isOwner;

    // Constructor không chứa residentId (auto-increment)
    public Resident(int apartmentId, String name, String birthday, boolean gender, String phoneNumber,
                    String nationality, String relationshipWithOwner, boolean isOwner, int status) {
        this.apartmentId = apartmentId;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.relationshipWithOwner = relationshipWithOwner;
        this.isOwner = isOwner;
        this.status = status;
    }

    // Getters và Setters cho các thuộc tính

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRelationshipWithOwner() {
        return relationshipWithOwner;
    }

    public void setRelationshipWithOwner(String relationshipWithOwner) {
        this.relationshipWithOwner = relationshipWithOwner;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(boolean owner) {
        isOwner = owner;
    }
}

