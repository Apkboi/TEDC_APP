package com.example.tedc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tedc.adapters.SkillsAdapter;
import com.example.tedc.databinding.ActivityAdminBinding;
import com.example.tedc.models.Skill;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    ActivityAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        setContentView(view);


        binding.btnAllComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(AdminActivity.this,AllComplaintActivity.class));
            }
        });

        binding.btnStudentRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this,StudentRecordActivity.class));
            }
        });
        binding.btnAddSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this,AddSkillActivity.class));
            }
        });
        ArrayList<Skill> skills = new ArrayList<>();


//        binding.skillRecyclerview.setAdapter(skillsAdapter);


        binding.btnSendInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this,MakePostActivity.class));
            }
        });



    }
}