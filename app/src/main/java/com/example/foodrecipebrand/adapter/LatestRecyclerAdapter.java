package com.example.foodrecipebrand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodrecipebrand.R;
import com.example.foodrecipebrand.models.Categories;
import com.example.foodrecipebrand.models.LatestDishes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LatestRecyclerAdapter extends RecyclerView.Adapter<LatestRecyclerAdapter.latestViewHolder> {
    ArrayList<LatestDishes> dishesList;
    Context context;
    Consumer<LatestDishes> onClick;
    public LatestRecyclerAdapter(ArrayList<LatestDishes> dishesList, Context context) {
        this.dishesList = dishesList;
        this.context = context;
    }

    public LatestRecyclerAdapter(ArrayList<LatestDishes> latestDishesList) {
    }

    public void setOnClick(Consumer<LatestDishes> onClick) {
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public LatestRecyclerAdapter.latestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.latest_dishes_cards, parent, false);
        return new latestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LatestRecyclerAdapter.latestViewHolder holder, int position) {
        holder.dishName.setText(dishesList.get(position).getStrMeal());
        Glide.with(context).load(dishesList.get(position).getStrMealThumb()).into(holder.dishImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick !=null){
                    onClick.accept(dishesList.get(position));
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return dishesList.size();
    }

    public class latestViewHolder extends RecyclerView.ViewHolder {
        TextView dishName;
        ImageView dishImage;
        public latestViewHolder(@NonNull View itemView) {
            super(itemView);
            dishImage = itemView.findViewById(R.id.latest_dishesIm);
            dishName = itemView.findViewById(R.id.latest_dishes_name);

        }
    }
}
