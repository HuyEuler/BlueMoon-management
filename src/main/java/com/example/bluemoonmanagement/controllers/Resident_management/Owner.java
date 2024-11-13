package com.example.bluemoonmanagement.controllers.Resident_management;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Owner {
    private final StringProperty tang;
    private final StringProperty phong;
    private final StringProperty dienTich;
    private final StringProperty tenChuSoHuu;
    private final StringProperty soDienThoai;

    public Owner(String tang, String phong, String dienTich, String tenChuSoHuu, String soDienThoai) {
        this.tang = new SimpleStringProperty(tang);
        this.phong = new SimpleStringProperty(phong);
        this.dienTich = new SimpleStringProperty(dienTich);
        this.tenChuSoHuu = new SimpleStringProperty(tenChuSoHuu);
        this.soDienThoai = new SimpleStringProperty(soDienThoai);
    }

    public StringProperty tangProperty() { return tang; }
    public StringProperty phongProperty() { return phong; }
    public StringProperty dienTichProperty() { return dienTich; }
    public StringProperty tenChuSoHuuProperty() { return tenChuSoHuu; }
    public StringProperty soDienThoaiProperty() { return soDienThoai; }
}

