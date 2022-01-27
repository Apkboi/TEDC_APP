package com.example.tedc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.tedc.databinding.ActivityRegisterBinding;
import com.example.tedc.databinding.FragmentComplaintBinding;
import com.example.tedc.models.Record;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.view_models.AuthViewModel;
import com.example.tedc.view_models.DataViewModel;
import com.tiper.MaterialSpinner;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private DataViewModel dataViewModel;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent =  getIntent();

        dialog = new ProgressDialog(this);
        dataViewModel = new DataViewModel();
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String[] levels = {"ND I", "ND II", "HND I", "HND II",};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, levels);
        String[] departments = {"Computer Science", "Buisness Admin", "Accounting", "Electrical Engeneering",};
        ArrayAdapter<String> dptAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, departments);
        String[] school = {"SIAS", "ENGENEERING", "BUISNESS", "HUMANITIES",};
        ArrayAdapter<String> schAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, school);
        String[] skill = {"baking", "Barbing", "Ice cream making", "Computer Maintianace",};
        ArrayAdapter<String> skillAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, skill);

        binding.levelSpinner.setAdapter(adapter);
        binding.dptSpinner.setAdapter(dptAdapter);
        binding.schoolSpinner.setAdapter(schAdapter);
        binding.skillSpinner.setAdapter(skillAdapter);


        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingDialog();
                dataViewModel.saveRecord(new Record(binding.edtFullname.getText().toString(),
                        binding.edtLregNo.getText().toString(),
                        binding.dptSpinner.getSelectedItem().toString(),
                        binding.schoolSpinner.getSelectedItem().toString(),
                        binding.levelSpinner.getSelectedItem().toString(),
                        binding.skillSpinner.getSelectedItem().toString()
                        ));
            }
        });

        dataViewModel.saveResponse().observe(this, new Observer<VerificationResult>() {
            @Override
            public void onChanged(VerificationResult verificationResult) {

                if (verificationResult.isHasError()){
                    Toast.makeText(RegisterActivity.this,verificationResult.getData().toString(),Toast.LENGTH_LONG).show();
               hideLoadingDialog();
                }else{
                    hideLoadingDialog();
                    Toast.makeText(RegisterActivity.this,"Saved",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }

    private void showLoadingDialog() {

        dialog.setMessage("Saving Your Details..");
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