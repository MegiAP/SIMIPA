package com.vincode.simipa.ui.beasiswa;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.ScholarshipAdapter;
import com.vincode.simipa.model.ScholarshipResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;

public class BeasiswaActivity extends AppCompatActivity {

    private RecyclerView rvCategory;
    private ScholarshipAdapter scholarshipAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beasiswa);

        rvCategory = findViewById(R.id.rv_category);
        scholarshipAdapter = new ScholarshipAdapter();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.scholarship);

        showRecyclerCardView();
        getData();

    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        rvCategory.setHasFixedSize(true);
        rvCategory.setAdapter(scholarshipAdapter);
    }

    private void getData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ScholarshipResponse> call = apiInterface.getScholarshipData(SharedPrefManager.getInstance(this).getUser().getUserLogin());
        call.enqueue(new Callback<ScholarshipResponse>() {
            @Override
            public void onResponse(Call<ScholarshipResponse> call, Response<ScholarshipResponse> response) {
                if (response.body() != null) {
                    scholarshipAdapter.setListScholarship(response.body().getRecords());
                    scholarshipAdapter.notifyDataSetChanged();
                }
                else {
                    Log.d("tes", "ssss");
                }
            }

            @Override
            public void onFailure(Call<ScholarshipResponse> call, Throwable t) {

            }
        });
    }
}
