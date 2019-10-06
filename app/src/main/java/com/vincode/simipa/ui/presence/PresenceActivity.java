package com.vincode.simipa.ui.presence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.vincode.simipa.R;
import com.vincode.simipa.adapter.CalendarPagerAdapter;

public class PresenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presence);

        TabLayout tabLayout = findViewById(R.id.tl_presence);
        ViewPager viewPager = findViewById(R.id.vp_presence);
        Toolbar toolbar = findViewById(R.id.tb_presence);

        CalendarPagerAdapter fragmentPagerAdapter = new CalendarPagerAdapter(getSupportFragmentManager());
        fragmentPagerAdapter.addFragment(new CollegeFragment(), "Kuliah");
        fragmentPagerAdapter.addFragment(new PracticeFragment(), "Praktikum");

        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.presence);
        }

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
