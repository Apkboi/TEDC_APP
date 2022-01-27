package com.example.tedc.models;

import java.util.ArrayList;

public class LogBook {

    String id;
    ArrayList<String> images;
    String studentName;
    String RegNo;

    public String getId() {
        return id;
    }

    public LogBook() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRegNo() {
        return RegNo;
    }

    public void setRegNo(String regNo) {
        RegNo = regNo;
    }

    public LogBook(ArrayList<String> images, String studentName, String regNo) {
        this.images = images;
        this.studentName = studentName;
        RegNo = regNo;
    }
}
