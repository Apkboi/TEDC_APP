package com.example.tedc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tedc.databinding.ActivityAddComplaintsBinding;
import com.example.tedc.models.Complaint;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.view_models.DataViewModel;

public class AddComplaintsActivity extends AppCompatActivity {

    ActivityAddComplaintsBinding binding;
    DataViewModel dataViewModel;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityAddComplaintsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        dataViewModel = new DataViewModel();
        dialog = new ProgressDialog(this);
        super.onCreate(savedInstanceState);
        setContentView(view);

        binding.btnComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showLoadingDialog();
                dataViewModel.saveComplaint(new Complaint("",
                        binding.edtComplaint.getText().toString(),
                        binding.edtTittle.getText().toString(),
                        "sent"
                        ));


            }
        });

        dataViewModel.complaintResponse().observe(this, new Observer<VerificationResult>() {
            @Override
            public void onChanged(VerificationResult verificationResult) {
                if (verificationResult.isHasError()){
                    Toast.makeText(AddComplaintsActivity.this,
                            verificationResult.getData().toString(),Toast.LENGTH_LONG).show();
                    hideLoadingDialog();
                }else {
                    hideLoadingDialog();
                    Toast.makeText(AddComplaintsActivity.this,
                            "Your Complaint is sent. You will receive response soon",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }


    private void showLoadingDialog() {

        dialog.setMessage("Sending Complaint...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void hideLoadingDialog() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
