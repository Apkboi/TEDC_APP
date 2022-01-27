package com.example.tedc.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tedc.AddComplaintsActivity;
import com.example.tedc.adapters.ComplaintAdapter;
import com.example.tedc.databinding.FragmentComplaintBinding;
import com.example.tedc.models.Complaint;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.view_models.DataViewModel;

import java.util.ArrayList;


public class ComplaintFragment extends Fragment {

    private ComplaintViewModel complaintViewModel;
    private FragmentComplaintBinding binding;
    private DataViewModel dataViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dataViewModel = new DataViewModel();
        complaintViewModel =
                new ViewModelProvider(this).get(ComplaintViewModel.class);

        binding = FragmentComplaintBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ComplaintAdapter adapter = new ComplaintAdapter();
        ArrayList<Complaint> complaintArrayList = new ArrayList<>();

        binding.complainRecycler.setAdapter(adapter);



        dataViewModel.getUserComplaint();


        dataViewModel.getUserComplaintResponse().observe(getViewLifecycleOwner(), new Observer<VerificationResult>() {
            @Override
            public void onChanged(VerificationResult verificationResult) {
                Log.i("getComplaint", verificationResult.getData().toString());
                ArrayList<Complaint> complaints = (ArrayList<Complaint>) verificationResult.getData();
                binding.txtComplaints.setText(complaints.size()+""

                );
                adapter.setComplaints(complaints);
            }
        });


        binding.btnAddComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddComplaintsActivity.class));
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}