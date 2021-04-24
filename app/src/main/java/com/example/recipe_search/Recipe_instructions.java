package com.example.recipe_search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Recipe_instructions extends AppCompatActivity {
    ImageView img;
    TextView tv;

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

    }
}