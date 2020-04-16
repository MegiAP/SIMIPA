package com.vincode.simipa.ui.agenda;

import androidx.annotation.NonNull;
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
import android.widget.TextView;

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
    private ProgressBar pgBar,pgBar1,pgBar2;
    private AgendaAdapter adapter;
    private RecyclerView rvCategory, rvCategory1, rvCategory2;
    private TextView agendaKosong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        TimeUtil timeUtil = new TimeUtil();
        //String semester = timeUtil.getSemester();
        String tahun = timeUtil.getWaktu();

        getDataCollege(tahun);
        getDataPractice(tahun);
        //getDataSeminar(tahun);

        agendaKosong = findViewById(R.id.agendakosong);
        agendaKosong.setVisibility(View.GONE);

        pgBar = findViewById(R.id.pg_bar);
        pgBar.setVisibility(View.VISIBLE);
        pgBar1 = findViewById(R.id.pg_bar1);
        pgBar1.setVisibility(View.VISIBLE);
        /*pgBar2 = findViewById(R.id.pg_bar2);
        pgBar2.setVisibility(View.VISIBLE);*/

        rvCategory = findViewById(R.id.rv_college_agenda);
        rvCategory1 = findViewById(R.id.rv_practice_agenda);
        //rvCategory2 = findViewById(R.id.rv_seminar_agenda);

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AgendaActivity.this);
        rvCategory.setLayoutManager(new LinearLayoutManager(AgendaActivity.this));
        rvCategory1.setLayoutManager(new LinearLayoutManager(AgendaActivity.this));
        //rvCategory2.setLayoutManager(new LinearLayoutManager(AgendaActivity.this));
    }

    //Read data jadwal kuliah
    private void getDataCollege(String tahun) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<CollegeScheduleResponse> call = apiInterface.getCollegeData("Selasa", SharedPrefManager.getInstance(this).getUser().getUserLogin(), tahun, "Ganjil", "Teori");
        call.enqueue(new Callback<CollegeScheduleResponse>() {
            @Override
            public void onResponse(@NonNull Call<CollegeScheduleResponse> call, @NonNull Response<CollegeScheduleResponse> response) {
                if (response.body() != null) {
                    adapter = new AgendaAdapter(AgendaActivity.this, response.body().getRecords());
                    adapter.notifyDataSetChanged();
                    pgBar.setVisibility(View.GONE);
                    rvCategory.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<CollegeScheduleResponse> call, Throwable t) {
                    rvCategory.setVisibility(View.GONE);
            }
        });
    }

    //Read data jadwal praktikum
    private void getDataPractice(String tahun) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<CollegeScheduleResponse> call = apiInterface.getCollegeData("Selasa", SharedPrefManager.getInstance(this).getUser().getUserLogin(), tahun, "Ganjil", "Praktikum");
        call.enqueue(new Callback<CollegeScheduleResponse>() {
            @Override
            public void onResponse(@NonNull Call<CollegeScheduleResponse> call, @NonNull Response<CollegeScheduleResponse> response) {
                if (response.body() != null) {
                    adapter = new AgendaAdapter(AgendaActivity.this, response.body().getRecords());
                    adapter.notifyDataSetChanged();
                    pgBar1.setVisibility(View.GONE);
                    rvCategory1.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<CollegeScheduleResponse> call, Throwable t) {
                rvCategory1.setVisibility(View.GONE);
            }
        });
    }

    //Read data jadwal seminar
/*    private void getDataSeminar(String tahun) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<CollegeScheduleResponse> call = apiInterface.getCollegeData("Selasa", SharedPrefManager.getInstance(this).getUser().getUserLogin(), tahun, "Ganjil", "Praktikum");
        call.enqueue(new Callback<CollegeScheduleResponse>() {
            @Override
            public void onResponse(@NonNull Call<CollegeScheduleResponse> call, @NonNull Response<CollegeScheduleResponse> response) {
                if (response.body() != null) {
                    adapter = new AgendaAdapter(AgendaActivity.this, response.body().getRecords());
                    adapter.notifyDataSetChanged();
                    pgBar2.setVisibility(View.GONE);
                    rvCategory2.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<CollegeScheduleResponse> call, Throwable t) {
                pgBar2.setVisibility(View.GONE);
                rvCategory2.setVisibility(View.GONE);
                agendaKosong.setVisibility(View.VISIBLE);
            }
        });
    }*/
}

