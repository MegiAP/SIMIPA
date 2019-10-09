package com.vincode.simipa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.Presence;

import java.util.ArrayList;

public class PresenceGuidanceAdapter extends RecyclerView.Adapter<PresenceGuidanceAdapter.PresenceViewHolder> {

    private ArrayList<Presence> listPresence;
    private final Context context;


    public PresenceGuidanceAdapter(Context context, ArrayList<Presence> listPresence) {
        this.listPresence = listPresence;
        this.context = context;
    }

    @NonNull
    @Override
    public PresenceGuidanceAdapter.PresenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_presence_guidance, parent, false);
        return new PresenceGuidanceAdapter.PresenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PresenceGuidanceAdapter.PresenceViewHolder holder, int position) {
        Presence presence = listPresence.get(position);

        holder.tvTimeOne.setText(presence.getTimeOne());
        holder.tvTimeTwo.setText(presence.getTimeTwo());
        holder.tvTitle.setText(presence.getTitle());
        holder.tvBab.setText(presence.getBab());
        holder.tvLecture.setText(presence.getLecture());
        holder.btnAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Button Absen Sedang dikerjakan :)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPresence.size();
    }

    class PresenceViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTimeOne, tvTimeTwo, tvTitle;
        private TextView tvBab, tvLecture;
        private Button btnAbsent;

        PresenceViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTimeOne = itemView.findViewById(R.id.tv_time1_absent);
            tvTimeTwo = itemView.findViewById(R.id.tv_time_absent);
            tvTitle = itemView.findViewById(R.id.tv_name_absent);
            tvBab = itemView.findViewById(R.id.tv_bab_absent);
            tvLecture = itemView.findViewById(R.id.tv_lecture_absent);
            btnAbsent = itemView.findViewById(R.id.btn_absent);
        }
    }
}