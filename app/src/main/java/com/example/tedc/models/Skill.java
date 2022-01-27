package com.example.tedc.models;

import androidx.recyclerview.widget.RecyclerView;

public class Skill   {



    public String getSkillId() {
        return SkillId;
    }

    public void setSkillId(String skillId) {
        SkillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Skill(String name, String description) {

        this.name = name;
        this.description = description;

    }

    String SkillId;
    String name;
    String description;
    int icon;
}
