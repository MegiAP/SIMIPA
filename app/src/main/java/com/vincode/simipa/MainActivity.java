package com.vincode.simipa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vincode.simipa.beasiswa.BeasiswaActivity;
import com.vincode.simipa.krs.KRSActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView cvKRS, cvBeasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cvKRS = findViewById(R.id.cv_krs);
        cvKRS.setOnClickListener(this);
        cvBeasiswa = findViewById(R.id.cv_beasiswa);
        cvBeasiswa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_krs :
                Intent krs = new Intent(MainActivity.this, KRSActivity.class);
                startActivity(krs);
                break;
            case R.id.cv_beasiswa :
                Intent beasiswa = new Intent(MainActivity.this, BeasiswaActivity.class);
                startActivity(beasiswa);
                break;
        }
    }
}
