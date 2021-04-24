package com.example.recipe_search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

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

    }
}