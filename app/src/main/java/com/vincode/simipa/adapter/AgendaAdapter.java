package com.vincode.simipa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.CollegeScheduleResult;

import java.util.ArrayList;
import java.util.List;


public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder> {
    private Context context;
    private List<CollegeScheduleResult> agendaList = new ArrayList<>();

    public AgendaAdapter(Context context, List<CollegeScheduleResult> agendaList) {
        this.context = context;
        if (agendaList == null) return;
        this.agendaList.clear();
        this.agendaList.addAll(agendaList);
    }

    @NonNull
    @Override
    public AgendaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_agenda, parent, false);
        return new AgendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AgendaViewHolder holder, int position) {
        final CollegeScheduleResult dataCollege = agendaList.get(position);

        holder.jam1.setText(dataCollege.getMulai());
        holder.jam2.setText(dataCollege.getSelesai());
        holder.judul.setText(dataCollege.getMataKuliah());
        holder.nama.setText(dataCollege.getKodeMK());
        holder.ruang.setText(dataCollege.getRuang());
        holder.jenis.setText(dataCollege.getJenis());
    }

    @Override
    public int getItemCount() {
        return agendaList.size();
    }

    static class AgendaViewHolder extends RecyclerView.ViewHolder {
        TextView jam1, jam2, judul, nama, ruang, jenis;
        public AgendaViewHolder(View itemView) {
            super(itemView);
            jam1 = itemView.findViewById(R.id.tv_jam1);
            jam2 = itemView.findViewById(R.id.tv_jam2);
            nama = itemView.findViewById(R.id.tv_mhsAgenda);
            judul = itemView.findViewById(R.id.tv_judulAgenda);
            ruang = itemView.findViewById(R.id.tv_rAgenda);
            jenis = itemView.findViewById(R.id.tv_jenisAgenda);
        }
    }
}