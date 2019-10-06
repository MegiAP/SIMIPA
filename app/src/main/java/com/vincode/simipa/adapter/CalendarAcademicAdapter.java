package com.vincode.simipa.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.CalendarAcademic;

import java.util.ArrayList;

public class CalendarAcademicAdapter extends RecyclerView.Adapter<CalendarAcademicAdapter.CalendarViewHolder> {

    private  ArrayList<CalendarAcademic> listCalendar;
    private final Context mContext;

    public CalendarAcademicAdapter(Context mContext, ArrayList<CalendarAcademic> list) {
        this.mContext = mContext;
        this.listCalendar = list;
    }

//    public void setData(ArrayList<CalendarAcademic> calendarAcademics){
//        listCalendar.clear();
//        listCalendar.addAll(calendarAcademics);
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_academic_calendar, viewGroup, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder calendarViewHolder, int i) {

        CalendarAcademic calendarAcademic = listCalendar.get(i);

        calendarViewHolder.tvDataCalendar.setText(calendarAcademic.getData());//calendarAcademic.getData()
        calendarViewHolder.tvDateCalendar.setText(calendarAcademic.getDate());//calendarAcademic.getDate()

    }

    @Override
    public int getItemCount() {
        return listCalendar.size();
    }

     class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView tvDataCalendar, tvDateCalendar;

         CalendarViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDataCalendar = itemView.findViewById(R.id.tv_data_calendar);
            tvDateCalendar = itemView.findViewById(R.id.tv_date_calendar);
        }
    }
}
