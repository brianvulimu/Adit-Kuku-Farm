package com.example.kukufarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.kukufarm.Adapter.EggsViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class EggsViewPageActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    EggsViewPagerAdapter eggsViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eggs_view_page);

        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.viewpager);
        eggsViewPagerAdapter=new EggsViewPagerAdapter(this);
        viewPager.setAdapter(eggsViewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });
    }
}