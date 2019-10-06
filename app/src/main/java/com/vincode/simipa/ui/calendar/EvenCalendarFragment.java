package com.vincode.simipa.ui.calendar;


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
import com.vincode.simipa.adapter.CalendarAcademicAdapter;
import com.vincode.simipa.util.TestData;
import com.vincode.simipa.model.CalendarAcademic;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvenCalendarFragment extends Fragment {

    private CalendarAcademicAdapter calendarAcademicAdapter;
    private RecyclerView rvEvenCalendar;
    private ArrayList<CalendarAcademic> list = new ArrayList<>();

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
        list.addAll(TestData.getListCalendar());
        calendarAcademicAdapter = new CalendarAcademicAdapter(getContext(), list);

        setLayout();
    }

    void setLayout(){
        rvEvenCalendar.setLayoutManager(new LinearLayoutManager(getContext()));
        rvEvenCalendar.setHasFixedSize(true);
        rvEvenCalendar.setAdapter(calendarAcademicAdapter);
    }
}
