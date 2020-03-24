package com.vincode.simipa.ui.presence;


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
import com.vincode.simipa.adapter.SeminarPresenceAdapter;

import com.vincode.simipa.model.SeminarPresenceResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeminarFragment extends Fragment {

    private SeminarPresenceAdapter adapter;
    private RecyclerView rvSeminar;
    private ProgressBar progressBar;


    public SeminarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seminar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvSeminar = view.findViewById(R.id.rv_seminar);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressBar.setVisibility(View.VISIBLE);
        adapter = new SeminarPresenceAdapter(getActivity());

        setLayout();
        getData();

    }

    private void setLayout(){
        rvSeminar.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSeminar.setHasFixedSize(true);
        rvSeminar.setAdapter(adapter);
    }

    private void getData(){
        ApiInterface apiInterface = ApiClient.getClientLocal().create(ApiInterface.class);

        Call<SeminarPresenceResponse> call = apiInterface.getSeminar();

        call.enqueue(new Callback<SeminarPresenceResponse>() {
            @Override
            public void onResponse(@NonNull Call<SeminarPresenceResponse> call, @NonNull Response<SeminarPresenceResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    adapter.setListSeminar(response.body().getResult());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeminarPresenceResponse> call, @NonNull Throwable t) {
                Log.d("c", Objects.requireNonNull(t.getMessage()));
            }
        });
    }


    //seminar
//    private void getData(){
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//
//        Call<PresenceResponse> call = apiInterface.getPresenceData(
//                SharedPrefManager.getInstance(getActivity()).getUser().getUserLogin(), "2019-11-12 13:48:11","Teori", "Ganjil");
//
//        call.enqueue(new Callback<PresenceResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<PresenceResponse> call, @NonNull Response<PresenceResponse> response) {
//                progressBar.setVisibility(View.GONE);
//                if (response.body() != null) {
//                    presenceAdapter.setListPresence(response.body().getRecords());
//                    presenceAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<PresenceResponse> call, @NonNull Throwable t) {
//                Log.d("c", Objects.requireNonNull(t.getMessage()));
//            }
//        });
//
//    }
}
