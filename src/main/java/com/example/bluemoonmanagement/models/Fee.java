package com.example.bluemoonmanagement.models;


public class Fee {
    private int feeId;
    private String name;
    private double ratePerSquareMeter; // Don gia tren m2
    private boolean isMandatory; // Phi bat buoc hay khong
    private FeeType feeType; // Loai Phi

    public Fee() {
    }
    public Fee(int feeId, String name, double ratePerSquareMeter, boolean isMandatory, FeeType feeType) {
        this.feeId = feeId;
        this.name = name;
        this.ratePerSquareMeter = ratePerSquareMeter;
        this.isMandatory = isMandatory;
        this.feeType = feeType;
    }

    // Getters and Setters
    public int getFeeId() {
        return feeId;
    }

    public void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRatePerSquareMeter() {
        return ratePerSquareMeter;
    }

    public void setRatePerSquareMeter(double ratePerSquareMeter) {
        this.ratePerSquareMeter = ratePerSquareMeter;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }

    public FeeType getFeeType() {
        return feeType;
    }

    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    public String toString() {
        return "Fee{" +
                "feeId=" + feeId +
                ", name=" + name +
                ", ratePerSquareMeter=" + ratePerSquareMeter +
                ", isMandatory=" + isMandatory +
                ", feeType=" + feeType +
                '}';
    }

}