package com.vincode.simipa.ui.profil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.SharedPrefManager;

public class ProfilActivity extends AppCompatActivity {

    TextView tvProfilName, tvProfilNPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        tvProfilName = (TextView)findViewById(R.id.tv_name_profil);
        tvProfilNPM = findViewById(R.id.tv_npm_profil);

        tvProfilName.setText(SharedPrefManager.getInstance(this).getUser().getDisplayName());
        tvProfilNPM.setText(SharedPrefManager.getInstance(this).getUser().getUserLogin());

        ImageView imgBack = findViewById(R.id.img_back_profil);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
