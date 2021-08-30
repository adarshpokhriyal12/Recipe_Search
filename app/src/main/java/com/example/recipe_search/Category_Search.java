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
import com.example.recipe_search.CONTACT_US.Contact_Us;
import com.example.recipe_search.FAQs.faqs_list;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Category_Search extends AppCompatActivity {
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private Dialog dialog;
    ImageView img;
    TextView welcome;
    Button submit;
    EditText used,notused;
    LinearLayout linearLayout;

    Animation anim1,anim2;
    //Token
    String url = "https://cosylab.iiitd.edu.in/api/auth/realms/bootadmin/protocol/openid-connect/token";
    String uname = "nitika";
    String pass = "nitika_cosylab";
    String client_id = "app-ims";
    String grant_type = "password";
    String scope = "openid";
    String catUsed="",catNotUsed="";
    String access_token ="",refresh_token="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category__search);

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

        used = findViewById(R.id.Used);
        notused = findViewById(R.id.notUsed);
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
                    Toast.makeText(Category_Search.this,error.toString()+" redirect to "+location,Toast.LENGTH_SHORT).show();
                    //Toast.makeText(Ingredient_Search.this, "Token " + refresh_token, Toast.LENGTH_SHORT).show();
                    //tv3.setText(refresh_token);
                }
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
                        Intent i = new Intent(Category_Search.this,MainActivity.class);
                        Toast.makeText(Category_Search.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        return true;

                    case R.id.how_to_use:
                        //startActivity(new Intent(getApplicationContext(),Search.class));
                        //overridePendingTransition(0,0);
                        //finish();
                        Toast.makeText(Category_Search.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.faqs:
                        //startActivity(new Intent(getApplicationContext(),Stats.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        Toast.makeText(Category_Search.this, "FAQs Selected", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), faqs_list.class));
                        return true;


                    case R.id.contact:
                        //startActivity(new Intent(getApplicationContext(),ContactUs.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        Toast.makeText(Category_Search.this, "Contact Us Selected", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Contact_Us.class));

                        return true;


                }
                return false;
            }
        });

    }

    public void categorySearchSubmit(View view) {
        catUsed = used.getText().toString();
        catNotUsed = notused.getText().toString();
        //Toast.makeText(Ingredient_Search.this,"ing :"+ ingUsed +" " + ingNotUsed,Toast.LENGTH_SHORT).show();
        if(access_token == null)
            Toast.makeText(Category_Search.this,"expire",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,Recipe_Searches.class);
        getRecipeInfo(access_token,catUsed,catNotUsed,i);
        //Toast.makeText(Ingredient_Search.this,"response :"+ recs.toString(),Toast.LENGTH_SHORT).show();

    }

    private void getRecipeInfo(String access_token, String u,String nu,Intent i) {
        RequestQueue r = Volley.newRequestQueue(Category_Search.this);
        String recipe_url = "";
        if(u.equals("") && nu.equals(""))
            Toast.makeText(Category_Search.this,"Please Enter Something",Toast.LENGTH_SHORT).show();
        else if(!u.equals("") && nu.equals(""))
            recipe_url = "https://cosylab.iiitd.edu.in/api/recipeDB/searchrecipe?categoryUsed="+u;
        else if(!nu.equals("") && u.equals(""))
            recipe_url = "https://cosylab.iiitd.edu.in/api/recipeDB/searchrecipe?categoryNotUsed="+nu;
        else
            recipe_url = "https://cosylab.iiitd.edu.in/api/recipeDB/searchrecipe?categoryNotUsed="+nu+"&categoryUsed="+u;
        StringRequest sr = new StringRequest(Request.Method.GET, recipe_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray recs = new JSONArray(response);
                    Toast.makeText(Category_Search.this,"response :"+ recs.toString(),Toast.LENGTH_SHORT).show();
                    Bundle b = new Bundle();
                    b.putString("Array",recs.toString());
                    i.putExtras(b);
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Category_Search.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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
        TextView tvclose;
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