package com.example.recipe_search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_search.CONTACT_US.Contact_Us;
import com.example.recipe_search.FAQs.faqs_list;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Recipe_ingredients extends AppCompatActivity {
    // Recycler View
    RecyclerView ingredientRecyclerView;
    IngredientsAdapter ingredientsAdapter;
    List<IngredientsData> mData;


    private  IngredientsAdapter.RecyclerViewClickListener listener;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_ingredients);

        // Action Bar
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        // on item click of Recipe

        setOnClickListener();

        // Recycler view

        ingredientRecyclerView = (RecyclerView) findViewById(R.id.recipe_ingr);
        mData = new ArrayList<>();


        mData.add(new IngredientsData("Water","3/4 cup",R.drawable.ingredients_main_activity));
        mData.add(new IngredientsData("Honey","2 teaspoons",R.drawable.ingredients_main_activity));
        mData.add(new IngredientsData("Olive Oil","2 teaspoons",R.drawable.ingredients_main_activity));
        mData.add(new IngredientsData("Salt","2/3 teaspoons",R.drawable.ingredients_main_activity));
        mData.add(new IngredientsData("White Sugar","2/3 teaspoons",R.drawable.ingredients_main_activity));
        mData.add(new IngredientsData("Bread Flour","2 cups",R.drawable.ingredients_main_activity));
        mData.add(new IngredientsData("Active Yeast","1.5 teaspoons",R.drawable.ingredients_main_activity));
        mData.add(new IngredientsData("Tomato","4",R.drawable.ingredients_main_activity));
        mData.add(new IngredientsData("Lemon","1",R.drawable.ingredients_main_activity));
        mData.add(new IngredientsData("Balsamic Vinegar","1-4 tablespoons",R.drawable.ingredients_main_activity));
        mData.add(new IngredientsData("Arugula","4 cups",R.drawable.ingredients_main_activity));
        mData.add(new IngredientsData("Extra Virgin Olive Oil","1/2 cup",R.drawable.ingredients_main_activity));
        mData.add(new IngredientsData("Pistachio","1/2 cup",R.drawable.ingredients_main_activity));
        // Adapter


        ingredientsAdapter = new IngredientsAdapter(this,mData,listener);
        ingredientRecyclerView.setAdapter(ingredientsAdapter);
        ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Bottom Navigation View
        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.search);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        Intent i = new Intent(Recipe_ingredients.this,MainActivity.class);
                        Toast.makeText(Recipe_ingredients.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        return true;

                    case R.id.how_to_use:
                        //startActivity(new Intent(getApplicationContext(),Search.class));
                        //overridePendingTransition(0,0);
                        //finish();
                        Toast.makeText(Recipe_ingredients.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.faqs:
                        //startActivity(new Intent(getApplicationContext(),Stats.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        Toast.makeText(Recipe_ingredients.this, "FAQs Selected", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), faqs_list.class));
                        return true;


                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(), Contact_Us.class));
                        //startActivity(new Intent(getApplicationContext(),ContactUs.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        Toast.makeText(Recipe_ingredients.this, "Contact Us Selected", Toast.LENGTH_SHORT).show();

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
        listener = new IngredientsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(Recipe_ingredients.this,IngredientDetails.class);
                //intent.putExtra("rollno",list.get(position).getRoll());
                startActivity(intent);

            }

        };
    }
}