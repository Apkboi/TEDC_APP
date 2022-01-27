package com.example.tedc.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tedc.R;
import com.example.tedc.RegisterActivity;
import com.example.tedc.models.Skill;

import java.util.ArrayList;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.SkillItemHolder> {


    ArrayList<Skill> skillArrayList = new ArrayList<>();

    public void  setSkills(ArrayList<Skill> skills){
        this.skillArrayList = skills;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public SkillItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_item,parent,false);

        return new SkillItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillItemHolder holder, int position) {
        Skill skill = skillArrayList.get(position);
        holder.txt_name.setText(skill.getName());
        holder.txt_description.setText(skill.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemView.getContext().startActivity(new Intent(  holder.itemView.getContext(), RegisterActivity.class));
            }
        });



    }

    @Override
    public int getItemCount() {
        return skillArrayList.size();
    }

    public class SkillItemHolder extends  RecyclerView.ViewHolder{

        TextView txt_name ,  txt_description ;

        public SkillItemHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.skill_name);
            txt_description = itemView.findViewById(R.id.skill_description);

        }
    }
}
