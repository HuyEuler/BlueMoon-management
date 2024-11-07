package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentAPI {

    public static boolean addPayment(int feeId, int apartmentId, Date timeValidate, int payForMonth, int payForYear, boolean isLate) {
        String query = "INSERT INTO payment (feeId, apartmentId, timeValidate, payForMonth, payForYear, isLate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, feeId);
            preparedStatement.setInt(2, apartmentId);
            preparedStatement.setDate(3, new java.sql.Date(timeValidate.getTime()));
            preparedStatement.setInt(4, payForMonth);
            preparedStatement.setInt(5, payForYear);
            preparedStatement.setBoolean(6, isLate);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Payment> getListPayment() {
        String query = "SELECT * FROM payment";
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Payment payment = new Payment(
                        resultSet.getInt("paymentId"),
                        resultSet.getInt("feeId"),
                        resultSet.getInt("apartmentId"),
                        resultSet.getDate("timeValidate"),
                        resultSet.getInt("payForMonth"),
                        resultSet.getInt("payForYear"),
                        resultSet.getBoolean("isLate")
                );
                payments.add(payment);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payments;
    }
}