package com.vincode.simipa.ui.beasiswa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.ScholarshipListAdapter;
import com.vincode.simipa.model.ScholarshipListResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.TimeUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarBeasiswaFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ScholarshipListAdapter scholarshipListAdapter;
    private String tanggal;

    public DaftarBeasiswaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daftar_beasiswa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_category);
        scholarshipListAdapter = new ScholarshipListAdapter();

        progressBar = view.findViewById(R.id.pg_bar);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TimeUtil timeUtil = new TimeUtil();
        tanggal = timeUtil.getTanggal();

        showRecyclerCardView();
        getData();
    }

    private void showRecyclerCardView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(scholarshipListAdapter);
    }

    public void getData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ScholarshipListResponse> call = apiInterface.getDaftarBeasiswa(tanggal);
        call.enqueue(new Callback<ScholarshipListResponse>() {
            @Override
            public void onResponse(Call<ScholarshipListResponse> call, Response<ScholarshipListResponse> response) {
                if (response.body() != null) {
                    progressBar.setVisibility(View.GONE);
                    scholarshipListAdapter.setListScholarship(response.body().getRecords());
                    scholarshipListAdapter.notifyDataSetChanged();
                }
                else {
                    Log.d("Null", " ");
                }
            }

            @Override
            public void onFailure(Call<ScholarshipListResponse> call, Throwable t) {

            }
        });
    }
}
