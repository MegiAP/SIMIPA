package com.vincode.simipa.ui.simipa_parents;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.SimipaParentsListParentApprovedAdapter;
import com.vincode.simipa.adapter.SimipaParentsListParentRequestAdapter;
import com.vincode.simipa.model.SimipaParentsListParentRecord;
import com.vincode.simipa.model.SimipaParentsListParentResponce;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class SimipaParentsActivity extends AppCompatActivity {

    RecyclerView recyclerViewRequest, recyclerViewApproved;
    SimipaParentsListParentApprovedAdapter listParentApprovedAdapter;
    SimipaParentsListParentRequestAdapter listParentRequestAdapter;
    List<SimipaParentsListParentRecord> listParentApproved = new ArrayList<>();
    List<SimipaParentsListParentRecord> listParentRequest = new ArrayList<>();
    List<SimipaParentsListParentRecord> listParentRejected = new ArrayList<>();
    LinearLayout viewRequestLoading, viewApprovedLoading;
    ProgressBar progressBarRequest, progressBarApproved;
    TextView tvDataEmptyRequest, tvDataEmptyApproved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simipa_parents);

        viewRequestLoading = findViewById(R.id.view_request_loading);
        viewApprovedLoading = findViewById(R.id.view_approved_loading);
        progressBarRequest = findViewById(R.id.progress_bar_request);
        progressBarApproved = findViewById(R.id.progress_bar_approved);
        tvDataEmptyRequest = findViewById(R.id.data_empty_request);
        tvDataEmptyApproved = findViewById(R.id.data_empty_approved);

        recyclerViewRequest = findViewById(R.id.rv_list_parent_request);
        recyclerViewRequest.setHasFixedSize(true);
        recyclerViewRequest.setLayoutManager(new LinearLayoutManager(SimipaParentsActivity.this));

        recyclerViewApproved = findViewById(R.id.rv_list_parent_approved);
        recyclerViewApproved.setHasFixedSize(true);
        recyclerViewApproved.setLayoutManager(new LinearLayoutManager(SimipaParentsActivity.this));

        getData();
    }

    public void getData() {

        listParentRequest.clear();
        listParentApproved.clear();
        listParentRejected.clear();

        recyclerViewRequest.setVisibility(View.GONE);
        recyclerViewApproved.setVisibility(View.GONE);
        viewRequestLoading.setVisibility(View.VISIBLE);
        viewApprovedLoading.setVisibility(View.VISIBLE);
        tvDataEmptyRequest.setVisibility(View.GONE);
        tvDataEmptyApproved.setVisibility(View.GONE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<SimipaParentsListParentResponce> call = apiInterface.listParent(SharedPrefManager.getNpmStudent());
        call.enqueue(new Callback<SimipaParentsListParentResponce>() {

            @Override
            public void onResponse(@NotNull Call<SimipaParentsListParentResponce> call, @NotNull retrofit2.Response<SimipaParentsListParentResponce> response) {

                Log.d("Get List", "on Responce");

                if (response.isSuccessful()) {

                    Log.d("Get List", "Respon Successful");

                    assert response.body() != null;
                    if (response.body().getTotalRecords() > 0) {

                        Log.d("Get List", "get total record");

                        for (SimipaParentsListParentRecord listParentRecord : response.body().getRecords()) {
                            if (listParentRecord.getStatus().contains("Belum")) {
                                listParentRequest.add(listParentRecord);
                            } else if (listParentRecord.getStatus().contains("Ditolak")) {
                                listParentRejected.add(listParentRecord);
                            } else {
                                listParentApproved.add(listParentRecord);
                            }
                        }

                        if (listParentRequest.size() > 0) {

                            viewRequestLoading.setVisibility(View.GONE);
                            recyclerViewRequest.setVisibility(View.VISIBLE);

                            listParentRequestAdapter = new SimipaParentsListParentRequestAdapter(SimipaParentsActivity.this);
                            listParentRequestAdapter.setListParents(listParentRequest);
                            listParentRequestAdapter.notifyDataSetChanged();
                            recyclerViewRequest.setAdapter(listParentRequestAdapter);
                        } else {
                            progressBarRequest.setVisibility(View.GONE);
                            tvDataEmptyRequest.setVisibility(View.VISIBLE);
                            tvDataEmptyRequest.setText(getResources().getString(R.string.data_kosong));
                        }

                        if (listParentApproved.size() > 0) {

                            viewApprovedLoading.setVisibility(View.GONE);
                            recyclerViewApproved.setVisibility(View.VISIBLE);

                            listParentApprovedAdapter = new SimipaParentsListParentApprovedAdapter(SimipaParentsActivity.this);
                            listParentApprovedAdapter.setListParents(listParentApproved);
                            listParentApprovedAdapter.notifyDataSetChanged();
                            recyclerViewApproved.setAdapter(listParentApprovedAdapter);
                        } else {
                            progressBarApproved.setVisibility(View.GONE);
                            tvDataEmptyApproved.setVisibility(View.VISIBLE);
                            tvDataEmptyApproved.setText(getResources().getString(R.string.data_kosong));
                        }

                    } else {
                        Log.d("Get List", "not get total record");

                        progressBarRequest.setVisibility(View.GONE);
                        tvDataEmptyRequest.setVisibility(View.VISIBLE);
                        tvDataEmptyRequest.setText(getResources().getString(R.string.data_kosong));

                        progressBarApproved.setVisibility(View.GONE);
                        tvDataEmptyApproved.setVisibility(View.VISIBLE);
                        tvDataEmptyApproved.setText(getResources().getString(R.string.data_kosong));
                    }
                } else {
                    Log.d("Get List", "Respon unSuccessful");
                }
            }

            @Override
            public void onFailure(@NotNull Call<SimipaParentsListParentResponce> call, @NotNull Throwable t) {
                Log.d("Get List", "Respon unFailure");

                progressBarRequest.setVisibility(View.GONE);
                tvDataEmptyRequest.setVisibility(View.VISIBLE);
                tvDataEmptyRequest.setText(getResources().getString(R.string.server_error));

                progressBarApproved.setVisibility(View.GONE);
                tvDataEmptyApproved.setVisibility(View.VISIBLE);
                tvDataEmptyApproved.setText(getResources().getString(R.string.server_error));
            }
        });
    }
}