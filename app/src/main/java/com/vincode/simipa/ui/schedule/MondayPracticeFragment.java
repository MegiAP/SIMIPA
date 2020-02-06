package com.vincode.simipa.ui.schedule;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.CollegeScheduleAdapter;
import com.vincode.simipa.model.CollegeScheduleResult;

import java.util.ArrayList;
import java.util.List;


public class MondayPracticeFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    List<CollegeScheduleResult> listCont;

    public MondayPracticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_monday_practice, container, false);
        recyclerView = v.findViewById(R.id.rv_mondaypractice);
        return v;
    }
}