package com.vincode.simipa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.SeminarScheduleResult;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AgendaSeminarAdapter extends RecyclerView.Adapter<AgendaSeminarAdapter.ViewHolder> {

    private Context context;
    private List<SeminarScheduleResult> listSeminarSchedule = new ArrayList<>();

    public AgendaSeminarAdapter(Context context, List<SeminarScheduleResult> seminarScheduleList) {
        this.context = context;
        if (seminarScheduleList == null)return;
        this.listSeminarSchedule.clear();
        this.listSeminarSchedule.addAll(seminarScheduleList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_seminar_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SeminarScheduleResult dataResult = listSeminarSchedule.get(position);

        holder.smNpm.setText(dataResult.getNPM());
        holder.smJudul.setText(dataResult.getJudul());
        holder.smNip.setText(dataResult.getPbg1());
        holder.smWaktu.setText(dataResult.getWaktu());
        holder.smRuang.setText(dataResult.getRuang());
    }

    @Override
    public int getItemCount() {
        return listSeminarSchedule.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView smNpm,smJudul,smRuang, smNip, smWaktu, smTgl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            smNpm = itemView.findViewById(R.id.tv_npmSem);
            smJudul = itemView.findViewById(R.id.tv_judulSem);
            smRuang= itemView.findViewById(R.id.tv_ruangSem);
            smNip = itemView.findViewById(R.id.tv_nipSeminar);
            smWaktu = itemView.findViewById(R.id.tv_jamsem);
            smTgl = itemView.findViewById(R.id.tv_tglsem);
            smTgl.setVisibility(View.GONE);

        }
    }
}
