package com.example.tedc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tedc.databinding.ActivityRecordDetailsBinding;

public class RecordDetailsActivity extends AppCompatActivity {

    ActivityRecordDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecordDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();

        binding.txtFullname.setText(intent.getStringExtra("name"));
        binding.txtRegNo.setText(intent.getStringExtra("regNo"));
        binding.txtDepartment.setText(intent.getStringExtra("dept"));
        binding.txtSkill.setText(intent.getStringExtra("skill"));




    }
}