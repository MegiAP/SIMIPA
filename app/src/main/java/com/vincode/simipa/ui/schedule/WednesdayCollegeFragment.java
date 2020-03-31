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
import com.vincode.simipa.model.CollegeScheduleResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WednesdayCollegeFragment extends Fragment {
    private CollegeScheduleAdapter collegeScheduleAdapter;
    private RecyclerView recyclerView;
    public WednesdayCollegeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wednesday_college, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvCollegeWednesday);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        collegeScheduleAdapter = new CollegeScheduleAdapter(getActivity());

        setLayout();
        getCollegeData();

    }

    private void setLayout(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(collegeScheduleAdapter);
    }

    private void getCollegeData(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<CollegeScheduleResponse> call = apiInterface.getCollegeData("rabu" , "1617051103","2019/2020","Ganjil", "Teori" );

        call.enqueue(new Callback<CollegeScheduleResponse>() {
            @Override
            public void onResponse(Call<CollegeScheduleResponse> call, Response<CollegeScheduleResponse> response) {
                if (response.body() != null) {
                    collegeScheduleAdapter.setListCollegeSchedule(response.body().getRecords());
                    collegeScheduleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CollegeScheduleResponse> call, Throwable t) {
                Log.d("c", Objects.requireNonNull(t.getMessage()));
            }
        });



    }
}
