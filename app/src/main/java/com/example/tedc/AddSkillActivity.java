package com.example.tedc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tedc.databinding.ActivityAddSkillBinding;
import com.example.tedc.models.Post;
import com.example.tedc.models.Skill;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.view_models.DataViewModel;

public class AddSkillActivity extends AppCompatActivity {

    ActivityAddSkillBinding binding;
    DataViewModel dataViewModel;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataViewModel = new DataViewModel();
        dialog = new ProgressDialog(this);
        binding = ActivityAddSkillBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        dataViewModel.saveSkillRes.observe(this, new Observer<VerificationResult>() {
            @Override
            public void onChanged(VerificationResult verificationResult) {
                if (verificationResult.isHasError()) {
                    hideLoadingDialog();
                    Toast.makeText(AddSkillActivity.this, verificationResult.getData().toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AddSkillActivity.this, "Saved", Toast.LENGTH_LONG).show();
                    finish();

                }
            }
        });
        binding.btnAddSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSkill();
            }
        });

    }



    private void saveSkill() {
        if (binding.edtSkillName.getText().toString().isEmpty()) {
            binding.inputSkillName.setError("Enter Skill Name ");
        } else if (binding.edtDescription.getText().toString().isEmpty()) {
            binding.inputDescription.setError("Enter Skill Description");

        } else {

            dataViewModel.saveSkill(new Skill(binding.edtSkillName.getText().toString(),binding.edtDescription.getText().toString()));

            showLoadingDialog();

        }
    }

    private void showLoadingDialog() {

        dialog.setMessage("Saving....");
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