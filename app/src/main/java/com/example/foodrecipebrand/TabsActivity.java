package com.example.foodrecipebrand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodrecipebrand.adapter.ViewPagerAdapter;
import com.example.foodrecipebrand.fragment.CategoriesFragment;
import com.example.foodrecipebrand.models.Categories;
import com.example.foodrecipebrand.models.LatestDishes;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TabsActivity extends AppCompatActivity {

//    private TabLayout tabLayout;
    private ViewPager2 viewPager;


//    ArrayList<Categories> categories;
//    ArrayList<Categories> tabsName = new ArrayList<Categories>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

//        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);

        ArrayList<Fragment>fragments=new ArrayList<>();

//        tabNames();

        CategoriesFragment categoriesFragment=CategoriesFragment.newInstance();
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(this,fragments);

        // set the adapter
        viewPager.setAdapter(pagerAdapter);




    }

//    private void tabNames() {
//        StringRequest request = new StringRequest(0,
//                "https://www.themealdb.com/api/json/v1/1/categories.php", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray tabs = jsonObject.getJSONArray("categories");
//                    for (int i = 0; i < tabs.length(); i++){
//                        JSONObject tabN = tabs.getJSONObject(i);
//                        categories.add(new Categories(tabN.getString("idCategory")
//                                , tabN.getString("strCategory")));
////                        tabLayout.addTab(tabLayout.newTab().setText("Page:" + i));
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//    }


}