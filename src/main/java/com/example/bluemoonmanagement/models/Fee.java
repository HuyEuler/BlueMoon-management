package com.example.bluemoonmanagement.models;

import java.util.Date;

public class Fee {
    private int feeId;
    private String name;
    private int cost;
    private boolean mandatory;
    private int cycle;
    private Date expiration;
    private int status;

    public Fee(int feeId, String name, int cost, boolean mandatory, int cycle, Date expiration, int status) {
        this.feeId = feeId;
        this.name = name;
        this.cost = cost;
        this.mandatory = mandatory;
        this.cycle = cycle;
        this.expiration = expiration;
        this.status = status;
    }

    // Getters and setters for all properties

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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
