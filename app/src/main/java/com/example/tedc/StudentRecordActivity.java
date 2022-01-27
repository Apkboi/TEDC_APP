package com.example.tedc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tedc.adapters.StudentsAdapter;
import com.example.tedc.databinding.ActivityStudentRecordBinding;
import com.example.tedc.models.Record;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.view_models.DataViewModel;

import java.util.ArrayList;

public class StudentRecordActivity extends AppCompatActivity {

    ActivityStudentRecordBinding binding;
    DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataViewModel = new DataViewModel();
        binding = ActivityStudentRecordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        StudentsAdapter adapter = new StudentsAdapter();

        dataViewModel.getAllRecords();


        dataViewModel.getRecordResponse().observe(this, new Observer<VerificationResult>() {
            @Override
            public void onChanged(VerificationResult verificationResult) {
                if (verificationResult.isHasError()) {
                    Log.i("recordError", verificationResult.getData().toString());
                    Toast.makeText(StudentRecordActivity.this, verificationResult.getData().toString(), Toast.LENGTH_LONG).show();
                } else {
                    ArrayList<Record> recordArrayList = (ArrayList<Record>) verificationResult.getData();
                    Log.i("recordList", recordArrayList.toString());
                    adapter.setRecods(recordArrayList);
                }
            }
        });
        binding.recordRecycler.setAdapter(adapter);


    }
}