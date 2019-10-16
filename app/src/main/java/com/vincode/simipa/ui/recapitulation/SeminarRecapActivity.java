package com.vincode.simipa.ui.recapitulation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.PresenceRecapAdapter;
import com.vincode.simipa.adapter.SeminarRecapAdapter;
import com.vincode.simipa.model.SeminarRecap;
import com.vincode.simipa.util.TestPresenceRecapData;
import com.vincode.simipa.util.TestSeminarRecapData;

import java.util.ArrayList;

public class SeminarRecapActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private ArrayList<SeminarRecap> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_recap);
        rvCategory = findViewById(R.id.rv_seminarRecap);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(TestSeminarRecapData.getListSeminar());

        showRecyclerCardView();
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        SeminarRecapAdapter seminarRecapAdapter = new SeminarRecapAdapter(this);
        seminarRecapAdapter.setListSeminar(list);
        rvCategory.setAdapter(seminarRecapAdapter);
    }
}