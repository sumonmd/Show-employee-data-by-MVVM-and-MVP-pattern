package com.example.user.internproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.internproject.R;
import com.example.user.internproject.databinding.SingleViewLayoutBinding;
import com.example.user.internproject.model.PersonModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<PersonModel> employeeList;

    Context context;


    /*public MyAdapter(ArrayList<PersonModel>employeeList , Context context) {
        this.employeeList = employeeList;
        this.context = context;

    }*/

    public MyAdapter() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleViewLayoutBinding singleViewLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.single_view_layout,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(singleViewLayoutBinding);
        return myViewHolder;
       /* LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_view_layout, parent ,false);
        return new MyViewHolder(view);*/
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        PersonModel personModel = employeeList.get(position);
        holder.singleViewLayoutBinding.setLocal(personModel);
/*
        holder.nameTextView.setText(employeeList.get(position).getName());
        holder.designationTextView.setText(employeeList.get(position).getDesignation());
        holder.teamTextView.setText(employeeList.get(position).getTeam());*/
       // holder.imageTextview.setImageResource(employeeList.get(position).getTeam());
       /* Glide
                .with(context)
                .load(employeeList.get(position).getImage())
                .centerCrop()
                .into(holder.imageTextview);*/

    }

    @Override
    public int getItemCount() {
        return employeeList == null? 0 : employeeList.size();
    }

    public void  setPersonModels(List<PersonModel>persons){

            this.employeeList = persons;
            notifyDataSetChanged();


    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        SingleViewLayoutBinding singleViewLayoutBinding;

        TextView nameTextView,designationTextView,teamTextView;
        ImageView imageTextview;

        public MyViewHolder(@NonNull SingleViewLayoutBinding itemView) {
            super(itemView.getRoot());
            singleViewLayoutBinding = itemView;
          /*  nameTextView = itemView.findViewById(R.id.nameViewId);
            designationTextView = itemView.findViewById(R.id.designationViewId);
            teamTextView = itemView.findViewById(R.id.teamViewId);
            imageTextview = itemView.findViewById(R.id.myImageViewId);*/
            
        }
    }
}
