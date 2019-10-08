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
import com.vincode.simipa.model.CollegeSchedule;

import java.util.ArrayList;
import java.util.List;


public class JadkulFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    List<CollegeSchedule> listCont;
    public JadkulFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_jadkul,container,false);
        recyclerView = v.findViewById(R.id.rvjdkul);
        CollegeScheduleAdapter viewAdapter = new CollegeScheduleAdapter(getContext(), listCont);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(viewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listCont = new ArrayList<>();
        listCont.add(new CollegeSchedule("Kapita Selekta","COM616402","Ardiansyah, S.Kom., M.Kom.","GIK Lt.2","11.20"));
        listCont.add(new CollegeSchedule("Kapita Selekta","COM616402","Ardiansyah, S.Kom., M.Kom.","GIK Lt.2","13.30"));
        listCont.add(new CollegeSchedule("Kapita Selekta","COM616402","Ardiansyah, S.Kom., M.Kom.","GIK Lt.2","15.30"));

    }
}