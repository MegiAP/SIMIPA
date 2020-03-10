package com.vincode.simipa.ui.achievement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.AchievementAdapter;
import com.vincode.simipa.adapter.AchievementPagerAdapter;
import com.vincode.simipa.adapter.KRSPagerAdapter;
import com.vincode.simipa.model.AchievementResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.ui.krs.FormKRSFragment;
import com.vincode.simipa.ui.krs.MyKRSFragment;
import com.vincode.simipa.util.SharedPrefManager;

public class AchievementActivity extends AppCompatActivity {

/*    private AchievementAdapter achievementAdapter;
    private RecyclerView rvCategory;
    private ProgressBar pgBar;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

/*        rvCategory = findViewById(R.id.rv_category);

        AchievementPagerAdapter fragmentPagerAdapter = new AchievementPagerAdapter(getSupportFragmentManager());
        fragmentPagerAdapter.addFragment(new FormKRSFragment(), getResources().getString(R.string.fill_krs));
        fragmentPagerAdapter.addFragment(new MyKRSFragment(), getResources().getString(R.string.my_krs));*/

        TextView tvName = findViewById(R.id.tv_achiev_name);
        TextView tvNpm = findViewById(R.id.tv_achiev_npm);
        tvName.setText(SharedPrefManager.getInstance(this).getUser().getDisplayName());
        tvNpm.setText(SharedPrefManager.getInstance(this).getUser().getUserLogin());

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.achievment);

        AcademicAchievementFragment academicAchievementFragment = new AcademicAchievementFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, academicAchievementFragment).commit();

/*        pgBar = findViewById(R.id.pg_bar);
        pgBar.setVisibility(View.VISIBLE);

        showRecyclerCardView();
        getData();*/
    }

    public void cacademic (View view) {
        AcademicAchievementFragment academicAchievementFragment = new AcademicAchievementFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, academicAchievementFragment).commit();
    }

    public void cnonacademic (View view) {
        NonAcademicAchievementFragment nonAcademicAchievementFragment = new NonAcademicAchievementFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, nonAcademicAchievementFragment).commit();
    }

/*    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        rvCategory.setHasFixedSize(true);
        rvCategory.setAdapter(achievementAdapter);
    }

    private void getData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<AchievementResponse> call = apiInterface.getAchievementData(SharedPrefManager.getInstance(this).getUser().getUserLogin());
        call.enqueue(new Callback<AchievementResponse>() {
            @Override
            public void onResponse(@NonNull Call<AchievementResponse> call, @NonNull Response<AchievementResponse> response) {
                if (response.body() != null) {
                    pgBar.setVisibility(View.GONE);
                    achievementAdapter.setListAchievement(response.body().getRecords());
                    achievementAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AchievementResponse> call, @NonNull Throwable t) {
                Log.d("Failure", " ");
            }
        });
    }*/
}
