package com.example.tedc.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tedc.R;
import com.example.tedc.models.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostItemHolder> {

    public void setPostArrayList(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
        notifyDataSetChanged();
    }

    ArrayList<Post> postArrayList = new ArrayList<>();




    @NonNull
    @Override
    public PostItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);

        return new PostItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostItemHolder holder, int position) {
        Post post  = postArrayList.get(position);
        holder.txt_tittle.setText(post.getTittle());
//      holder.postImage.setImageURI(Uri.parse(post.getImage()));

        if (post != null){
            if (post.getImage().equals("")){
                holder.postImage.setVisibility(View.GONE);
            }else {
                Glide.with(holder.itemView.getContext()).load(post.getImage()).into(holder.postImage);
            }
        }


        holder.description.setText(post.getDescription());

    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public static class  PostItemHolder extends RecyclerView.ViewHolder{
        TextView txt_tittle,description;
        ImageView postImage;

        public PostItemHolder(@NonNull View itemView) {
            super(itemView);
            txt_tittle = itemView.findViewById(R.id.txt_tittle);
            description = itemView.findViewById(R.id.txt_description);
            postImage = itemView.findViewById(R.id.postImage);
        }
    }
}
