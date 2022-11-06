package com.example.foodrecipebrand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodrecipebrand.R;
import com.example.foodrecipebrand.fragment.CategoriesFragment;
import com.example.foodrecipebrand.models.Categories;
import com.example.foodrecipebrand.models.LatestDishes;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MealsOfCategoryRAdapter extends RecyclerView.Adapter<MealsOfCategoryRAdapter.MealViewHolder> {
    ArrayList<LatestDishes>arrayList;
    Consumer<LatestDishes>onClick;

    public MealsOfCategoryRAdapter( ArrayList<LatestDishes> arrayList) {
        this.arrayList = arrayList;
    }

    public void setOnClick(Consumer<LatestDishes> onClick) {
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public MealsOfCategoryRAdapter.MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.dishes_items, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsOfCategoryRAdapter.MealViewHolder holder, int position) {
        Glide.with(new Fragment()).load(arrayList.get(position).getStrMealThumb()).into(holder.mealImage);
        holder.mealTv.setText(arrayList.get(position).getStrMeal());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick !=null){
                    onClick.accept(arrayList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImage;
        TextView mealTv;
        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage=itemView.findViewById(R.id.mealIm);
            mealTv=itemView.findViewById(R.id.mealName);
        }
    }
}
