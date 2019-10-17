package com.vincode.simipa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.GuidanceRecap;
import com.vincode.simipa.ui.guidance.DetailGuidanceActivity;
import com.vincode.simipa.ui.recapitulation.GuidanceRecapActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class GuidanceRecapAdapter extends RecyclerView.Adapter<GuidanceRecapAdapter.GuidanceViewHolder>{
    private ArrayList<GuidanceRecap> listGuidanceRecap;
    private final Context context;

    public GuidanceRecapAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<GuidanceRecap> getlistGuidanceRecap() {
        return listGuidanceRecap;
    }

    public void setlistGuidanceRecap(ArrayList<GuidanceRecap> listGuidanceRecap) {
        this.listGuidanceRecap = listGuidanceRecap;
    }

    @NonNull
    @Override
    public GuidanceRecapAdapter.GuidanceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_guidance, viewGroup, false);
        return new GuidanceRecapAdapter.GuidanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuidanceViewHolder holder, int position) {
        GuidanceRecap guidanceRecap = listGuidanceRecap.get(position);

        holder.tvTitle.setText(guidanceRecap.getTitle());
        holder.tvLecture.setText(guidanceRecap.getLecture());
        holder.tvLocation.setText(guidanceRecap.getLocation());
        holder.tvDate.setText(guidanceRecap.getDate());
        holder.tvTime.setText(guidanceRecap.getTime());
        holder.cvGuidance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailGuidanceActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listGuidanceRecap.size();
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
