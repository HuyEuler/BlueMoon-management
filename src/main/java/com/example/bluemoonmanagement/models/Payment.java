package com.example.bluemoonmanagement.models;

import java.util.Date;

public class Payment {
    private int paymentId;
    private int feeId;
    private int apartmentId;
    private Date timeValidate;
    private int payForMonth;
    private int payForYear;
    private boolean isLate;

    public Payment(int paymentId, int feeId, int apartmentId, Date timeValidate, int payForMonth, int payForYear, boolean isLate) {
        this.paymentId = paymentId;
        this.feeId = feeId;
        this.apartmentId = apartmentId;
        this.timeValidate = timeValidate;
        this.payForMonth = payForMonth;
        this.payForYear = payForYear;
        this.isLate = isLate;
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

    public Date getTimeValidate() {
        return timeValidate;
    }

    public void setTimeValidate(Date timeValidate) {
        this.timeValidate = timeValidate;
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

    public boolean isLate() {
        return isLate;
    }

    public void setLate(boolean late) {
        isLate = late;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", feeId=" + feeId +
                ", apartmentId=" + apartmentId +
                ", timeValidate=" + timeValidate +
                ", payForMonth=" + payForMonth +
                ", payForYear=" + payForYear +
                ", isLate=" + isLate +
                '}';
    }
}