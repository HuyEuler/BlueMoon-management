package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.MainApplication;
import com.example.bluemoonmanagement.models.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.example.bluemoonmanagement.api.ActivityAPI.getAllActivityOfResident;
import static com.example.bluemoonmanagement.api.ApartmentAPI.getResidentsFromApartmentId;
import static com.example.bluemoonmanagement.api.FeeAPI.*;
import static com.example.bluemoonmanagement.api.LoginAPI.*;
import static com.example.bluemoonmanagement.api.UserAPI.*;
import static com.example.bluemoonmanagement.common.GlobalVariable.screenHeight;
import static com.example.bluemoonmanagement.common.GlobalVariable.screenWidth;

public class TestAPIMethod {
    public static void main(String[] args) throws IOException {
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

//
//          List<Activity> activityList = getAllActivityOfResident(1);
//          System.out.printf("getResidentsFromApartmentId(1) executed successfully, list size: %d\n", activityList.size());
//          for (Activity activity : activityList) {
//              System.out.println(activity);
//          }
        // Test FeeAPI
        // 1. addFee
//        if(addFee("Phí cố định", 1000, true, 1, new java.sql.Date(System.currentTimeMillis()), 1)) {
//            System.out.println("Add fee successfully");
//        }
//        // 2. editFee
//        if(editFee(3, "Updated Fee", 200, false, 6, new java.sql.Date(System.currentTimeMillis()), 0)) {
//            System.out.println("Edit fee successfully");
//        }
        // 3. deleteFee
//        if(deleteFee(3)) {
//            System.out.println("Delete fee successfully");
//        }
        // 4. getFeeList()
//        List<Fee> fees = getAllFees();
//        if (fees != null) {
//            System.out.println("getFeeList() executed successfully, list size: " + fees.size());
//            for (Fee fee : fees) {
//                System.out.println(fee);
//            }
//        } else {
//            System.out.println("getFeeList() returned null");
//        }

        // 5. getFeeList(mandatory)
//        List<Fee> fees = getFeeList(true);
//        if (fees != null) {
//            System.out.println("getFeeList(mandatory) executed successfully, list size: " + fees.size());
//            for (Fee fee : fees) {
//                System.out.println(fee);
//            }
//        } else {
//            System.out.println("getFeeList(mandatory) returned null");
//        }


        //Test PaymentAPI
        // Test addPayment
//        boolean addResult = PaymentAPI.addPayment(1, 2, new java.sql.Date(new Date().getTime()), 10, 2023, false);
//        if (addResult) {
//            System.out.println("Add payment successfully");
//        } else {
//            System.out.println("Failed to add payment");
//        }

        // Test getListPayment
//        var payments = PaymentAPI.getListPayment();
//        if (payments != null) {
//            System.out.println("getListPayment() executed successfully, list size: " + payments.size());
//            for (Payment payment : payments) {
//                System.out.println(payment);
//            }
//        } else {
//            System.out.println("getListPayment() returned null");
//        }











    }
}
