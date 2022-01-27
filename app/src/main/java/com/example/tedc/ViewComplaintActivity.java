package com.example.tedc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tedc.databinding.ActivityViewComplaintBinding;

public class ViewComplaintActivity extends AppCompatActivity {

    ActivityViewComplaintBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewComplaintBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();

        binding.txtCtittle.setText(intent.getStringExtra("tittle"));
        binding.txtCdescription.setText(intent.getStringExtra("detail"));




    }
}