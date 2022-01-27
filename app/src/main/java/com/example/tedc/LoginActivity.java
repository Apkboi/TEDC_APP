package com.example.tedc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;
import androidx.lifecycle.Observer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tedc.databinding.ActivityLoginBinding;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.view_models.AuthViewModel;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private AuthViewModel authViewModel;
    ProgressDialog dialog;
    boolean checked;
    String loginAs = "student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        authViewModel = new AuthViewModel();
        dialog = new ProgressDialog(this);
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        setContentView(view);


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loginAs.equals("student")) {
                    loginUser();
                } else {

                    if (binding.edtLemail.getText().toString().equals("admin")
                            && (binding.edtLpassword.getText().toString().equals("admin"))){

                        startActivity(new Intent(LoginActivity.this, AdminActivity.class));

                    }else {
                        Toast.makeText(LoginActivity.this,"Enter a valid Credential",Toast.LENGTH_LONG).show();
                    }


                }

            }
        });
        binding.txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        authViewModel.authRes().observe(this, new Observer<VerificationResult>() {
            @Override
            public void onChanged(VerificationResult verificationResult) {

                if (!verificationResult.isHasError()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, verificationResult.getData().toString(), Toast.LENGTH_SHORT).show();
                }
                hideLoadingDialog();
            }
        });

    }


    public void loginUser() {
        if (binding.edtLemail.getText().toString().isEmpty() || !PatternsCompat.EMAIL_ADDRESS
                .matcher(binding.edtLemail.getText().toString()).matches()) {
            binding.inputLemail.setError("Invalid Email");
        } else if (binding.edtLpassword.getText().toString().isEmpty()) {
            binding.inputLpassword.setError("Enter Password");
        } else {

            authViewModel.loginUser(binding.edtLemail.getText().toString(), binding.edtLpassword.getText().toString());
            showLoadingDialog();

        }
    }

    private void showLoadingDialog() {

        dialog.setMessage("Signing in...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void hideLoadingDialog() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void onRadioButtonClicked(View button) {
        // Is the button now checked?
        checked = ((RadioButton) button).isChecked();

        // Check which radio button was clicked
        switch (button.getId()) {
            case R.id.student:
                if (checked)
                    loginAs = "student";
                // Pirates are the best
                break;
            case R.id.admin:
                if (checked)
                    loginAs = "admin";
                // Ninjas rule
                break;
        }
    }
}


