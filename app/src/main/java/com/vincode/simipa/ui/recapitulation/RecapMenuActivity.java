package com.vincode.simipa.ui.recapitulation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vincode.simipa.R;

public class RecapMenuActivity extends AppCompatActivity implements View.OnClickListener {

    CardView cvScore, cvGuidance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap_menu);

        setCardClick();
    }

    private void setCardClick() {
        cvScore = findViewById(R.id.cv_recScore);
        cvScore.setOnClickListener(this);

        cvGuidance = findViewById(R.id.cv_recGuidance);
        cvGuidance.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_recScore :
                Intent score = new Intent(RecapMenuActivity.this, ScoreRecapActivity.class);
                startActivity(score);
                break;
        }
    }
}
