package com.vincode.simipa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vincode.simipa.ui.calendar.AcademicCalendarActivity;
import com.vincode.simipa.ui.guidance.GuidanceScheduleActivity;
import com.vincode.simipa.ui.profil.ProfilActivity;

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

        }
    }
}
