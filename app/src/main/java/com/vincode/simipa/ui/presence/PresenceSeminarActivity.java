package com.vincode.simipa.ui.presence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.PresenceSeminarAdapter;
import com.vincode.simipa.model.PresenceSeminarResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenceSeminarActivity extends AppCompatActivity {

    private PresenceSeminarAdapter adapter;
    private RecyclerView rvSeminar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presence_seminar);

        rvSeminar = findViewById(R.id.rv_seminar);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        adapter = new PresenceSeminarAdapter(this);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Jadwal Seminar");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = dateFormat.format(new Date());

        setLayout();
        getData(date);
    }

    private void setLayout(){
        rvSeminar.setLayoutManager(new LinearLayoutManager(this));
        rvSeminar.setHasFixedSize(true);
        rvSeminar.setAdapter(adapter);
    }

    private void getData(String tgl){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<PresenceSeminarResponse> call = apiInterface.getPresenceSeminar(tgl);

        call.enqueue(new Callback<PresenceSeminarResponse>() {
            @Override
            public void onResponse(@NonNull Call<PresenceSeminarResponse> call, @NonNull Response<PresenceSeminarResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    adapter.setListSeminar(response.body().getRecords());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PresenceSeminarResponse> call, @NonNull Throwable t) {
                Log.d("c", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
