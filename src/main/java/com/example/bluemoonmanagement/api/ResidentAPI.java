package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.Resident;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResidentAPI {
    // Phương thức trả về Resident object theo id
    public static Resident getResidentById(int id) {
        String query = "SELECT * FROM Resident WHERE residentId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Resident resident = new Resident(
                        rs.getInt("apartmentId"),
                        rs.getString("name"),
                        rs.getString("birthday"),
                        rs.getBoolean("gender"),
                        rs.getString("phoneNumber"),
                        rs.getString("nationality"),
                        rs.getString("relationshipWithOwner"),
                        rs.getBoolean("isOwner"),
                        rs.getInt("status")
                );
                resident.setResidentId(id);
                return resident;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức thêm 1 resident vào chung cư
    public static Resident addResident(int apartmentId, String name, String birthday, boolean gender,
                                String phoneNumber, String nationality,
                                String relationshipWithOwner, boolean isOwner, int status) {
        String query = "INSERT INTO Resident (apartmentId, name, birthday, gender, phoneNumber, "
                + "nationality, relationshipWithOwner, isOwner, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, apartmentId);
            stmt.setString(2, name);
            stmt.setString(3, birthday);
            stmt.setBoolean(4, gender);
            stmt.setString(5, phoneNumber);
            stmt.setString(6, nationality);
            stmt.setString(7, relationshipWithOwner);
            stmt.setBoolean(8, isOwner);
            stmt.setInt(9, status);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int residentId = rs.getInt(1);
                    Resident resident = new Resident(apartmentId, name, birthday, gender, phoneNumber, nationality,
                            relationshipWithOwner, isOwner, status);
                    resident.setResidentId(residentId);
                    return resident;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Update Resident theo id
    public static boolean updateResidentById(int id, String name, String birthday, boolean gender,
                                      String phoneNumber, String nationality,
                                      String relationshipWithOwner, boolean isOwner, int status) {
        String query = "UPDATE Resident SET name = ?, birthday = ?, gender = ?, phoneNumber = ?, "
                + "nationality = ?, relationshipWithOwner = ?, isOwner = ?, status = ? WHERE residentId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, birthday);
            stmt.setBoolean(3, gender);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, nationality);
            stmt.setString(6, relationshipWithOwner);
            stmt.setBoolean(7, isOwner);
            stmt.setInt(8, status);
            stmt.setInt(9, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Trả về danh sách tất cả các Resident
    public static List<Resident> getAllResidents() {
        List<Resident> residents = new ArrayList<>();
        String query = "SELECT * FROM Resident";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Resident resident = new Resident(
                        rs.getInt("apartmentId"),
                        rs.getString("name"),
                        rs.getString("birthday"),
                        rs.getBoolean("gender"),
                        rs.getString("phoneNumber"),
                        rs.getString("nationality"),
                        rs.getString("relationshipWithOwner"),
                        rs.getBoolean("isOwner"),
                        rs.getInt("status")
                );
                resident.setResidentId(rs.getInt("residentId"));
                residents.add(resident);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return residents;
    }

    // Xóa Resident theo id
    public static boolean deleteResidentById(int id) {
        String query = "DELETE FROM Resident WHERE residentId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

