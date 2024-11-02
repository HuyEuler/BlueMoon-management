package com.example.bluemoonmanagement.models;

import javafx.scene.image.ImageView;

public class User {
    private int id;
    private String name;
    private String birthday;
    private String phoneNumber;
    private ImageView img;
    private String address;

    public User() {}
    public User(String name, String birthday, String phoneNumber, ImageView img, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.img = img;
        this.address = address;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getBirthday() {return birthday;}
    public ImageView getImg() {return img;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getAddress() {return address;}

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setBirthday(String birthday) {this.birthday = birthday;}
    public void setImg(ImageView img) {this.img = img;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setAddress(String address) {this.address = address;}
}
