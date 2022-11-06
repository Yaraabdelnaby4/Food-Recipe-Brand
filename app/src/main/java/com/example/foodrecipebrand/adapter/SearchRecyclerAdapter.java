package com.example.foodrecipebrand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodrecipebrand.R;
import com.example.foodrecipebrand.models.LatestDishes;

import java.util.ArrayList;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.searchViewHolder> {
  private   ArrayList<LatestDishes> dishes;
   private Context context;

    public SearchRecyclerAdapter(Context context) {
        this.context = context;
    }
    public void setList(ArrayList<LatestDishes> list) {
        this.dishes=list;
    }

    @NonNull
    @Override
    public SearchRecyclerAdapter.searchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.latest_dishes_cards, parent, false);
        return new searchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerAdapter.searchViewHolder holder, int position) {
        holder.textView.setText(dishes.get(position).getStrMeal());
        Glide.with(context).load(dishes.get(position).getStrMealThumb()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return (dishes==null) ? 0 : dishes.size();
    }


    public class searchViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public searchViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.latest_dishesIm);
            textView=itemView.findViewById(R.id.latest_dishes_name);

        }
    }
}
