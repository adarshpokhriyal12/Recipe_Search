package com.example.recipe_search.FAQs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
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
import com.example.recipe_search.MainActivity;
import com.example.recipe_search.R;
import com.example.recipe_search.RecipeAdapter;
import com.example.recipe_search.Recipe_Details;
import com.example.recipe_search.Recipe_Searches;
import com.example.recipe_search.recipe_utensils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class faqs_list extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String> question,answer;
    FaqAdapter adapter;

    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private Dialog dialog;
    // Recipe Of The Day
    String title;
    TextView tvclose;
    TextView recipe_title;
    ImageView recipe_img;
    JSONObject obj_rpd;
    String recipe_url_rpd = "https://cosylab.iiitd.edu.in/api/recipeDB/recipeoftheday";
    //Token
    //String url = "https://cosylab.iiitd.edu.in/api/auth/realms/bootadmin/protocol/openid-connect/token";
    String uname = "nitika";
    String pass = "nitika_cosylab";
    String client_id = "app-ims";
    String grant_type = "password";
    String scope = "openid";
    //String catUsed="",catNotUsed="";
    //String access_token ="",refresh_token="";


    private  FaqAdapter.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs_list);
        recyclerView = findViewById(R.id.faqrecycler);

        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        question = new ArrayList<>();
        answer = new ArrayList<>();

        setOnClickListener();

        adapter = new FaqAdapter(this, question,answer,listener);

        question.add("What is RecipeDB?");
        answer.add("RecipeDB is a structured repository of recipes and ingredients from over 22 world regions, intended to enable data-driven explorations of recipes. In conjunction with flavor molecules data from FlavorDB; RecipeDB facilitates multi-level analysis of traditional recipes (dietary classifications, ingredient composition, nutritional profile, recipes, etc.).");

        question.add("What are the different concepts represented in RecipeDB and how do they relate to each other?");
        answer.add("Concepts presented in RecipeDB include recipes, the region they originated in, and the ingredients and utensils utilized in their preparation. It also includes the nutritional profiles of both recipes and their ingredients. The illustration depicts relationships among the different concepts incorporated into RecipeDB. In this example, we have the recipe ‘Prawn Nasi Goreng’ which belongs to the Southeast Asian region, specifically to the Indonesian sub-region. The estimated nutritional profile of the recipe contains information on its nutrient content such as the number of calories, proteins, etc. The recipe is characterized by its dietary attributes, list of utensils used as well as an ordered list of the processes that take place in the recipe. Each ingredient in a recipe is characterized by attributes such as quantity, unit and state as well as its own nutritional profile.");

        question.add("What types of queries are processed by RecipeDB?");
        answer.add("RecipeDB facilitates an elastic search to query recipes based on factors such as geographical location of origin (i.e. region, country), name, dietary classification, ingredients associated/unassociated with the recipe and its nutritional information (i.e. calorie, protein, etc. content).");

        question.add("How do I use “Recipe Search”?");
        answer.add("Refer to the “Search” section on the “How to Use” page.");

        question.add("How do I use “Advanced Search”?");
        answer.add("Refer to the “Advanced Search” section on the “How to Use” page");

        question.add("What are the various recipes within RecipeDB?");
        answer.add("A list of all the recipes in RecipeDB can be obtained by simply hitting the ‘Search’ button on the recipe search page without providing any specific query.");

        question.add("What are RecipeDB Statistics?");
        answer.add("RecipeDB Statistics explain the vastness and depth of RecipeDB using interactive plots made using Plotly");

        question.add("What are the prerequisites required in order to use RecipeDB");
        answer.add("A modern web browser.");

        question.add("How does the ‘Find Similar Recipes’ feature work?");
        answer.add("The similarity in recipes was ascertained based on shared category composition of ingredients and processes.");

        question.add("How does RecipeDB obtain the individual nutritional profiles of ingredients in a recipe?");
        answer.add("RecipeDB uses information such as the state and quantity of an ingredient in a recipe to calculate its estimated nutritional profile by mapping it to the USDA data.");

        question.add("How does RecipeDB estimate a recipe’s nutritional profile?");
        answer.add("For every ingredient of a recipe its features were extracted: ingredient name, state, quantity, unit, dry/fresh, temperature, and size. Using the name of the ingredient and its state, each ingredient was mapped to a unique ‘NDB ID’ from USDA database. Further using the standard nutrition data from USDA data tables, the nutritional profile of the ingredient was estimated by mapping quantity and unit. The final ‘estimated nutritional profile’ is a sum total of nutritional values of each ingredient in it.");

        question.add("How were the Dietary Styles (vegan, pescatarian, ovo-vegetarian, lacto-vegetarian, ovo-lacto-vegetarian) ascertained for the recipes?");
        answer.add("The Dietary Style of each recipe was decided based on the constraints followed by each of these categories. For this version the ingredient categories dish and bakery were refrained from the dietary style determination.");

        question.add("What are the categories ingredients are sorted into?");
        answer.add("The ingredients were manually curated into the 23 categories given below:\n\n" +
                "• Additive\n"+  "• Bakery\n" +
                "• Beverage\n"+ "• Beverage-Alcoholic\n" +
                "• Cereal\n"+    "• Condiment\n" +
                "• Dairy\n"+     "• Dish\n" +
                "• Fish\n"+      "• Essential Oil\n" +
                "• Fruit\n"+     "• Fungi\n" +
                "• Herb\n"+      "• Legume\n" +
                "• Maize\n"+     "• Meat\n" +
                "• Plant\n"+     "• Nuts and Seeds\n" +
                "• Seafood\n"+   "• Plant Derivative\n" +
                "• Spice\n"+     "• Vegetable\n");

        question.add("How does the integration of RecipeDB with DietRx and FlavorDB work?");
        answer.add("For each ingredient found in recipes of the database, a ‘Generic Ingredient Name’ was assigned manually to account for degeneracy due to derivative products. For each generic ingredient the FlavorDB ID and DietRx ID were manually labelled. These external resources provide details of the ‘Flavor Profile’ and ‘Health Impacts’ of the ingredient, respectively");

        question.add("What browsers are supported by RecipeDB?");
        answer.add("RecipeDB supports all modern web browsers, but it can be best viewed on Chrome, Firefox and Edge.");

        question.add("What is the Tech Stack used to build RecipeDB?");
        answer.add("Frontend: Bootstrap, Materialize CSS, JavaScript, Google Charts, Plotly\n" +
                "\n" +
                "Backend: Flask, SQLite, Python");

        question.add("What are the sources of information for RecipeDB?");
        answer.add("RecipeDB has sourced the recipes from many online resources, and its nutritional information from the USDA Food Composition Databases");

        question.add("Does RecipeDB use cookies?");
        answer.add("RecipeDB uses cookies to provide statistics that help us give the best possible experience for our site.");

        question.add("Can I access RecipeDB using my mobile phone?");
        answer.add("Although RecipeDB is best viewed on a desktop, the website is accessible from most modern mobile web browsers.");

        question.add("How do I access the data from RecipeDB?");
        answer.add("We provide options for downloading the data from RecipeDB. The data is available under Creative Commons License Attribution-NonCommercial-ShareAlike 3.0 Unported (CC BY-NC-SA 3.0)");

        question.add("How do I contribute to the data from RecipeDB?");
        answer.add("You may contact us at bagler+RecipeDB@iiitd.ac.in for errata along with relevant references.\n" +
                "Additionally, you may also fill the following form to report errata or give suggestions:\n" +
                "\n" +
                "Errata: https://forms.gle/cRcdTbUUg7osr2Ak7\n" +
                "\n" +
                "Suggestions: https://forms.gle/bb44J6taJnmiwipo9");

        question.add("What is the license under which RecipeDB is available?");
        answer.add("We provide options for downloading the data from RecipeDB. The data is available under Creative Commons License Attribution-NonCommercial-ShareAlike 3.0 Unported (CC BY-NC-SA 3.0)");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        // Bottom Navigation View
        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.faqs);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        //Toast.makeText(MainActivity.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.how_to_use:
                       // startActivity(new Intent(getApplicationContext(),.class));
                        //overridePendingTransition(0,0);
                        //finish();
                        //Toast.makeText(.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.faqs:
                        //startActivity(new Intent(getApplicationContext(),.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        //Toast.makeText(MainActivity.this, "FAQs Selected", Toast.LENGTH_SHORT).show();

                        //startActivity(new Intent(getApplicationContext(), faqs_list.class));
                        return true;


                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(), Contact_Us.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        //Toast.makeText(MainActivity.this, "Contact Us Selected", Toast.LENGTH_SHORT).show();

                        return true;


                }
                return false;
            }
        });

    }

    private void setOnClickListener()
    {
        listener = new FaqAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(faqs_list.this, Faq_answers.class);
                intent.putExtra("answer",answer.get(position));
                startActivity(intent);

            }

        };
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
        Intent i =  new Intent(faqs_list.this,Recipe_Details.class);
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
                Toast.makeText(faqs_list.this,error.toString(),Toast.LENGTH_SHORT).show();

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
        RequestQueue r = Volley.newRequestQueue(faqs_list.this);
        StringRequest sr = new StringRequest(Request.Method.GET, recipe_url_rpd, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    obj_rpd = new JSONObject(response);
                    title = obj_rpd.getString("recipe_title").toString();
                    recipe_title.setText(title);
                    Glide.with(faqs_list.this)
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
                Toast.makeText(faqs_list.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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