package com.vincode.simipa.ui.schedule;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.SeminarScheduleAdapter;
import com.vincode.simipa.model.SeminarScheduleResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeminarUsulFragment extends Fragment {
    private SeminarScheduleAdapter seminarScheduleAdapter;
    private RecyclerView recyclerView;


    public SeminarUsulFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seminar_usul, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_usul);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        seminarScheduleAdapter = new SeminarScheduleAdapter(getActivity());

        setLayout();
        getData();

    }

    private void setLayout(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(seminarScheduleAdapter);
    }

    private void getData(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<SeminarScheduleResponse> call = apiInterface.getSeminarData("Seminar Usul", "Ilmu Komputer", "2019-04-18");

        call.enqueue(new Callback<SeminarScheduleResponse>() {
            @Override
            public void onResponse(Call<SeminarScheduleResponse> call, Response<SeminarScheduleResponse> response) {
                if (response.body() != null) {
                    seminarScheduleAdapter.setListSeminarSchedule(response.body().getRecords());
                    seminarScheduleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<SeminarScheduleResponse> call, Throwable t) {
                Log.d("c", Objects.requireNonNull(t.getMessage()));
            }
        });



    }
}

