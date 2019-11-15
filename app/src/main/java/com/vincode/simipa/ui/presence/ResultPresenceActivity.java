package com.vincode.simipa.ui.presence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.vincode.simipa.R;

public class ResultPresenceActivity extends AppCompatActivity {

    public static final String EXTRA_INTENT = "extra_intent";
    public static final String EXTRA_LONGI = "extra_longi";
    public static final String EXTRA_LATI = "extra_lati";
    public static final String EXTRA_ADDRESS = "extra_addres";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_presence);

        String value = getIntent().getStringExtra(EXTRA_INTENT);
        double latitude = getIntent().getDoubleExtra(EXTRA_LATI,0);
        double longitude = getIntent().getDoubleExtra(EXTRA_LONGI, 0);
        String address = getIntent().getStringExtra(EXTRA_ADDRESS);

        TextView tvValue = findViewById(R.id.tv_value);
        tvValue.setText(value);

        TextView tvLongi = findViewById(R.id.tv_longi);
        tvLongi.setText(String.valueOf(longitude));

        TextView tvLati = findViewById(R.id.tv_lati);
        tvLati.setText(String.valueOf(latitude));

        TextView tvLocation = findViewById(R.id.tv_addres_location);
        tvLocation.setText(address);

    }
}
