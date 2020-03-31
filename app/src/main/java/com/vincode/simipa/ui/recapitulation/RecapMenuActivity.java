package com.vincode.simipa.ui.recapitulation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vincode.simipa.R;

public class RecapMenuActivity extends AppCompatActivity implements View.OnClickListener {

//    CardView cvScore, cvGuidance, cvPresence, cvSeminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap_menu);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.recapitulation);

        //langsung ke SeminarRecapActivity untuk sementara
        Intent seminar = new Intent(RecapMenuActivity.this, SeminarRecapActivity.class);
        startActivity(seminar);

        setCardClick();
    }

    private void setCardClick() {
/*        cvScore = findViewById(R.id.cv_recScore);
        cvScore.setOnClickListener(this);

        cvGuidance = findViewById(R.id.cv_recGuidance);
        cvGuidance.setOnClickListener(this);

        cvPresence = findViewById(R.id.cv_recPresence);
        cvPresence.setOnClickListener(this);

        cvSeminar = findViewById(R.id.cv_recSeminar);
        cvSeminar.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
/*            case R.id.cv_recScore :
                Intent score = new Intent(RecapMenuActivity.this, ScoreRecapActivity.class);
                startActivity(score);
                break;
            case R.id.cv_recGuidance :
                Intent guidance = new Intent(RecapMenuActivity.this, GuidanceRecapActivity.class);
                startActivity(guidance);
                break;
            case R.id.cv_recPresence :
                Intent presence = new Intent(RecapMenuActivity.this, PresenceRecapActivity.class);
                startActivity(presence);
                break;
            case R.id.cv_recSeminar :
                Intent seminar = new Intent(RecapMenuActivity.this, SeminarRecapActivity.class);
                startActivity(seminar);
                break;*/
        }
    }
}
