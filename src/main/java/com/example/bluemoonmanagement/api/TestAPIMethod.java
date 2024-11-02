package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.User;

import static com.example.bluemoonmanagement.api.LoginAPI.*;
import static com.example.bluemoonmanagement.api.UserAPI.*;

public class TestAPIMethod {
    public static void main(String[] args) {
        // 1. Test gọi các API cho phần đăng nhập của người quản lý

        // Test chức năng xác thực username password
        System.out.println(authenticate("admim", "password123"));

        // Test chức năng cập nhật thông tin User
        if(updateUser(1, "Lê Đức Huy", "2003-08-19",
                "0964081173", "Hoàng Mai Hà Nội")){
            System.out.println("Update information successfully");
        }

        User user = getUser(1);
        System.out.println(user.getName() + user.getPhoneNumber() + user.getAddress() + user.getBirthday());

        // Test chức năng câp nhật thông tin đăng nhập User
        if(updateLogin(1, "newUsn", "123")){
            System.out.println("Update login successfully");
        }

        // 2. Test gọi các API cho chức năng quản lý cư dân
    }
}