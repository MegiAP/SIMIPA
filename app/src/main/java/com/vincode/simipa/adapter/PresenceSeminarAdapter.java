package com.vincode.simipa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vincode.simipa.R;
import com.vincode.simipa.model.Presence;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PresenceSeminarAdapter extends RecyclerView.Adapter<PresenceSeminarAdapter.PresenceViewHolder> {

    private ArrayList<Presence> listPresence;
    private final Context context;


    public PresenceSeminarAdapter(Context context, ArrayList<Presence> listPresence) {
        this.listPresence = listPresence;
        this.context = context;
    }

    @NonNull
    @Override
    public PresenceSeminarAdapter.PresenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_presence_seminar, parent, false);
        return new PresenceSeminarAdapter.PresenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PresenceSeminarAdapter.PresenceViewHolder holder, int position) {
        Presence presence = listPresence.get(position);

        holder.tvTimeOne.setText(presence.getTimeOne());
        holder.tvTimeTwo.setText(presence.getTimeTwo());
        holder.tvTitle.setText(presence.getTitle());
        holder.tvLecture.setText(presence.getLecture());
        holder.tvRoom.setText(presence.getRoom());
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
        private TextView tvLecture, tvRoom;
        private Button btnAbsent;

        PresenceViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTimeOne = itemView.findViewById(R.id.tv_time1_absent);
            tvTimeTwo = itemView.findViewById(R.id.tv_time_absent);
            tvTitle = itemView.findViewById(R.id.tv_name_absent);
            tvLecture = itemView.findViewById(R.id.tv_lecture_absent);
            tvRoom = itemView.findViewById(R.id.tv_place_absent);
            btnAbsent = itemView.findViewById(R.id.btn_absent);
        }
    }
}
