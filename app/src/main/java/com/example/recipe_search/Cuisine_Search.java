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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Cuisine_Search extends AppCompatActivity {
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private Dialog dialog;
    ImageView img;
    TextView welcome;
    Button submit;
    EditText reg,count,title;
    LinearLayout linearLayout;

    Animation anim1,anim2;
    // Recipe Of The Day
    String title_rd;
    TextView tvclose;
    TextView recipe_title;
    ImageView recipe_img;
    JSONObject obj_rpd;
    String recipe_url_rpd = "https://cosylab.iiitd.edu.in/api/recipeDB/recipeoftheday";
    //Token
    String url = "https://cosylab.iiitd.edu.in/api/auth/realms/bootadmin/protocol/openid-connect/token";
    String uname = "nitika";
    String pass = "nitika_cosylab";
    String client_id = "app-ims";
    String grant_type = "password";
    String scope = "openid";
    String region="",country="",r_title="";
    String access_token ="",refresh_token="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine__search);

        // Action Bar
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        anim1 = AnimationUtils.loadAnimation(this,R.anim.img_drop_down);
        anim2 = AnimationUtils.loadAnimation(this,R.anim.text_slide_up);

        img = findViewById(R.id.search_img);
        welcome = findViewById(R.id.welcome);
        submit = findViewById(R.id.submit);
        linearLayout = findViewById(R.id.form);
        img.setAnimation(anim1);
        welcome.setAnimation(anim2);
        submit.setAnimation(anim2);
        linearLayout.setAnimation(anim2);
        reg = findViewById(R.id.region);
        count = findViewById(R.id.country);
        title = findViewById(R.id.recipe_title);

        //Token
        RequestQueue rq = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject obj = new JSONObject(response);
                    access_token = obj.getString("access_token");
                    refresh_token = obj.getString("refresh_token");
                    //getRecipeInfo(access_token);
                    //Toast.makeText(Ingredient_Search.this,"Access Token generated " +access_token ,Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(access_token.isEmpty()) {
                    String location = error.networkResponse.headers.get("Location");
                    Toast.makeText(Cuisine_Search.this,error.toString()+" redirect to "+location,Toast.LENGTH_SHORT).show();
                    //Toast.makeText(Ingredient_Search.this, "Token " + refresh_token, Toast.LENGTH_SHORT).show();
                    //tv3.setText(refresh_token);
                }
                //Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
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
        // Bottom Navigation View
        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.search);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        Intent i = new Intent(Cuisine_Search.this,MainActivity.class);
                        Toast.makeText(Cuisine_Search.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        return true;

                    case R.id.how_to_use:
                        //startActivity(new Intent(getApplicationContext(),Search.class));
                        //overridePendingTransition(0,0);
                        //finish();
                        Toast.makeText(Cuisine_Search.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.faqs:
                        //startActivity(new Intent(getApplicationContext(),Stats.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        Toast.makeText(Cuisine_Search.this, "FAQs Selected", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), faqs_list.class));
                        return true;


                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(), Contact_Us.class));
                        //startActivity(new Intent(getApplicationContext(),ContactUs.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        Toast.makeText(Cuisine_Search.this, "Contact Us Selected", Toast.LENGTH_SHORT).show();

                        return true;


                }
                return false;
            }
        });

    }

    public void cuisineSearchSubmit(View view) {
        region = reg.getText().toString();
        country = count.getText().toString();
        r_title = title.getText().toString();
        //Toast.makeText(Ingredient_Search.this,"ing :"+ ingUsed +" " + ingNotUsed,Toast.LENGTH_SHORT).show();
        if (access_token == null)
            Toast.makeText(Cuisine_Search.this, "expire", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, Recipe_Searches.class);
        getRecipeInfo(access_token, region, country,r_title, i);
    }

    private void getRecipeInfo(String access_token, String region,String country,String r_title,Intent i) {
        RequestQueue r = Volley.newRequestQueue(Cuisine_Search.this);
        //Toast.makeText(this,"In getRecipeInfo() : "+u+" "+nu,Toast.LENGTH_SHORT).show();
        String recipe_url = "";
        if(region.equals(""))
        {
            if(country.equals("") && r_title.equals(""))
                Toast.makeText(Cuisine_Search.this,"Please Enter Something",Toast.LENGTH_SHORT).show();
            else if(country.equals("") && !r_title.equals(""))
                recipe_url = "https://cosylab.iiitd.edu.in/api/recipeDB/searchrecipe?recipeTitle="+r_title;
            else if(!country.equals("") && r_title.equals(""))
                recipe_url = "https://cosylab.iiitd.edu.in/api/recipeDB/searchrecipe?country="+country;
            else
                recipe_url = "https://cosylab.iiitd.edu.in/api/recipeDB/searchrecipe?country="+country+"recipeTitle="+r_title;
        }
        else
        {
            if(country.equals("") && r_title.equals(""))
                recipe_url = "https://cosylab.iiitd.edu.in/api/recipeDB/searchrecipe?region="+region;
            else if(country.equals("") && !r_title.equals(""))
                recipe_url = "https://cosylab.iiitd.edu.in/api/recipeDB/searchrecipe?region="+region+"recipeTitle="+r_title;
            else if(!country.equals("") && r_title.equals(""))
                recipe_url = "https://cosylab.iiitd.edu.in/api/recipeDB/searchrecipe?region="+region+"country="+country;
            else
                recipe_url = "https://cosylab.iiitd.edu.in/api/recipeDB/searchrecipe?region="+region+"country="+country+"recipeTitle="+r_title;
        }
        StringRequest sr = new StringRequest(Request.Method.GET, recipe_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray recs = new JSONArray(response);
                    if(recs.length()==0)
                        Toast.makeText(Cuisine_Search.this,"No Match",Toast.LENGTH_SHORT).show();
                    //Toast.makeText(Cuisine_Search.this,"response :"+ recs.toString(),Toast.LENGTH_SHORT).show();
                    else{
                        Bundle b = new Bundle();
                    b.putString("Array",recs.toString());
                    i.putExtras(b);
                    startActivity(i);}
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Cuisine_Search.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Authorization"," Bearer "+access_token);
                //Toast.makeText(MainActivity.this,"Authorization :"+params.get("Authorization"),Toast.LENGTH_SHORT).show();
//              new Handler(Looper.getMainLooper()).post(new Runnable() {
//                  @Override
//                  public void run() {
//                      Toast toast = Toast.makeText(MainActivity.this, "Authorization :"+params.get("Authorization"), Toast.LENGTH_SHORT);
//                      toast.show();
//                  }
//              });
                return params;
            }
        };
        r.add(sr);
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
        Intent i =  new Intent(Cuisine_Search.this,Recipe_Details.class);
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
                Toast.makeText(Cuisine_Search.this,error.toString(),Toast.LENGTH_SHORT).show();

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
        RequestQueue r = Volley.newRequestQueue(Cuisine_Search.this);
        StringRequest sr = new StringRequest(Request.Method.GET, recipe_url_rpd, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    obj_rpd = new JSONObject(response);
                    title_rd = obj_rpd.getString("recipe_title").toString();
                    recipe_title.setText(title_rd);
                    Glide.with(Cuisine_Search.this)
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
                Toast.makeText(Cuisine_Search.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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