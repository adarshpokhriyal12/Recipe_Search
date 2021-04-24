package com.example.recipe_search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash_screen extends AppCompatActivity {
    Animation leftAnim, rightAnim;
    ImageView iv;
    TextView tv;
    private static int SPLASH_SCREEN = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // hide action bar
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);

        //Animations
        leftAnim = AnimationUtils.loadAnimation(this,R.anim.left_anim);

        rightAnim = AnimationUtils.loadAnimation(this,R.anim.right_anim);

        iv = findViewById(R.id.imageView);
        tv = findViewById(R.id.textView);

        iv.setAnimation(leftAnim);
        tv.setAnimation(rightAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =new Intent(splash_screen.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },SPLASH_SCREEN);

    }
}