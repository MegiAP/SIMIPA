package com.vincode.simipa.ui.krs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.vincode.simipa.R;
import com.vincode.simipa.adapter.KRSPagerAdapter;

public class KRSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krs);

        TabLayout tabLayout = findViewById(R.id.tl_krs);
        ViewPager viewPager = findViewById(R.id.vp_krs);
        Toolbar toolbar = findViewById(R.id.tb_krs);

        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.krs);
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
