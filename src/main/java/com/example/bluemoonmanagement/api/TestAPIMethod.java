package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.Fee;
import com.example.bluemoonmanagement.models.FeeType;
import com.example.bluemoonmanagement.models.Payment;
import com.example.bluemoonmanagement.models.User;
import com.example.bluemoonmanagement.models.PaymentStatus;
import java.util.Date;
import java.util.List;

import static com.example.bluemoonmanagement.api.FeeAPI.*;
import static com.example.bluemoonmanagement.api.LoginAPI.*;
import static com.example.bluemoonmanagement.api.PaymentAPI.*;
import static com.example.bluemoonmanagement.api.UserAPI.*;

public class TestAPIMethod {
    public static void main(String[] args) {
//        // 1. Test gọi các API cho phần đăng nhập của người quản lý
//
//        // Test chức năng xác thực username password
//        System.out.println(authenticate("admim", "password123"));
//
//        // Test chức năng cập nhật thông tin User
//        if(updateUser(1, "Lê Đức Huy", "2003-08-19",
//                "0964081173", "Hoàng Mai Hà Nội")){
//            System.out.println("Update information successfully");
//        }
//
//        User user = getUser(1);
//        System.out.println(user.getName() + user.getPhoneNumber() + user.getAddress() + user.getBirthday());
//
//        // Test chức năng câp nhật thông tin đăng nhập User
//        if(updateLogin(1, "newUsn", "123")){
//            System.out.println("Update login successfully");
//        }
//
//        // 2. Test gọi các API cho chức năng quản lý cư dân


        // Test FeeAPI
        //1. boolean addFee(Fee fee)
//        if(addFee(new Fee(7, "Phi test", 1000, true, FeeType.MANAGEMENT_FEE)))
//        {
//            System.out.println("Add fee successfully");
//        } else {
//            System.out.println("Failed to add fee");
//        }
        //2. Fee getFeeById(int feeId)
//        Fee fee = getFeeById(7);
//        if(fee != null)
//        {
//            System.out.println("Get fee by id successfully");
//            System.out.println(fee);
//        } else {
//            System.out.println("Failed to get fee by id");
//        }
        //3. boolean updateFee(Fee fee)
//        if (updateFee(new Fee(7, "Phi test updated", 1001, true, FeeType.SERVICE_FEE))) {
//            System.out.println("Update fee successfully");
//        } else {
//            System.out.println("Failed to update fee");
//        }
        //4. boolean deleteFee(int feeId)
//        if(deleteFee(7)) {
//            System.out.println("Delete fee successfully");
//        } else {
//            System.out.println("Failed to delete fee");
//        }

        //5. List<Fee> getAllFees()
//        List<Fee> fees = getAllFees();
//        if(fees != null)
//        {
//            System.out.println("Get all fees successfully");
//            for (Fee fee : fees) {
//                System.out.println(fee);
//            }
//        } else {
//            System.out.println("Failed to get all fees");
//        }
        //Test PaymentAPI
        //1.  addPayment(Payment payment)
//        Payment(int paymentId, int feeId, int apartmentId, double amountDue, double amountPaid, Date paymentDate, int payForMonth, int payForYear, PaymentStatus status)
        // Ở đây em cho paymentId bằng bao nhiêu cũng được vì hàm addPayment sau đó không dùng paymentId, chỉ dùng các trường còn lại
        // Khi thêm vào CSDL sẽ tự sinh id cho payment
//        if(addPayment(new Payment(-1, 1, 1, 1000, 0, new Date(), 1, 2021, PaymentStatus.PENDING)))
//        {
//            System.out.println("Add payment successfully");
//        } else {
//            System.out.println("Failed to add payment");
//        }
        //2. Payment getPaymentById(int paymentId)
//        Payment payment = getPaymentById(1);
//        if(payment != null)
//        {
//            System.out.println("Get payment by id successfully");
//            System.out.println(payment);
//        } else {
//            System.out.println("Failed to get payment by id");
//        }
        //3. boolean updatePayment(Payment payment)
//        if(updatePayment(new Payment(1, 1, 1, 1000, 1000, new Date(), 1, 2021, PaymentStatus.PAID)))
//        {
//            System.out.println("Update payment successfully");
//        } else {
//            System.out.println("Failed to update payment");
//        }
        //4. boolean deletePayment(int paymentId)
//        if(deletePayment(1))
//        {
//            System.out.println("Delete payment successfully");
//        } else {
//            System.out.println("Failed to delete payment");
//        }
        //5. List<Payment> getAllPayments()
//        List<Payment> payments = getAllPayments();
//        if(payments != null)
//        {
//            System.out.println("Get all payments successfully");
//            for (Payment payment : payments) {
//                System.out.println(payment);
//            }
//        } else {
//            System.out.println("Failed to get all payments");
//        }
        //6. List<Payment> getPaymentsByApartmentId(int apartmentId)
//        List<Payment> payments = getPaymentsByApartmentId(1);
//        if(payments != null)
//        {
//            System.out.println("Get payments by apartment id successfully");
//            for (Payment payment : payments) {
//                System.out.println(payment);
//            }
//        } else {
//            System.out.println("Failed to get payments by apartment id");
//        }
        //7. List<Payment> getPaymentsByFeeId(int feeId)
        List<Payment> payments = getPaymentsByFeeId(1);
        if(payments != null)
        {
            System.out.println("Get payments by fee id successfully");
            for (Payment payment : payments) {
                System.out.println(payment);
            }
        } else {
            System.out.println("Failed to get payments by fee id");
        }




    }
}
