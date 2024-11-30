package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.Apartment;
import com.example.bluemoonmanagement.models.Fee;
import com.example.bluemoonmanagement.models.Payment;
import com.example.bluemoonmanagement.models.PaymentStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//addPayment(Payment payment): Thêm một giao dịch thanh toán mới vào cơ sở dữ liệu.
//getPaymentById(int paymentId): Lấy thông tin chi tiết của một thanh toán dựa trên paymentId.
//updatePayment(Payment payment): Cập nhật thông tin của một thanh toán.
//deletePayment(int paymentId): Xóa một thanh toán khỏi cơ sở dữ liệu.
//getAllPayments(): Lấy danh sách tất cả các thanh toán hiện có.
//getPaymentsByApartmentId(int apartmentId): Lấy danh sách các thanh toán của một căn hộ cụ thể.
//getPaymentsByFeeId(int feeId): Lấy danh sách các thanh toán liên quan đến một loại phí cụ thể.

public class PaymentAPI {

    // Phương thức thêm mới một thanh toán
    public static boolean addPayment(Payment payment) {
        String query = "INSERT INTO Payment (feeId, apartmentId, amountDue, amountPaid, paymentDate, payForMonth, payForYear, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, payment.getFeeId());
            preparedStatement.setInt(2, payment.getApartmentId());
            preparedStatement.setDouble(3, payment.getAmountDue());
            preparedStatement.setDouble(4, payment.getAmountPaid());
            preparedStatement.setDate(5, new java.sql.Date(payment.getPaymentDate().getTime()));
            preparedStatement.setInt(6, payment.getPayForMonth());
            preparedStatement.setInt(7, payment.getPayForYear());
            preparedStatement.setString(8, payment.getStatus().toString());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Phương thức lấy thông tin một thanh toán theo paymentId
    public static Payment getPaymentById(int paymentId) {
        String query = "SELECT * FROM Payment WHERE paymentId = ?";
        Payment payment = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, paymentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                payment = new Payment();
                payment.setPaymentId(resultSet.getInt("paymentId"));
                payment.setFeeId(resultSet.getInt("feeId"));
                payment.setApartmentId(resultSet.getInt("apartmentId"));
                payment.setAmountDue(resultSet.getDouble("amountDue"));
                payment.setAmountPaid(resultSet.getDouble("amountPaid"));
                payment.setPaymentDate(resultSet.getDate("paymentDate"));
                payment.setPayForMonth(resultSet.getInt("payForMonth"));
                payment.setPayForYear(resultSet.getInt("payForYear"));
                payment.setStatus(PaymentStatus.valueOf(resultSet.getString("status")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payment;
    }

    // Phương thức cập nhật thông tin một thanh toán
    public static boolean updatePayment(Payment payment) {
        String query = "UPDATE Payment SET feeId = ?, apartmentId = ?, amountDue = ?, amountPaid = ?, paymentDate = ?, " +
                "payForMonth = ?, payForYear = ?, status = ? WHERE paymentId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, payment.getFeeId());
            preparedStatement.setInt(2, payment.getApartmentId());
            preparedStatement.setDouble(3, payment.getAmountDue());
            preparedStatement.setDouble(4, payment.getAmountPaid());
            preparedStatement.setDate(5, new java.sql.Date(payment.getPaymentDate().getTime()));
            preparedStatement.setInt(6, payment.getPayForMonth());
            preparedStatement.setInt(7, payment.getPayForYear());
            preparedStatement.setString(8, payment.getStatus().toString());
            preparedStatement.setInt(9, payment.getPaymentId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Phương thức xóa một thanh toán
    public static boolean deletePayment(int paymentId) {
        String query = "DELETE FROM Payment WHERE paymentId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, paymentId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Phương thức lấy danh sách tất cả các thanh toán
    public static List<Payment> getAllPayments() {
        String query = "SELECT * FROM Payment";
        List<Payment> payments = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(resultSet.getInt("paymentId"));
                payment.setFeeId(resultSet.getInt("feeId"));
                payment.setApartmentId(resultSet.getInt("apartmentId"));
                payment.setAmountDue(resultSet.getDouble("amountDue"));
                payment.setAmountPaid(resultSet.getDouble("amountPaid"));
                payment.setPaymentDate(resultSet.getDate("paymentDate"));
                payment.setPayForMonth(resultSet.getInt("payForMonth"));
                payment.setPayForYear(resultSet.getInt("payForYear"));
                payment.setStatus(PaymentStatus.valueOf(resultSet.getString("status")));

                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    // Phương thức lấy danh sách thanh toán theo apartmentId
    public static List<Payment> getPaymentsByApartmentId(int apartmentId) {
        String query = "SELECT * FROM Payment WHERE apartmentId = ?";
        List<Payment> payments = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, apartmentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(resultSet.getInt("paymentId"));
                payment.setFeeId(resultSet.getInt("feeId"));
                payment.setApartmentId(resultSet.getInt("apartmentId"));
                payment.setAmountDue(resultSet.getDouble("amountDue"));
                payment.setAmountPaid(resultSet.getDouble("amountPaid"));
                payment.setPaymentDate(resultSet.getDate("paymentDate"));
                payment.setPayForMonth(resultSet.getInt("payForMonth"));
                payment.setPayForYear(resultSet.getInt("payForYear"));
                payment.setStatus(PaymentStatus.valueOf(resultSet.getString("status")));

                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    // Phương thức lấy danh sách thanh toán theo feeId
    public static List<Payment> getPaymentsByFeeId(int feeId) {
        String query = "SELECT * FROM Payment WHERE feeId = ?";
        List<Payment> payments = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, feeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(resultSet.getInt("paymentId"));
                payment.setFeeId(resultSet.getInt("feeId"));
                payment.setApartmentId(resultSet.getInt("apartmentId"));
                payment.setAmountDue(resultSet.getDouble("amountDue"));
                payment.setAmountPaid(resultSet.getDouble("amountPaid"));
                payment.setPaymentDate(resultSet.getDate("paymentDate"));
                payment.setPayForMonth(resultSet.getInt("payForMonth"));
                payment.setPayForYear(resultSet.getInt("payForYear"));
                payment.setStatus(PaymentStatus.valueOf(resultSet.getString("status")));

                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    // Tạo payment cho các chi phí bắt buộc hàng tháng cho tất cả apartment
    public static boolean createMonthlyPayments() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);

        try {
            List<Apartment> apartments = ApartmentAPI.getAllApartment();

            List<Fee> mandatoryFees = FeeAPI.getMandatoryFees();

            for (Apartment apartment : apartments) {
                for (Fee fee : mandatoryFees) {
                    // Tính toán số tiền phải trả (amountDue)
                    double amountDue = apartment.getArea() * fee.getRatePerSquareMeter();

                    // Tạo đối tượng Payment
                    Payment payment = new Payment();
                    payment.setFeeId(fee.getFeeId());
                    payment.setApartmentId(apartment.getApartmentId());
                    payment.setAmountDue(amountDue);
                    payment.setAmountPaid(0);
                    payment.setPaymentDate(currentDate);
                    payment.setPayForMonth(currentMonth);
                    payment.setPayForYear(currentYear);
                    payment.setStatus(PaymentStatus.PENDING);

                    // Thêm thanh toán vào cơ sở dữ liệu
                    boolean check = addPayment(payment);
                }
            }
            System.out.println("Đã tạo thanh toán cho tháng " + currentMonth + "/" + currentYear);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Payment> getPaidPayments() {
        String query = "SELECT * FROM Payment WHERE status = ?";
        List<Payment> payments = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, PaymentStatus.PAID.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(resultSet.getInt("paymentId"));
                payment.setFeeId(resultSet.getInt("feeId"));
                payment.setApartmentId(resultSet.getInt("apartmentId"));
                payment.setAmountDue(resultSet.getDouble("amountDue"));
                payment.setAmountPaid(resultSet.getDouble("amountPaid"));
                payment.setPaymentDate(resultSet.getDate("paymentDate"));
                payment.setPayForMonth(resultSet.getInt("payForMonth"));
                payment.setPayForYear(resultSet.getInt("payForYear"));
                payment.setStatus(PaymentStatus.valueOf(resultSet.getString("status")));

                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    public static List<Payment> getPendingPayments() {
        String query = "SELECT * FROM Payment WHERE status = ?";
        List<Payment> payments = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, PaymentStatus.PENDING.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(resultSet.getInt("paymentId"));
                payment.setFeeId(resultSet.getInt("feeId"));
                payment.setApartmentId(resultSet.getInt("apartmentId"));
                payment.setAmountDue(resultSet.getDouble("amountDue"));
                payment.setAmountPaid(resultSet.getDouble("amountPaid"));
                payment.setPaymentDate(resultSet.getDate("paymentDate"));
                payment.setPayForMonth(resultSet.getInt("payForMonth"));
                payment.setPayForYear(resultSet.getInt("payForYear"));
                payment.setStatus(PaymentStatus.valueOf(resultSet.getString("status")));

                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    public static List<Payment> getOverduePayments() {
        String query = "SELECT * FROM Payment WHERE status = ?";
        List<Payment> payments = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, PaymentStatus.OVERDUE.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(resultSet.getInt("paymentId"));
                payment.setFeeId(resultSet.getInt("feeId"));
                payment.setApartmentId(resultSet.getInt("apartmentId"));
                payment.setAmountDue(resultSet.getDouble("amountDue"));
                payment.setAmountPaid(resultSet.getDouble("amountPaid"));
                payment.setPaymentDate(resultSet.getDate("paymentDate"));
                payment.setPayForMonth(resultSet.getInt("payForMonth"));
                payment.setPayForYear(resultSet.getInt("payForYear"));
                payment.setStatus(PaymentStatus.valueOf(resultSet.getString("status")));

                payments.add(payment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }


    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//        int currentMonth = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
//        int currentYear = calendar.get(Calendar.YEAR);
//
//        boolean ud1 = updatePayment(new Payment(5, 1, 1, 188750, 188750, new Date(), currentMonth, currentYear, PaymentStatus.PAID));
//        boolean ud2 = updatePayment(new Payment(6, 2, 1, 1245750, 1245750, new Date(), currentMonth, currentYear, PaymentStatus.PAID));
//
//        System.out.println(ud1);
//        System.out.println(ud2);

        List<Payment> payments = getPaidPayments();
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

}


































//
//import com.example.bluemoonmanagement.models.Payment;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
////public class PaymentAPI {
////
////    public static boolean addPayment(int feeId, int apartmentId, Date timeValidate, int payForMonth, int payForYear, boolean isLate) {
////        String query = "INSERT INTO payment (feeId, apartmentId, timeValidate, payForMonth, payForYear, isLate) VALUES (?, ?, ?, ?, ?, ?)";
////        try (Connection connection = DBConnection.getConnection();
////             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
////
////            preparedStatement.setInt(1, feeId);
////            preparedStatement.setInt(2, apartmentId);
////            preparedStatement.setDate(3, new java.sql.Date(timeValidate.getTime()));
////            preparedStatement.setInt(4, payForMonth);
////            preparedStatement.setInt(5, payForYear);
////            preparedStatement.setBoolean(6, isLate);
////
////            int affectedRows = preparedStatement.executeUpdate();
////
////            return affectedRows > 0;
////
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
////    }
////
////    public static List<Payment> getListPayment() {
////        String query = "SELECT * FROM payment";
////        List<Payment> payments = new ArrayList<>();
////        try (Connection connection = DBConnection.getConnection();
////             Statement statement = connection.createStatement();
////             ResultSet resultSet = statement.executeQuery(query)) {
////
////            while (resultSet.next()) {
////                Payment payment = new Payment(
////                        resultSet.getInt("paymentId"),
////                        resultSet.getInt("feeId"),
////                        resultSet.getInt("apartmentId"),
////                        resultSet.getDate("timeValidate"),
////                        resultSet.getInt("payForMonth"),
////                        resultSet.getInt("payForYear"),
////                        resultSet.getBoolean("isLate")
////                );
////                payments.add(payment);
////            }
////
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
////        return payments;
////    }
////}