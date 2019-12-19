package com.vincode.simipa.ui.krs;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.ClassScheduleAdapter;
import com.vincode.simipa.adapter.KRSAdapter;
import com.vincode.simipa.model.KRSResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyKRSFragment extends Fragment {

    private RecyclerView recyclerView;
    private KRSAdapter krsAdapter;


    public MyKRSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_kr, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_my_krs);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        krsAdapter = new KRSAdapter(getActivity());

        setLayout();
        getData();

    }

    private void getData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<KRSResponse> call = apiInterface.getKRSData("1", SharedPrefManager.getInstance(getActivity()).getUser().getUserLogin());
        call.enqueue(new Callback<KRSResponse>() {
            @Override
            public void onResponse(Call<KRSResponse> call, Response<KRSResponse> response) {
                if (response.body() != null) {
                    krsAdapter.setListKRS(response.body().getRecords());
                    krsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<KRSResponse> call, Throwable t) {

            }
        });
    }

    private void setLayout() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(krsAdapter);
    }

}
