package com.example.bluemoonmanagement.models;

import java.sql.Date;

public class Resident {
    private long id;
    private String name;
    private Date birthday;
    private boolean gender;
    private int phoneNumber;
    private String nationality;
    private String ethnic;
    private int floor;
    private int room;
    private String relationship;
    private int status;

    public Resident() {}
    public Resident(String name, Date birthday, boolean gender, int phoneNumber, String nationality, String ethnic, int floor, int room, String relationship) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.ethnic = ethnic;
        this.floor = floor;
        this.room = room;
        this.relationship = relationship;
    }
    public Resident(long id, String name, Date birthday, boolean gender, int phoneNumber, String nationality, String ethnic, int floor, int room, String relationship, int status) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.ethnic = ethnic;
        this.floor = floor;
        this.room = room;
        this.relationship = relationship;
        this.status = status;
    }

    public long getId() {return id;}
    public String getName() {return name;}
    public Date getBirthday() {return birthday;}
    public boolean getGender() {return gender;}
    public int getPhoneNumber() {return phoneNumber;}
    public String getNationality() {return nationality;}
    public String getEthnic() {return ethnic;}
    public int getFloor() {return floor;}
    public int getRoom() {return room;}
    public String getRelationship() {return relationship;}
    public int getStatus() {return status;}
    public String getStatusStr() {
        switch (status) {
            case 0:
                return "Thường trú";
            case 1:
                return "Tạm trú";
            case 2:
                return "Tạm vắng";
            case 3:
                return "Rời đi";
            default:
                return "";
        }
    }

    public void setId(long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setBirthday(Date birthday) {this.birthday = birthday;}
    public void setGender(boolean gender) {this.gender = gender;}
    public void setPhoneNumber(int phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setNationality(String nationality) {this.nationality = nationality;}
    public void setEthnic(String ethnic) {this.ethnic = ethnic;}
    public void setFloor(int floor) {this.floor = floor;}
    public void setRoom(int room) {this.room = room;}
    public void setRelationship(String relationship) {this.relationship = relationship;}
    public void setStatus(int status) {this.status = status;}

    @Override
    public String toString() {
        return name + " (Tầng " + floor + ", phòng" + room + ", " + relationship + "," + status + ")";
    }

    public String[] toData() {
        String[] data = {
                name, gender ? "Nữ" : "Nam",
                String.valueOf(birthday),
                "0" + phoneNumber,
                ethnic,
                nationality,
                String.valueOf(floor),
                String.valueOf(room),
                relationship,
                status == 0 ? "Thường trú" : status == 1 ? "Tạm trú" : "Tạm vắng"
        };
        return data;
    }

    public String[] toDataLite() {
        String[] data = {name, String.valueOf(birthday), String.valueOf(id), "0" + phoneNumber};
        return data;
    }
}
