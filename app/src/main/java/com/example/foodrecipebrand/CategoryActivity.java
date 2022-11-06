package com.example.foodrecipebrand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodrecipebrand.adapter.LatestRecyclerAdapter;
import com.example.foodrecipebrand.adapter.MealsOfCategoryRAdapter;
import com.example.foodrecipebrand.models.Categories;
import com.example.foodrecipebrand.models.LatestDishes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.function.Consumer;

public class CategoryActivity extends AppCompatActivity {
    LatestRecyclerAdapter latestRecyclerAdapter;
    RecyclerView recyclerView;
    ArrayList<Categories> categories=new ArrayList<>();
    private final String TAG = "CategoryActivity";
    TextView description;
    ArrayList<LatestDishes>latestDishesList;
    StaggeredGridLayoutManager sGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getDescription();
        initViews();
        description=findViewById(R.id.description);


        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        latestRecyclerAdapter = new LatestRecyclerAdapter(latestDishesList, this);


    }

    private void getDescription() {
        String name = getIntent().getStringExtra("strCategory");

        StringRequest request = new StringRequest(0, "https://www.themealdb.com/api/json/v1/1/categories.php"+name, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.e(TAG, "Response of Fragment Description ");

                    JSONObject jsonObject = new JSONObject(response);
                    String descriptionCat = jsonObject.getString("strCategoryDescription");
                    Log.e(TAG, "onResponse" + "strCategoryDescription: " + descriptionCat );

                    description.setText(descriptionCat);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG,"Catch ...");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void initViews() {
        String name = getIntent().getStringExtra("strMeal");

        StringRequest request = new StringRequest(0, "www.themealdb.com/api/json/v1/1/filter.php?c"+name, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e(TAG, "Response of Fragment initView ");

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray latestDish = jsonObject.getJSONArray("meals");
                    for (int i = 0; i < latestDish.length(); i++) {
                        JSONObject latestD = latestDish.getJSONObject(i);

                        latestDishesList.add(new LatestDishes(latestD.getString("idMeal")
                                , latestD.getString("strMeal")
                                , latestD.getString("strMealThumb")));

                        Log.e(TAG, "onResponse" + latestDishesList.get(i).getStrMeal() + latestDishesList.get(i).getStrMealThumb());

                        recyclerView.setAdapter(latestRecyclerAdapter);

                        latestRecyclerAdapter.setOnClick(new Consumer<LatestDishes>() {
                            @Override
                            public void accept(LatestDishes dishes) {
                                Intent intent = new Intent(CategoryActivity.this, DetailsActivity.class);
                                intent.putExtra("idMeal", dishes.getIdMeal());
                                startActivity(intent);
                            }
                        });


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }
}