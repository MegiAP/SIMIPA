package com.vincode.simipa.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.PresenceResult;

import java.util.ArrayList;
import java.util.List;

public class PresenceAdapter extends RecyclerView.Adapter<PresenceAdapter.PresenceViewHolder> {

    private List<PresenceResult> listPresence = new ArrayList<>();
    private final Activity activity;


    public PresenceAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListPresence(List<PresenceResult> presenceList){

        if (presenceList == null)return;
        this.listPresence.clear();
        this.listPresence.addAll(presenceList);

    }

    @NonNull
    @Override
    public PresenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_presence, parent, false);
        return new PresenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PresenceViewHolder holder, int position) {
        PresenceResult presence = listPresence.get(position);

        holder.tvTimeOne.setText(presence.getMulai());
        holder.tvTimeTwo.setText(presence.getSelesai());
        holder.tvTitle.setText(presence.getMataKuliah());
        holder.tvCode.setText(presence.getKodeMK());
        holder.tvLecture.setText(presence.getDosenPJ());
        holder.tvRoom.setText(presence.getRuang());
        holder.btnAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Lagi tes button", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPresence.size();
    }

     class PresenceViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTimeOne, tvTimeTwo, tvTitle, tvCode;
        private TextView tvLecture, tvRoom;
        private Button btnAbsent;

        PresenceViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTimeOne = itemView.findViewById(R.id.tv_time1_absent);
            tvTimeTwo = itemView.findViewById(R.id.tv_time_absent);
            tvTitle = itemView.findViewById(R.id.tv_name_absent);
            tvCode = itemView.findViewById(R.id.tv_code_absent);
            tvLecture = itemView.findViewById(R.id.tv_lecture_absent);
            tvRoom = itemView.findViewById(R.id.tv_place_absent);
            btnAbsent = itemView.findViewById(R.id.btn_absent);
        }
    }
}
