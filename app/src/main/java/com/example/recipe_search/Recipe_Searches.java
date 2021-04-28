package com.example.recipe_search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_search.CONTACT_US.Contact_Us;
import com.example.recipe_search.FAQs.faqs_list;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Recipe_Searches extends AppCompatActivity {
    // Recycler View
    RecyclerView RecipeRecyclerView;
    RecipeAdapter recipeAdapter;
    List<RecipeData> mData;


    private  RecipeAdapter.RecyclerViewClickListener listener;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe__searches);

        // Action Bar
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);




        // on item click of Recipe

        setOnClickListener();

        // Recycler view

        RecipeRecyclerView = findViewById(R.id.recipe_rv);
        mData = new ArrayList<>();

        mData.add(new RecipeData("Sweet Honey French Bread","It is very tasty.It is very tasty.It is very tasty.It is very tasty","2",R.drawable.categories));

        mData.add(new RecipeData("Southwestern Beef Brisket","It is very tasty.It is very tasty.It is very tasty.It is very tasty","2",R.drawable.categories));

        mData.add(new RecipeData("Crushed Heirloom Potatoes","It is very tasty.It is very tasty.It is very tasty.It is very tasty","2",R.drawable.categories));

        mData.add(new RecipeData("Heirloom Tomato Salad With Goat Cheese and Arugula","It is very tasty.It is very tasty.It is very tasty.It is very tasty","2",R.drawable.categories));

        mData.add(new RecipeData("Heirloom Tomato Sandwich With Basil Mayo","It is very tasty.It is very tasty.It is very tasty.It is very tasty","2",R.drawable.categories));

        mData.add(new RecipeData("Heirloom Apple Pie","It is very tasty.It is very tasty.It is very tasty.It is very tasty","2",R.drawable.categories));

        mData.add(new RecipeData("Family Heirloom Pumpkin Chiffon Pie","It is very tasty.It is very tasty.It is very tasty.It is very tasty","2",R.drawable.categories));

        mData.add(new RecipeData("Creole \"style\" Chicken & Sausage Jambalaya","It is very tasty.It is very tasty.It is very tasty.It is very tasty","2",R.drawable.categories));

        mData.add(new RecipeData("Chicken, Wild Rice and Green Bean Soup","It is very tasty.It is very tasty.It is very tasty.It is very tasty","2",R.drawable.categories));

        mData.add(new RecipeData("My Mom's Cranberry Sauce","It is very tasty.It is very tasty.It is very tasty.It is very tasty","2",R.drawable.categories));

        mData.add(new RecipeData("My Mom's Cranberry Sauce","It is very tasty.It is very tasty.It is very tasty.It is very tasty","2",R.drawable.categories));

        mData.add(new RecipeData("Chicken and Yellow Rice Soup","It is very tasty.It is very tasty.It is very tasty.It is very tasty","2",R.drawable.categories));

        mData.add(new RecipeData("Heirloom Tomato Panzanella","It is very tasty.It is very tasty.It is very tasty.It is very tasty","2",R.drawable.categories));


        // Adapter


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);

        RecipeRecyclerView.setLayoutManager(gridLayoutManager);
        RecipeRecyclerView.setHasFixedSize(true);
        recipeAdapter = new RecipeAdapter(this,mData,listener);
        RecipeRecyclerView.setAdapter(recipeAdapter);
        //RecipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Bottom Navigation View
        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.search);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        Intent i = new Intent(Recipe_Searches.this,MainActivity.class);
                        Toast.makeText(Recipe_Searches.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        return true;

                    case R.id.how_to_use:
                        //startActivity(new Intent(getApplicationContext(),Search.class));
                        //overridePendingTransition(0,0);
                        //finish();
                        Toast.makeText(Recipe_Searches.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.faqs:
                        //startActivity(new Intent(getApplicationContext(),Stats.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        Toast.makeText(Recipe_Searches.this, "FAQs Selected", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), faqs_list.class));
                        return true;


                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(), Contact_Us.class));
                        //startActivity(new Intent(getApplicationContext(),ContactUs.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        Toast.makeText(Recipe_Searches.this, "Contact Us Selected", Toast.LENGTH_SHORT).show();

                        return true;


                }
                return false;
            }
        });

    }
    public void showPop(View v) {
        TextView tvclose;
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.popup);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.getAttributes().windowAnimations = R.style.DialogAnimation;

        tvclose = (TextView) dialog.findViewById(R.id.tvclose);

        tvclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    private void setOnClickListener() {
        listener = new RecipeAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(Recipe_Searches.this,Recipe_Details.class);
                //intent.putExtra("rollno",list.get(position).getRoll());
                startActivity(intent);

            }

        };
    }
}