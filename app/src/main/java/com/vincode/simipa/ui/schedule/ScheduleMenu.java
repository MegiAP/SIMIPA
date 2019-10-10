package com.vincode.simipa.ui.schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vincode.simipa.R;

public class ScheduleMenu extends AppCompatActivity implements View.OnClickListener {
    CardView cvJdkul, cvJdprak, cvJdsem, cvJdruang, cvJddosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_menu);

        setCardClick();
    }

    private void setCardClick() {

        cvJdkul = findViewById(R.id.cv_jdkul);
        cvJdkul.setOnClickListener(this);

        cvJdprak = findViewById(R.id.cv_jdprak);
        cvJdprak.setOnClickListener(this);

        cvJdsem = findViewById(R.id.cv_jdseminar);
        cvJdsem.setOnClickListener(this);

        cvJddosen = findViewById(R.id.cv_jddosen);
        cvJddosen.setOnClickListener(this);

        cvJdruang = findViewById(R.id.cv_jdruang);
        cvJdruang.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_jdkul:
                Intent jdkul = new Intent(ScheduleMenu.this, CollegeScheduleActivity.class);
                startActivity(jdkul);
                break;
            case R.id.cv_jdseminar:
                Intent jdsem = new Intent (ScheduleMenu.this, SeminarScheduleActivity.class);
                startActivity(jdsem);
                break;
            case R.id.cv_jdruang :
                Intent ruang = new Intent(ScheduleMenu.this, ClassScheduleActivity.class);
                startActivity(ruang);
                break;
            case R.id.cv_jddosen :
                Intent dosen = new Intent(ScheduleMenu.this, LectureScheduleActivity.class);
                startActivity(dosen);
                break;
            case R.id.cv_jdprak:
                Intent jdpractice = new Intent( ScheduleMenu.this, PracticeScheduleActivity.class);
                startActivity(jdpractice);
                break;

        }
    }
}