package com.vincode.simipa.adapter;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.CalendarResult;
import com.vincode.simipa.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class CalendarAcademicAdapter extends RecyclerView.Adapter<CalendarAcademicAdapter.CalendarViewHolder> {

    private List<CalendarResult> listCalendar = new ArrayList<>();
    private final Activity activity;

    public CalendarAcademicAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListCalendar(List<CalendarResult> calendarList){

        if (calendarList == null)return;
        this.listCalendar.clear();
        this.listCalendar.addAll(calendarList);

    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_academic_calendar, viewGroup, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder calendarViewHolder, int i) {

        final CalendarResult dataCalendar = listCalendar.get(i);
        TimeUtil timeUtil = new TimeUtil();

        calendarViewHolder.tvDataCalendar.setText(dataCalendar.getKeterangan());
        calendarViewHolder.tvDateCalendar.setText(timeUtil.converDate(dataCalendar.getDate())
        );

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
