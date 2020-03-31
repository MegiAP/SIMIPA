package com.vincode.simipa.ui.schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.vincode.simipa.R;

import java.util.ArrayList;
import java.util.List;

public class CollegeScheduleActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_schedule);
        viewPager = findViewById(R.id.vp_collegeSchedule);
        setupViewPager(viewPager);
        Toolbar toolbar = findViewById(R.id.tb_collegeSchedule);

        tabLayout = findViewById(R.id.tl_collegeSchedule);
        tabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.collegeschedule);
        }

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        CollegeScheduleActivity.ViewPagerAdapter adapter = new CollegeScheduleActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new MondayCollegeFragment(), "Senin");
        adapter.addFrag(new TuesdayCollegeFragment(), "Selasa");
        adapter.addFrag(new dump2(), "Rabu");
        adapter.addFrag(new dump3(), "Kamis");
        adapter.addFrag(new dump4(), "Jumat");
        viewPager.setAdapter(adapter);
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}