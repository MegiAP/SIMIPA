package com.vincode.simipa.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vincode.simipa.R;
import com.vincode.simipa.util.SharedPrefManager;
import com.vincode.simipa.ui.beasiswa.BeasiswaActivity;
import com.vincode.simipa.ui.krs.KRSActivity;
import com.vincode.simipa.model.ProfileResponse;
import com.vincode.simipa.model.UserProfile;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.ui.achievement.AchievementActivity;
import com.vincode.simipa.ui.agenda.AgendaActivity;
import com.vincode.simipa.ui.login.LoginActivity;
import com.vincode.simipa.ui.recapitulation.RecapMenuActivity;
import com.vincode.simipa.ui.schedule.ScheduleMenu;
import com.vincode.simipa.ui.calendar.AcademicCalendarActivity;
import com.vincode.simipa.ui.guidance.GuidanceScheduleActivity;
import com.vincode.simipa.ui.presence.PresenceActivity;
import com.vincode.simipa.ui.profil.ProfilActivity;
import com.vincode.simipa.ui.service.ServiceActivity;
import com.vincode.simipa.ui.settings.SettingActivity;
import com.vincode.simipa.ui.study_progress.StudyProgressActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleImageView imageUser;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageUser = findViewById(R.id.img_user);
        tvName = findViewById(R.id.tv_home_name);

        TextView tvNpm = findViewById(R.id.tv_home_npm);
        tvNpm.setText(SharedPrefManager.getInstance(this).getUser().getUserLogin());

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }


        setCardClick();
        getDataProfil();

    }

    private void getDataProfil (){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ProfileResponse> call = apiInterface.userProfile(SharedPrefManager.getInstance(this).getUser().getUserLogin());

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProfileResponse> call, @NonNull Response<ProfileResponse> response) {
                if (response.body() != null) {
                    List<UserProfile> userProfiles = response.body().getUserProfiles();
                    if (userProfiles.get(0).getFoto() != null){
                        Glide.with(getApplicationContext())
                                .load(userProfiles.get(0).getFoto())
                                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error))
                                .into(imageUser);
                    }else {
                        imageUser.setImageResource(R.drawable.ic_account_circle_black);
                    }
                    tvName.setText(userProfiles.get(0).getDisplayName());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProfileResponse> call, @NonNull Throwable t) {

            }
        });
    }

    private void setCardClick(){
        ImageView imgSetting = findViewById(R.id.setting);
        imgSetting.setOnClickListener(this);

        CardView cvUser = findViewById(R.id.cv_user);
        cvUser.setOnClickListener(this);

        CardView cvGuidance = findViewById(R.id.cv_guidance);
        cvGuidance.setOnClickListener(this);

        CardView cvCalendar = findViewById(R.id.cv_calendar);
        cvCalendar.setOnClickListener(this);

        CardView cvPresence = findViewById(R.id.cv_presence);
        cvPresence.setOnClickListener(this);

        CardView cvProgress = findViewById(R.id.cv_progress);
        cvProgress.setOnClickListener(this);

        CardView cvService = findViewById(R.id.cv_service);
        cvService.setOnClickListener(this);

        CardView cvBeasiswa = findViewById(R.id.cv_beasiswa);
        cvBeasiswa.setOnClickListener(this);

        CardView cvKRS = findViewById(R.id.cv_krs);
        cvKRS.setOnClickListener(this);

        CardView cvRecap = findViewById(R.id.cv_recap);
        cvRecap.setOnClickListener(this);

        CardView cvSchedule = findViewById(R.id.cv_schedule);
        cvSchedule.setOnClickListener(this);

        CardView cvAgenda = findViewById(R.id.cv_agenda);
        cvAgenda.setOnClickListener(this);

        CardView cvAchieve = findViewById(R.id.cv_achieve);
        cvAchieve.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_beasiswa :
                Intent beasiswa = new Intent(MainActivity.this, BeasiswaActivity.class);
                startActivity(beasiswa);
                break;
            case R.id.cv_krs :
                Intent krs = new Intent(MainActivity.this, KRSActivity.class);
                startActivity(krs);
                break;
            case R.id.cv_recap :
                Intent recap = new Intent(MainActivity.this, RecapMenuActivity.class);
                startActivity(recap);
                break;
            case R.id.setting:
                Intent settingIntent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(settingIntent);
                break;
            case R.id.cv_user:
                Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(intent);
                break;
            case R.id.cv_guidance:
                Intent guidanceIntent = new Intent(this, GuidanceScheduleActivity.class);
                startActivity(guidanceIntent);
                break;
            case R.id.cv_calendar:
                Intent calendarIntent = new Intent(this, AcademicCalendarActivity.class);
                startActivity(calendarIntent);
                break;
            case R.id.cv_presence:
                Intent presentIntent = new Intent(this, PresenceActivity.class);
                startActivity(presentIntent);
                break;
            case R.id.cv_progress:
                Intent progressIntent = new Intent(this, StudyProgressActivity.class);
                startActivity(progressIntent);
                break;
            case R.id.cv_service:
                Intent serviceIntent = new Intent(this, ServiceActivity.class);
                startActivity(serviceIntent);
                break;
            case R.id.cv_schedule:
                Intent ScheduleIntent = new Intent(this, ScheduleMenu.class);
                startActivity(ScheduleIntent );
                break;
            case R.id.cv_agenda:
                Intent Agenda = new Intent (this, AgendaActivity.class);
                startActivity(Agenda);
                break;

            case R.id.cv_achieve:
                Intent Achieve = new Intent (this, AchievementActivity.class);
                startActivity(Achieve);
                break;
        }
    }
}
