package com.example.recipe_search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_search.CONTACT_US.Contact_Us;
import com.example.recipe_search.FAQs.faqs_list;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Recipe_Details extends AppCompatActivity{
    PieChart pieChart ;
    ArrayList<PieEntry> entries;
    PieDataSet pieDataSet ;
    PieData pieData ;

    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private Dialog dialog1,dialog2;

    FloatingActionButton fab_pop;

    private ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();
    Runnable sliderRunnable;

    CardView cv1,cv2;
    Animation anim1,anim2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe__details);

        // Action Bar
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        anim1 = AnimationUtils.loadAnimation(this,R.anim.left_anim);
        anim2 = AnimationUtils.loadAnimation(this,R.anim.right_anim);

        cv1 = findViewById(R.id.cv1);
        cv2 = findViewById(R.id.cv2);

        cv1.setAnimation(anim1);
        cv2.setAnimation(anim2);

        // utensils
        dialog2 = new Dialog(this);

        // Bottom Navigation View
        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.search);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        Intent i = new Intent(Recipe_Details.this,MainActivity.class);
                        Toast.makeText(Recipe_Details.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        return true;

                    case R.id.how_to_use:
                        //startActivity(new Intent(getApplicationContext(),Search.class));
                        //overridePendingTransition(0,0);
                        //finish();
                        Toast.makeText(Recipe_Details.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.faqs:
                        //startActivity(new Intent(getApplicationContext(),Stats.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        Toast.makeText(Recipe_Details.this, "FAQs Selected", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), faqs_list.class));
                        return true;


                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(), Contact_Us.class));
                        //startActivity(new Intent(getApplicationContext(),ContactUs.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        Toast.makeText(Recipe_Details.this, "Contact Us Selected", Toast.LENGTH_SHORT).show();

                        return true;


                }
                return false;
            }
        });


        fab_pop = findViewById(R.id.fab_pop);
        pieChart = findViewById(R.id.pieChart);

        entries = new ArrayList<>();

        entries.add(new PieEntry((float) 13.54,"Fat"));
        entries.add(new PieEntry((float) 32.82,"Proteins"));
        entries.add(new PieEntry((float) 201.53,"Carbs"));
        entries.add(new PieEntry((float) 1079.53,"Energy"));

        //AddValuesToPIEENTRY();

        //AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");

        pieData = new PieData(pieDataSet);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.getValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        pieChart.setEntryLabelTextSize(10f);
        pieChart.setEntryLabelColor(Color.WHITE);

        pieChart.getLegend().setTextColor(Color.BLACK);

        pieChart.setData(pieData);
        pieChart.setCenterText("Nutrient Profile");
        pieChart.getDescription().setEnabled(false);
        pieChart.animateY(1500);


        fab_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Recipe_Details.this, fab_pop);
                popup.getMenuInflater().inflate(R.menu.popupmenu,popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.recipe_overview:
                                Toast.makeText(Recipe_Details.this, "Recipe Overview", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.recipe_ingredients:
                                Toast.makeText(Recipe_Details.this, "Ingredients", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Recipe_Details.this,Recipe_ingredients.class);
                                startActivity(i);
                                return true;
                            case R.id.recipe_process:
                                startActivity(new Intent(Recipe_Details.this,Recipe_process.class));
                                Toast.makeText(Recipe_Details.this, "Process", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.recipe_utensils:
                                Toast.makeText(Recipe_Details.this, "Utensils", Toast.LENGTH_SHORT).show();
                                showUtensils();
                                return true;
                            case R.id.recipe_instructions:
                                startActivity(new Intent(Recipe_Details.this,Recipe_instructions.class));
                                Toast.makeText(Recipe_Details.this, "Instructions", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
            }
        });

    }

    public void showUtensils()
    {
        TextView tvclose;

        dialog2.setContentView(R.layout.activity_recipe_utensils);
        tvclose = (TextView) dialog2.findViewById(R.id.tvclose);


        viewPager2 = dialog2.findViewById(R.id.viewPagerImageSlider);

        List<SliderItem> slideritems = new ArrayList<>();

        slideritems.add(new SliderItem(R.drawable.ctgr_bg,"Burger"));
        slideritems.add(new SliderItem(R.drawable.cuisines,"Hello"));
        slideritems.add(new SliderItem(R.drawable.ingr_bg,"Chicken"));
        slideritems.add(new SliderItem(R.drawable.nut_bg,"Donut"));
        slideritems.add(new SliderItem(R.drawable.nutrients,"GolGappa"));

        viewPager2.setAdapter(new SliderAdapter(slideritems,viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);

        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f+r*0.15f);

            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable,3000); //slide duration 3 seconds
            }
        });

        tvclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
        dialog2.show();

        sliderRunnable = new Runnable() {
            @Override
            public void run() {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
            }
        };
    }

    // Pop Up : Recipe of the day
    public void showPop(View v) {
        TextView tvclose;
        dialog1 = new Dialog(this);

        dialog1.setContentView(R.layout.popup);
        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Window window = dialog1.getWindow();
        window.setGravity(Gravity.CENTER);
        window.getAttributes().windowAnimations = R.style.DialogAnimation;

        tvclose = (TextView) dialog1.findViewById(R.id.tvclose);

        tvclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        dialog1.setCancelable(true);
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
        dialog1.show();

    }

  /*  Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };
*/

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable,3000);
    }
}