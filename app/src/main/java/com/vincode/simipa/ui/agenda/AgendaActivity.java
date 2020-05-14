package com.vincode.simipa.ui.agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.AgendaAdapter;
import com.vincode.simipa.adapter.AgendaSeminarAdapter;
import com.vincode.simipa.model.CollegeScheduleResponse;
import com.vincode.simipa.model.SeminarScheduleResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;
import com.vincode.simipa.util.TimeUtil;

import java.util.Objects;

public class AgendaActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar pgBar,pgBar1,pgBar2;
    private AgendaAdapter adapter;
    private RecyclerView rvCategory, rvCategory1, rvCategory2, rvCategory3, rvCategory4;
    private TextView agendaKosong,tvKuliah,tvPraktikum,tvKP,tvUsul,tvHasil;
    private AgendaSeminarAdapter seminarScheduleAdapter;
    private HorizontalScrollView horizontalScrollView;
    private RelativeLayout rlKuliah,rlPraktikum,rlKP,rlUsul,rlHasil,showAll;
    private ImageView clsKuliah,clsPraktikum,clsKP,clsUsul,clsHasil;
    private Integer sum = 0;

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

        //horizontalScrollView = findViewById(R.id.horizontalScroll);
        //horizontalScrollView.setVisibility(View.GONE);
        clsKuliah = findViewById(R.id.close_agenda_kuliah);
        clsPraktikum = findViewById(R.id.close_agenda_praktikum);
        clsKP = findViewById(R.id.close_agenda_kp);
        clsUsul = findViewById(R.id.close_usul);
        clsHasil = findViewById(R.id.close_hasil);

        rlKuliah = findViewById(R.id.agenda_kuliah);
        rlKuliah.setOnClickListener(this);
        rlPraktikum = findViewById(R.id.agenda_praktikum);
        rlPraktikum.setOnClickListener(this);
        rlKP = findViewById(R.id.agenda_seminar_kp);
        rlKP.setOnClickListener(this);
        rlUsul = findViewById(R.id.agenda_seminar_usul);
        rlUsul.setOnClickListener(this);
        rlHasil = findViewById(R.id.agenda_seminar_hasil);
        rlHasil.setOnClickListener(this);
        showAll = findViewById(R.id.showAll);
        showAll.setOnClickListener(this);

        tvKuliah = findViewById(R.id.tv_agenda_kuliah);
        tvPraktikum = findViewById(R.id.tv_agenda_praktikum);
        tvKP = findViewById(R.id.tv_agenda_kp);
        tvUsul = findViewById(R.id.tv_agenda_usul);
        tvHasil = findViewById(R.id.tv_agenda_hasil);

        agendaKosong = findViewById(R.id.agendakosong);
        agendaKosong.setVisibility(View.GONE);
        if (sum == 5) {
            agendaKosong.setVisibility(View.VISIBLE);
        } else {
            agendaKosong.setVisibility(View.GONE);
        }

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
                sum += 1;

                Log.d("c", Objects.requireNonNull(t.getMessage()));
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
                sum += 1;

                Log.d("c", Objects.requireNonNull(t.getMessage()));
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

                    seminarScheduleAdapter = new AgendaSeminarAdapter(AgendaActivity.this, response.body().getRecords());
                    seminarScheduleAdapter.notifyDataSetChanged();
                    rvCategory2.setAdapter(seminarScheduleAdapter);
                }
            }

            @Override
            public void onFailure(Call<SeminarScheduleResponse> call, Throwable t) {
                rvCategory2.setVisibility(View.GONE);
                sum += 1;

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
                    seminarScheduleAdapter = new AgendaSeminarAdapter(AgendaActivity.this, response.body().getRecords());
                    seminarScheduleAdapter.notifyDataSetChanged();
                    rvCategory3.setAdapter(seminarScheduleAdapter);
                }
            }

            @Override
            public void onFailure(Call<SeminarScheduleResponse> call, Throwable t) {
                rvCategory3.setVisibility(View.GONE);
                sum += 1;

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
                    seminarScheduleAdapter = new AgendaSeminarAdapter(AgendaActivity.this, response.body().getRecords());
                    seminarScheduleAdapter.notifyDataSetChanged();
                    rvCategory4.setAdapter(seminarScheduleAdapter);
                    pgBar2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<SeminarScheduleResponse> call, Throwable t) {
                rvCategory4.setVisibility(View.GONE);
                pgBar2.setVisibility(View.GONE);
                sum += 1;

                Log.d("c", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.agenda_kuliah:
                if (rvCategory.getVisibility() == View.VISIBLE) {
                    rvCategory.setVisibility(View.GONE);
                    tvKuliah.setVisibility(View.GONE);
                    clsKuliah.setImageResource(R.drawable.ic_autorenew);
                } else {
                    rvCategory.setVisibility(View.VISIBLE);
                    tvKuliah.setVisibility(View.VISIBLE);
                    clsKuliah.setImageResource(R.drawable.ic_add_white);
                }
                break;
            case R.id.agenda_praktikum:
                if (rvCategory1.getVisibility() == View.VISIBLE) {
                    rvCategory1.setVisibility(View.GONE);
                    tvPraktikum.setVisibility(View.GONE);
                    clsPraktikum.setImageResource(R.drawable.ic_autorenew);
                } else {
                    rvCategory1.setVisibility(View.VISIBLE);
                    tvPraktikum.setVisibility(View.VISIBLE);
                    clsPraktikum.setImageResource(R.drawable.ic_add_white);
                }
                break;
            case R.id.agenda_seminar_kp:
                if (rvCategory2.getVisibility() == View.VISIBLE) {
                    rvCategory2.setVisibility(View.GONE);
                    tvKP.setVisibility(View.GONE);
                    clsKP.setImageResource(R.drawable.ic_autorenew);
                } else {
                    rvCategory2.setVisibility(View.VISIBLE);
                    tvKP.setVisibility(View.VISIBLE);
                    clsKP.setImageResource(R.drawable.ic_add_white);
                }
                break;
            case R.id.agenda_seminar_usul:
                if (rvCategory3.getVisibility() == View.VISIBLE) {
                    rvCategory3.setVisibility(View.GONE);
                    tvUsul.setVisibility(View.GONE);
                    clsUsul.setImageResource(R.drawable.ic_autorenew);
                } else {
                    rvCategory3.setVisibility(View.VISIBLE);
                    tvUsul.setVisibility(View.VISIBLE);
                    clsUsul.setImageResource(R.drawable.ic_add_white);
                }
                break;
            case R.id.agenda_seminar_hasil:
                if (rvCategory4.getVisibility() == View.VISIBLE) {
                    rvCategory4.setVisibility(View.GONE);
                    tvHasil.setVisibility(View.GONE);
                    clsHasil.setImageResource(R.drawable.ic_autorenew);
                } else {
                    rvCategory4.setVisibility(View.VISIBLE);
                    tvHasil.setVisibility(View.VISIBLE);
                    clsHasil.setImageResource(R.drawable.ic_add_white);
                }
                break;
            case R.id.showAll:
                if (tvKuliah.getVisibility() == View.GONE || tvPraktikum.getVisibility() == View.GONE || tvKP.getVisibility() == View.GONE || tvUsul.getVisibility() == View.GONE || tvHasil.getVisibility() == View.GONE){
                    tvKuliah.setVisibility(View.VISIBLE);
                    tvPraktikum.setVisibility(View.VISIBLE);
                    tvKP.setVisibility(View.VISIBLE);
                    tvUsul.setVisibility(View.VISIBLE);
                    tvHasil.setVisibility(View.VISIBLE);
                    rvCategory.setVisibility(View.VISIBLE);
                    rvCategory1.setVisibility(View.VISIBLE);
                    rvCategory2.setVisibility(View.VISIBLE);
                    rvCategory3.setVisibility(View.VISIBLE);
                    rvCategory4.setVisibility(View.VISIBLE);
                    clsKuliah.setImageResource(R.drawable.ic_add_white);
                    clsPraktikum.setImageResource(R.drawable.ic_add_white);
                    clsKP.setImageResource(R.drawable.ic_add_white);
                    clsUsul.setImageResource(R.drawable.ic_add_white);
                    clsHasil.setImageResource(R.drawable.ic_add_white);
                } else {
                    Toast.makeText(this, "Semua agenda telah tampil", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}

