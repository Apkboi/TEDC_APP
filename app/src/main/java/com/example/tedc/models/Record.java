package com.example.tedc.models;

public class Record {
    public Record() {
    }

    String recordId, sName, regNo,Dpt,School,Level,Skill;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getDpt() {
        return Dpt;
    }

    public void setDpt(String dpt) {
        Dpt = dpt;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public Record(String sName, String regNo, String dpt, String school, String level, String skill) {
        this.recordId = recordId;
        this.sName = sName;
        this.regNo = regNo;
        Dpt = dpt;
        School = school;
        Level = level;
        Skill = skill;
    }
}
