package com.example.foodrecipebrand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodrecipebrand.adapter.CategoriesHomeRAdapter;
import com.example.foodrecipebrand.adapter.LatestRecyclerAdapter;
import com.example.foodrecipebrand.fragment.CategoriesFragment;
import com.example.foodrecipebrand.models.Categories;
import com.example.foodrecipebrand.models.LatestDishes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class HomeActivity extends AppCompatActivity {
   private RecyclerView latestRecyclerV;
   private RecyclerView categoryRecyclerV;

   private ArrayList<Categories> categoriesArrayList;
   public static ArrayList<LatestDishes> latestDishesList;

   private LatestRecyclerAdapter latestRecyclerAdapter;
   private CategoriesHomeRAdapter categoriesHomeRAdapter;

   private EditText search;
    private final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        search=findViewById(R.id.searchEd);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });


        latestRecyclerV = findViewById(R.id.latest_dishes_RV);
        latestDishesList = new ArrayList<>();
        categoryRecyclerV = findViewById(R.id.categories_RV);
        categoriesArrayList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
        latestRecyclerV.setLayoutManager(layoutManager);
        latestRecyclerAdapter = new LatestRecyclerAdapter(latestDishesList, HomeActivity.this);
        fetchCategories();
        fetchLatestDishes();
    }

    private void fetchLatestDishes() {

        StringRequest request = new StringRequest(0, "https://www.themealdb.com/api/json/v2/9973533/latest.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "reach onResponse()!!!!!!!!!");
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray latestDish = jsonObject.getJSONArray("meals");
                    for (int i = 0; i < latestDish.length(); i++) {
                        Log.e(TAG, "Looop!!!!!!!!!");
                        JSONObject latestD = latestDish.getJSONObject(i);
                        latestDishesList.add(new LatestDishes(latestD.getString("idMeal"), latestD.getString("strMeal"), latestD.getString("strMealThumb")));
                        Log.e(TAG, "onResponse" + latestD.getString("strMeal"));
                        latestRecyclerV.setAdapter(latestRecyclerAdapter);

                    }
                    Log.e(TAG, "List : " + latestDishesList.get(0).getStrMeal());


                    latestRecyclerAdapter.setOnClick(new Consumer<LatestDishes>() {
                        @Override
                        public void accept(LatestDishes dishes) {
                            Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                            intent.putExtra("idMeal", dishes.getIdMeal());
                            startActivity(intent);
                        }
                    });
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

    private void fetchCategories() {
        StringRequest request = new StringRequest(0, "https://www.themealdb.com/api/json/v1/1/categories.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "reach onResponse()!!!!!!!!!");
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray categories = jsonObject.getJSONArray("categories");
                    Log.e(TAG, "reach the array!!!!!!!!!");
                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject categoryH = categories.getJSONObject(i);
                        Log.e(TAG, "inside the array!!!!!!!!!");
                        categoriesArrayList.add(new Categories(categoryH.getString("idCategory")
                                , categoryH.getString("strCategory"), categoryH.getString("strCategoryThumb"))

                        );
                        Log.e(TAG, "onResponse: " + categoriesArrayList.get(i).getStrCategory());
                        GridLayoutManager manager = new GridLayoutManager(HomeActivity.this, 3, GridLayoutManager.VERTICAL, false);
                        categoryRecyclerV.setLayoutManager(manager);
                        categoryRecyclerV.setAdapter(categoriesHomeRAdapter);
                        categoriesHomeRAdapter = new CategoriesHomeRAdapter(HomeActivity.this, categoriesArrayList);

//                        On Click to Category Activity
                        categoriesHomeRAdapter.setOnClick(new Consumer<Categories>() {
                            @Override
                            public void accept(Categories categories1) {
                                Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
                                intent.putExtra("strCategory", categories1.getStrCategory());
                                startActivity(intent);
                            }
                        });


                    }
                } catch (JSONException e) {
                    Log.e(TAG, "onResponse: " + e.getLocalizedMessage() + "catch of fetchCategories()");

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: " + error.getMessage());

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }

}