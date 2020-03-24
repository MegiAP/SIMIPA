package com.vincode.simipa.ui.achievement;

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
import com.vincode.simipa.adapter.AchievementAdapter;
import com.vincode.simipa.model.AchievementResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class NonAcademicAchievementFragment extends Fragment {

    private AchievementAdapter achievementAdapter;
    private RecyclerView rvCategory;
    private ProgressBar pgBar;

    public NonAcademicAchievementFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_non_academic_achievement, container, false);

        rvCategory = view.findViewById(R.id.rv_category);
        pgBar = view.findViewById(R.id.pg_bar);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        achievementAdapter = new AchievementAdapter(getActivity());
        pgBar.setVisibility(View.VISIBLE);

        showRecyclerCardView();
        getData();
    }

    private void showRecyclerCardView() {
        rvCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCategory.setHasFixedSize(true);
        rvCategory.setAdapter(achievementAdapter);
    }

    private void getData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<AchievementResponse> call = apiInterface.getAchievementData(SharedPrefManager.getInstance(getActivity()).getUser().getUserLogin(),"Non Akademik");
        call.enqueue(new Callback<AchievementResponse>() {
            @Override
            public void onResponse(@NonNull Call<AchievementResponse> call, @NonNull Response<AchievementResponse> response) {
                if (response.body() != null) {
                    achievementAdapter.setListAchievement(response.body().getRecords());
                    pgBar.setVisibility(View.GONE);
                    achievementAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AchievementResponse> call, @NonNull Throwable t) {
                Log.d("Failure", " ");
            }
        });
    }
}
