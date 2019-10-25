package com.vincode.simipa.ui.splash;

import android.content.Intent;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vincode.simipa.R;
import com.vincode.simipa.model.SplashResponse;
import com.vincode.simipa.model.SplashResult;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.ui.intro.IntroSliderActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView imgSplashBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //getSupportActionBar().hide();

        imgSplashBottom = findViewById(R.id.img_splash_bottom);

        getImageSplashscreen();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent moveIntent = new Intent(SplashScreenActivity.this, IntroSliderActivity.class);
                startActivity(moveIntent);
                finish();
            }
        }, 3000);
    }

    private void getImageSplashscreen(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<SplashResponse> call = apiInterface.getSplashImage("1");

        call.enqueue(new Callback<SplashResponse>() {
            @Override
            public void onResponse(@NonNull Call<SplashResponse> call, @NonNull Response<SplashResponse> response) {
                if (response.body() != null) {
                    List<SplashResult> splashResults = response.body().getRecords();

                    String imagePath = "https://fmipa.unila.ac.id/" +  splashResults.get(0).getVector();
                    Glide.with(getApplicationContext())
                            .load(imagePath)
                            .into(imgSplashBottom);
                }

            }

            @Override
            public void onFailure(@NonNull Call<SplashResponse> call, @NonNull Throwable t) {

            }
        });

    }
}
