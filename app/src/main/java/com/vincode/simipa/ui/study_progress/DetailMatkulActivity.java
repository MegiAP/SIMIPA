package com.vincode.simipa.ui.study_progress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.DetailProgressAdapter;
import com.vincode.simipa.model.ScoreMatkul;
import com.vincode.simipa.util.TestScoreMatkul;

import java.util.ArrayList;

public class DetailMatkulActivity extends AppCompatActivity {

    public static final String EXTRA_INTENT = "extra_intent";

    private DetailProgressAdapter adapter;
    private RecyclerView rVDetailSocre;
    private ArrayList<ScoreMatkul> listScore = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_matkul);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Semester 1");
        }

        rVDetailSocre = findViewById(R.id.rv_score_matkul);

        listScore.addAll(TestScoreMatkul.getListData());
        adapter = new DetailProgressAdapter(this, listScore);

        setLayout();
    }

    private void setLayout(){
        rVDetailSocre.setLayoutManager(new LinearLayoutManager(this));
        rVDetailSocre.setHasFixedSize(true);
        rVDetailSocre.setAdapter(adapter);
    }
}
