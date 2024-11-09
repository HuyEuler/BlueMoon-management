package com.example.bluemoonmanagement.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAPI {
    // Phương thức kiểm tra đăng nhập
    public static boolean authenticate(String username, String password) {
        String query = "SELECT password FROM Login WHERE username = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return storedPassword.equals(password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Phương thức lấy username
    public static String getUsername(int userId){
        String query = "SELECT username FROM login WHERE userId = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getString("username");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Phương thức lấy password
    public static String getPassword(int userId){
        String query = "SELECT password FROM login WHERE userId = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getString("password");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Phương thức updateLogin - Cập nhật thông tin đăng nhập admin
    public static boolean updateLogin(int userId, String newUsername, String newPassword) {
        String query = "UPDATE login SET username = ?, password = ? WHERE userId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, newPassword); // Hash nếu cần thiết trước khi lưu
            preparedStatement.setInt(3, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
