package com.example.bluemoonmanagement.api;

import com.example.bluemoonmanagement.models.*;

import java.util.List;

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
//             Tạo 1 apartment mới
//        ApartmentAPI.addApartment(1, 20, 13, "1306");
        // Có thể khởi tạo 1 căn hộ với ownerId = null (chưa xác định)
//        ApartmentAPI.addApartment(null, 30, 6, "603");

//              Cập nhật ownerId cho apartment
//        ApartmentAPI.updateOwnerApartment(4, 6);
        // Xóa apartment theo Id
//        ApartmentAPI.deleteApartment(3);

//              Lấy 1 apartment theo Id
//        Apartment apartment = ApartmentAPI.getApartmentById(1);
//        System.out.println(apartment.getApartmentId() + " " + apartment.getOwnerId() + " " + apartment.getArea()
//            + " " + apartment.getFloor() + " " + apartment.getRoom());

//              Lấy apartmentId theo số phòng (room)
//        System.out.println(ApartmentAPI.getApartmentIdByRoom("601"));


//              Lấy danh sách tất cả apartment
//        List<Apartment> apartments = ApartmentAPI.getAllApartment();
//        for(Apartment apartment : apartments){
//            System.out.println(apartment.toString());
//        }

//              Lấy danh sách tất cả resident từ 1 apartment
//        List<Resident> residents = ApartmentAPI.getResidentsFromApartmentId(4);
//        for (Resident resident : residents){
//            System.out.println(resident.toString());
//        }


//        2. Resident

//              Tạo 1 resident mới
//        ResidentAPI.addResident(5, "Naruto", "2003-08-19", true,
//                "0964081173", "Japan",
//                "Con trai", false, 1, null);
//
//        ResidentAPI.addResident(5, "Luffy", "2003-09-19", true,
//                "0964081173", "Brazil",
//                "Con trai", true, 1, "Thuê trọ học đại học");

//

//              Trả về 1 resident theo Id
//        Resident resident = ResidentAPI.getResidentById(2);
//        assert resident != null;
//        System.out.println(resident.getResidentId() + " " + resident.getName() + " " + resident.getBirthday() + " " + resident.getGender() + " "
//            + resident.getPhoneNumber() + " " + resident.getNationality() + " " + resident.getRelationshipWithOwner()
//            + " " + resident.getIsOwner() + " " + resident.getStatus());

//              Update resident info
//        Resident resident = ResidentAPI.getResidentById(10);
//        assert resident != null;
//        resident.setStatus(2);
//        resident.setName("Luffy Mũ rơm");
//        resident.setIsOwner(true);
//        ResidentAPI.updateResidentById(resident, "Đi làm vua hải tặc ");

//                Xóa 1 resident
//        ResidentAPI.deleteResidentById(9, null);

//              Trả về tất cả resident
//        ResidentAPI.deleteResidentById(1, null);
//        List<Resident> residents = ResidentAPI.getAllResidents();
//        for (Resident resident : residents){
//            System.out.println(resident.toString());
//        }

//        3. Vehicle
//            Trả về Vehicle theo Id
//        Vehicle vehicle = VehicleAPI.getVehicleById(1);
//        System.out.println(vehicle.toString());

//              Thêm 1 vehicle mới
//        VehicleAPI.addVehicle(5, "Xe máy", "29H2-439.37");

//              Chỉnh sửa thông tin vehicle
//        VehicleAPI.editVehicle(3, 5, "Xe đạp", null);
//
//        //C2 :
//        Vehicle vehicle = VehicleAPI.getVehicleById(1);
//        vehicle.setType("Máy bay");
//        VehicleAPI.editVehicle(vehicle);

//              Xóa vehicle
//        VehicleAPI.deleteVehicle(1);

//        Trả về tất cả vehicle
//        List<Vehicle> vehicles = VehicleAPI.getAllVehicles();
//        for(Vehicle vehicle : vehicles){
//            System.out.println(vehicle.toString());
//        }

//        Trả về tất cả vehicle trong 1 apartment
//        List<Vehicle> vehicles = VehicleAPI.getAllVehiclesByApartmentId(4);
//        for(Vehicle vehicle : vehicles){
//            System.out.println(vehicle.toString());
//        }


        // ***********************************************************************

        // III. Test gọi các API cho chức năng quản lý phí thu


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
