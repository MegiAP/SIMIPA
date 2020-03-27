package com.vincode.simipa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.SeminarResult;

import java.util.ArrayList;
import java.util.List;

public class SeminarRecapAdapter extends RecyclerView.Adapter<SeminarRecapAdapter.CardViewViewHolder> {
    private List<SeminarResult> listSeminar = new ArrayList<>();

    public void setListSeminar(List<SeminarResult> listSeminar) {
        if (listSeminar == null) return;
        this.listSeminar.clear();
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
        SeminarResult p = listSeminar.get(position);

        holder.tvName.setText(p.getNama());
        holder.tvNpm.setText(p.getNpm());
        holder.tvSjudul.setText(p.getPem1());
        holder.tvSdosen.setText(p.getJudul());
        holder.tvSjenis.setText(p.getTanggal());

        holder.ivImage.setText(p.getJenis());
        /*switch (p.getJenis()) {
            case "Seminar Kerja Praktek" :
                holder.ivImage.setBackgroundResource(R.drawable.bg_blue_skies);
                break;
            case "Seminar Usul" :
                holder.ivImage.setBackgroundResource(R.drawable.bg_blue_dark);
                break;
            case "Seminar Hasil" :
                holder.ivImage.setBackgroundResource(R.drawable.bg_green);
                break;
            case "Seminar Komprehensif" :
                holder.ivImage.setBackgroundResource(R.drawable.bg_red);
        }*/
    }

    @Override
    public int getItemCount() {
        return listSeminar.size();
    }

    static class CardViewViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvNpm, tvSjudul, tvSdosen, tvSjenis;
        TextView ivImage;
        CardViewViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_recMhsSem);
            tvNpm = itemView.findViewById(R.id.tv_recNpm);
            tvSjudul = itemView.findViewById(R.id.tv_rec_JudulSem);
            tvSdosen = itemView.findViewById(R.id.tv_rec_dosenSem);
            tvSjenis = itemView.findViewById(R.id.tv_recJenisSem);
            ivImage = itemView.findViewById(R.id.iv_image);
        }
    }
}
