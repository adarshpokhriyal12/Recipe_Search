package com.example.recipe_search.CONTACT_US;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipe_search.R;

import org.w3c.dom.Text;

public class Contact_Us extends AppCompatActivity {
    TextView tv;
    ImageView img;
    Animation anim;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);

        tv = findViewById(R.id.answer);
        img = findViewById(R.id.contact_img);
        anim = AnimationUtils.loadAnimation(this,R.anim.img_drop_down);
        img.setAnimation(anim);
        btn = (Button)findViewById(R.id.location);
        String st = "Dr. Ganesh Bagler\n" +
                "Center for Computational Biology\n\n" +
                "Indraprastha Institute of Information Technology Delhi (IIIT Delhi),\n" +
                "R&D Block,\n" +
                "Okhla Phase III, Near Govindpuri Metro Station,\n" +
                "New Delhi, India 110020.\n\n" +
                "Email: bagler+RecipeDB@iiitd.ac.in\n" +
                "Tel: +91-11-26907-443 (Work)";
        tv.setText(st);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("geo:28.543953755237048, 77.27170497668655?z=21& q=Center for Computational Biology, IIIT-Delhi R&D Building , Okhla Phase III ,New Delhi ");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW,uri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });
    }
}