package com.example.tedc.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tedc.R;
import com.example.tedc.ViewComplaintActivity;
import com.example.tedc.models.Complaint;

import java.util.ArrayList;

public class ComplaintAdapter extends  RecyclerView.Adapter<ComplaintAdapter.ComplaintItemHolder> {


    ArrayList<Complaint> complaints = new ArrayList<>();

    public  void setComplaints(ArrayList<Complaint> complaints) {
        this.complaints = complaints;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ComplaintItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_item,parent,false);

        return new ComplaintItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintItemHolder holder, int position) {
        Complaint complaint = complaints.get(position);
        holder.tittle.setText(complaint.getComplaintTittle());
        holder.status.setText(complaint.getStatus());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ViewComplaintActivity.class);
                intent.putExtra("tittle",complaint.getComplaintTittle());
                intent.putExtra("detail",complaint.getComplaint());
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return complaints.size();
    }

    public  class ComplaintItemHolder extends RecyclerView.ViewHolder {
        TextView tittle,status;
        public ComplaintItemHolder(@NonNull View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.complaint_tittle);
            status = itemView.findViewById(R.id.status);
        }
    }

}
