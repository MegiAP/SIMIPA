package com.vincode.simipa.krs;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vincode.simipa.R;

import java.util.ArrayList;

public class KRSActivity extends AppCompatActivity {

    private RecyclerView rvCategory;
    private ArrayList<KRS> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krs);

        rvCategory = (RecyclerView)findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(KRSData.getListData());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("KRS");

        showRecyclerCardView();

    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewKRSAdapter cardViewPresidentAdapter = new CardViewKRSAdapter(this);
        cardViewPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(cardViewPresidentAdapter);
    }
}
