package com.example.recipe_search.CONTACT_US;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipe_search.R;

import org.w3c.dom.Text;

public class Contact_Us extends AppCompatActivity {
    TextView tv;
    ImageView img;
    Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);

        tv = findViewById(R.id.answer);
        img = findViewById(R.id.contact_img);
        anim = AnimationUtils.loadAnimation(this,R.anim.img_drop_down);
        img.setAnimation(anim);

        String st = "Dr. Ganesh Bagler\n" +
                "Center for Computational Biology\n\n" +
                "Indraprastha Institute of Information Technology Delhi (IIIT Delhi),\n" +
                "R&D Block,\n" +
                "Okhla Phase III, Near Govindpuri Metro Station,\n" +
                "New Delhi, India 110020.\n\n" +
                "Email: bagler+RecipeDB@iiitd.ac.in\n" +
                "Tel: +91-11-26907-443 (Work)";
        tv.setText(st);
    }
}