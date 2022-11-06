package com.example.foodrecipebrand.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodrecipebrand.DetailsActivity;
import com.example.foodrecipebrand.HomeActivity;
import com.example.foodrecipebrand.R;
import com.example.foodrecipebrand.adapter.LatestRecyclerAdapter;
import com.example.foodrecipebrand.adapter.MealsOfCategoryRAdapter;
import com.example.foodrecipebrand.models.LatestDishes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;


public class CategoriesFragment extends Fragment {


    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }
    ArrayList<LatestDishes> mealsList;
    MealsOfCategoryRAdapter mealsOfCategoryRAdapter;
    RecyclerView mealRecycler;
    TextView description;
    ArrayList<LatestDishes>latestDishesList;
    StaggeredGridLayoutManager sGridLayoutManager;
    private static final String TAG = "CategoriesFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        mealRecycler=view.findViewById(R.id.dishesNameRv);
        description=view.findViewById(R.id.descriptionTv);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        getDescription();
    }
    private void getDescription() {
        StringRequest request = new StringRequest(0, "https://www.themealdb.com/api/json/v1/1/categories.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e(TAG,"Response of Fragment Description ");
                    JSONObject jsonObject = new JSONObject(response);
                    String categoryDiscrip = jsonObject.getString("strCategoryDescription");
                    description.setText(categoryDiscrip);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void initViews(View view) {
        StringRequest request=new StringRequest(0,"https://www.themealdb.com/api/json/v2/9973533/latest.php"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e(TAG,"Response of Fragment initView ");
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray latestDish = jsonObject.getJSONArray("meals");
                    for (int i =0 ;i< latestDish.length();i++){
                        JSONObject latestD = latestDish.getJSONObject(i);
                        latestDishesList.add(new LatestDishes(
                                latestD.getString("idMeal")
                                , latestD.getString("strMeal")
                                , latestD.getString("strMealThumb")));

                        Log.e(TAG, "onResponse" + latestDishesList.get(i).getStrMeal() + latestDishesList.get(i).getStrMealThumb());
                        mealRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
                        mealRecycler.setAdapter(mealsOfCategoryRAdapter);
                        mealsOfCategoryRAdapter = new MealsOfCategoryRAdapter(latestDishesList);
                        mealsOfCategoryRAdapter.setOnClick(new Consumer<LatestDishes>() {
                            @Override
                            public void accept(LatestDishes dishes) {
                                Intent intent = new Intent(getActivity(), DetailsActivity.class);
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
        RequestQueue queue= Volley.newRequestQueue(requireActivity());
        queue.add(request);

    }
}