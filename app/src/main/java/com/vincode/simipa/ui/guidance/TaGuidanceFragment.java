package com.vincode.simipa.ui.guidance;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.GuidanceScheduleAdapter;
import com.vincode.simipa.util.TestGuidance;
import com.vincode.simipa.model.GuidanceSchedule;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaGuidanceFragment extends Fragment {

    private GuidanceScheduleAdapter guidanceScheduleAdapter;
    private RecyclerView rvGuidanceSchedule;
    private ArrayList<GuidanceSchedule> list = new ArrayList<>();

    public TaGuidanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ta_guidance, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvGuidanceSchedule = view.findViewById(R.id.rv_guidance_ta);
        list.addAll(TestGuidance.getListGuidance());
        guidanceScheduleAdapter = new GuidanceScheduleAdapter(getContext(), list);

        setLayout();
    }

    void setLayout(){
        rvGuidanceSchedule.setLayoutManager(new LinearLayoutManager(getContext()));
        rvGuidanceSchedule.setHasFixedSize(true);
        rvGuidanceSchedule.setAdapter(guidanceScheduleAdapter);
    }
}
