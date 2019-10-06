package com.vincode.simipa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vincode.simipa.ui.profil.ProfilActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCardClick();
    }

    private void setCardClick(){

        CardView cvUser = findViewById(R.id.cv_user);
        cvUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cv_user:
                Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(intent);
                break;
        }
    }
}
