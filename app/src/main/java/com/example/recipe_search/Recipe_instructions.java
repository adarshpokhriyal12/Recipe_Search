package com.example.recipe_search;

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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recipe_search.CONTACT_US.Contact_Us;
import com.example.recipe_search.FAQs.faqs_list;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Recipe_instructions extends AppCompatActivity {
    ImageView img;
    TextView tv;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private Dialog dialog;
    Animation anim,anim2;

    ScrollView sv;

    // Recipe Of The Day
    String title;
    TextView tvclose;
    TextView recipe_title;
    ImageView recipe_img;
    JSONObject obj_rpd;
    String recipe_url_rpd = "https://cosylab.iiitd.edu.in/api/recipeDB/recipeoftheday";

    // Volley credentials
    String uname = "nitika";
    String pass = "nitika_cosylab";
    String client_id = "app-ims";
    String grant_type = "password";
    String scope = "openid";
    String access_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_instructions);

        // Action Bar
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        anim = AnimationUtils.loadAnimation(this,R.anim.img_drop_down);
        anim2 = AnimationUtils.loadAnimation(this,R.anim.text_slide_up);

        img = findViewById(R.id.inst_img);
        tv = findViewById(R.id.inst);
        sv = findViewById(R.id.scrollview);
        sv.setAnimation(anim2);
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

        getAccessToken();
        // Pop Up : Recipe of the Day

        dialog = new Dialog(this);

        dialog.setContentView(R.layout.popup);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.getAttributes().windowAnimations = R.style.DialogAnimation;

        tvclose = (TextView) dialog.findViewById(R.id.tvclose);
        recipe_title = (TextView)dialog.findViewById((R.id.recipe_title));
        recipe_img = (ImageView)dialog.findViewById(R.id.recipe_img);

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
    public void recipeOfDayCLick(View view) throws JSONException {
        Intent i =  new Intent(Recipe_instructions.this,Recipe_Details.class);
        i.putExtra("Object",obj_rpd.toString());
        startActivity(i);

    }

    // Recipe Of the Day : Volley
    private void getAccessToken() {
        RequestQueue rq;
        rq = Volley.newRequestQueue(this);
        String url = "https://cosylab.iiitd.edu.in/api/auth/realms/bootadmin/protocol/openid-connect/token";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject obj = new JSONObject(response);
                    //access_token = obj.getString("access_token");
                    getRecipeOfTheDay(obj.getString("access_token").toString());
                    //     Toast.makeText(MainActivity.this,"Access Token generated " + obj.getString("access_token").toString(),Toast.LENGTH_SHORT).show();
                    //tv.setText(obj.getString("access_token"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Recipe_instructions.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String,String> getParams()
            {
                Map<String,String> params = new HashMap<String,String>();
                params.put("username",uname);
                params.put("password",pass);
                params.put("grant_type",grant_type);
                params.put("client_id",client_id);
                params.put("scope",scope);
                return params;
            }
        };

        rq.add(stringRequest);

    }

    // Recipe Of The Day

    private void getRecipeOfTheDay(String token) {
        RequestQueue r = Volley.newRequestQueue(Recipe_instructions.this);
        StringRequest sr = new StringRequest(Request.Method.GET, recipe_url_rpd, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    obj_rpd = new JSONObject(response);
                    title = obj_rpd.getString("recipe_title").toString();
                    recipe_title.setText(title);
                    Glide.with(Recipe_instructions.this)
                            .load(obj_rpd.getString("img_url").toString())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(recipe_img);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Recipe_instructions.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Authorization"," Bearer "+token);

                return params;
            }
        };
        r.add(sr);
    }


}