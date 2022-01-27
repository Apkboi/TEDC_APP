package com.example.tedc.ui.information;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tedc.adapters.PostAdapter;
import com.example.tedc.databinding.FragmentInformationBinding;
import com.example.tedc.models.Post;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.ui.logbook.LogBookViewModel;
import com.example.tedc.view_models.DataViewModel;

import java.util.ArrayList;

public class InformationFragment extends Fragment {

    private LogBookViewModel logBookViewModel;
    private FragmentInformationBinding binding;
    private DataViewModel dataViewModel = new DataViewModel();

    ArrayList<Post> posts = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logBookViewModel =
                new ViewModelProvider(this).get(LogBookViewModel.class);

        binding = FragmentInformationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        posts.add(new Post("EED results out"," clsknclskclskcs","csmkclc"));
        posts.add(new Post("EED results out"," clsknclskclskcs","csmkclc"));
        posts.add(new Post("EED results out"," clsknclskclskcs","csmkclc"));
        posts.add(new Post("EED results out"," clsknclskclskcs","csmkclc"));
        PostAdapter postAdapter = new PostAdapter();

    dataViewModel.getAllPost();


        dataViewModel.allPostRes.observe(getViewLifecycleOwner(), new Observer<VerificationResult>() {
            @Override
            public void onChanged(VerificationResult verificationResult) {
                ArrayList<Post> allposts;

                if (verificationResult.isHasError()){
                    Toast.makeText(getContext(),verificationResult.getData().toString(),Toast.LENGTH_LONG).show();
                }else {

                    allposts = (ArrayList<Post>) verificationResult.getData();
                    Log.i("postError", allposts.toString());
                    postAdapter.setPostArrayList(allposts);
                }



            }

        });

        binding.infoRecycler.setAdapter(postAdapter);

//        final TextView textView = binding.textGallery;
//        logBookViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}