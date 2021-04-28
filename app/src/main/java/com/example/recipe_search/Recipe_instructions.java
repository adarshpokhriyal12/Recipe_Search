package com.example.recipe_search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.recipe_search.CONTACT_US.Contact_Us;
import com.example.recipe_search.FAQs.faqs_list;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Recipe_instructions extends AppCompatActivity {
    ImageView img;
    TextView tv;

    private Dialog dialog;
    Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_instructions);

        anim = AnimationUtils.loadAnimation(this,R.anim.img_drop_down);

        img = findViewById(R.id.inst_img);
        tv = findViewById(R.id.inst);

        img.setAnimation(anim);

        String s = "1. Thinly slice tomatoes and arrange on salad plates in an overlapping fashion forming a ring alternating colors as you go.\n" +
                "\n" +
                "2. Season tomatoes with salt and pepper and drizzle lightly with some of the olive oil and balsamic reserve some for the arugula.\n" +
                "\n" +
                "3. Press wheels of goat cheese into the chopped pistachios covering both sides.\n" +
                "\n" +
                "4. Place arugula into a small bowl and toss gently with olive oil juice of the lemon balsamic salt and pepper.\n" +
                "\n" +
                "5. Place approximately one cup of the arugula salad into the center of the tomatoes.\n" +
                "\n" +
                "6. Top arugula with pistachio goat cheese.\n" +
                "\n" +
                "7. Garnish with one half cherry tomato if desired.";

        tv.setText(s);
        // Bottom Navigation View
        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.search);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        Intent i = new Intent(Recipe_instructions.this,MainActivity.class);
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
}