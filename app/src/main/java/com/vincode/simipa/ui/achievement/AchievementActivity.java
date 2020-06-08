package com.vincode.simipa.ui.achievement;

import androidx.annotation.NonNull;
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
import java.util.Objects;

public class AchievementActivity extends AppCompatActivity {
    private TextView tvAcademic, tvNonAcademic;
    private ImageView imgPhoto;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        TextView tvName = findViewById(R.id.tv_achiev_name);
        TextView tvNpm = findViewById(R.id.tv_achiev_npm);
        FloatingActionButton fabAddAchieve = findViewById(R.id.fab_add_achieve);

//        tvName.setText(SharedPrefManager.getInstance(this).getUser().getDisplayName());
        tvNpm.setText(SharedPrefManager.getInstance(this).getUser().getUserLogin());

        fabAddAchieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AchievementActivity.this, AddAchievmentActivity.class);
                intent.putExtra("nama", name);
                startActivity(intent);
            }
        });


        tvAcademic = findViewById(R.id.academic);
        tvNonAcademic = findViewById(R.id.non_academic);
        tvAcademic.setEnabled(false);
        imgPhoto = findViewById(R.id.img_photo);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.achievment);

        AcademicAchievementFragment academicAchievementFragment = new AcademicAchievementFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, academicAchievementFragment).commit();

        getData(tvName);

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

    public void getData(final TextView tvName) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ProfileResponse> call = apiInterface.userProfile(SharedPrefManager.getInstance(this).getUser().getUserLogin());
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProfileResponse> call, @NonNull Response<ProfileResponse> response) {
                assert response.body() != null;
                List<UserProfile> userProfile = response.body().getUserProfiles();

                Glide.with(getApplicationContext())
                        .load(userProfile.get(0).getFoto())
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPhoto);
                name = userProfile.get(0).getDisplayName();
                tvName.setText(name);
            }

            @Override
            public void onFailure(@NonNull Call<ProfileResponse> call, @NonNull Throwable t) {

            }
        });
    }

}
