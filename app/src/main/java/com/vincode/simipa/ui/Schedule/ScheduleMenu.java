package com.vincode.simipa.ui.Schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vincode.simipa.R;
import com.vincode.simipa.model.CollegeSchedule;

public class ScheduleMenu extends AppCompatActivity implements View.OnClickListener {
    CardView cvJdkul, cvJdprak, cvJdsem;
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_jdkul:
                Intent jdkul = new Intent(ScheduleMenu.this, CollegeScheduleActivity.class);
                startActivity(jdkul);
                break;

        }
    }
}