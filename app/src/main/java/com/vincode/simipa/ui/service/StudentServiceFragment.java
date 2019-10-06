package com.vincode.simipa.ui.service;


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
import com.vincode.simipa.adapter.ServiceAdapter;
import com.vincode.simipa.util.TestDataService;
import com.vincode.simipa.model.Service;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentServiceFragment extends Fragment implements View.OnClickListener {

    private ServiceAdapter serviceAdapter;
    private RecyclerView rvAcademicService;
    private ArrayList<Service> list = new ArrayList<>();
    //private FloatingActionButton fabAdd;


    public StudentServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvAcademicService = view.findViewById(R.id.rv_service_student);
        list.addAll(TestDataService.getListService());
        serviceAdapter = new ServiceAdapter(getContext(), list);

//        fabAdd = view.findViewById(R.id.fab_add);
//        fabAdd.setOnClickListener(this);

        setLayout();
    }

    void setLayout(){
        rvAcademicService.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAcademicService.setHasFixedSize(true);
        rvAcademicService.setAdapter(serviceAdapter);
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.fab_add){
//            showSnackbarMessage("Test Fab Add");
//        }
    }
//
//    private void showSnackbarMessage(String message) {
//        Snackbar.make(fabAdd, message, Snackbar.LENGTH_SHORT).show();
//    }
}
