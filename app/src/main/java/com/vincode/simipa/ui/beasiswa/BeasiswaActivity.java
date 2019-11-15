package com.vincode.simipa.ui.beasiswa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vincode.simipa.R;

import java.util.ArrayList;

public class BeasiswaActivity extends AppCompatActivity {

    private RecyclerView rvCategory;
    private ArrayList<Beasiswa> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beasiswa);

        rvCategory = (RecyclerView)findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(BeasiswaData.getListData());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Beasiswa");

        showRecyclerCardView();

    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewBeasiswaAdapter cardViewPresidentAdapter = new CardViewBeasiswaAdapter(this);
        cardViewPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(cardViewPresidentAdapter);
    }
}
