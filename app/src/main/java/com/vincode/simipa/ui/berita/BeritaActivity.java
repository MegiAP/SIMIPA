package com.vincode.simipa.ui.berita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.BeritaAdapterAll;
import com.vincode.simipa.model.BeritaResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaActivity extends AppCompatActivity {

    private BeritaAdapterAll adapter;
    private RecyclerView rvBerita;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);

        rvBerita = findViewById(R.id.rv_berita);
        progressBar = findViewById(R.id.progress_bar);
        adapter = new BeritaAdapterAll(this);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Berita FMIPA");

        progressBar.setVisibility(View.VISIBLE);
        setLayout();
        getData();
    }

    private void setLayout(){
        rvBerita.setLayoutManager(new LinearLayoutManager(this));
        rvBerita.setHasFixedSize(true);
        rvBerita.setAdapter(adapter);
    }

    private void getData(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<BeritaResponse> call = apiInterface.getListNews();
        call.enqueue(new Callback<BeritaResponse>() {
            @Override
            public void onResponse(@NonNull Call<BeritaResponse> call, @NonNull Response<BeritaResponse> response) {
                if (response.body() != null) {
                    progressBar.setVisibility(View.GONE);
                    adapter.setListBerita(response.body().getNews());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<BeritaResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
