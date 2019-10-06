package com.vincode.simipa.beasiswa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vincode.simipa.R;
import com.vincode.simipa.krs.CardViewKRSAdapter;
import com.vincode.simipa.krs.KRS;
import com.vincode.simipa.krs.KRSData;

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

        showRecyclerCardView();

    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewBeasiswaAdapter cardViewPresidentAdapter = new CardViewBeasiswaAdapter(this);
        cardViewPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(cardViewPresidentAdapter);
    }
}
