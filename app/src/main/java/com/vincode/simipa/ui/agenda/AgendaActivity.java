package com.vincode.simipa.ui.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.AgendaAdapter;
import com.vincode.simipa.adapter.CollegeScheduleAdapter;
import com.vincode.simipa.model.Agenda;
import com.vincode.simipa.model.CollegeScheduleResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;
import com.vincode.simipa.util.TimeUtil;

import java.util.ArrayList;
import java.util.Objects;

public class AgendaActivity extends AppCompatActivity {
    private ProgressBar pgBar;
    private AgendaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        TimeUtil timeUtil = new TimeUtil();
        //String semester = timeUtil.getSemester();
        String tahun = timeUtil.getWaktu();

        getData(tahun);

        pgBar = findViewById(R.id.pg_bar);
        pgBar.setVisibility(View.VISIBLE);
        RecyclerView rvCategory = findViewById(R.id.rv_Agenda);
        adapter = new AgendaAdapter();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AgendaActivity.this);
        rvCategory.setLayoutManager(layoutManager);
        rvCategory.setAdapter(adapter);
    }

    private void getData(String tahun) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<CollegeScheduleResponse> call = apiInterface.getCollegeData("Selasa", SharedPrefManager.getInstance(this).getUser().getUserLogin(), tahun, "Ganjil", "Teori");
        call.enqueue(new Callback<CollegeScheduleResponse>() {
            @Override
            public void onResponse(Call<CollegeScheduleResponse> call, Response<CollegeScheduleResponse> response) {
                if (response.body() != null) {
                    adapter.setAgendaList(response.body().getRecords());
                    adapter.notifyDataSetChanged();
                    pgBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<CollegeScheduleResponse> call, Throwable t) {

            }
        });
    }
}

