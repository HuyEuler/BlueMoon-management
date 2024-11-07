package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAPI {
    // Phương thức lấy thông tin admin
    public static User getUser(int userId){
        String query = "SELECT * FROM user WHERE userId = ?";
        User user = new User();
        user.setId(userId);
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setBirthday(resultSet.getString("birthday"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setAddress(resultSet.getString("address"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Phương thức update - Cập nhật thông tin admin
    public static boolean updateUser(int userId, String name, String birthday, String phoneNumber, String address) {
        String query = "UPDATE User SET name = ?, birthday = ?, phoneNumber = ?, address = ? WHERE userId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, java.sql.Date.valueOf(birthday));  // Định dạng ngày kiểu yyyy-MM-dd
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, address);
            preparedStatement.setInt(5, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
