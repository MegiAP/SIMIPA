package com.vincode.simipa.ui.calendar;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.CalendarPagerAdapter;

public class AcademicCalendarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calendar);

        TabLayout tabLayout = findViewById(R.id.tl_calendar);
        ViewPager viewPager = findViewById(R.id.vp_calendar);
        Toolbar toolbar = findViewById(R.id.tb_calendar);

        CalendarPagerAdapter fragmentPagerAdapter = new CalendarPagerAdapter(getSupportFragmentManager());
        fragmentPagerAdapter.addFragment(new OddCalendarFragment(), "Ganjil");
        fragmentPagerAdapter.addFragment(new EvenCalendarFragment(), "Genap");

        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.calendar);
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
