package com.vincode.simipa.ui.schedule;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vincode.simipa.R;

public class LectureScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    CardView cvCoba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_schedule);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Jadwal Dosen");

        setCardClick();
    }

    private void setCardClick() {
        cvCoba = findViewById(R.id.cv_coba);
        cvCoba.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_coba :
                Intent coba = new Intent(LectureScheduleActivity.this, DetailLectureActivity.class);
                startActivity(coba);
                break;
        }
    }
}
