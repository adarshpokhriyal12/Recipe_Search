package com.example.recipe_search.FAQs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.recipe_search.R;
import com.example.recipe_search.RecipeAdapter;
import com.example.recipe_search.Recipe_Details;
import com.example.recipe_search.Recipe_Searches;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class faqs_list extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String> question,answer;
    FaqAdapter adapter;


    private  FaqAdapter.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs_list);
        recyclerView = findViewById(R.id.faqrecycler);

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
        answer.add("The ingredients were manually curated into the 23 categories given below:\n" +
                " • Additive  • Bakery\n" +
                " • Beverage  • Beverage-Alcoholic\n" +
                " • Cereal    • Condiment\n" +
                " • Dairy     • Dish\n" +
                " • Fish      • Essential Oil\n" +
                " • Fruit     • Fungi\n " +
                " • Herb      • Legume\n" +
                " • Maize     • Meat\n" +
                " • Plant     • Nuts and Seeds\n" +
                " • Seafood   • Plant Derivative\n" +
                " • Spice     • Vegetable\n");

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
}