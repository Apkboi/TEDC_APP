package com.example.tedc.ui.logbook;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tedc.adapters.ImagesAdapter;
import com.example.tedc.databinding.FragmentLogbookBinding;
import com.example.tedc.interfaces.CloseListener;
import com.example.tedc.models.LogBook;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.view_models.DataViewModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class LogBookFragment extends Fragment implements CloseListener {

    private LogBookViewModel logBookViewModel;
    private FragmentLogbookBinding binding;
    DataViewModel dataViewModel;
    ProgressDialog dialog;
    List<Uri> imageList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logBookViewModel =
                new ViewModelProvider(this).get(LogBookViewModel.class);
        dialog = new ProgressDialog(getContext());
        dataViewModel = new DataViewModel();
        binding = FragmentLogbookBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ImagesAdapter imagesAdapter = new ImagesAdapter();

        imagesAdapter.setCloseListener(this);

        ActivityResultLauncher<String> launcher = registerForActivityResult(new ActivityResultContracts.GetMultipleContents(), new ActivityResultCallback<List<Uri>>() {
            @Override
            public void onActivityResult(List<Uri> result) {
                imageList = result;
                imagesAdapter.setImages((ArrayList<Uri>) result);


            }
        });

        binding.imagesRecycler.setAdapter(imagesAdapter);

        binding.btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch("image/*");
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImages((ArrayList<Uri>) imageList);
            }
        });

        dataViewModel.saveLogBooRes.observe(getViewLifecycleOwner(), new Observer<VerificationResult>() {
            @Override
            public void onChanged(VerificationResult verificationResult) {
                if (!verificationResult.isHasError()) {
                    imageList.clear();
                    binding.edtLogfullname.setText("");
                    binding.edtLogregNo.setText("");
                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_LONG).show();
                    hideLoadingDialog();
                } else {
                    hideLoadingDialog();
                    Toast.makeText(getContext(), verificationResult.getData().toString(), Toast.LENGTH_LONG).show();

                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void uploadImages(ArrayList<Uri> uriArrayList) {

        ArrayList<String> images = new ArrayList<>();
        for (int i = 0; i < uriArrayList.size(); i++) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference()
                    .child("image/" + uriArrayList.get(i).getLastPathSegment());


            UploadTask task = storageReference.putFile(uriArrayList.get(i));
            int finalI = i;
            Task<Uri> uriTask = task.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    return storageReference.getDownloadUrl();
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    images.add(uri.toString());
                    Log.i("onSucces", String.valueOf(images.size()));
                    Log.i("onSucces", String.valueOf(imageList.size()));

                    if (String.valueOf(images.size()).equals(String.valueOf(imageList.size()))) {
                        LogBook logBook = new LogBook(images, binding.edtLogfullname.getText().toString(), binding.edtLogregNo.getText().toString());
                        saveLogBook(logBook);
                        Log.i("onSucces", "Do Your Shit Here ");
                    }


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("upload failded", e.getMessage().toString());
                }
            });


//            UploadTask task =


        }
        showLoadingDialog();
    }


    public void saveLogBook(LogBook logBook) {

        dataViewModel.saveLogBook(logBook);

    }


    private void showLoadingDialog() {

        dialog.setMessage("Uploading LogBook");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void hideLoadingDialog() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void save(Uri uri) {


    }


    @Override
    public void onCanceled(int index) {
        Log.i("onCanceled", String.valueOf(index));
        imageList.remove(index);
    }
}