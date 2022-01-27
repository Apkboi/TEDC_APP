package com.example.tedc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tedc.adapters.ComplaintAdapter;
import com.example.tedc.databinding.ActivityAllComplaintBinding;
import com.example.tedc.models.Complaint;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.view_models.DataViewModel;

import java.util.ArrayList;

public class AllComplaintActivity extends AppCompatActivity {

    private DataViewModel dataViewModel;
    ActivityAllComplaintBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataViewModel = new DataViewModel();
        binding = ActivityAllComplaintBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ComplaintAdapter adapter = new ComplaintAdapter();


        dataViewModel.getAllComplaint();


        dataViewModel.getAllComplaintResponse().observe(this, new Observer<VerificationResult>() {
            @Override
            public void onChanged(VerificationResult verificationResult) {
                Log.i("getComplaint", verificationResult.getData().toString());
                ArrayList<Complaint> complaints = (ArrayList<Complaint>) verificationResult.getData();
                adapter.setComplaints(complaints);
            }
        });

        binding.complainRecycler.setAdapter(adapter);


    }
}