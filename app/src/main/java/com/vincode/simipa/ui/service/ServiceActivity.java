package com.vincode.simipa.ui.service;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.CalendarPagerAdapter;
import com.vincode.simipa.ui.presence.CollegeFragment;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        TabLayout tabLayout = findViewById(R.id.tl_service);
        ViewPager viewPager = findViewById(R.id.vp_service);
        Toolbar toolbar = findViewById(R.id.tb_service);

        CalendarPagerAdapter fragmentPagerAdapter = new CalendarPagerAdapter(getSupportFragmentManager());
        fragmentPagerAdapter.addFragment(new AcademicServiceFragment(), "Akademik");
        fragmentPagerAdapter.addFragment(new FinanceServiceFragment(), "Keuangan");
        fragmentPagerAdapter.addFragment(new StudentServiceFragment(), "Mahasiswa");

        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.Service);
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
