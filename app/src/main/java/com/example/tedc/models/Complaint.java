package com.example.tedc.models;

import androidx.annotation.Keep;

public class Complaint {
    String complaintId;
    String senderId;
    String complaint;
    String complaintTittle;
    String status;


    public Complaint() {
    }

    public Complaint(String senderId, String complaint, String complaintTittle, String status) {
        this.senderId = senderId;
        this.complaint = complaint;
        this.complaintTittle = complaintTittle;
        this.status = status;
    }

    public String getComplaintTittle() {
        return complaintTittle;
    }

    public void setComplaintTittle(String complaintTittle) {
        this.complaintTittle = complaintTittle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }


    //    Date Needed
}
