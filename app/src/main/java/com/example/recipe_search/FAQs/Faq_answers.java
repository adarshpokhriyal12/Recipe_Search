package com.example.recipe_search.FAQs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipe_search.R;

import java.util.List;

public class Faq_answers extends AppCompatActivity {

    TextView tv;
    Animation anim;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_answers);
        Intent i = getIntent();
        String ans = i.getStringExtra("answer");
        tv = findViewById(R.id.answer);
        tv.setText(ans);

        anim = AnimationUtils.loadAnimation(this,R.anim.img_drop_down);
        iv = findViewById(R.id.faq_img);

        iv.setAnimation(anim);

    }
}