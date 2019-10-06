package com.vincode.simipa.ui.guidance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vincode.simipa.R;

public class DetailGuidanceActivity extends AppCompatActivity {

    TextView tvTime, tvDate, tvTitle, tvLecture, tvRoom, tvTopic, tvDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_guidance);

//        Toolbar toolbar = findViewById(R.id.tb_detail_guidance);
//        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null){
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }

        ImageView imgBack = findViewById(R.id.img_back_detail);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
