package com.vincode.simipa.ui.recapitulation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.GuidanceRecapAdapter;
import com.vincode.simipa.adapter.ScoreRecapAdapter;
import com.vincode.simipa.model.GuidanceRecap;
import com.vincode.simipa.model.ScoreRecap;
import com.vincode.simipa.util.TestGuidance;
import com.vincode.simipa.util.TestGuidanceRecapData;
import com.vincode.simipa.util.TestScoreData;

import java.util.ArrayList;

public class GuidanceRecapActivity extends AppCompatActivity {

    private RecyclerView rvCategory;
    private ArrayList<GuidanceRecap> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance_recap);

        rvCategory = (RecyclerView)findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(TestGuidanceRecapData.getListGuidanceRecap());

        showRecyclerCardView();
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        GuidanceRecapAdapter guidanceRecapAdapter = new GuidanceRecapAdapter(this);
        guidanceRecapAdapter.setlistGuidanceRecap(list);
        rvCategory.setAdapter(guidanceRecapAdapter);
    }
}
