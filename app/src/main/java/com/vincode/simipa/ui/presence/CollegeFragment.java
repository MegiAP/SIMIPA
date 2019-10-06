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
import com.vincode.simipa.model.Presence;
import com.vincode.simipa.util.TestPresence;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollegeFragment extends Fragment {

    private PresenceAdapter presenceAdapter;
    private RecyclerView rvCollege;
    private ArrayList<Presence> list = new ArrayList<>();

    public CollegeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_college, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvCollege = view.findViewById(R.id.rv_college);
        list.addAll(TestPresence.getListPresence());
        presenceAdapter = new PresenceAdapter(getContext(), list);

        setLayout();
    }

    private void setLayout(){
        rvCollege.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCollege.setHasFixedSize(true);
        rvCollege.setAdapter(presenceAdapter);
    }
}
