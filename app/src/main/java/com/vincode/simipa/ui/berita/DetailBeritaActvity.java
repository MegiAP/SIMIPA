package com.vincode.simipa.ui.berita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vincode.simipa.R;
import com.vincode.simipa.model.BeritaResult;

public class DetailBeritaActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

        final BeritaResult data = getIntent().getParcelableExtra("berita");

        ImageView imgBerita = findViewById(R.id.img_detail_berita);
        ImageView imgShare = findViewById(R.id.img_share_berita);
        TextView tvTitle = findViewById(R.id.tv_detail_title);
        TextView tvTime = findViewById(R.id.tv_time_berita);
        TextView tvDetail = findViewById(R.id.tv_detail_berita);

        assert data != null;
        Glide.with(this)
                .load("https://fmipa.unila.ac.id/"+data.getPhoto())
                .into(imgBerita);
        tvTitle.setText(data.getTitle());
        tvTime.setText(data.getReleaseDate());
        tvDetail.setText(data.getContent());
        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent beritaIntent = new Intent(Intent.ACTION_SEND);
                beritaIntent.setType("text/plain");
                String text = data.getTitle() + "\n\n" +data.getContent();
                beritaIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                beritaIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(beritaIntent, "Share With"));
                Toast.makeText(DetailBeritaActvity.this, "Share Berita", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
