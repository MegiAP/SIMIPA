package com.vincode.simipa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vincode.simipa.ui.calendar.AcademicCalendarActivity;
import com.vincode.simipa.ui.guidance.GuidanceScheduleActivity;
import com.vincode.simipa.ui.presence.PresenceActivity;
import com.vincode.simipa.ui.profil.ProfilActivity;
import com.vincode.simipa.ui.service.ServiceActivity;
import com.vincode.simipa.ui.study_progress.StudyProgressActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCardClick();
    }

    private void setCardClick(){

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
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
        }
    }
}
