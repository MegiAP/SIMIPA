package com.vincode.simipa.ui.recapitulation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.PresenceRecapAdapter;
import com.vincode.simipa.adapter.ScoreRecapAdapter;
import com.vincode.simipa.model.PresenceRecap;
import com.vincode.simipa.util.TestPresenceRecapData;
import com.vincode.simipa.util.TestScoreData;

import java.util.ArrayList;

public class PresenceRecapActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private ArrayList<PresenceRecap> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presence_recap);
        rvCategory = findViewById(R.id.rv_presenceRecap);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(TestPresenceRecapData.getListPresence());

        showRecyclerCardView();
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        PresenceRecapAdapter presenceRecapAdapter = new PresenceRecapAdapter(this);
        presenceRecapAdapter.setListPresence(list);
        rvCategory.setAdapter(presenceRecapAdapter);
    }
}
