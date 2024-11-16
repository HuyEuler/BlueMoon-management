package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.*;

import java.util.Date;
import java.util.List;

import static com.example.bluemoonmanagement.api.FeeAPI.*;
import static com.example.bluemoonmanagement.api.LoginAPI.*;
import static com.example.bluemoonmanagement.api.UserAPI.*;

public class TestAPIMethod {
    public static void main(String[] args) {
        //*********************************************************************
//        // I. Test gọi các API cho phần đăng nhập của người quản lý
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
        // ***********************************************************************************
//        // II. Test gọi các API cho chức năng quản lý cư dân

        // 1. Apartment
        // Tạo 1 apartment mới
//        ApartmentAPI.addApartment(1, 20, 13, "1306");
        // Có thể khởi tạo 1 căn hộ với ownerId = null (chưa xác định)
//        ApartmentAPI.addApartment(null, 30, 14, "1401");

        // Cập nhật ownerId cho apartment
//        ApartmentAPI.updateOwnerApartment(4, 2);
        // Xóa apartment theo Id
//        ApartmentAPI.deleteApartment(3);

//        2. Resident
        // get Resident
//        Resident resident = ResidentAPI.getResidentById(2);
//        System.out.println(resident.getName() + " " + resident.getBirthday() + " " + resident.getGender() + " "
//            + resident.getPhoneNumber() + " " + resident.getNationality() + " " + resident.getRelationshipWithOwner()
//            + " " + resident.getIsOwner());

//        // Update resident info
//        ResidentAPI.updateResidentById(1, "Lý Tiểu Long", "1955-09-23", true,
//                "0964081173", "Tung của", "Thần tượng", true, 1);


//        // Get All resident
//        ResidentAPI.deleteResidentById(4);
//        List<Resident> residents = ResidentAPI.getAllResidents();
//        for (Resident resident : residents){
//            System.out.println(resident.getResidentId()+ " " + resident.getName() + " " + resident.getBirthday() + " " + resident.getGender() + " "
//            + resident.getPhoneNumber() + " " + resident.getNationality() + " " + resident.getRelationshipWithOwner()
//            + " " + resident.getIsOwner());
//        }


        // ***********************************************************************

        // III. Test gọi các API cho chức năng quản lý phí thu
        // FeeAPI
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
