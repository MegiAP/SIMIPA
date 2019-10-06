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

public class PracticeFragment extends Fragment {

    private PresenceAdapter presenceAdapter;
    private RecyclerView rvPractice;
    private ArrayList<Presence> list = new ArrayList<>();

    public PracticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_practice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPractice = view.findViewById(R.id.rv_practice);
        list.addAll(TestPresence.getListPresence());
        presenceAdapter = new PresenceAdapter(getContext(), list);

        setLayout();
    }

    private void setLayout(){
        rvPractice.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPractice.setHasFixedSize(true);
        rvPractice.setAdapter(presenceAdapter);
    }

}
