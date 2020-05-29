package com.vincode.simipa.ui.beasiswa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;
import com.vincode.simipa.R;
import com.vincode.simipa.adapter.KRSPagerAdapter;
import com.vincode.simipa.adapter.ScholarshipAdapter;
import com.vincode.simipa.adapter.ScholarshipPagerAdapter;
import com.vincode.simipa.model.ScholarshipResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.ui.krs.FormKRSFragment;
import com.vincode.simipa.ui.krs.MyKRSFragment;
import com.vincode.simipa.util.SharedPrefManager;

public class BeasiswaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beasiswa);

        TabLayout tabLayout = findViewById(R.id.tl_beasiswa);
        ViewPager viewPager = findViewById(R.id.vp_beasiswa);
        Toolbar toolbar = findViewById(R.id.tb_beasiswa);

        ScholarshipPagerAdapter fragmentPagerAdapter = new ScholarshipPagerAdapter(getSupportFragmentManager());
        fragmentPagerAdapter.addFragment(new BeasiswaFragment(), getResources().getString(R.string.history));
        fragmentPagerAdapter.addFragment(new FormBeasiswaFragment(), getResources().getString(R.string.form_beasiswa));

        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.scholarship);
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
