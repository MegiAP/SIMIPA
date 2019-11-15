package com.vincode.simipa.ui.recapitulation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.ScoreRecapAdapter;
import com.vincode.simipa.model.ScoreRecap;
import com.vincode.simipa.util.TestScoreData;

import java.util.ArrayList;

public class ScoreRecapActivity extends AppCompatActivity {

    private RecyclerView rvCategory;
    private ArrayList<ScoreRecap> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_recap);

        rvCategory = (RecyclerView)findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(TestScoreData.getListData());

        showRecyclerCardView();
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ScoreRecapAdapter scoreRecapAdapter = new ScoreRecapAdapter(this);
        scoreRecapAdapter.setlistScore(list);
        rvCategory.setAdapter(scoreRecapAdapter);
    }
}
