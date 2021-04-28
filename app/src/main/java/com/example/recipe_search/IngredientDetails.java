package com.example.recipe_search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe_search.ALL_FORMS.AllFormAdapter;
import com.example.recipe_search.CONTACT_US.Contact_Us;
import com.example.recipe_search.FAQs.faqs_list;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class IngredientDetails extends AppCompatActivity {
    PieChart pieChart ;
    ArrayList<PieEntry> entries;
    PieDataSet pieDataSet ;
    PieData pieData ;

    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private Dialog dialog1,dialog2,dialog3;


    // All forms adapter
    RecyclerView formRecycler;
    List<String> AllForms;
    AllFormAdapter adapter;



    CardView cv1,cv2;
    Animation anim1,anim2,anim3,anim4,anim5,anim6;

    private ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();
    Runnable sliderRunnable;

    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_details);

        // Action Bar
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        cv1 = findViewById(R.id.ig_category);
        cv2 = findViewById(R.id.ig_forms);
        img = findViewById(R.id.ingr_img);

        anim1 = AnimationUtils.loadAnimation(this,R.anim.left_anim);

        anim2 = AnimationUtils.loadAnimation(this,R.anim.right_anim);
        anim3 = AnimationUtils.loadAnimation(this,R.anim.img_drop_down);
        anim4 = AnimationUtils.loadAnimation(this,R.anim.text_slide_up);

        anim5 = AnimationUtils.loadAnimation(this,R.anim.slide_up);
        anim6 = AnimationUtils.loadAnimation(this,R.anim.slide_bottom);


        img.setAnimation(anim3);


        cv1.setAnimation(anim1);
        cv2.setAnimation(anim2);


        // Bottom Navigation View
        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bnv.setSelectedItemId(R.id.search);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        Intent i = new Intent(IngredientDetails.this,MainActivity.class);
                        Toast.makeText(IngredientDetails.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        return true;

                    case R.id.how_to_use:
                        //startActivity(new Intent(getApplicationContext(),Search.class));
                        //overridePendingTransition(0,0);
                        //finish();
                        Toast.makeText(IngredientDetails.this, "How to use Selected", Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.faqs:
                        //startActivity(new Intent(getApplicationContext(),Stats.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        Toast.makeText(IngredientDetails.this, "FAQs Selected", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), faqs_list.class));
                        return true;


                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(), Contact_Us.class));
                        //startActivity(new Intent(getApplicationContext(),ContactUs.class));
                        //overridePendingTransition(0,0);
                        //finish();

                        Toast.makeText(IngredientDetails.this, "Contact Us Selected", Toast.LENGTH_SHORT).show();

                        return true;


                }
                return false;
            }
        });


        pieChart = findViewById(R.id.pieChart);
        pieChart.setAnimation(anim4);

        pieChart.setHoleRadius(30f);
        pieChart.setTransparentCircleAlpha(25);
        pieChart.setCenterText("Nutrient\nProfile");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);




        entries = new ArrayList<>();

        entries.add(new PieEntry((float) 40,"Fat (g)"));
        entries.add(new PieEntry((float) 34,"Protein (g)"));
        entries.add(new PieEntry((float) 46,"Carbs (g)"));
        entries.add(new PieEntry((float) 600,"Energy (KCal)"));

        //AddValuesToPIEENTRY();

        //AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");

        pieData = new PieData(pieDataSet);

        // add colors

        ArrayList<Integer> colors = new ArrayList<>();
        //colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.parseColor("#FFA500"));
        //colors.add(Color.CYAN);
        pieDataSet.setColors(colors);

        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(10f);

        // pieChart.setEntryLabelTextSize(10f);
        pieChart.setHoleRadius(25f);

        pieChart.setDrawEntryLabels(false);

        // pieChart.setEntryLabelColor(Color.WHITE);

        pieChart.getLegend().setTextColor(Color.BLACK);
        pieChart.getLegend().setTextSize(16f);

        //pieChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        pieChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        //pieChart.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);

        pieChart.setData(pieData);
        pieChart.setCenterText("Nutrient Profile");
        pieChart.getDescription().setEnabled(false);
        pieChart.animateY(1000);



    }

    public void showCategory(View view) {
        TextView tvclose;

        dialog2 = new Dialog(this);
        dialog2.setContentView(R.layout.activity_ingredient__categories);

        dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Window window = dialog2.getWindow();
        window.setGravity(Gravity.CENTER);
        window.getAttributes().windowAnimations = R.style.DialogAnimationReverse;

        tvclose = (TextView) dialog2.findViewById(R.id.tvclose);


        viewPager2 = dialog2.findViewById(R.id.viewPagerImageSlider);

        List<SliderItem> slideritems = new ArrayList<>();

        slideritems.add(new SliderItem(R.drawable.lemon,"LEMON"));
        slideritems.add(new SliderItem(R.drawable.banana,"BANANA"));
        slideritems.add(new SliderItem(R.drawable.melon,"MELON"));
        slideritems.add(new SliderItem(R.drawable.straw,"STRAWBERRY"));
        slideritems.add(new SliderItem(R.drawable.green_apple,"GREEN APPLE"));

        slideritems.add(new SliderItem(R.drawable.apple,"APPLE"));

        slideritems.add(new SliderItem(R.drawable.pineapple,"PINE-APPLE"));

        slideritems.add(new SliderItem(R.drawable.blueberry,"BLUEBERRY"));

        slideritems.add(new SliderItem(R.drawable.berry,"BERRY"));

        slideritems.add(new SliderItem(R.drawable.orange,"ORANGE"));

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
                slideHandler.postDelayed(sliderRunnable,2000); //slide duration 2 seconds
            }
        });

        tvclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
        sliderRunnable = new Runnable() {
            @Override
            public void run() {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
            }
        };

        dialog2.setCancelable(true);
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
        dialog2.show();
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

    public void showAllForms(View view) {

        TextView tvclose;

        dialog3 = new Dialog(this);
        dialog3.setContentView(R.layout.activity_all__forms);

        dialog3.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Window window = dialog3.getWindow();
        window.setGravity(Gravity.CENTER);
        window.getAttributes().windowAnimations = R.style.DialogAnimationReverse;

        tvclose = (TextView) dialog3.findViewById(R.id.tvclose);

        formRecycler = dialog3.findViewById(R.id.formrecycle);

        formRecycler.setHasFixedSize(true);
        formRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        AllForms = new ArrayList<>();
        AllForms.add("Bottled Water");
        AllForms.add("Divided Water");
        AllForms.add("Acidulated Water");
        AllForms.add("Blanched Water");
        AllForms.add("Blended Water");
        AllForms.add("Boiled Boiled Water");
        AllForms.add("Boiled Refreshed Water");
        AllForms.add("Boiled Bottled Water");
        AllForms.add("Boiling Cooled Water");
        AllForms.add("Boiling Less Water");
        AllForms.add("Bottle Water");
        AllForms.add("Bottled Divided Water");
        AllForms.add("Called Water");
        AllForms.add("Canned Crushed Water");
        AllForms.add("Chilled Chilled Water");
        AllForms.add("Chopped Roasted Drained Water");
        adapter = new AllFormAdapter(AllForms);
        formRecycler.setAdapter(adapter);

        tvclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3.dismiss();
            }
        });
        dialog3.setCancelable(true);
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT);
        dialog3.show();
    }
}

