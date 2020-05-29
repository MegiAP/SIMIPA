package com.vincode.simipa.ui.beasiswa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
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
import com.vincode.simipa.adapter.KRSAdapter;
import com.vincode.simipa.adapter.ScholarshipAdapter;
import com.vincode.simipa.model.ScholarshipResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeasiswaFragment extends Fragment {

    private RecyclerView rvCategory;
    private ScholarshipAdapter scholarshipAdapter;
    private ProgressBar pgBar;

    public BeasiswaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beasiswa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvCategory = view.findViewById(R.id.rv_category);
        scholarshipAdapter = new ScholarshipAdapter();

        pgBar = view.findViewById(R.id.pg_bar);
        pgBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showRecyclerCardView();
        getData();
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCategory.setHasFixedSize(true);
        rvCategory.setAdapter(scholarshipAdapter);
    }

    private void getData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ScholarshipResponse> call = apiInterface.getScholarshipData(SharedPrefManager.getInstance(getContext()).getUser().getUserLogin());
        call.enqueue(new Callback<ScholarshipResponse>() {
            @Override
            public void onResponse(@NonNull Call<ScholarshipResponse> call, @NonNull Response<ScholarshipResponse> response) {
                if (response.body() != null) {
                    pgBar.setVisibility(View.GONE);
                    scholarshipAdapter.setListScholarship(response.body().getRecords());
                    scholarshipAdapter.notifyDataSetChanged();
                }
                else {
                    Log.d("Success", " ");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ScholarshipResponse> call, @NonNull Throwable t) {
                Log.d("Failure", " ");
            }
        });
    }

}
