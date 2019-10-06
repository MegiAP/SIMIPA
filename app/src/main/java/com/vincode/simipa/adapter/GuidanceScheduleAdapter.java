package com.vincode.simipa.adapter;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.GuidanceSchedule;
import com.vincode.simipa.ui.guidance.DetailGuidanceActivity;

import java.util.ArrayList;

public class GuidanceScheduleAdapter extends RecyclerView.Adapter<GuidanceScheduleAdapter.GuidanceViewHolder> {

    private ArrayList<GuidanceSchedule> listGuidance;
    private final Context context;


    public GuidanceScheduleAdapter(Context context, ArrayList<GuidanceSchedule> listGuidance) {
        this.listGuidance = listGuidance;
        this.context = context;
    }

    @NonNull
    @Override
    public GuidanceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_guidance, viewGroup, false);
        return new GuidanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuidanceViewHolder guidanceViewHolder, int i) {
        GuidanceSchedule guidanceSchedule = listGuidance.get(i);

        guidanceViewHolder.tvTitle.setText(guidanceSchedule.getTitle());
        guidanceViewHolder.tvLecture.setText(guidanceSchedule.getLecture());
        guidanceViewHolder.tvLocation.setText(guidanceSchedule.getLocation());
        guidanceViewHolder.tvDate.setText(guidanceSchedule.getDate());
        guidanceViewHolder.tvTime.setText(guidanceSchedule.getTime());
        guidanceViewHolder.cvGuidance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailGuidanceActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listGuidance.size();
    }

    public class GuidanceViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvLecture, tvLocation, tvDate, tvTime;
        CardView cvGuidance;
        public GuidanceViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title_ta);
            tvLecture = itemView.findViewById(R.id.tv_lecture_ta);
            tvLocation = itemView.findViewById(R.id.tv_place_ta);
            tvDate = itemView.findViewById(R.id.tv_date_ta);
            tvTime = itemView.findViewById(R.id.tv_time_ta);
            cvGuidance = itemView.findViewById(R.id.cv_item_guidance);

        }
    }
}
