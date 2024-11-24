package com.example.bluemoonmanagement.models;

import java.util.Date;

public class Payment {
    private int paymentId;
    private int feeId;
    private int apartmentId;
    private double amountDue;      // Số tiền phải thanh toán
    private double amountPaid;     // Số tiền đã thanh toán
    private Date paymentDate;      // Ngày thanh toán
    private int payForMonth;       // Tháng áp dụng phí
    private int payForYear;        // Năm áp dụng phí
    private PaymentStatus status;  // Trạng thái thanh toán

    public Payment() {
    }
    // Contructor khong chua paymentId: auto-increase
    public Payment(int feeId, int apartmentId, double amountDue, double amountPaid, Date paymentDate, int payForMonth, int payForYear, PaymentStatus status) {
        this.paymentId = paymentId;
        this.feeId = feeId;
        this.apartmentId = apartmentId;
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.payForMonth = payForMonth;
        this.payForYear = payForYear;
        this.status = status;
    }
    public Payment(int paymentId, int feeId, int apartmentId, double amountDue, double amountPaid, Date paymentDate, int payForMonth, int payForYear, PaymentStatus status) {
        this.paymentId = paymentId;
        this.feeId = feeId;
        this.apartmentId = apartmentId;
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.payForMonth = payForMonth;
        this.payForYear = payForYear;
        this.status = status;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getFeeId() {
        return feeId;
    }

    public void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getPayForMonth() {
        return payForMonth;
    }

    public void setPayForMonth(int payForMonth) {
        this.payForMonth = payForMonth;
    }

    public int getPayForYear() {
        return payForYear;
    }

    public void setPayForYear(int payForYear) {
        this.payForYear = payForYear;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", feeId=" + feeId +
                ", apartmentId=" + apartmentId +
                ", amountDue=" + amountDue +
                ", amountPaid=" + amountPaid +
                ", paymentDate=" + paymentDate +
                ", payForMonth=" + payForMonth +
                ", payForYear=" + payForYear +
                ", status=" + status +
                '}';
    }


}

























//import java.util.Date;

//public class Payment {
//    private int paymentId;
//    private int feeId;
//    private int apartmentId;
//    private Date timeValidate;
//    private int payForMonth;
//    private int payForYear;
//    private boolean isLate;
//
//    public Payment(int paymentId, int feeId, int apartmentId, Date timeValidate, int payForMonth, int payForYear, boolean isLate) {
//        this.paymentId = paymentId;
//        this.feeId = feeId;
//        this.apartmentId = apartmentId;
//        this.timeValidate = timeValidate;
//        this.payForMonth = payForMonth;
//        this.payForYear = payForYear;
//        this.isLate = isLate;
//    }
//
//    // Getters and Setters
//    public int getPaymentId() {
//        return paymentId;
//    }
//
//    public void setPaymentId(int paymentId) {
//        this.paymentId = paymentId;
//    }
//
//    public int getFeeId() {
//        return feeId;
//    }
//
//    public void setFeeId(int feeId) {
//        this.feeId = feeId;
//    }
//
//    public int getApartmentId() {
//        return apartmentId;
//    }
//
//    public void setApartmentId(int apartmentId) {
//        this.apartmentId = apartmentId;
//    }
//
//    public Date getTimeValidate() {
//        return timeValidate;
//    }
//
//    public void setTimeValidate(Date timeValidate) {
//        this.timeValidate = timeValidate;
//    }
//
//    public int getPayForMonth() {
//        return payForMonth;
//    }
//
//    public void setPayForMonth(int payForMonth) {
//        this.payForMonth = payForMonth;
//    }
//
//    public int getPayForYear() {
//        return payForYear;
//    }
//
//    public void setPayForYear(int payForYear) {
//        this.payForYear = payForYear;
//    }
//
//    public boolean isLate() {
//        return isLate;
//    }
//
//    public void setLate(boolean late) {
//        isLate = late;
//    }
//
//    @Override
//    public String toString() {
//        return "Payment{" +
//                "paymentId=" + paymentId +
//                ", feeId=" + feeId +
//                ", apartmentId=" + apartmentId +
//                ", timeValidate=" + timeValidate +
//                ", payForMonth=" + payForMonth +
//                ", payForYear=" + payForYear +
//                ", isLate=" + isLate +
//                '}';
//    }
//}