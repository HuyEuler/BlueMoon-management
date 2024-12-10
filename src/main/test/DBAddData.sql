USE bluemoon_project;

-- Xóa dữ liệu từ các bảng theo thứ tự từ bảng phụ thuộc đến bảng chính
UPDATE Resident SET apartmentId = NULL WHERE 1 = 1;
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
VALUES ('BlueMoon Admin', '2000-02-20', '01234 567 789', NULL, 'Hanoi University of Science and Technology');

-- Chèn dữ liệu vào bảng Login
INSERT INTO `Login` (userId, username, password)
VALUES (1, 'admin', '123456');

-- Chèn dữ liệu vào bảng Resident
INSERT INTO Resident (apartmentId, name, birthday, gender, phoneNumber, nationality, relationshipWithOwner, isOwner, status) VALUES
  (NULL, 'Nguyễn Minh Tuấn', '1975-06-15', 1, '0123456789', 'Việt Nam', 'Chủ sở hữu', 1, 1),
  (NULL, 'Trần Quang Huy', '1972-09-10', 1, '0123456790', 'Việt Nam', 'Chủ sở hữu', 1, 1),
  (NULL, 'Lê Hoàng Nam', '1978-02-20', 1, '0123456701', 'Việt Nam', 'Chủ sở hữu', 1, 1),
  (NULL, 'Phan Thanh Tùng', '1973-12-05', 1, '0123456702', 'Việt Nam', 'Chủ sở hữu', 1, 1),
  (NULL, 'Ngô Đức Mạnh', '1977-04-18', 1, '0123456703', 'Việt Nam', 'Chủ sở hữu', 1, 1),
  (NULL, 'Vũ Quốc Hùng', '1974-11-25', 1, '0123456704', 'Việt Nam', 'Chủ sở hữu', 1, 1),
  (NULL, 'Phạm Đức Duy', '1976-03-10', 1, '0123456705', 'Việt Nam', 'Chủ sở hữu', 1, 1),
  (NULL, 'Trương Thanh Sơn', '1979-07-15', 1, '0123456706', 'Việt Nam', 'Chủ sở hữu', 1, 1),
  (NULL, 'Đoàn Minh Quân', '1973-06-03', 1, '0123456707', 'Việt Nam', 'Chủ sở hữu', 1, 1),
  (NULL, 'Nguyễn Đức Thắng', '1980-01-25', 1, '0123456708', 'Việt Nam', 'Chủ sở hữu', 1, 1),
  (NULL, 'Lý Ngọc Hải', '1975-08-09', 1, '0123456709', 'Việt Nam', 'Chủ sở hữu', 1, 1),
  (NULL, 'Hoàng Hải Đăng', '1976-09-12', 1, '0123456710', 'Việt Nam', 'Chủ sở hữu', 1, 1),
  (NULL, 'Bùi Minh Đức', '1974-10-22', 1, '0123456711', 'Việt Nam', 'Chủ sở hữu', 1, 1),
  (NULL, 'Nguyễn Ngọc Tân', '1977-05-18', 1, '0123456712', 'Việt Nam', 'Chủ sở hữu', 1, 1),

  (NULL, 'Phan Minh Tuyết', '1980-03-20', 0, '0123456713', 'Việt Nam', 'Vợ', 0, 1),
  (NULL, 'Trần Thị Lan', '1979-11-15', 0, '0123456714', 'Việt Nam', 'Vợ', 0, 1),
  (NULL, 'Lê Thanh Mai', '1978-05-30', 0, '0123456715', 'Việt Nam', 'Vợ', 0, 1),
  (NULL, 'Nguyễn Thị Thảo', '1977-02-10', 0, '0123456716', 'Việt Nam', 'Vợ', 0, 1),
  (NULL, 'Vũ Thanh Tâm', '1975-08-25', 0, '0123456717', 'Việt Nam', 'Vợ', 0, 1),
  (NULL, 'Phạm Kim Chi', '1976-12-12', 0, '0123456718', 'Việt Nam', 'Vợ', 0, 1),
  (NULL, 'Ngô Thị Thanh', '1974-10-15', 0, '0123456719', 'Việt Nam', 'Vợ', 0, 1),
  (NULL, 'Trương Minh Hoa', '1973-07-03', 0, '0123456720', 'Việt Nam', 'Vợ', 0, 1),
  (NULL, 'Đoàn Thị Thu', '1979-09-25', 0, '0123456721', 'Việt Nam', 'Vợ', 0, 1),
  (NULL, 'Lý Thị Thanh', '1978-06-18', 0, '0123456722', 'Việt Nam', 'Vợ', 0, 1),
  (NULL, 'Bùi Thị Lan', '1977-04-30', 0, '0123456723', 'Việt Nam', 'Vợ', 0, 1),
  (NULL, 'Hoàng Thị Hương', '1975-02-20', 0, '0123456724', 'Việt Nam', 'Vợ', 0, 1),
  (NULL, 'Nguyễn Kim Liên', '1979-01-18', 0, '0123456725', 'Việt Nam', 'Vợ', 0, 1),
  (NULL, 'Lý Thanh Tuyền', '1980-02-22', 0, '0123456726', 'Việt Nam', 'Vợ', 0, 1),

  (NULL, 'Nguyễn Minh Khoa', '2002-01-12', 1, '0123456727', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Trần Quang Hòa', '2003-07-25', 1, '0123456728', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Lê Hoàng Tuấn', '2004-08-05', 1, '0123456729', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Phan Thanh Hải', '2000-12-20', 1, '0123456730', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Ngô Đức Long', '2001-03-18', 1, '0123456731', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Vũ Quốc Minh', '2003-05-11', 1, '0123456732', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Phạm Đức Bảo', '2002-09-14', 1, '0123456733', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Trương Thanh Kỳ', '2005-04-10', 1, '0123456734', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Đoàn Minh Trí', '2000-11-07', 1, '0123456735', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Nguyễn Đức Tân', '2001-06-22', 1, '0123456736', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Lý Ngọc Hưng', '2003-12-04', 1, '0123456737', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Hoàng Hải Lâm', '2004-10-28', 1, '0123456738', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Bùi Minh Tuân', '2005-03-19', 1, '0123456739', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Nguyễn Ngọc Sơn', '2002-05-01', 1, '0123456740', 'Việt Nam', 'Con', 0, 1),

  (NULL, 'Nguyễn Minh Duy', '2007-06-15', 1, '0123456741', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Trần Quang Bình', '2009-04-18', 1, '0123456742', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Lê Hoàng Thế', '2006-11-29', 1, '0123456743', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Phan Thanh Nam', '2010-05-22', 1, '0123456744', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Ngô Đức Phúc', '2008-03-11', 1, '0123456745', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Vũ Quốc Vinh', '2011-08-10', 1, '0123456746', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Phạm Đức Trung', '2007-09-21', 1, '0123456747', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Trương Thanh Nam', '2012-02-28', 1, '0123456748', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Đoàn Minh Thái', '2009-06-13', 1, '0123456749', 'Việt Nam', 'Con', 0, 1),
  (NULL, 'Nguyễn Đức Khang', '2010-12-01', 1, '0123456750', 'Việt Nam', 'Con', 0, 1),

  (NULL, 'Lê Minh Quang', '1990-01-15', 1, '0123456751', 'Việt Nam', 'Người thuê', 0, 0),
  (NULL, 'Nguyễn Văn Linh', '1987-07-20', 1, '0123456752', 'Việt Nam', 'Người thuê', 0, 2),
  (NULL, 'Trần Hữu Nam', '1993-02-03', 1, '0123456753', 'Việt Nam', 'Người thuê', 0, 2),
  (NULL, 'Phan Đình Khoa', '1991-04-22', 1, '0123456754', 'Việt Nam', 'Người thuê', 0, 2),
  (NULL, 'Lý Minh Tấn', '1992-11-18', 1, '0123456755', 'Việt Nam', 'Người thuê', 0, 3);

-- Chèn dữ liệu vào bảng Apartment (sử dụng apartmentId đã được tự động tạo ra)
INSERT INTO Apartment (ownerId, area, floor, room) VALUES
  (1, 40, 5, 'BL-501'),
  (2, 40, 6, 'BL-601'),
  (3, 40, 7, 'BL-701'),
  (4, 40, 8, 'BL-801'),
  (5, 40, 9, 'BL-901'),
  (6, 40, 10, 'BL-1001'),
  (7, 40, 11, 'BL-1101'),
  (8, 40, 12, 'BL-1201'),
  (9, 45, 5, 'BL-502'),
  (10, 45, 6, 'BL-602'),
  (11, 45, 7, 'BL-702'),
  (12, 45, 8, 'BL-802'),
  (13, 45, 9, 'BL-902'),
  (14, 45, 10, 'BL-1002'),
  (15, 45, 11, 'BL-1102');

UPDATE Resident SET apartmentId = 1 WHERE residentId = 1;
UPDATE Resident SET apartmentId = 2 WHERE residentId = 2;
UPDATE Resident SET apartmentId = 3 WHERE residentId = 3;
UPDATE Resident SET apartmentId = 4 WHERE residentId = 4;
UPDATE Resident SET apartmentId = 5 WHERE residentId = 5;
UPDATE Resident SET apartmentId = 6 WHERE residentId = 6;
UPDATE Resident SET apartmentId = 7 WHERE residentId = 7;
UPDATE Resident SET apartmentId = 8 WHERE residentId = 8;
UPDATE Resident SET apartmentId = 9 WHERE residentId = 9;
UPDATE Resident SET apartmentId = 10 WHERE residentId = 10;
UPDATE Resident SET apartmentId = 11 WHERE residentId = 11;
UPDATE Resident SET apartmentId = 12 WHERE residentId = 12;
UPDATE Resident SET apartmentId = 13 WHERE residentId = 13;
UPDATE Resident SET apartmentId = 14 WHERE residentId = 14;
UPDATE Resident SET apartmentId = 15 WHERE residentId = 15;

UPDATE Resident SET apartmentId = 1 WHERE residentId = 16;
UPDATE Resident SET apartmentId = 2 WHERE residentId = 17;
UPDATE Resident SET apartmentId = 3 WHERE residentId = 18;
UPDATE Resident SET apartmentId = 4 WHERE residentId = 19;
UPDATE Resident SET apartmentId = 5 WHERE residentId = 20;
UPDATE Resident SET apartmentId = 6 WHERE residentId = 21;
UPDATE Resident SET apartmentId = 7 WHERE residentId = 22;
UPDATE Resident SET apartmentId = 8 WHERE residentId = 23;
UPDATE Resident SET apartmentId = 9 WHERE residentId = 24;
UPDATE Resident SET apartmentId = 10 WHERE residentId = 25;
UPDATE Resident SET apartmentId = 11 WHERE residentId = 26;
UPDATE Resident SET apartmentId = 12 WHERE residentId = 27;
UPDATE Resident SET apartmentId = 13 WHERE residentId = 28;
UPDATE Resident SET apartmentId = 14 WHERE residentId = 29;
UPDATE Resident SET apartmentId = 15 WHERE residentId = 30;

UPDATE Resident SET apartmentId = 1 WHERE residentId = 31;
UPDATE Resident SET apartmentId = 2 WHERE residentId = 32;
UPDATE Resident SET apartmentId = 3 WHERE residentId = 33;
UPDATE Resident SET apartmentId = 4 WHERE residentId = 34;
UPDATE Resident SET apartmentId = 5 WHERE residentId = 35;
UPDATE Resident SET apartmentId = 6 WHERE residentId = 36;
UPDATE Resident SET apartmentId = 7 WHERE residentId = 37;
UPDATE Resident SET apartmentId = 8 WHERE residentId = 38;
UPDATE Resident SET apartmentId = 9 WHERE residentId = 39;
UPDATE Resident SET apartmentId = 10 WHERE residentId = 40;
UPDATE Resident SET apartmentId = 11 WHERE residentId = 41;
UPDATE Resident SET apartmentId = 12 WHERE residentId = 42;
UPDATE Resident SET apartmentId = 13 WHERE residentId = 43;
UPDATE Resident SET apartmentId = 14 WHERE residentId = 44;
UPDATE Resident SET apartmentId = 15 WHERE residentId = 45;

UPDATE Resident SET apartmentId = 1 WHERE residentId = 46;
UPDATE Resident SET apartmentId = 2 WHERE residentId = 47;
UPDATE Resident SET apartmentId = 3 WHERE residentId = 48;
UPDATE Resident SET apartmentId = 4 WHERE residentId = 49;
UPDATE Resident SET apartmentId = 5 WHERE residentId = 50;
UPDATE Resident SET apartmentId = 6 WHERE residentId = 51;
UPDATE Resident SET apartmentId = 7 WHERE residentId = 52;
UPDATE Resident SET apartmentId = 8 WHERE residentId = 53;
UPDATE Resident SET apartmentId = 9 WHERE residentId = 54;
UPDATE Resident SET apartmentId = 10 WHERE residentId = 55;
UPDATE Resident SET apartmentId = 11 WHERE residentId = 56;
UPDATE Resident SET apartmentId = 12 WHERE residentId = 57;

-- Chèn dữ liệu vào bảng Activity
INSERT INTO Activity (residentId, status, timeIn, timeOut, note) VALUES
    (1, 0, '2020-01-01', '2020-01-10', 'Du lịch nước ngoài'),
    (1, 0, '2020-01-01', '2020-01-10', 'Du lịch nước ngoài'),
    (1, 0, '2020-01-15', '2020-05-25', 'Đi công tác'),
    (1, 0, '2020-05-26', '2024-01-20', 'Đi làm xa'),
    (1, 1, '2024-01-25', NULL, 'Cư trú tại căn hộ'),
    (2, 0, '2022-03-15', '2022-05-25', 'Cư trú tại căn hộ'),
    (2, 0, '2022-05-26', '2024-01-20', 'Đi làm xa'),
    (2, 1, '2024-01-25', NULL, 'Cư trú tại căn hộ'),
    (3, 0, '2021-06-10', '2021-07-20', 'Đi công tác'),
    (3, 0, '2021-08-01', '2022-01-10', 'Du lịch nước ngoài'),
    (3, 1, '2022-01-15', NULL, 'Cư trú tại căn hộ'),
    (4, 0, '2020-03-01', '2020-03-15', 'Đi du học'),
    (4, 1, '2020-03-20', NULL, 'Cư trú tại căn hộ'),
    (5, 0, '2022-07-01', '2022-07-15', 'Đi công tác'),
    (5, 1, '2022-07-20', NULL, 'Cư trú tại căn hộ'),
    (6, 0, '2023-04-10', '2023-04-20', 'Du lịch nước ngoài'),
    (6, 0, '2023-05-01', '2023-06-01', 'Đi công tác'),
    (6, 1, '2023-06-10', NULL, 'Cư trú tại căn hộ'),
    (7, 0, '2021-02-15', '2021-03-15', 'Đi làm xa'),
    (7, 1, '2021-03-20', NULL, 'Cư trú tại căn hộ'),
    (8, 0, '2022-08-15', '2022-09-15', 'Đi du học'),
    (8, 1, '2022-09-20', NULL, 'Cư trú tại căn hộ'),
    (9, 0, '2021-09-10', '2021-09-25', 'Đi công tác'),
    (9, 1, '2021-09-30', NULL, 'Cư trú tại căn hộ'),
    (10, 0, '2022-05-10', '2022-06-10', 'Đi du lịch'),
    (10, 1, '2022-06-15', NULL, 'Cư trú tại căn hộ'),
    (11, 0, '2021-10-20', '2021-11-10', 'Đi làm xa'),
    (11, 1, '2021-11-15', NULL, 'Cư trú tại căn hộ'),
    (12, 0, '2020-12-01', '2021-01-10', 'Đi công tác'),
    (12, 1, '2021-01-15', NULL, 'Cư trú tại căn hộ'),
    (13, 0, '2022-11-01', '2022-11-20', 'Đi công tác'),
    (13, 1, '2022-11-25', NULL, 'Cư trú tại căn hộ'),
    (14, 0, '2023-05-15', '2023-06-10', 'Du lịch nước ngoài'),
    (14, 1, '2023-06-15', NULL, 'Cư trú tại căn hộ'),
    (15, 0, '2023-03-01', '2023-04-01', 'Đi du học'),
    (15, 1, '2023-04-10', NULL, 'Cư trú tại căn hộ'),
    (16, 0, '2021-05-01', '2021-05-15', 'Đi du lịch'),
    (16, 1, '2021-05-20', NULL, 'Cư trú tại căn hộ'),
    (17, 0, '2022-06-01', '2022-06-15', 'Đi công tác'),
    (17, 1, '2022-06-20', NULL, 'Cư trú tại căn hộ'),
    (18, 0, '2020-10-01', '2020-10-10', 'Đi làm xa'),
    (18, 1, '2020-10-15', NULL, 'Cư trú tại căn hộ'),
    (19, 0, '2023-02-01', '2023-02-20', 'Đi công tác'),
    (19, 1, '2023-02-25', NULL, 'Cư trú tại căn hộ'),
    (20, 0, '2022-12-15', '2022-12-30', 'Du lịch nước ngoài'),
    (20, 1, '2023-01-05', NULL, 'Cư trú tại căn hộ'),
    (21, 0, '2021-09-10', '2021-09-25', 'Đi du lịch'),
    (21, 1, '2021-09-30', NULL, 'Cư trú tại căn hộ'),
    (22, 0, '2022-03-01', '2022-03-20', 'Đi du học'),
    (22, 1, '2022-03-25', NULL, 'Cư trú tại căn hộ'),
    (23, 0, '2023-06-01', '2023-06-10', 'Đi làm xa'),
    (23, 1, '2023-06-15', NULL, 'Cư trú tại căn hộ'),
    (24, 0, '2020-04-01', '2020-04-20', 'Du lịch nước ngoài'),
    (24, 1, '2020-04-25', NULL, 'Cư trú tại căn hộ'),
    (25, 0, '2021-07-01', '2021-07-20', 'Đi công tác'),
    (25, 1, '2021-07-25', NULL, 'Cư trú tại căn hộ'),
    (26, 0, '2023-01-15', '2023-01-30', 'Đi du lịch'),
    (26, 1, '2023-02-05', NULL, 'Cư trú tại căn hộ'),
    (27, 0, '2020-08-01', '2020-08-15', 'Đi làm xa'),
    (27, 1, '2020-08-20', NULL, 'Cư trú tại căn hộ'),
    (28, 0, '2022-04-10', '2022-04-25', 'Du lịch nước ngoài'),
    (28, 1, '2022-04-30', NULL, 'Cư trú tại căn hộ'),
    (29, 0, '2021-11-10', '2021-11-20', 'Đi công tác'),
    (29, 1, '2021-11-25', NULL, 'Cư trú tại căn hộ'),
    (30, 0, '2023-03-15', '2023-03-30', 'Đi làm xa'),
    (30, 1, '2023-04-05', NULL, 'Cư trú tại căn hộ'),
    (31, 0, '2021-12-01', '2021-12-15', 'Đi du lịch'),
    (31, 1, '2021-12-20', NULL, 'Cư trú tại căn hộ'),
    (32, 0, '2022-05-01', '2022-05-20', 'Đi du lịch'),
    (32, 1, '2022-05-25', NULL, 'Cư trú tại căn hộ'),
    (33, 0, '2020-01-01', '2020-01-10', 'Đi du lịch'),
    (33, 1, '2020-01-15', NULL, 'Cư trú tại căn hộ'),
    (34, 0, '2023-07-01', '2023-07-15', 'Du lịch nước ngoài'),
    (34, 1, '2023-07-20', NULL, 'Cư trú tại căn hộ'),
    (35, 0, '2021-06-01', '2021-06-10', 'Du lịch nước ngoài'),
    (35, 1, '2021-06-15', NULL, 'Cư trú tại căn hộ'),
    (36, 0, '2022-02-01', '2022-02-20', 'Đi du lịch'),
    (36, 1, '2022-02-25', NULL, 'Cư trú tại căn hộ'),
    (37, 0, '2023-03-01', '2023-03-15', 'Đi công tác'),
    (37, 1, '2023-03-20', NULL, 'Cư trú tại căn hộ'),
    (38, 0, '2022-09-01', '2022-09-10', 'Du lịch nước ngoài'),
    (38, 1, '2022-09-15', NULL, 'Cư trú tại căn hộ'),
    (39, 0, '2020-05-01', '2020-05-10', 'Đi công tác'),
    (39, 1, '2020-05-15', NULL, 'Cư trú tại căn hộ'),
    (40, 0, '2023-01-01', '2023-01-20', 'Đi du lịch'),
    (40, 1, '2023-01-25', NULL, 'Cư trú tại căn hộ'),
    (41, 0, '2021-08-01', '2021-08-20', 'Đi công tác'),
    (41, 1, '2021-08-25', NULL, 'Cư trú tại căn hộ'),
    (42, 0, '2020-09-01', '2020-09-20', 'Đi làm xa'),
    (42, 1, '2020-09-25', NULL, 'Cư trú tại căn hộ'),
    (43, 0, '2023-02-01', '2023-02-10', 'Đi du lịch'),
    (43, 1, '2023-02-15', NULL, 'Cư trú tại căn hộ'),
    (44, 0, '2021-10-01', '2021-10-15', 'Đi làm xa'),
    (44, 1, '2021-10-20', NULL, 'Cư trú tại căn hộ'),
    (45, 0, '2022-12-01', '2022-12-15', 'Du lịch nước ngoài'),
    (45, 1, '2022-12-20', NULL, 'Cư trú tại căn hộ'),
    (46, 0, '2020-11-01', '2020-11-15', 'Đi công tác'),
    (46, 1, '2020-11-20', NULL, 'Cư trú tại căn hộ'),
    (47, 0, '2023-04-01', '2023-04-10', 'Du lịch nước ngoài'),
    (47, 1, '2023-04-15', NULL, 'Cư trú tại căn hộ'),
    (48, 0, '2021-04-01', '2021-04-10', 'Đi du lịch'),
    (48, 1, '2021-04-15', NULL, 'Cư trú tại căn hộ'),
    (49, 0, '2022-08-01', '2022-08-20', 'Đi công tác'),
    (49, 1, '2022-08-25', NULL, 'Cư trú tại căn hộ'),
    (50, 0, '2023-06-01', '2023-06-15', 'Đi du lịch'),
    (50, 1, '2023-06-20', NULL, 'Cư trú tại căn hộ'),
    (51, 0, '2021-11-01', '2021-11-10', 'Đi làm xa'),
    (51, 1, '2021-11-15', NULL, 'Cư trú tại căn hộ'),
    (52, 0, '2022-10-01', '2022-10-20', 'Du lịch nước ngoài'),
    (52, 1, '2022-10-25', NULL, 'Cư trú tại căn hộ'),
    (53, 0, '2023-05-01', 'NULL', 'Đi công tác'),
    (54, 0, '2020-06-01', '2020-06-10', 'Du lịch nước ngoài'),
    (54, 1, '2020-06-15', NULL, 'Cư trú tại căn hộ'),
    (55, 0, '2023-08-01', '2023-08-10', 'Đi du lịch'),
    (55, 1, '2023-08-15', NULL, 'Cư trú tại căn hộ'),
    (56, 0, '2020-02-01', '2020-02-10', 'Du lịch nước ngoài'),
    (56, 0, '2020-03-01', '2020-03-15', 'Đi công tác'),
    (57, 0, '2021-07-01', '2021-07-15', 'Đi làm xa'),
    (57, 0, '2021-08-01', 'NULL', 'Du lịch nước ngoài');

-- Chèn dữ liệu vào bảng Fee
INSERT INTO Fee (name, ratePerSquareMeter, isMandatory, feeType) VALUES
  ('Phí quản lý chung cư', 10000, TRUE, 'MANAGEMENT_FEE'),
  ('Phí đất đai nhà ở', 5000, TRUE, 'MANAGEMENT_FEE'),
  ('Phí điện', 0, TRUE, 'SERVICE_FEE'),
  ('Phí môi trường', 1650, FALSE, 'SERVICE_FEE'),
  ('Phí gửi ô tô', 1200000, FALSE, 'VEHICLE_FEE'),
  ('Phí gửi xe máy', 70000, FALSE, 'VEHICLE_FEE'),
  ('Phí gửi xe đạp', 30000, FALSE, 'VEHICLE_FEE'),
  ('Phí bảo trì hệ thống', 15000, TRUE, 'MANAGEMENT_FEE'),
  ('Phí vệ sinh khu vực', 2000, TRUE, 'SERVICE_FEE'),
  ('Phí bảo vệ an ninh', 3000, TRUE, 'SERVICE_FEE'),
  ('Phí điện thoại', 5000, FALSE, 'SERVICE_FEE'),
  ('Phí internet', 100000, FALSE, 'SERVICE_FEE'),
  ('Phí bảo hiểm', 50000, TRUE, 'MANAGEMENT_FEE'),
  ('Phí gửi xe đạp điện', 20000, FALSE, 'VEHICLE_FEE'),
  ('Phí dịch vụ cho thú cưng', 50000, FALSE, 'SERVICE_FEE'),
  ('Phí an ninh', 8000, TRUE, 'SERVICE_FEE'),
  ('Phí bảo trì thang máy', 10000, TRUE, 'MANAGEMENT_FEE'),
  ('Phí dịch vụ rác thải', 1000, TRUE, 'SERVICE_FEE'),
  ('Phí điện cho khu vực công cộng', 2000, TRUE, 'SERVICE_FEE'),
  ('Phí vệ sinh sân vườn', 1000, TRUE, 'SERVICE_FEE'),
  ('Phí dọn dẹp căn hộ', 30000, FALSE, 'SERVICE_FEE'),
  ('Phí giặt đồ', 5000, FALSE, 'SERVICE_FEE'),
  ('Phí bảo trì thiết bị điện', 10000, FALSE, 'SERVICE_FEE'),
  ('Phí gửi xe đạp thể thao', 15000, FALSE, 'VEHICLE_FEE'),
  ('Phí bảo trì đường bộ', 10000, FALSE, 'SERVICE_FEE');

-- Chèn dữ liệu vào bảng Payment
INSERT INTO Payment (feeId, apartmentId, amountDue, amountPaid, paymentDate, payForMonth, payForYear, status) VALUES
  (1, 1, 50000, 25000, '2024-09-01', 9, 2024, 'PENDING'),
  (1, 2, 60000, 0, '2024-09-01', 9, 2024, 'PENDING'),
  (2, 1, 100000, 0, '2024-10-01', 10, 2024, 'PENDING'),
  (3, 3, 200000, 100000, '2024-11-01', 11, 2024, 'PAID'),
  (4, 1, 70000, 70000, '2024-12-01', 12, 2024, 'PAID'),
  (5, 5, 150000, 50000, '2024-09-10', 9, 2024, 'PENDING'),
  (6, 7, 120000, 120000, '2024-08-01', 8, 2024, 'PAID'),
  (7, 2, 10000, 10000, '2024-10-15', 10, 2024, 'PAID'),
  (8, 3, 50000, 0, '2024-11-10', 11, 2024, 'PENDING'),
  (9, 4, 90000, 0, '2024-10-20', 10, 2024, 'PENDING'),
  (10, 8, 30000, 10000, '2024-09-30', 9, 2024, 'PENDING'),
  (11, 9, 40000, 30000, '2024-12-01', 12, 2024, 'PAID'),
  (12, 10, 5000, 5000, '2024-08-05', 8, 2024, 'PAID'),
  (13, 11, 35000, 35000, '2024-09-01', 9, 2024, 'PAID'),
  (14, 6, 110000, 0, '2024-09-25', 9, 2024, 'PENDING'),
  (15, 15, 80000, 50000, '2024-10-10', 10, 2024, 'PENDING');


-- Chèn dữ liệu vào bảng Vehicle
INSERT INTO Vehicle (residentId, type, licensePlate)
VALUES
  (1, 'Xe máy', '59A1-12345'),
  (2, 'Ô tô', '51G-67890'),
  (3, 'Xe máy', '59C1-98765'),
  (4, 'Xe đạp', 'HN-11223'),
  (5, 'Xe máy', '59A1-54321'),
  (6, 'Ô tô', '51B-11223'),
  (7, 'Xe máy', '59E1-45678'),
  (8, 'Ô tô', '51C-67891'),
  (9, 'Xe máy', '59D1-87654'),
  (10, 'Xe đạp', 'HN-22334'),
  (11, 'Ô tô', '51G-66789'),
  (12, 'Xe máy', '59A1-99876'),
  (13, 'Xe đạp', 'HN-33445'),
  (14, 'Ô tô', '51F-55667'),
  (15, 'Xe máy', '59A1-76543');

