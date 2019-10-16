package com.vincode.simipa.ui.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.AgendaAdapter;
import com.vincode.simipa.model.Agenda;

import java.util.ArrayList;

public class AgendaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AgendaAdapter adapter;
    private ArrayList<Agenda> agendaArrayList;

    private androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        toolbar = (Toolbar) findViewById(R.id.tb_agenda);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.rv_Agenda);

        adapter = new AgendaAdapter(agendaArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AgendaActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
    void addData(){
        agendaArrayList = new ArrayList<>();
        agendaArrayList.add(new Agenda("07.30", "09.10", "Android", "Putra", "GIK", "Kuliah"));
        agendaArrayList.add(new Agenda("07.30", "09.10", "Android", "Putra", "GIK", "Kuliah"));
        agendaArrayList.add(new Agenda("07.30", "09.10", "Android", "Putra", "GIK", "Seminar"));
        agendaArrayList.add(new Agenda("07.30", "09.10", "Android", "Putra", "GIK", "Kuliah"));
        agendaArrayList.add(new Agenda("07.30", "09.10", "Android", "Putra", "GIK", "Kuliah"));
        agendaArrayList.add(new Agenda("07.30", "09.10", "Android", "Putra", "GIK", "Kuliah"));
        agendaArrayList.add(new Agenda("07.30", "09.10", "Android", "Putra", "GIK", "Kuliah"));
        agendaArrayList.add(new Agenda("07.30", "09.10", "Android", "Putra", "GIK", "Kuliah"));
        agendaArrayList.add(new Agenda("07.30", "09.10", "Android", "Putra", "GIK", "Kuliah"));

    }
}

