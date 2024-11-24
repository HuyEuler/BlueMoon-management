package com.example.bluemoonmanagement.api;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.bluemoonmanagement.models.Activity;

public class ActivityAPI {
    // Ý nghĩa trường status :
    // status = 0 : Đã rời chung cư
    // status = 1 : Thường trú
    // status = 2 : Tạm trú
    // status = 3 : Tạm vắng
    // Thêm Activity mới
    public static boolean addActivity(int residentId, int status, String timeIn, String timeOut, String note) {
        String query = "INSERT INTO Activity (residentId, status, timeIn, timeOut, note) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, residentId);
            stmt.setInt(2, status);
            stmt.setString(3, timeIn);
            stmt.setString(4, timeOut);
            stmt.setString(5, note);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy tất cả Activity của một Resident
    public static List<Activity> getAllActivityOfResident(int residentId) {
        List<Activity> activities = new ArrayList<>();
        String query = "SELECT * FROM Activity WHERE residentId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, residentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                activities.add(new Activity(
                        rs.getInt("residentId"),
                        rs.getInt("status"),
                        rs.getString("timeIn"),
                        rs.getString("timeOut"),
                        rs.getString("note")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }
}