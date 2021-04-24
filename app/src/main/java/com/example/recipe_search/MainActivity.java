package com.example.recipe_search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_search.CONTACT_US.Contact_Us;
import com.example.recipe_search.FAQs.faqs_list;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Animation cv1Anim, cv2Anim, cv3Anim, cv4Anim,fadeAnim, popupAnim;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private Dialog dialog;
    ImageView iv;
    CardView cv1,cv2,cv3,cv4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Action Bar
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //Animations
        cv1Anim = AnimationUtils.loadAnimation(this,R.anim.cv1_anim);
        cv2Anim = AnimationUtils.loadAnimation(this,R.anim.cv2_anim);
        cv3Anim = AnimationUtils.loadAnimation(this,R.anim.cv3_anim);
        cv4Anim = AnimationUtils.loadAnimation(this,R.anim.cv4_anim);
        fadeAnim = AnimationUtils.loadAnimation(this,R.anim.fade_img);
        popupAnim = AnimationUtils.loadAnimation(this,R.anim.popup_anim);

        cv1 = findViewById(R.id.cv1);
        cv2 = findViewById(R.id.cv2);
        cv3 = findViewById(R.id.cv3);
        cv4 = findViewById(R.id.cv4);

        cv1.setAnimation(cv1Anim);
        cv2.setAnimation(cv2Anim);
        cv3.setAnimation(cv3Anim);
        cv4.setAnimation(cv4Anim);

        iv = findViewById(R.id.bg_fade);

        iv.animate().alpha(0).setDuration(1000);

        // Bottom Navigation View
        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.search);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        Toast.makeText(MainActivity.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.how_to_use:
                        //startActivity(new Intent(getApplicationContext(),Search.class));
                        //overridePendingTransition(0,0);
                        //finish();
                        Toast.makeText(MainActivity.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.faqs:
                        //overridePendingTransition(0,0);
                        //finish();

                        Toast.makeText(MainActivity.this, "FAQs Selected", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(), faqs_list.class));
                        return true;


                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(), Contact_Us.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        Toast.makeText(MainActivity.this, "Contact Us Selected", Toast.LENGTH_SHORT).show();

                        return true;


                }
                return false;
            }
        });


    }


    // Pop Up : Recipe of the day
    public void showPop(View v) {

        TextView tvclose;

        // Pop Up : Recipe of the Day
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

    // cuisine search
    public void cuisineSearch(View view) {
        Intent i = new Intent(this,Cuisine_Search.class);
        startActivity(i);
    }


    // category search
    public void categorySearch(View view) {
        Intent i = new Intent(this,Category_Search.class);
        startActivity(i);
    }

    // ingredient search
    public void ingredientSearch(View view) {
        Intent i = new Intent(this,Ingredient_Search.class);
        startActivity(i);
    }

    // nutrition search
    public void nutritionSearch(View view) {
        Intent i = new Intent(this,Nutrition_Search.class);
        startActivity(i);

    }
}