package com.example.tedc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;
import androidx.lifecycle.Observer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.tedc.databinding.ActivityLoginBinding;
import com.example.tedc.databinding.ActivitySignUpBinding;
import com.example.tedc.models.Student;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.view_models.AuthViewModel;

public class SignUpActivity extends AppCompatActivity {

    AuthViewModel authViewModel;
    //    DataViewModel dataViewModel;
    ProgressDialog dialog;
    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        dialog = new ProgressDialog(this);
        authViewModel = new AuthViewModel();
//        dataViewModel = new DataViewModel();
        super.onCreate(savedInstanceState);
        setContentView(view);



        String[] levels = {"ND I", "ND II", "HND I", "HND II",};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, levels);
        String[] departments = {"Computer Science", "Buisness Admin", "Accounting", "Electrical Engeneering",};
        ArrayAdapter<String> dptAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, departments);
        String[] school = {"SIAS", "ENGENEERING", "BUISNESS", "HUMANITIES",};
        ArrayAdapter<String> schAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, school);
        String[] skill = {"baking", "Barbing", "Ice cream making", "Computer Maintianace",};
        ArrayAdapter<String> skillAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, skill);

        binding.SdptSpinner.setAdapter(dptAdapter);
        binding.SschoolSpinner.setAdapter(schAdapter);






        //        --------- Onclicks --------------
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

//        ----------- SignUp Exception Handling -------------
        authViewModel.authRes().observe(this, new Observer<VerificationResult>() {
            @Override
            public void onChanged(VerificationResult verificationResult) {

                if (!verificationResult.isHasError()) {
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));

                    finish();
                } else {
                    Log.i("Signup error", verificationResult.getData().toString());
                    Toast.makeText(SignUpActivity.this, verificationResult.getData().toString(), Toast.LENGTH_SHORT).show();
                }
                hideLoadingDialog();
            }
        });


    }

    public void registerUser() {
        if (binding.edtSemail.getText().toString().isEmpty()) {
            binding.inputSname.setError("Enter your name");
        } else if (binding.edtSemail.getText().toString().isEmpty() || !PatternsCompat.EMAIL_ADDRESS
                .matcher(binding.edtSemail.getText().toString()).matches()) {
            binding.inputSemail.setError("Invalid Email");
        } else if (binding.edtSregNo.getText().toString().isEmpty()) {
            binding.inputSregNo.setError("Enter Your Registration Number");
        } else if (binding.SdptSpinner.getSelectedItem().toString().isEmpty()) {
            binding.SdptSpinner.setError("Select Department");
        } else if (binding.SschoolSpinner.getSelectedItem().toString().isEmpty()) {
            binding.SschoolSpinner.setError("Select Your Level");
        } else if (binding.edtPassword.getText().toString().isEmpty()) {
            binding.inputPassword.setError("Enter Password");
        } else {

            authViewModel.registerUser(new Student(binding.edtSname.getText().toString(),
                    binding.edtSregNo.getText().toString(),
                    binding.SdptSpinner.getSelectedItem().toString(),
                    "","",
                    binding.edtSemail.getText().toString()
                    ), binding.edtPassword.getText().toString());
            showLoadingDialog();

        }
    }


    private void showLoadingDialog() {

        dialog.setMessage("Signing up...");
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
