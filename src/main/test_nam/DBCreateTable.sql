-- Tạo database bluemoon2
CREATE DATABASE bluemoon2;
USE bluemoon2;

-- Tạo bảng Fee
CREATE TABLE Fee (
    feeId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    ratePerSquareMeter DOUBLE NOT NULL,
    isMandatory BOOLEAN NOT NULL,
    feeType VARCHAR(50) CHECK (feeType IN ('SERVICE_FEE', 'MANAGEMENT_FEE', 'CONTRIBUTION_FEE'))
);

-- Tạo bảng Payment
CREATE TABLE Payment (
    paymentId INT PRIMARY KEY AUTO_INCREMENT,
    feeId INT,
    apartmentId INT NOT NULL,
    amountDue DOUBLE NOT NULL,
    amountPaid DOUBLE NOT NULL,
    paymentDate DATE NOT NULL,
    payForMonth INT NOT NULL CHECK (payForMonth BETWEEN 1 AND 12),
    payForYear INT NOT NULL,
    status VARCHAR(50) CHECK (status IN ('PENDING', 'PAID', 'OVERDUE')),
    FOREIGN KEY (feeId) REFERENCES Fee(feeId)
);

-- Tạo bảng Apartment
CREATE TABLE Apartment (
    apartmentId INT PRIMARY KEY AUTO_INCREMENT,
    ownerId INT NOT NULL,
    area DOUBLE NOT NULL,           -- Diện tích căn hộ
    floor INT NOT NULL,
    roomNumber VARCHAR(50) NOT NULL
);
