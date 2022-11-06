package com.example.foodrecipebrand;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.widget.EditText;

import com.example.foodrecipebrand.adapter.SearchRecyclerAdapter;
import com.example.foodrecipebrand.models.LatestDishes;


import java.util.ArrayList;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SearchActivity extends AppCompatActivity {

  private   RecyclerView searchRecycler;
   private SearchRecyclerAdapter recyclerAdapter;
   private EditText editText;
    private static final String TAG = "SearchActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText = findViewById(R.id.searchEdS);
        searchRecycler = findViewById(R.id.rv_search);
        recyclerAdapter = new SearchRecyclerAdapter(SearchActivity.this);

        search();
    }

    private void search() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                ArrayList<LatestDishes> list = (ArrayList<LatestDishes>) HomeActivity.latestDishesList.stream().filter(new Predicate<LatestDishes>() {
                    @Override
                    public boolean test(LatestDishes latestDishes) {
                        return latestDishes.getStrMeal().toLowerCase(Locale.ROOT).contains(s.toString().toLowerCase(Locale.ROOT));
                    }
                }).collect(Collectors.toList());
                if (list.size() == HomeActivity.latestDishesList.size()) list.clear();
                recyclerAdapter.setList(list);
                searchRecycler.setAdapter(recyclerAdapter);
            }
        });
    }


}