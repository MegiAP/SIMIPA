package com.vincode.simipa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.Agenda;

import java.util.ArrayList;


public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder> {

    private ArrayList<Agenda> dataList;

    public AgendaAdapter(ArrayList<Agenda> dataList) {
        this.dataList = dataList;
    }

    @Override
    public AgendaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_agenda, parent, false);
        return new AgendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AgendaViewHolder holder, int position) {
        holder.jam1.setText(dataList.get(position).getJam1());
        holder.jam2.setText(dataList.get(position).getJam2());
        holder.judul.setText(dataList.get(position).getJudul());
        holder.nama.setText(dataList.get(position).getNama());
        holder.ruang.setText(dataList.get(position).getRuang());
        holder.jenis.setText(dataList.get(position).getJenis());
    }
    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class AgendaViewHolder extends RecyclerView.ViewHolder {
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