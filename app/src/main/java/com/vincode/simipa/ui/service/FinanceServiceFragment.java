package com.vincode.simipa.ui.service;

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
import android.widget.ProgressBar;

import com.vincode.simipa.R;
import com.vincode.simipa.SharedPrefManager;
import com.vincode.simipa.adapter.ServiceAdapter;
import com.vincode.simipa.model.ServiceResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FinanceServiceFragment extends Fragment  {

    private ServiceAdapter serviceAdapter;
    private RecyclerView rvAcademicService;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progress_bar);
        rvAcademicService = view.findViewById(R.id.rv_service_finance);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressBar.setVisibility(View.VISIBLE);
        serviceAdapter = new ServiceAdapter(getActivity());


        setLayout();
        getData();

    }

    private void setLayout(){
        rvAcademicService.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAcademicService.setHasFixedSize(true);
        rvAcademicService.setAdapter(serviceAdapter);
    }

    private void getData(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ServiceResponse> call = apiInterface.getServiceData
                (SharedPrefManager.getInstance(getActivity()).getUser().getUserLogin(), "form-layanan-umum-keuangan");
        call.enqueue(new Callback<ServiceResponse>() {

            @Override
            public void onResponse(@NonNull Call<ServiceResponse> call, @NonNull Response<ServiceResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.body() != null) {
                    serviceAdapter.setListService(response.body().getServices());
                    serviceAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServiceResponse> call,@NonNull Throwable t) {
                Log.d("c", t.getMessage());
            }
        });
    }

}
