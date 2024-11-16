package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.Apartment;

import java.sql.*;

public class ApartmentAPI {
    // Phương thức trả về 1 Apartment theo Id

    // Phương thức trả về List tất cả Apartment


    // Phương thức tạo thêm 1 Apartment mới
    public static Apartment addApartment(Integer ownerId, float area, int floor, String room) {
        String query = "INSERT INTO Apartment (ownerId, area, floor, room) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, ownerId, Types.INTEGER);
            preparedStatement.setFloat(2, area);
            preparedStatement.setInt(3, floor);
            preparedStatement.setString(4, room);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    Apartment newApartment = new Apartment(ownerId, area, floor, room);
                    newApartment.setApartmentId(id);
                    return newApartment;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức cập nhật ownerId cho 1 apartment
    public static boolean updateOwnerApartment(int id, int ownerId) {
        String query = "UPDATE Apartment SET ownerId = ? WHERE apartmentId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, ownerId);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa Apartment theo id
    public static boolean deleteApartment(int id) {
        String query = "DELETE FROM Apartment WHERE apartmentId = ?";
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

