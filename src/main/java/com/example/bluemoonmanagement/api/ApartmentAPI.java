package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.Apartment;
import com.example.bluemoonmanagement.models.Resident;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentAPI {

    // Phương thức trả về List tất cả Apartment
    // Lấy tất cả Apartment
    public static List<Apartment> getAllApartment() {
        List<Apartment> apartments = new ArrayList<>();
        String query = "SELECT * FROM Apartment";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                apartments.add(new Apartment(
                        rs.getInt("apartmentId"),
                        rs.getObject("ownerId") != null ? rs.getInt("ownerId") : null,
                        rs.getFloat("area"),
                        rs.getInt("floor"),
                        rs.getString("room")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartments;
    }

    // Phương thức trả về 1 Apartment theo Id
    public static Apartment getApartmentById(int id) {
        String query = "SELECT * FROM Apartment WHERE apartmentId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Apartment(
                        rs.getInt("apartmentId"),
                        rs.getObject("ownerId") != null ? rs.getInt("ownerId") : null,
                        rs.getFloat("area"),
                        rs.getInt("floor"),
                        rs.getString("room")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức trả về apartmentId theo số phòng
    public static Integer getApartmentIdByRoom(String room) {
        String query = "SELECT apartmentId FROM Apartment WHERE room = ?";
        Integer apartmentId = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, room);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                apartmentId = rs.getInt("apartmentId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartmentId;
    }


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
    public static boolean updateOwnerApartment(int apartmentId, Integer ownerId) {
        String query = "UPDATE Apartment SET ownerId = ? WHERE apartmentId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setObject(1, ownerId, Types.INTEGER);
            preparedStatement.setInt(2, apartmentId);
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
            List<Resident> residents = getResidentsFromApartmentId(id);
            for(Resident resident : residents){
                ResidentAPI.deleteResidentById(resident.getResidentId(), "Phá dỡ căn hộ");
            }
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Phương thức lấy danh sách các resident sinh sống trong 1 apartment
    public static List<Resident> getResidentsFromApartmentId(int apartmentId) {
        List<Resident> residents = new ArrayList<>();
        String query = "SELECT * FROM Resident WHERE apartmentId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, apartmentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                residents.add(new Resident(
                        rs.getInt("residentId"),
                        rs.getInt("apartmentId"),
                        rs.getString("name"),
                        rs.getString("birthday"),
                        rs.getBoolean("gender"),
                        rs.getString("phoneNumber"),
                        rs.getString("nationality"),
                        rs.getString("relationshipWithOwner"),
                        rs.getBoolean("isOwner"),
                        rs.getInt("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return residents;
    }
}

