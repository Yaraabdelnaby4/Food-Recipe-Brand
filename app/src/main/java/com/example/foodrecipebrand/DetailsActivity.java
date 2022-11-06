package com.example.foodrecipebrand;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.foodrecipebrand.models.LatestDishes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    ArrayList<LatestDishes> arrayList;
    ImageView mealThumb;
    TextView meal,category, country, instructions, ingredients, measures;
  //  CardView youtube, source;
    TextView youtube, source;
    private static final String TAG = "DetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mealThumb=findViewById(R.id.mealIm);
        meal=findViewById(R.id.mealName);
        category=findViewById(R.id.categoryTypeTv);
        country=findViewById(R.id.countryTypeTv);
        instructions=findViewById(R.id.instructionsTv);
        ingredients=findViewById(R.id.ingredientTv);
        measures=findViewById(R.id.measureTv);
        youtube=findViewById(R.id.text_youtube);
        source=findViewById(R.id.text_source);


        String idMeal = getIntent().getStringExtra("idMeal");



        StringRequest request = new StringRequest(0
                , "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + idMeal,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)  {
                        try {
                            Log.e(TAG,"Response Done ....");
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array= jsonObject.getJSONArray("meals");
                            JSONObject object= array.getJSONObject(0);
//                            Get Data
                            String imageM = object.getString("strMealThumb");
                            String name = object.getString("strMeal");
                            String categoryM = object.getString("strCategory");
                            String countryM = object.getString("strArea");
                            String instructionsM = object.getString("strInstructions");

//                           Intent Youtube

                            String youTube=object.getString("strYoutube");
                            youtube.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.e(TAG,"Youtube");
                                    Intent youtubeIntent=new Intent(Intent.ACTION_VIEW);
                                    youtubeIntent.setData(Uri.parse(youTube));
                                    startActivity(youtubeIntent);

                                }
                            });

//                      Intent Source

                            String souRce=object.getString("strSource");
                            source.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.e(TAG,"Source");
                                    Intent youtubeIntent=new Intent(Intent.ACTION_VIEW);
                                    youtubeIntent.setData(Uri.parse(souRce));
                                    startActivity(youtubeIntent);


                                }
                            });
                            Log.e(TAG, "onResponse: " + "strMealThumb: " + mealThumb + " strMeal " + name + "strCategory "+ categoryM+"strArea " + countryM
                                    + "strInstructions " + instructionsM);
//                            Set Data

                            Glide.with(DetailsActivity.this).load(imageM).into(mealThumb);
                            meal.setText(name);
                            category.setText(categoryM);
                            country.setText(countryM);
                            instructions.setText(instructionsM);

//                            Get Ingredients
                            for (int i=1 ;i<=20;i++ ){
                                String ingredient = object.getString("strIngredient" + i);
                                String measure = object.getString("strMeasure" + i);
                                if (!ingredient.trim().equals("") && !ingredient.trim().equals("null")) ingredients.append("\n \u2022 " + ingredient);
                                if (!measure.trim().equals("") && !measure.trim().equals("null")) measures.append("\n - " + measure);
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG,"From catch ..." + e.getLocalizedMessage());
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"Response Fail ....");

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


    }

}