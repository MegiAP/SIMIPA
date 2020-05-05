package com.vincode.simipa.ui.agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.AgendaAdapter;
import com.vincode.simipa.adapter.AgendaSeminarAdapter;
import com.vincode.simipa.adapter.CollegeScheduleAdapter;
import com.vincode.simipa.adapter.SeminarScheduleAdapter;
import com.vincode.simipa.model.Agenda;
import com.vincode.simipa.model.CollegeScheduleResponse;
import com.vincode.simipa.model.SeminarScheduleResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;
import com.vincode.simipa.util.TimeUtil;

import java.util.ArrayList;
import java.util.Objects;

public class AgendaActivity extends AppCompatActivity {
    private ProgressBar pgBar,pgBar1,pgBar2;
    private AgendaAdapter adapter;
    private RecyclerView rvCategory, rvCategory1, rvCategory2, rvCategory3, rvCategory4;
    private TextView agendaKosong;
    private AgendaSeminarAdapter seminarScheduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        TimeUtil timeUtil = new TimeUtil();
        //String semester = timeUtil.getSemester();
        String tahun = timeUtil.getWaktu();

        getDataCollege(tahun);
        getDataPractice(tahun);
        getDataSeminarKP();
        getDataSeminarUsul();
        getDataSeminarHasil();

        agendaKosong = findViewById(R.id.agendakosong);
        agendaKosong.setVisibility(View.GONE);

        pgBar = findViewById(R.id.pg_bar);
        pgBar.setVisibility(View.VISIBLE);
        pgBar1 = findViewById(R.id.pg_bar1);
        pgBar1.setVisibility(View.VISIBLE);
        pgBar2 = findViewById(R.id.pg_bar2);
        pgBar2.setVisibility(View.VISIBLE);

        rvCategory = findViewById(R.id.rv_college_agenda);
        rvCategory1 = findViewById(R.id.rv_practice_agenda);
        rvCategory2 = findViewById(R.id.rv_seminarkp_agenda);
        rvCategory3 = findViewById(R.id.rv_seminarusul_agenda);
        rvCategory4 = findViewById(R.id.rv_seminarhasil_agenda);

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AgendaActivity.this);
        rvCategory.setLayoutManager(new LinearLayoutManager(AgendaActivity.this));
        rvCategory1.setLayoutManager(new LinearLayoutManager(AgendaActivity.this));
        rvCategory2.setLayoutManager(new LinearLayoutManager(AgendaActivity.this));
        rvCategory3.setLayoutManager(new LinearLayoutManager(AgendaActivity.this));
        rvCategory4.setLayoutManager(new LinearLayoutManager(AgendaActivity.this));
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
                pgBar.setVisibility(View.GONE);
                rvCategory.setVisibility(View.GONE);
                agendaKosong.setVisibility(View.VISIBLE);
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
                pgBar1.setVisibility(View.GONE);
                rvCategory1.setVisibility(View.GONE);
                agendaKosong.setVisibility(View.VISIBLE);
            }
        });
    }

    //Read data jadwal seminar
    private void getDataSeminarKP() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<SeminarScheduleResponse> call = apiInterface.getSeminarData("Seminar Kerja Praktek", "Fisika", "2019-11-05");

        call.enqueue(new Callback<SeminarScheduleResponse>() {
            @Override
            public void onResponse(Call<SeminarScheduleResponse> call, Response<SeminarScheduleResponse> response) {
                if (response.body() != null) {

                    seminarScheduleAdapter = new AgendaSeminarAdapter(getBaseContext(), response.body().getRecords());
                    seminarScheduleAdapter.notifyDataSetChanged();
                    rvCategory2.setAdapter(seminarScheduleAdapter);

                   /* seminarScheduleAdapter.setListSeminarSchedule(response.body().getRecords());
                    seminarScheduleAdapter.notifyDataSetChanged();*/
                }
            }

            @Override
            public void onFailure(Call<SeminarScheduleResponse> call, Throwable t) {
                Log.d("c", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public void getDataSeminarUsul() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<SeminarScheduleResponse> call = apiInterface.getSeminarData("Seminar Usul", "Ilmu Komputer", "2019-04-18");

        call.enqueue(new Callback<SeminarScheduleResponse>() {
            @Override
            public void onResponse(Call<SeminarScheduleResponse> call, Response<SeminarScheduleResponse> response) {
                if (response.body() != null) {
                    seminarScheduleAdapter = new AgendaSeminarAdapter(getBaseContext(), response.body().getRecords());
                    seminarScheduleAdapter.notifyDataSetChanged();
                    rvCategory3.setAdapter(seminarScheduleAdapter);
                }
            }

            @Override
            public void onFailure(Call<SeminarScheduleResponse> call, Throwable t) {
                Log.d("c", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
    public void getDataSeminarHasil() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<SeminarScheduleResponse> call = apiInterface.getSeminarData("Seminar Hasil", "Fisika", "2019-05-10");

        call.enqueue(new Callback<SeminarScheduleResponse>() {
            @Override
            public void onResponse(Call<SeminarScheduleResponse> call, Response<SeminarScheduleResponse> response) {
                if (response.body() != null) {
                    seminarScheduleAdapter = new AgendaSeminarAdapter(getBaseContext(), response.body().getRecords());
                    seminarScheduleAdapter.notifyDataSetChanged();
                    rvCategory4.setAdapter(seminarScheduleAdapter);
                    pgBar2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<SeminarScheduleResponse> call, Throwable t) {
                Log.d("c", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}

