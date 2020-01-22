package com.vincode.simipa.ui.calendar;


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
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.CalendarAcademicAdapter;
import com.vincode.simipa.model.CalendarResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.TimeUtil;


import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvenCalendarFragment extends Fragment {

    private CalendarAcademicAdapter calendarAcademicAdapter;
    private RecyclerView rvEvenCalendar;
    private ProgressBar progressBar;
    private TextView academicYear;

    public EvenCalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_even_calendar, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvEvenCalendar = view.findViewById(R.id.rv_even_calendar);
        progressBar = view.findViewById(R.id.progress_bar);
        academicYear = view.findViewById(R.id.tv_title_calendar);

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressBar.setVisibility(View.VISIBLE);
        calendarAcademicAdapter = new CalendarAcademicAdapter(getActivity());

        TimeUtil timeUtil = new TimeUtil();
        String tahun = timeUtil.getWaktu();
        academicYear.setText(String.format("Tahun akademik %s", tahun));

        setLayout();
        getData(tahun);

    }

    private void setLayout(){
        rvEvenCalendar.setLayoutManager(new LinearLayoutManager(getContext()));
        rvEvenCalendar.setHasFixedSize(true);
        rvEvenCalendar.setAdapter(calendarAcademicAdapter);
    }

    private void getData(String tahun){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<CalendarResponse> call = apiInterface.getCalendarAcademic("genap", tahun);
        call.enqueue(new Callback<CalendarResponse>() {

            @Override
            public void onResponse(@NonNull Call<CalendarResponse> call, @NonNull Response<CalendarResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.body() != null) {
                    calendarAcademicAdapter.setListCalendar(response.body().getRecords());
                    calendarAcademicAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CalendarResponse> call,@NonNull Throwable t) {
                Log.d("c", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
