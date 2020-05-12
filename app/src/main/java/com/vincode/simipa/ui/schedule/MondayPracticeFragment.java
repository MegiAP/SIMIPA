package com.vincode.simipa.ui.schedule;

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
import com.vincode.simipa.adapter.CollegeScheduleAdapter;
import com.vincode.simipa.adapter.PracticeScheduleAdapter;
import com.vincode.simipa.model.CollegeScheduleResponse;
import com.vincode.simipa.model.CollegeScheduleResult;
import com.vincode.simipa.model.PracticeScheduleResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MondayPracticeFragment extends Fragment {
    private PracticeScheduleAdapter practiceScheduleAdapter;
    private RecyclerView recyclerView;
    public MondayPracticeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monday_practice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_mondaypractice);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        practiceScheduleAdapter = new PracticeScheduleAdapter(getActivity());

        setLayout();
        getPracticeData();

    }

    private void setLayout(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(practiceScheduleAdapter);
    }

    private void getPracticeData(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<PracticeScheduleResponse> call = apiInterface.getPracticeData("senin", "1617051103","2019/2020","Ganjil", "Praktikum" );

        call.enqueue(new Callback<PracticeScheduleResponse>() {
            @Override
            public void onResponse(Call<PracticeScheduleResponse> call, Response<PracticeScheduleResponse> response) {
                if (response.body() != null) {
                    practiceScheduleAdapter.setListPracticeSchedule(response.body().getRecords());
                    practiceScheduleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PracticeScheduleResponse> call, Throwable t) {
                Log.d("c", Objects.requireNonNull(t.getMessage()));
            }
        });



    }
}
