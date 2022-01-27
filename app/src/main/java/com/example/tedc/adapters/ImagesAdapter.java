package com.example.tedc.adapters;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tedc.R;
import com.example.tedc.interfaces.CloseListener;

import java.util.ArrayList;

public class ImagesAdapter extends  RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder> {

    ArrayList<Uri> images = new ArrayList<>();

    CloseListener closeListener;

    public void setCloseListener(CloseListener closeListener) {
        this.closeListener = closeListener;
    }

    public void setImages(ArrayList<Uri> images) {
        this.images = images;
        notifyDataSetChanged();
    }







    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.images_item,parent,false);

        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder,  int position) {
        int index = position;

        Uri imageUri = images.get(position);
        holder.image.setImageURI(imageUri);
        holder.ic_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeListener.onCanceled(index);
                notifyDataSetChanged();
//                images.remove(index);
            }
        });


    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public  class ImagesViewHolder extends RecyclerView.ViewHolder {

        ImageView image,ic_close;
        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_log);
            ic_close = itemView.findViewById(R.id.btn_close);
        }
    }
}
