package com.vincode.simipa.ui.presence;


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
import com.vincode.simipa.adapter.PresenceAdapter;
import com.vincode.simipa.adapter.PresenceSeminarAdapter;
import com.vincode.simipa.model.Presence;
import com.vincode.simipa.util.TestPresence;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeminarFragment extends Fragment {

    private PresenceSeminarAdapter presenceSeminarAdapter;
    private RecyclerView rvSeminar;
    private ArrayList<Presence> list = new ArrayList<>();


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
        list.addAll(TestPresence.getListPresence());
        presenceSeminarAdapter = new PresenceSeminarAdapter(getContext(), list);

        setLayout();
    }

    private void setLayout(){
        rvSeminar.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSeminar.setHasFixedSize(true);
        rvSeminar.setAdapter(presenceSeminarAdapter);
    }

}
