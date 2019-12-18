package com.vincode.simipa.ui.achievement;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.AchievementAdapter;
import com.vincode.simipa.model.AchievementResponse;
import com.vincode.simipa.model.ScholarshipResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;

public class AchievementActivity extends AppCompatActivity {

    private AchievementAdapter achievementAdapter;
    private RecyclerView rvCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        rvCategory = findViewById(R.id.rv_category);
        achievementAdapter = new AchievementAdapter();

        showRecyclerCardView();
        getData();
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        rvCategory.setHasFixedSize(true);
        rvCategory.setAdapter(achievementAdapter);
    }

    private void getData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<AchievementResponse> call = apiInterface.getAchievementData(SharedPrefManager.getInstance(this).getUser().getUserLogin());
        call.enqueue(new Callback<AchievementResponse>() {
            @Override
            public void onResponse(Call<AchievementResponse> call, Response<AchievementResponse> response) {
                if (response.body() != null) {
                    achievementAdapter.setListAchievement(response.body().getRecords());
                    achievementAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<AchievementResponse> call, Throwable t) {

            }
        });
    }
}
