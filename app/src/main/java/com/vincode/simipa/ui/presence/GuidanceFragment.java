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
import com.vincode.simipa.util.SharedPrefManager;
import com.vincode.simipa.adapter.PresenceAdapter;
import com.vincode.simipa.model.PresenceResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GuidanceFragment extends Fragment {
    private PresenceAdapter presenceAdapter;
    private RecyclerView rvGuidance;
    private ProgressBar progressBar;

    public GuidanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guidance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvGuidance = view.findViewById(R.id.rv_prGuidance);
        progressBar = view.findViewById(R.id.progress_bar);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressBar.setVisibility(View.VISIBLE);
        presenceAdapter = new PresenceAdapter(getActivity());

        setLayout();
        getData();

    }

    private void setLayout(){
        rvGuidance.setLayoutManager(new LinearLayoutManager(getContext()));
        rvGuidance.setHasFixedSize(true);
        rvGuidance.setAdapter(presenceAdapter);
    }

    //bimbingan
    private void getData(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<PresenceResponse> call = apiInterface.getPresenceData(
                SharedPrefManager.getInstance(getActivity()).getUser().getUserLogin(), "2019-11-12 13:48:11","Teori");

        call.enqueue(new Callback<PresenceResponse>() {
            @Override
            public void onResponse(@NonNull Call<PresenceResponse> call, @NonNull Response<PresenceResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    presenceAdapter.setListPresence(response.body().getRecords());
                    presenceAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PresenceResponse> call, @NonNull Throwable t) {
                Log.d("c", Objects.requireNonNull(t.getMessage()));
            }
        });

    }
}