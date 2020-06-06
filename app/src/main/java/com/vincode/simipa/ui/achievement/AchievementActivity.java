package com.vincode.simipa.ui.achievement;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vincode.simipa.R;
import com.vincode.simipa.model.ProfileResponse;
import com.vincode.simipa.model.UserProfile;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;

import java.util.List;

public class AchievementActivity extends AppCompatActivity {
    private TextView tvAcademic, tvNonAcademic;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        TextView tvName = findViewById(R.id.tv_achiev_name);
        TextView tvNpm = findViewById(R.id.tv_achiev_npm);
        FloatingActionButton fabAddAchieve = findViewById(R.id.fab_add_achieve);

        tvName.setText(SharedPrefManager.getInstance(this).getUser().getDisplayName());
        tvNpm.setText(SharedPrefManager.getInstance(this).getUser().getUserLogin());

        fabAddAchieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AchievementActivity.this, AddAchievmentActivity.class));
            }
        });


        tvAcademic = findViewById(R.id.academic);
        tvNonAcademic = findViewById(R.id.non_academic);
        tvAcademic.setEnabled(false);
        imgPhoto = findViewById(R.id.img_photo);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.achievment);

        AcademicAchievementFragment academicAchievementFragment = new AcademicAchievementFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, academicAchievementFragment).commit();

        getData();

    }

    public void cacademic (View view) {
        AcademicAchievementFragment academicAchievementFragment = new AcademicAchievementFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, academicAchievementFragment).commit();
        tvAcademic.setEnabled(false);
        tvNonAcademic.setEnabled(true);
    }

    public void cnonacademic (View view) {
        NonAcademicAchievementFragment nonAcademicAchievementFragment = new NonAcademicAchievementFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, nonAcademicAchievementFragment).commit();
        tvAcademic.setEnabled(true);
        tvNonAcademic.setEnabled(false);
    }

    public void getData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProfileResponse> call = apiInterface.userProfile(SharedPrefManager.getInstance(this).getUser().getUserLogin());
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                assert response.body() != null;
                List<UserProfile> userProfile = response.body().getUserProfiles();

                Glide.with(getApplicationContext())
                        .load(userProfile.get(0).getFoto())
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPhoto);
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });
    }

}
