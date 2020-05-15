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
    private ProgressBar pgBar,pgBar1,pgBar2,pgBar3,pgBar4;
    private AgendaAdapter adapter;
    private RecyclerView rvCategory, rvCategory1, rvCategory2, rvCategory3, rvCategory4;
    private TextView agendaKosong;
    private AgendaSeminarAdapter seminarScheduleAdapter;
    private HorizontalScrollView horizontalScrollView;
    private RelativeLayout rlKuliah,rlPraktikum,rlKP,rlUsul,rlHasil,showAll,rvKuliah,rvPraktikum,rvKP,rvUsul,rvHasil;
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

        rvKuliah = findViewById(R.id.rv_kuliah);
        rvPraktikum = findViewById(R.id.rv_praktikum);
        rvKP = findViewById(R.id.rv_kp);
        rvUsul = findViewById(R.id.rv_usul);
        rvHasil = findViewById(R.id.rv_hasil);

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
        pgBar3 = findViewById(R.id.pg_bar3);
        pgBar3.setVisibility(View.VISIBLE);
        pgBar4 = findViewById(R.id.pg_bar4);
        pgBar4.setVisibility(View.VISIBLE);

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
                    if (response.body().getResponsCode() == 200) {
                        adapter = new AgendaAdapter(AgendaActivity.this, response.body().getRecords());
                        adapter.notifyDataSetChanged();
                        pgBar.setVisibility(View.GONE);
                        rvCategory.setAdapter(adapter);
                    } else if (response.body().getResponsCode() == 404) {
                        rvKuliah.setVisibility(View.GONE);
                        pgBar.setVisibility(View.GONE);
                        rlKuliah.setVisibility(View.GONE);

                        sum += 1;
                    }
                }
            }

            @Override
            public void onFailure(Call<CollegeScheduleResponse> call, Throwable t) {
                pgBar.setVisibility(View.GONE);

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
                    if (response.body().getResponsCode() == 200) {
                        adapter = new AgendaAdapter(AgendaActivity.this, response.body().getRecords());
                        adapter.notifyDataSetChanged();
                        pgBar1.setVisibility(View.GONE);
                        rvCategory1.setAdapter(adapter);
                    } else if (response.body().getResponsCode() == 404) {
                        rvPraktikum.setVisibility(View.GONE);
                        pgBar1.setVisibility(View.GONE);
                        rlPraktikum.setVisibility(View.GONE);

                        sum += 1;
                    }
                }
            }

            @Override
            public void onFailure(Call<CollegeScheduleResponse> call, Throwable t) {
                pgBar1.setVisibility(View.GONE);

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
                    if (response.body().getResponsCode() == 200) {
                        seminarScheduleAdapter = new AgendaSeminarAdapter(AgendaActivity.this, response.body().getRecords());
                        seminarScheduleAdapter.notifyDataSetChanged();
                        pgBar2.setVisibility(View.GONE);
                        rvCategory2.setAdapter(seminarScheduleAdapter);
                    } else if (response.body().getResponsCode() == 404) {
                        rvKP.setVisibility(View.GONE);
                        pgBar2.setVisibility(View.GONE);
                        rlKP.setVisibility(View.GONE);

                        sum += 1;
                    }
                }
            }

            @Override
            public void onFailure(Call<SeminarScheduleResponse> call, Throwable t) {
                pgBar2.setVisibility(View.GONE);

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
                    if (response.body().getResponsCode() == 200) {
                        seminarScheduleAdapter = new AgendaSeminarAdapter(AgendaActivity.this, response.body().getRecords());
                        seminarScheduleAdapter.notifyDataSetChanged();
                        pgBar3.setVisibility(View.GONE);
                        rvCategory3.setAdapter(seminarScheduleAdapter);
                    } else if (response.body().getResponsCode() == 404) {
                        rvUsul.setVisibility(View.GONE);
                        pgBar3.setVisibility(View.GONE);
                        rlUsul.setVisibility(View.GONE);

                        sum += 1;
                    }
                }
            }

            @Override
            public void onFailure(Call<SeminarScheduleResponse> call, Throwable t) {
                pgBar3.setVisibility(View.GONE);

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
                    if (response.body().getResponsCode() == 200) {
                        seminarScheduleAdapter = new AgendaSeminarAdapter(AgendaActivity.this, response.body().getRecords());
                        seminarScheduleAdapter.notifyDataSetChanged();
                        rvCategory4.setAdapter(seminarScheduleAdapter);
                        pgBar4.setVisibility(View.GONE);
                    } else if (response.body().getResponsCode() == 404) {
                        rvHasil.setVisibility(View.GONE);
                        pgBar4.setVisibility(View.GONE);
                        rlHasil.setVisibility(View.GONE);

                        sum += 1;
                    }
                }
            }

            @Override
            public void onFailure(Call<SeminarScheduleResponse> call, Throwable t) {
                pgBar4.setVisibility(View.GONE);

                Log.d("c", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.agenda_kuliah:
                if (rvKuliah.getVisibility() == View.VISIBLE) {
                    rvKuliah.setVisibility(View.GONE);
                    clsKuliah.setImageResource(R.drawable.ic_autorenew);
                } else {
                    rvKuliah.setVisibility(View.VISIBLE);
                    clsKuliah.setImageResource(R.drawable.ic_add_white);
                }
                break;
            case R.id.agenda_praktikum:
                if (rvPraktikum.getVisibility() == View.VISIBLE) {
                    rvPraktikum.setVisibility(View.GONE);
                    clsPraktikum.setImageResource(R.drawable.ic_autorenew);
                } else {
                    rvPraktikum.setVisibility(View.VISIBLE);
                    clsPraktikum.setImageResource(R.drawable.ic_add_white);
                }
                break;
            case R.id.agenda_seminar_kp:
                if (rvKP.getVisibility() == View.VISIBLE) {
                    rvKP.setVisibility(View.GONE);
                    clsKP.setImageResource(R.drawable.ic_autorenew);
                } else {
                    rvKP.setVisibility(View.VISIBLE);
                    clsKP.setImageResource(R.drawable.ic_add_white);
                }
                break;
            case R.id.agenda_seminar_usul:
                if (rvUsul.getVisibility() == View.VISIBLE) {
                    rvUsul.setVisibility(View.GONE);
                    clsUsul.setImageResource(R.drawable.ic_autorenew);
                } else {
                    rvUsul.setVisibility(View.VISIBLE);
                    clsUsul.setImageResource(R.drawable.ic_add_white);
                }
                break;
            case R.id.agenda_seminar_hasil:
                if (rvHasil.getVisibility() == View.VISIBLE) {
                    rvHasil.setVisibility(View.GONE);
                    clsHasil.setImageResource(R.drawable.ic_autorenew);
                } else {
                    rvHasil.setVisibility(View.VISIBLE);
                    clsHasil.setImageResource(R.drawable.ic_add_white);
                }
                break;
            case R.id.showAll:
                if (rvKuliah.getVisibility() == View.GONE || rvPraktikum.getVisibility() == View.GONE || rvKP.getVisibility() == View.GONE || rvUsul.getVisibility() == View.GONE || rvHasil.getVisibility() == View.GONE){
                    rvKuliah.setVisibility(View.VISIBLE);
                    rvPraktikum.setVisibility(View.VISIBLE);
                    rvKP.setVisibility(View.VISIBLE);
                    rvUsul.setVisibility(View.VISIBLE);
                    rvHasil.setVisibility(View.VISIBLE);
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

