package com.example.recipe_search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_search.CONTACT_US.Contact_Us;
import com.example.recipe_search.FAQs.faqs_list;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Recipe_process extends AppCompatActivity {
    TextView tv;
    ImageView iv;
    Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_process);
        tv = findViewById(R.id.process);
        iv = findViewById(R.id.process_img);
        anim = AnimationUtils.loadAnimation(this,R.anim.img_drop_down);

        iv.setAnimation(anim);
        String s = "Early Stage\n" +
                "Add\n\n" +
                "Middle Stage Processes\n" +
                "Drizzle\n\n" +
                "Late Stage\n" +
                "No Late Stage Cooking Processes Found";

        tv.setText(s);
        // Bottom Navigation View
        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.search);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        Intent i = new Intent(Recipe_process.this,MainActivity.class);
                        //Toast.makeText(Category_Search.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        return true;

                    case R.id.how_to_use:
                        //startActivity(new Intent(getApplicationContext(),Search.class));
                        //overridePendingTransition(0,0);
                        //finish();
                        //Toast.makeText(Category_Search.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.faqs:
                        //startActivity(new Intent(getApplicationContext(),Stats.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        //Toast.makeText(Category_Search.this, "FAQs Selected", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), faqs_list.class));
                        return true;


                    case R.id.contact:
                        //startActivity(new Intent(getApplicationContext(),ContactUs.class));
                        //overridePendingTransition(0,0);
                        //finish();

                       // Toast.makeText(Category_Search.this, "Contact Us Selected", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Contact_Us.class));

                        return true;


                }
                return false;
            }
        });

    }
}