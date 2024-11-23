USE bluemoon_project;

-- Xóa dữ liệu từ các bảng theo thứ tự từ bảng phụ thuộc đến bảng chính
DELETE FROM `Vehicle`;
DELETE FROM `Activity`;
DELETE FROM `Payment`;
DELETE FROM `Apartment`;
DELETE FROM `Resident`;
DELETE FROM `Login`;
DELETE FROM `Fee`;
DELETE FROM `User`;

-- Đặt lại AUTO_INCREMENT cho các bảng
ALTER TABLE `User` AUTO_INCREMENT = 1;
ALTER TABLE `Login` AUTO_INCREMENT = 1;
ALTER TABLE `Apartment` AUTO_INCREMENT = 1;
ALTER TABLE `Resident` AUTO_INCREMENT = 1;
ALTER TABLE `Activity` AUTO_INCREMENT = 1;
ALTER TABLE `Fee` AUTO_INCREMENT = 1;
ALTER TABLE `Payment` AUTO_INCREMENT = 1;
ALTER TABLE `Vehicle` AUTO_INCREMENT = 1;

-- Chèn dữ liệu vào bảng User
INSERT INTO `User` (name, birthday, phoneNumber, image, address)
VALUES
  ('Lê Admin', '1990-05-15', '0123456789', NULL, '123 Đường Lê Lợi, Quận 1, TP.HCM'),
  ('Nguyễn Admin', '1999-10-15', '0345436223', NULL, '123 Đường Tân Mai, Quận 3, TP.HCM');

-- Chèn dữ liệu vào bảng Login
INSERT INTO `Login` (userId, username, password)
VALUES
  (1, 'admin', 'password123'),
  (2, '', '');

-- Chèn dữ liệu vào bảng Resident trước
INSERT INTO `Resident` (apartmentId, name, birthday, gender, phoneNumber, nationality, relationshipWithOwner, status)
VALUES
  (NULL, 'Nguyễn Văn A', '1990-05-15', 1, '0123456789', 'Việt Nam', 'Chủ sở hữu', 1),
  (NULL, 'Nguyễn Văn C', '2015-09-23', 1, '0123456710', 'Việt Nam', 'Con', 1),
  (NULL, 'Trần Thị B', '1985-08-10', 0, '0987654321', 'Việt Nam', 'Chủ sở hữu', 1);

-- Chèn dữ liệu vào bảng Apartment (sử dụng residentId đã được tự động tạo ra)
INSERT INTO `Apartment` (ownerId, area, floor, room) VALUES
  (1, 75.5, 2, 'A-201'),  -- ownerId là residentId của Nguyễn Văn A
  (3, 65.0, 3, 'B-301');  -- ownerId là residentId của Trần Thị B

-- Chèn dữ liệu vào bảng Activity
INSERT INTO `Activity` (residentId, status, timeIn, timeOut, note)
VALUES
  (1, 1, '2024-01-01', '2024-01-10', 'Du lịch nước ngoài'),
  (2, 1, '2024-02-05', NULL, 'Đang cư trú tại căn hộ');

-- Chèn dữ liệu vào bảng Fee
INSERT INTO Fee (name, ratePerSquareMeter, isMandatory, feeType) VALUES
    ('Phí quản lý chung cư', 10000, TRUE, 'MANAGEMENT_FEE'),
    ('Phí đất đai nhà ở', 5000, TRUE, 'MANAGEMENT_FEE'),
    ('Phí an ninh', 3000, TRUE, 'MANAGEMENT_FEE'),
    ('Phí điện', 9000, TRUE, 'SERVICE_FEE'),
    ('Phí nước', 6050, TRUE, 'SERVICE_FEE'),
    ('Phí môi trường', 1650, FALSE, 'SERVICE_FEE'),
    ('Phí người nghèo', 0, FALSE, 'CONTRIBUTION_FEE'),
    ('Phí ủng hộ lũ lụt', 0, FALSE, 'CONTRIBUTION_FEE' );


-- Chèn dữ liệu vào bảng Payment
INSERT INTO Payment (feeId, apartmentId, amountDue, amountPaid, paymentDate, payForMonth, payForYear, status) VALUES
  (1, 1, 50000, 25000, '2024-9-01', 9, 2024, 'PENDING'),
  (1, 2, 60000, 0, '2024-9-01', 9, 2024, 'PENDING'),
  (2, 1, 100000, 0, '2024-10-01', 10, 2024, 'PENDING');

-- Chèn dữ liệu vào bảng Vehicle
INSERT INTO `Vehicle` (residentId, type, licensePlate)
VALUES
  (1, 'Xe máy', '59A1-12345'),
  (2, 'Ô tô', '51G-67890');
