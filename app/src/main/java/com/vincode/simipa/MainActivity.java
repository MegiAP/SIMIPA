package com.vincode.simipa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.vincode.simipa.beasiswa.BeasiswaActivity;
import com.vincode.simipa.krs.KRSActivity;
import com.vincode.simipa.model.ScoreRecap;
import com.vincode.simipa.ui.agenda.AgendaActivity;
import com.vincode.simipa.ui.recapitulation.RecapMenuActivity;
import com.vincode.simipa.ui.recapitulation.ScoreRecapActivity;
import com.vincode.simipa.ui.schedule.ScheduleMenu;
import com.vincode.simipa.ui.calendar.AcademicCalendarActivity;
import com.vincode.simipa.ui.guidance.GuidanceScheduleActivity;
import com.vincode.simipa.ui.presence.PresenceActivity;
import com.vincode.simipa.ui.profil.ProfilActivity;
import com.vincode.simipa.ui.service.ServiceActivity;
import com.vincode.simipa.ui.settings.SettingActivity;
import com.vincode.simipa.ui.study_progress.StudyProgressActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView cvKRS, cvBeasiswa, cvUser, cvGuidance, cvCalendar, cvPresence, cvProgress,cvService, cvSchedule, cvRecap, cvAgenda;
    ImageView imgSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCardClick();

    }

    private void setCardClick(){
        imgSetting = findViewById(R.id.setting);
        imgSetting.setOnClickListener(this);

        cvUser = findViewById(R.id.cv_user);
        cvUser.setOnClickListener(this);

        cvGuidance = findViewById(R.id.cv_guidance);
        cvGuidance.setOnClickListener(this);

        cvCalendar = findViewById(R.id.cv_calendar);
        cvCalendar.setOnClickListener(this);

        cvPresence = findViewById(R.id.cv_presence);
        cvPresence.setOnClickListener(this);

        cvProgress = findViewById(R.id.cv_progress);
        cvProgress.setOnClickListener(this);

        cvService = findViewById(R.id.cv_service);
        cvService.setOnClickListener(this);

        cvBeasiswa = findViewById(R.id.cv_beasiswa);
        cvBeasiswa.setOnClickListener(this);

        cvKRS = findViewById(R.id.cv_krs);
        cvKRS.setOnClickListener(this);

        cvRecap = findViewById(R.id.cv_recap);
        cvRecap.setOnClickListener(this);

        cvSchedule = findViewById(R.id.cv_schedule);
        cvSchedule.setOnClickListener(this);

        cvAgenda = findViewById(R.id.cv_agenda);
        cvAgenda.setOnClickListener(this);
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
        }
    }
}
