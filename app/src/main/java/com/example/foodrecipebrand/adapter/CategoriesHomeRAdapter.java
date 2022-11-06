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
import com.example.foodrecipebrand.models.Categories;

import java.util.ArrayList;
import java.util.function.Consumer;

public class CategoriesHomeRAdapter extends RecyclerView.Adapter<CategoriesHomeRAdapter.categoryViewHolder> {

    Context context;
    ArrayList<Categories> list;

    Consumer<Categories> onClick;

    public void setOnClick(Consumer<Categories> onClick) {
        this.onClick = onClick;
    }

    public CategoriesHomeRAdapter(Context context, ArrayList<Categories> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CategoriesHomeRAdapter.categoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.categories_items, parent, false);
        return new categoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesHomeRAdapter.categoryViewHolder holder, int position) {
        holder.categoryTv.setText(list.get(position).getStrCategory());
        Glide.with(context).load(list.get(position).getStrCategoryThumb()).into(holder.categoryIm);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick !=null){
                    onClick.accept(list.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class categoryViewHolder extends RecyclerView.ViewHolder
    {
        ImageView categoryIm;
        TextView categoryTv;

        public categoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIm = itemView.findViewById(R.id.categoryIm);
            categoryTv = itemView.findViewById(R.id.categoryName);
        }
    }
}
