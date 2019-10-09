package com.vincode.simipa.ui.schedule;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.SeminarScheduleAdapter;
import com.vincode.simipa.model.SeminarSchedule;

import java.util.ArrayList;
import java.util.List;


public class JadsemFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    List<SeminarSchedule> listCont;

    public JadsemFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_jadsem,container,false);
        recyclerView = v.findViewById(R.id.rv_jdsem);
        SeminarScheduleAdapter viewAdapter = new SeminarScheduleAdapter(getContext(), listCont);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(viewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listCont = new ArrayList<>();
        listCont.add(new SeminarSchedule("07-10-2019","09.10","SIMIPA","Cindy Prakasa Putra","GIK L2","Usul"));
        listCont.add(new SeminarSchedule("07-10-2019","13.30","SIMIPA","Cindy Prakasa Putra","GIK L2","Hasil"));
        listCont.add(new SeminarSchedule("07-10-2019","15.30","SIMIPA","Cindy Prakasa Putra","GIK L2","KP"));


    }
}
