package com.vincode.simipa.ui.guidance;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.CalendarPagerAdapter;

public class GuidanceScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance_schedule);

        TabLayout tabLayout = findViewById(R.id.tl_guidance);
        ViewPager viewPager = findViewById(R.id.vp_guidance);
        Toolbar toolbar = findViewById(R.id.tb_guidance);

        CalendarPagerAdapter fragmentPagerAdapter = new CalendarPagerAdapter(getSupportFragmentManager());
        fragmentPagerAdapter.addFragment(new PaGuidanceFragment(), "Akademik");
        fragmentPagerAdapter.addFragment(new KpGuidanceFragment(), "KP");
        fragmentPagerAdapter.addFragment(new TaGuidanceFragment(), "Skripsi/TA");



        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.guidance);
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
