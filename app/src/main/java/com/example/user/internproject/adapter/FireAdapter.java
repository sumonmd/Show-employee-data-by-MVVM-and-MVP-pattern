package com.example.user.internproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.internproject.R;
import com.example.user.internproject.model.FireBaseModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FireAdapter extends RecyclerView.Adapter<FireAdapter.MyViewHolder>{
    List<FireBaseModel>artistList;
    Context context;

    public FireAdapter(List<FireBaseModel> artistList, Context con){

        this.artistList = artistList;
        this.context = con;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.single_view_layout, parent ,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nameTextView.setText(artistList.get(position).getArtistName());
        holder.designationTextView.setText(artistList.get(position).getArtistDesignation());
        holder.teamTextView.setText(artistList.get(position).getArtistTeam());
        //holder.imageTextview.setImageResource(employeeList.get(position).getTeam());
        Glide
                .with(context)
                .load(artistList.get(position).getArtistImage())
                .centerCrop()
                .into(holder.imageTextview);
        // holder.imageView.setImageResource(employeeList.get(position).getTeam());


    }


    @Override
    public int getItemCount() {
        return artistList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView,designationTextView,teamTextView;
        ImageView imageTextview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameViewId);
            designationTextView = itemView.findViewById(R.id.designationViewId);
            teamTextView = itemView.findViewById(R.id.teamViewId);
            imageTextview = itemView.findViewById(R.id.myImageViewId);

        }
    }
}
