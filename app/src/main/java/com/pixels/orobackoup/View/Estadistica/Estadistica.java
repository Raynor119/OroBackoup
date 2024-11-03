package com.pixels.orobackoup.View.Estadistica;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.Estadistica.TabLayout.PagerController;

public class Estadistica extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerController pagerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica);
        tabLayout= findViewById(R.id.Tablayout);
        viewPager= findViewById(R.id.viewpager);
        pagerController= new PagerController(getSupportFragmentManager(), 3);
        viewPager.setAdapter(pagerController);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        ImageView RecargarD= findViewById(R.id.buscarRecivo);
        RecargarD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}