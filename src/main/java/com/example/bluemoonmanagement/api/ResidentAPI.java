package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.Resident;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                return new Resident(
                        id,
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức thêm 1 resident vào chung cư (2 hàm)
    // Trường note : Ghi chú khi thêm cư dân mới (), nếu không có để null
    // VD : A đăng ký thường trú ở trọ, thêm note "Ở trọ"
    // 2 phương thức add, trong đó 1 phương thức truyền vào apartmentId, 1 phương thức truyền vào room

    public static Resident addResident(int apartmentId, String name, String birthday, boolean gender,
                                String phoneNumber, String nationality,
                                String relationshipWithOwner, boolean isOwner, int status, String note) {
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
            // Lấy ngày hiện tại
            LocalDate currentDate = LocalDate.now();

            // Định dạng ngày
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDate.format(formatter);

            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int residentId = rs.getInt(1);
                    Resident resident = new Resident(residentId, apartmentId, name, birthday, gender, phoneNumber, nationality,
                            relationshipWithOwner, isOwner, status);

                    // Thêm sự kiện người dùng đăng ký thường trú / tạm trú vào Activity
                    ActivityAPI.addActivity(residentId, status, formattedDate, null, note);
                    if(isOwner){
                        ApartmentAPI.updateOwnerApartment(apartmentId, residentId);
                    }

                    return resident;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Resident addResident(String room, String name, String birthday, boolean gender,
                                       String phoneNumber, String nationality,
                                       String relationshipWithOwner, boolean isOwner, int status, String note) {
        String query = "INSERT INTO Resident (apartmentId, name, birthday, gender, phoneNumber, "
                + "nationality, relationshipWithOwner, isOwner, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            int apartmentId = ApartmentAPI.getApartmentIdByRoom(room);
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
            // Lấy ngày hiện tại
            LocalDate currentDate = LocalDate.now();

            // Định dạng ngày
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDate.format(formatter);

            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int residentId = rs.getInt(1);
                    Resident resident = new Resident(residentId, apartmentId, name, birthday, gender, phoneNumber, nationality,
                            relationshipWithOwner, isOwner, status);

                    // Thêm sự kiện người dùng đăng ký thường trú / tạm trú vào Activity
                    ActivityAPI.addActivity(residentId, status, formattedDate, null, note);
                    if(isOwner){
                        ApartmentAPI.updateOwnerApartment(apartmentId, residentId);
                    }

                    return resident;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update Resident theo id (3 hàm )
    // Trường note : Lý do (nếu có) nếu đăng ký tạm vắng, nếu không có điền null


    public static boolean updateResidentById(Resident updatedResident, String note) {
        int id = updatedResident.getResidentId();
        Resident unUpdatedResident = getResidentById(id);

        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        String query = "UPDATE Resident SET name = ?, birthday = ?, gender = ?, phoneNumber = ?, "
                + "nationality = ?, relationshipWithOwner = ?, isOwner = ?, status = ?, apartmentId = ? "
                + "WHERE residentId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, updatedResident.getName());
            stmt.setString(2, updatedResident.getBirthday());
            stmt.setBoolean(3, updatedResident.getGender());
            stmt.setString(4, updatedResident.getPhoneNumber());
            stmt.setString(5, updatedResident.getNationality());
            stmt.setString(6, updatedResident.getRelationshipWithOwner());
            stmt.setBoolean(7, updatedResident.getIsOwner());
            stmt.setInt(8, updatedResident.getStatus());
            stmt.setInt(9, updatedResident.getApartmentId());
            stmt.setInt(10, id);

            // Nếu đăng ký tạm vắng
            if(updatedResident.getStatus() != unUpdatedResident.getStatus()){
                ActivityAPI.addActivity(id, updatedResident.getStatus(), null, formattedDate, note);
            }
            // Nếu thay đổi căn hộ
            if(updatedResident.getApartmentId() != unUpdatedResident.getApartmentId()){
                if(updatedResident.getIsOwner()) ApartmentAPI.updateOwnerApartment(updatedResident.getApartmentId(), id);
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // hàm này khác hàm ở trên là có thể cập nhật số phòng(room)
    public static boolean updateResidentById(Resident updatedResident, String note, String room) {
        int id = updatedResident.getResidentId();
        Resident unUpdatedResident = getResidentById(id);
        updatedResident.setApartmentId(ApartmentAPI.getApartmentIdByRoom(room));

        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        String query = "UPDATE Resident SET name = ?, birthday = ?, gender = ?, phoneNumber = ?, "
                + "nationality = ?, relationshipWithOwner = ?, isOwner = ?, status = ?, apartmentId = ? "
                + "WHERE residentId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, updatedResident.getName());
            stmt.setString(2, updatedResident.getBirthday());
            stmt.setBoolean(3, updatedResident.getGender());
            stmt.setString(4, updatedResident.getPhoneNumber());
            stmt.setString(5, updatedResident.getNationality());
            stmt.setString(6, updatedResident.getRelationshipWithOwner());
            stmt.setBoolean(7, updatedResident.getIsOwner());
            stmt.setInt(8, updatedResident.getStatus());
            stmt.setInt(9, updatedResident.getApartmentId());
            stmt.setInt(10, id);

            // Nếu đăng ký tạm vắng
            if(updatedResident.getStatus() != unUpdatedResident.getStatus()){
                ActivityAPI.addActivity(id, updatedResident.getStatus(), null, formattedDate, note);
            }
            // Nếu thay đổi căn hộ
            if(updatedResident.getApartmentId() != unUpdatedResident.getApartmentId()){
                if(updatedResident.getIsOwner()) ApartmentAPI.updateOwnerApartment(updatedResident.getApartmentId(), id);
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateResidentById(int id, int apartmentId, String name, String birthday, boolean gender,
                                      String phoneNumber, String nationality,
                                      String relationshipWithOwner, boolean isOwner, int status, String note) {
        Resident unUpdatedResident = getResidentById(id);

        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        String query = "UPDATE Resident SET name = ?, birthday = ?, gender = ?, phoneNumber = ?, "
                + "nationality = ?, relationshipWithOwner = ?, isOwner = ?, status = ?, apartmentId = ? "
                + "WHERE residentId = ?";
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
            stmt.setInt(9, apartmentId);
            stmt.setInt(10, id);

            // Nếu đăng ký tạm vắng
            if(status != unUpdatedResident.getStatus()){
                ActivityAPI.addActivity(id, status, null, formattedDate, note);
            }
            // Nếu thay đổi căn hộ
            if(apartmentId != unUpdatedResident.getApartmentId()){
                if(isOwner) ApartmentAPI.updateOwnerApartment(apartmentId, id);
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Trả về danh sách tất cả các Resident
    public static List<Resident> getAllResidents() {
        List<Resident> residents = new ArrayList<>();
        String query = "SELECT * FROM Resident WHERE status != 0";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
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

    // Xóa Resident theo id
    // Trường note : Ghi chú (nếu có) khi rời chung cư, nếu không có điền null
    public static boolean deleteResidentById(int id, String note) {
        String query = "UPDATE Resident SET status = 0, apartmentId = NULL WHERE residentId = ?";
        String queryDeleteVehicle = "DELETE FROM Vehicle WHERE residentId = ?";
        Resident deletedResident = getResidentById(id);
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        try (Connection conn = DBConnection.getConnection()) {
            // Xóa tất cả Vehicle của resident
            try (PreparedStatement deleteVehicleStmt = conn.prepareStatement(queryDeleteVehicle)) {
                deleteVehicleStmt.setInt(1, id);
                deleteVehicleStmt.executeUpdate();
            }

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                ActivityAPI.addActivity(id, 0, null, formattedDate, note);

                assert deletedResident != null;
                if(deletedResident.getIsOwner()){
                    ApartmentAPI.updateOwnerApartment(deletedResident.getApartmentId(), null);
                }
                return stmt.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Resident> getAllDataFromResidentTableInDB() {
        List<Resident> residents = new ArrayList<>();
        String query = "SELECT * FROM Resident";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
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

