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
import com.vincode.simipa.model.SeminarRecap;


import java.util.ArrayList;

public class SeminarRecapAdapter extends RecyclerView.Adapter<SeminarRecapAdapter.CardViewViewHolder> {
    private ArrayList<SeminarRecap> listSeminar;
    private Context context;

    public SeminarRecapAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<SeminarRecap> getlistSeminar() {
        return listSeminar;
    }

    public void setListSeminar(ArrayList<SeminarRecap> listSeminar) {
        this.listSeminar = listSeminar;
    }

    @NonNull
    @Override
    public SeminarRecapAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seminar_recap, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeminarRecapAdapter.CardViewViewHolder holder, int position) {
        SeminarRecap p = getlistSeminar().get(position);

        holder.tvName.setText(p.getName());
        holder.tvSjudul.setText(p.getSjudul());
        holder.tvSdosen.setText(p.getSdosen());
        holder.tvSjenis.setText(p.getSjenis());
    }

    @Override
    public int getItemCount() {
        return getlistSeminar().size();
    }

    static class CardViewViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvSjudul, tvSdosen, tvSjenis;
        private Button btnPresence;
        CardViewViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_recMhsSem);
            tvSjudul = itemView.findViewById(R.id.tv_rec_JudulSem);
            tvSdosen = itemView.findViewById(R.id.tv_rec_dosenSem);
            tvSjenis = itemView.findViewById(R.id.tv_recJenisSem);
        }
    }
}
