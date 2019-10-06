package com.vincode.simipa.ui.splash;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.vincode.simipa.R;
import com.vincode.simipa.ui.intro.IntroSliderActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent moveIntent = new Intent(SplashScreenActivity.this, IntroSliderActivity.class);
                startActivity(moveIntent);
                finish();
            }
        }, 3000);
    }
}
