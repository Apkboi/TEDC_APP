package com.example.tedc;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tedc.databinding.ActivityMakePostBinding;
import com.example.tedc.models.Post;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.view_models.DataViewModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MakePostActivity extends AppCompatActivity {

    ActivityMakePostBinding binding;
    DataViewModel dataViewModel;
    ProgressDialog dialog;
    Uri image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMakePostBinding.inflate(getLayoutInflater());
        dataViewModel = new DataViewModel();
        dialog = new ProgressDialog(this);
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        setContentView(view);

        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                binding.imgPost.setImageURI(result);
                Toast.makeText(MakePostActivity.this, result.toString(), Toast.LENGTH_LONG).show();
                image = result;

            }
        });

        dataViewModel.savePostRes.observe(this, new Observer<VerificationResult>() {
            @Override
            public void onChanged(VerificationResult verificationResult) {
                if (verificationResult.isHasError()) {
                    hideLoadingDialog();
                    Toast.makeText(MakePostActivity.this, verificationResult.getData().toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MakePostActivity.this, "Posted", Toast.LENGTH_LONG).show();
                    finish();

                }
            }
        });

        binding.btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mGetContent.launch("image/*");

            }
        });

        binding.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload(image);
            }
        });
    }


    private void savePost(String uri) {



            dataViewModel.savePost(new Post(binding.edtPostTittle.getText().toString(),
                    binding.edtPost.getText().toString(), uri));



    }

    private void showLoadingDialog() {

        dialog.setMessage("Uploading Post");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void hideLoadingDialog() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private void upload(Uri uri) {


        if (binding.edtPostTittle.getText().toString().isEmpty()) {
            binding.inputPostTittle.setError("Enter Information Tittle");
        } else if (binding.edtPost.getText().toString().isEmpty()) {
            binding.inputPost.setError("Enter Information Content");

        } else {

            if (uri!= null){
                StorageReference reference = FirebaseStorage.getInstance().getReference().child("image/" + uri.getLastPathSegment());
                UploadTask uploadTask = reference.putFile(uri);

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return reference.getDownloadUrl();
                    }
                }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String mUri  = uri.toString();
                        savePost(mUri);
                    }
                });

                showLoadingDialog();
            }else {
                savePost("");
                showLoadingDialog();
            }



        }



    }
}