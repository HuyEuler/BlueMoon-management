package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.Fee;
import com.example.bluemoonmanagement.models.User;

import java.util.List;

import static com.example.bluemoonmanagement.api.FeeAPI.*;
import static com.example.bluemoonmanagement.api.LoginAPI.*;
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
//        List<Fee> fees = getFeeList();
//        if (fees != null) {
//            System.out.println("getFeeList() executed successfully, list size: " + fees.size());
//            for (Fee fee : fees) {
//                System.out.println(fee);
//            }
//        } else {
//            System.out.println("getFeeList() returned null");
//        }

        // 5. getFeeList(mandatory)
        List<Fee> fees = getFeeList(true);
        if (fees != null) {
            System.out.println("getFeeList(mandatory) executed successfully, list size: " + fees.size());
            for (Fee fee : fees) {
                System.out.println(fee);
            }
        } else {
            System.out.println("getFeeList(mandatory) returned null");
        }




    }
}
