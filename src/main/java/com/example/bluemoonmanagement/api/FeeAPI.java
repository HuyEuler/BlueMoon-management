package com.example.bluemoonmanagement.api;

import java.sql.*;

public class FeeAPI {
//    public static void addFee(String name, int cost, boolean mandatory, int cycle, Date expiration, int status) {
//        String query = "INSERT INTO fee (name, cost, mandatory, cycle, expiration, status) VALUES (?, ?, ?, ?, ?, ?)";
//        try (Connection connection = DBConnection.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            preparedStatement.setString(1, name);
//            preparedStatement.setInt(2, cost);
//            preparedStatement.setBoolean(3, mandatory);
//            preparedStatement.setInt(4, cycle);
//            preparedStatement.setDate(5, new java.sql.Date(expiration.getTime()));
//            preparedStatement.setInt(6, status);
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public static boolean addFee(String name, int cost, boolean mandatory, int cycle, Date expiration, int status) {
        String query = "INSERT INTO fee (name, cost, mandatory, cycle, expiration, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, cost);
            preparedStatement.setBoolean(3, mandatory);
            preparedStatement.setInt(4, cycle);
            preparedStatement.setDate(5, new java.sql.Date(expiration.getTime()));
            preparedStatement.setInt(6, status);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean editFee(int feeId, String name, int cost, boolean mandatory, int cycle, Date expiration, int status) {
        String query = "UPDATE fee SET name = ?, cost = ?, mandatory = ?, cycle = ?, expiration = ?, status = ? WHERE feeId = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, cost);
            preparedStatement.setBoolean(3, mandatory);
            preparedStatement.setInt(4, cycle);
            preparedStatement.setDate(5, new java.sql.Date(expiration.getTime()));
            preparedStatement.setInt(6, status);
            preparedStatement.setInt(7, feeId);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteFee(int feeId) {
        String query = "DELETE FROM fee WHERE feeId = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, feeId);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
