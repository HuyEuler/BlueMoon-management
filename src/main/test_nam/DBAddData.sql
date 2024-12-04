USE bluemoon2;

-- Chèn dữ liệu vào bảng Fee
-- Phí dịch vụ chung cư: Thu hàng tháng dựa trên diện tích căn hộ, dao động từ 2.500 đến 16.500 đồng/m²/tháng
INSERT INTO Fee (name, ratePerSquareMeter, isMandatory, feeType) VALUES
('Phí dịch vụ chung cư (thấp)', 2500, TRUE, 'SERVICE_FEE'),
('Phí dịch vụ chung cư (cao)', 16500, TRUE, 'SERVICE_FEE');

-- Phí quản lý chung cư: Thu hàng tháng, dao động từ 7.000 đồng/m²
INSERT INTO Fee (name, ratePerSquareMeter, isMandatory, feeType) VALUES
('Phí quản lý chung cư', 7000, TRUE, 'MANAGEMENT_FEE');

-- Các khoản đóng góp không bắt buộc như quỹ từ thiện, quỹ vì người nghèo, quỹ biển đảo, thu theo từng đợt
INSERT INTO Fee (name, ratePerSquareMeter, isMandatory, feeType) VALUES
('Quỹ từ thiện', 0, FALSE, 'CONTRIBUTION_FEE'),
('Quỹ vì người nghèo', 0, FALSE, 'CONTRIBUTION_FEE'),
('Quỹ biển đảo', 0, FALSE, 'CONTRIBUTION_FEE');

-- Chèn dữ liệu vào bảng Payment (ví dụ)
-- Giả định một vài khoản thanh toán cho các khoản phí đã tạo
INSERT INTO Payment (feeId, apartmentId, amountDue, amountPaid, paymentDate, payForMonth, payForYear, status) VALUES
(1, 101, 50000, 25000, '2024-11-01', 11, 2024, 'PENDING'),
(2, 102, 70000, 70000, '2024-10-15', 10, 2024, 'PAID'),
(3, 103, 45000, 0, '2024-09-10', 9, 2024, 'OVERDUE');


-- Thêm dữ liệu vào bảng Apartment
INSERT INTO Apartment (ownerId, area, floor, roomNumber) VALUES
(1, 75.5, 3, '301'),
(2, 85.0, 5, '501'),
(3, 60.0, 2, '202'),
(4, 120.3, 7, '701'),
(5, 95.8, 4, '402');
