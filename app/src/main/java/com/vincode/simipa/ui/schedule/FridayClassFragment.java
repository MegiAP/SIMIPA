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
import com.vincode.simipa.adapter.ClassScheduleAdapter;
import com.vincode.simipa.model.ClassScheduleResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FridayClassFragment extends Fragment {
    private ClassScheduleAdapter classScheduleAdapter;
    private RecyclerView recyclerView;
    public FridayClassFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friday_class, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvclassFriday);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        classScheduleAdapter = new ClassScheduleAdapter(getActivity());

        setLayout();
        getData();

    }

    private void setLayout(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(classScheduleAdapter);
    }

    private void getData(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ClassScheduleResponse> call = apiInterface.getClassData("jumat", "Ilmu Komputer");

        call.enqueue(new Callback<ClassScheduleResponse>() {
            @Override
            public void onResponse(Call<ClassScheduleResponse> call, Response<ClassScheduleResponse> response) {
                if (response.body() != null) {
                    classScheduleAdapter.setListClassSchedule(response.body().getRecords());
                    classScheduleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ClassScheduleResponse> call, Throwable t) {
                Log.d("c", Objects.requireNonNull(t.getMessage()));
            }
        });



    }
}