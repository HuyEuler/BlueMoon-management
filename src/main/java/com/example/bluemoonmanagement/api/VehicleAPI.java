package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleAPI {

    // Phương thức trả về 1 Vehicle theo ID
    public static Vehicle getVehicleById(int vehicleId) {
        String query = "SELECT * FROM Vehicle WHERE vehicleId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Vehicle(
                        rs.getInt("vehicleId"),
                        rs.getInt("residentId"),
                        rs.getString("type"),
                        rs.getString("licensePlate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức thêm Vehicle
    public static boolean addVehicle(int residentId, String type, String licensePlate) {
        String query = "INSERT INTO Vehicle (residentId, type, licensePlate) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, residentId);
            stmt.setString(2, type);
            stmt.setString(3, licensePlate);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Phương thức sửa thông tin Vehicle
    public static boolean editVehicle(Vehicle updatedVehicle) {
        int vehicleId = updatedVehicle.getVehicleId();
        String query = "UPDATE Vehicle SET residentId = ?, type = ?, licensePlate = ? WHERE vehicleId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, updatedVehicle.getResidentId());
            stmt.setString(2, updatedVehicle.getType());
            stmt.setString(3, updatedVehicle.getLicensePlate());
            stmt.setInt(4, vehicleId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean editVehicle(int vehicleId, int residentId, String type, String licensePlate) {
        String query = "UPDATE Vehicle SET residentId = ?, type = ?, licensePlate = ? WHERE vehicleId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, residentId);
            stmt.setString(2, type);
            stmt.setString(3, licensePlate);
            stmt.setInt(4, vehicleId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Phương thức xóa Vehicle theo ID
    public static boolean deleteVehicle(int vehicleId) {
        String query = "DELETE FROM Vehicle WHERE vehicleId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, vehicleId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Phương thức lấy tất cả Vehicle
    public static List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicle";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                        rs.getInt("vehicleId"),
                        rs.getInt("residentId"),
                        rs.getString("type"),
                        rs.getString("licensePlate")
                );
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    // Phương thức trả về tất cả vehicle trong 1 apartment
    public static List<Vehicle> getAllVehiclesByApartmentId(int apartmentId) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = """
            SELECT v.vehicleId, v.residentId, v.type, v.licensePlate 
            FROM Vehicle v 
            JOIN Resident r ON v.residentId = r.residentId 
            WHERE r.apartmentId = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, apartmentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                        rs.getInt("vehicleId"),
                        rs.getInt("residentId"),
                        rs.getString("type"),
                        rs.getString("licensePlate")
                );
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    // Phương thức trả về tất cả vehicle của 1 resident
    public static List<Vehicle> getAllVehiclesByResidentId(int residentId) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = """
            SELECT v.vehicleId, v.residentId, v.type, v.licensePlate 
            FROM Vehicle v 
            WHERE v.residentId = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, residentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                        rs.getInt("vehicleId"),
                        rs.getInt("residentId"),
                        rs.getString("type"),
                        rs.getString("licensePlate")
                );
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

}

