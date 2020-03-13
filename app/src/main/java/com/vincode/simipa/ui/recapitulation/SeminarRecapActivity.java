package com.vincode.simipa.ui.recapitulation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.PresenceRecapAdapter;
import com.vincode.simipa.adapter.SeminarRecapAdapter;
import com.vincode.simipa.model.SeminarRecap;
import com.vincode.simipa.model.SeminarResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;
import com.vincode.simipa.util.TestPresenceRecapData;
import com.vincode.simipa.util.TestSeminarRecapData;

import java.util.ArrayList;

public class SeminarRecapActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private ArrayList<SeminarRecap> list;

    private ProgressBar pgBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_recap);

        pgBar = findViewById(R.id.pg_bar);
        pgBar.setVisibility(View.VISIBLE);

        rvCategory = findViewById(R.id.rv_seminar_recap);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(TestSeminarRecapData.getListSeminar());

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.seminar);

        showRecyclerCardView();
//        getData();
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        SeminarRecapAdapter seminarRecapAdapter = new SeminarRecapAdapter(this);
        seminarRecapAdapter.setListSeminar(list);
        rvCategory.setAdapter(seminarRecapAdapter);
        pgBar.setVisibility(View.GONE);
    }

/*    private void getData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SeminarResponse> call = apiInterface.getRecapSeminar(SharedPrefManager.getInstance(this).getUser().getUserLogin());
        call.enqueue(new Callback<SeminarResponse>() {
            @Override
            public void onResponse(Call<SeminarResponse> call, Response<SeminarResponse> response) {
                if (response.body() != null) {

                } else {
                    Log.d("Tes ", " ");
                }
            }

            @Override
            public void onFailure(Call<SeminarResponse> call, Throwable t) {
                Log.d("gagal", " ");
            }
        });
    }*/
}