package com.example.bluemoonmanagement.models;

public class Activity {
    private int activityId;
    private int residentId;
    private int status;
    private String timeIn;
    private String timeOut;
    private String note;

    // Constructor không chứa activityId (auto-increment)
    public Activity(int residentId, int status, String timeIn, String timeOut, String note) {
        this.residentId = residentId;
        this.status = status;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.note = note;
    }

    // Getters và Setters cho các thuộc tính

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}