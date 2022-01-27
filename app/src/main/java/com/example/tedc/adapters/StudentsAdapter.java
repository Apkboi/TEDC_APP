package com.example.tedc.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tedc.R;
import com.example.tedc.RecordDetailsActivity;
import com.example.tedc.models.Complaint;
import com.example.tedc.models.Record;

import java.util.ArrayList;

public class StudentsAdapter extends  RecyclerView.Adapter<StudentsAdapter.RecordItemHolder> {


    ArrayList<Record> recods = new ArrayList<>();

    public  void setRecods(ArrayList<Record> recods) {
        this.recods = recods;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecordItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_record_item,parent,false);

        return new RecordItemHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecordItemHolder holder, int position) {
        Record record = recods.get(position);
        Log.i("onBind", record.getDpt());
        holder.student_name.setText(record.getsName());
        holder.skill.setText(record.getSkill());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), RecordDetailsActivity.class);
                intent.putExtra("name",record.getsName());
                intent.putExtra("regNo",record.getRegNo());
                intent.putExtra("dept",record.getDpt());
                intent.putExtra("skill",record.getSkill());

                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recods.size();
    }

    public  class RecordItemHolder extends RecyclerView.ViewHolder {
        TextView student_name, skill;
        public RecordItemHolder(@NonNull View itemView) {
            super(itemView);
            student_name = itemView.findViewById(R.id.student_name);
            skill = itemView.findViewById(R.id.skill);
        }
    }

}
