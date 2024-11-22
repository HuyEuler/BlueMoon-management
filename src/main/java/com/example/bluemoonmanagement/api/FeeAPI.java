package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.Fee;
import com.example.bluemoonmanagement.models.FeeType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//addFee(Fee fee): Thêm một khoản phí mới vào cơ sở dữ liệu.
//getFeeById(int feeId): Lấy thông tin chi tiết của một khoản phí dựa trên feeId.
//updateFee(Fee fee): Cập nhật thông tin của một khoản phí.
//deleteFee(int feeId): Xóa một khoản phí khỏi cơ sở dữ liệu.
//getAllFees(): Lấy danh sách tất cả các khoản phí hiện có.

public class FeeAPI {
    // Phương thức thêm mới một khoản phí
    public static boolean addFee(Fee fee) {
        String query = "INSERT INTO Fee (name, ratePerSquareMeter, isMandatory, feeType) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, fee.getName());
            preparedStatement.setDouble(2, fee.getRatePerSquareMeter());
            preparedStatement.setBoolean(3, fee.isMandatory());
            preparedStatement.setString(4, fee.getFeeType().toString());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Phương thức lấy thông tin một khoản phí theo feeId
    public static Fee getFeeById(int feeId) {
        String query = "SELECT * FROM Fee WHERE feeId = ?";
        Fee fee = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, feeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                fee = new Fee();
                fee.setFeeId(resultSet.getInt("feeId"));
                fee.setName(resultSet.getString("name"));
                fee.setRatePerSquareMeter(resultSet.getDouble("ratePerSquareMeter"));
                fee.setMandatory(resultSet.getBoolean("isMandatory"));
                fee.setFeeType(FeeType.valueOf(resultSet.getString("feeType")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fee;
    }

    // Phương thức cập nhật thông tin một khoản phí
    public static boolean updateFee(Fee fee) {
        String query = "UPDATE Fee SET name = ?, ratePerSquareMeter = ?, isMandatory = ?, feeType = ? WHERE feeId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, fee.getName());
            preparedStatement.setDouble(2, fee.getRatePerSquareMeter());
            preparedStatement.setBoolean(3, fee.isMandatory());
            preparedStatement.setString(4, fee.getFeeType().toString());
            preparedStatement.setInt(5, fee.getFeeId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Phương thức xóa một khoản phí
    public static boolean deleteFee(int feeId) {
        String query = "DELETE FROM Fee WHERE feeId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, feeId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Phương thức lấy danh sách tất cả các khoản phí
    public static List<Fee> getAllFees() {
        String query = "SELECT * FROM Fee";
        List<Fee> fees = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Fee fee = new Fee();
                fee.setFeeId(resultSet.getInt("feeId"));
                fee.setName(resultSet.getString("name"));
                fee.setRatePerSquareMeter(resultSet.getDouble("ratePerSquareMeter"));
                fee.setMandatory(resultSet.getBoolean("isMandatory"));
                fee.setFeeType(FeeType.valueOf(resultSet.getString("feeType")));

                fees.add(fee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fees;
    }

    //Lay danh sách các khoản phí bắt buộc
    public static List<Fee> getMandatoryFees() {
        String query = "SELECT * FROM Fee WHERE isMandatory = true";
        List<Fee> fees = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Fee fee = new Fee();
                fee.setFeeId(resultSet.getInt("feeId"));
                fee.setName(resultSet.getString("name"));
                fee.setRatePerSquareMeter(resultSet.getDouble("ratePerSquareMeter"));
                fee.setMandatory(resultSet.getBoolean("isMandatory"));
                fee.setFeeType(FeeType.valueOf(resultSet.getString("feeType")));

                fees.add(fee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fees;
    }

    public static void main(String[] args) {
        List<Fee> fees_mandatory = getMandatoryFees();
        for (Fee fee : fees_mandatory) {
            System.out.println(fee);
        }
    }
}