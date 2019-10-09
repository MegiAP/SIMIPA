package com.vincode.simipa.ui.presence;

import android.content.Context;
import android.net.Uri;
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
import com.vincode.simipa.adapter.PresenceGuidanceAdapter;
import com.vincode.simipa.adapter.PresenceSeminarAdapter;
import com.vincode.simipa.model.Presence;
import com.vincode.simipa.util.TestPresence;

import java.util.ArrayList;


public class GuidanceFragment extends Fragment {
    private PresenceGuidanceAdapter presenceGuidanceAdapter;
    private RecyclerView rvGuidance;
    private ArrayList<Presence> list = new ArrayList<>();


    public GuidanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guidance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvGuidance = view.findViewById(R.id.rv_prGuidance);
        list.addAll(TestPresence.getListPresence());
        presenceGuidanceAdapter = new PresenceGuidanceAdapter(getContext(), list);

        setLayout();
    }

    private void setLayout(){
        rvGuidance.setLayoutManager(new LinearLayoutManager(getContext()));
        rvGuidance.setHasFixedSize(true);
        rvGuidance.setAdapter(presenceGuidanceAdapter);
    }

}