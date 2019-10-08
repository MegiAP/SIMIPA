package com.vincode.simipa.ui.Schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.vincode.simipa.R;

import java.util.ArrayList;
import java.util.List;

public class CollegeScheduleActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_schedule);
        viewPager = findViewById(R.id.viewpager1);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs1);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        CollegeScheduleActivity.ViewPagerAdapter adapter = new CollegeScheduleActivity.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new JadkulFragment(), "Senin");
        adapter.addFrag(new dump1(), "Selasa");
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