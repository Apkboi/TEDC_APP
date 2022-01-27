package com.example.tedc.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tedc.R;
import com.example.tedc.adapters.PostAdapter;
import com.example.tedc.adapters.SkillsAdapter;
import com.example.tedc.databinding.FragmentHomeBinding;
import com.example.tedc.models.Post;
import com.example.tedc.models.Skill;
import com.example.tedc.models.VerificationResult;
import com.example.tedc.view_models.DataViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private DataViewModel dataViewModel = new DataViewModel();
    private FragmentHomeBinding binding;
    ArrayList<Post> posts = new ArrayList<>();
    ArrayList<Skill> skills = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
            dataViewModel.getAllPost();







//        Skills
        skills.add(new Skill("Baking","Learn how make cake, bread etc"));
        skills.add(new Skill("Barbing ","Learn How to style hairs"));
//        skills.add(new Skill("ccdcd","Networking"));


//        Posts
        posts.add(new Post("EED results out"," clsknclskclskcs","csmkclc"));
        posts.add(new Post("EED results out"," clsknclskclskcs","csmkclc"));
        posts.add(new Post("EED results out"," clsknclskclskcs","csmkclc"));
        posts.add(new Post("EED results out"," clsknclskclskcs","csmkclc"));
        PostAdapter postAdapter = new PostAdapter();
        SkillsAdapter skillsAdapter = new SkillsAdapter();

        skillsAdapter.setSkills(skills);
        binding.skillRecycler.setAdapter(skillsAdapter);

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

        binding.postRcycler.setAdapter(postAdapter);

        return root;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}