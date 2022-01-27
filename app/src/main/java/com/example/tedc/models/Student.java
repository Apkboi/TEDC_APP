package com.example.tedc.models;

public class Student {

    String studentId;
    String name;
    String regNo;
    String dpt;
    String school;
    String level;
    String email;

    public Student(String name, String regNo, String dpt, String school,String level, String email) {
        this.studentId = studentId;
        this.name = name;
        this.regNo = regNo;
        this.dpt = dpt;
        this.school = school;
       this.level = level;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
