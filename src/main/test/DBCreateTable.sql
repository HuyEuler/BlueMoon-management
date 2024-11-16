-- Xóa database nếu đã tồn tại
DROP DATABASE IF EXISTS bluemoon_project;

-- Tạo database mới
CREATE DATABASE bluemoon_project CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE bluemoon_project;

-- Tạo bảng `User`
CREATE TABLE `User` (
  userId INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  birthday DATE,
  phoneNumber VARCHAR(12),
  image LONGBLOB,
  address VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
);

-- Tạo bảng `Login` với ràng buộc khóa chính và duy nhất
CREATE TABLE `Login` (
  userId INT PRIMARY KEY,
  username VARCHAR(50),
  password VARCHAR(50),
  FOREIGN KEY (userId) REFERENCES `User`(userId)
);

-- Tạo bảng `Apartment` trước để tham chiếu trong bảng `Resident`
CREATE TABLE `Apartment` (
  apartmentId INT PRIMARY KEY AUTO_INCREMENT,
  ownerId INT,
  area FLOAT,
  floor INT,
  room VARCHAR(20)
);

-- Tạo bảng `Resident`
CREATE TABLE `Resident` (
  residentId INT PRIMARY KEY AUTO_INCREMENT,
  apartmentId INT,
  name VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  birthday DATE,
  gender BIT,
  phoneNumber VARCHAR(12),
  nationality VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  relationshipWithOwner VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  isOwner BIT,
  status INT,
  FOREIGN KEY (apartmentId) REFERENCES `Apartment`(apartmentId)
);

-- Tạo bảng `Activity`
CREATE TABLE `Activity` (
  activityId INT PRIMARY KEY AUTO_INCREMENT,
  residentId INT,
  status INT,
  timeIn DATE,
  timeOut DATE,
  note VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  FOREIGN KEY (residentId) REFERENCES `Resident`(residentId)
);

-- Tạo bảng `Fee`
CREATE TABLE `Fee` (
  feeId INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  cost INT,
  mandatory BIT,
  cycle INT,
  expiration DATE,
  status INT
);

-- Tạo bảng `Payment`
CREATE TABLE `Payment` (
  paymentId INT PRIMARY KEY AUTO_INCREMENT,
  feeId INT,
  apartmentId INT,
  timeValidate DATE,
  payForMonth INT,
  payForYear INT,
  isLate BIT,
  FOREIGN KEY (apartmentId) REFERENCES `Apartment`(apartmentId),
  FOREIGN KEY (feeId) REFERENCES `Fee`(feeId)
);

-- Tạo bảng `Vehicle`
CREATE TABLE `Vehicle` (
  vehicleId INT PRIMARY KEY AUTO_INCREMENT,
  residentId INT,
  type VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  licensePlate VARCHAR(30),
  FOREIGN KEY (residentId) REFERENCES `Resident`(residentId)
);

-- Thêm khóa ngoại ownerId trong `Apartment` sau khi tạo `Resident`
ALTER TABLE `Apartment`
ADD CONSTRAINT fk_ownerId FOREIGN KEY (ownerId) REFERENCES `Resident`(residentId);

-- Tạo ràng buộc UNIQUE cho trường username
ALTER TABLE `Login`
ADD CONSTRAINT `unique_username` UNIQUE (`username`);

-- Tạo ràng buộc UNIQUE cho trường room
ALTER TABLE `Apartment`
ADD CONSTRAINT `unique_room` UNIQUE (`room`);



