package com.example.recipe_search.FAQs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.recipe_search.CONTACT_US.Contact_Us;
import com.example.recipe_search.MainActivity;
import com.example.recipe_search.R;
import com.example.recipe_search.Recipe_Details;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class Faq_answers extends AppCompatActivity {

    TextView tv;
    Animation anim,anim2;
    ScrollView sv;
    ImageView iv;


    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_answers);

        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        Intent i = getIntent();
        String ans = i.getStringExtra("answer");
        tv = findViewById(R.id.answer);
        sv = findViewById(R.id.scrollview);
        tv.setText(ans);

        anim = AnimationUtils.loadAnimation(this,R.anim.img_drop_down);
        iv = findViewById(R.id.faq_img);
        anim2 = AnimationUtils.loadAnimation(this,R.anim.text_slide_up);
        sv.setAnimation(anim2);

        iv.setAnimation(anim);

        // Bottom Navigation View
        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.faqs);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        //Toast.makeText(MainActivity.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.how_to_use:
                        // startActivity(new Intent(getApplicationContext(),.class));
                        //overridePendingTransition(0,0);
                        //finish();
                        //Toast.makeText(.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.faqs:
                        startActivity(new Intent(getApplicationContext(),faqs_list.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        //Toast.makeText(MainActivity.this, "FAQs Selected", Toast.LENGTH_SHORT).show();

                        //startActivity(new Intent(getApplicationContext(), faqs_list.class));
                        return true;


                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(), Contact_Us.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        //Toast.makeText(MainActivity.this, "Contact Us Selected", Toast.LENGTH_SHORT).show();

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
        TextView name;
        name = (TextView) dialog.findViewById(R.id.recipe_name);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Faq_answers.this, Recipe_Details.class);
                startActivity(i);
            }
        });

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
}