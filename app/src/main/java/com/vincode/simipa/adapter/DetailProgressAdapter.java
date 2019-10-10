package com.vincode.simipa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.ScoreMatkul;

import java.util.ArrayList;

public class DetailProgressAdapter extends RecyclerView.Adapter<DetailProgressAdapter.DetailProgressViewHolder> {

    private ArrayList<ScoreMatkul> listScore;
    private final Context mContext;

    public DetailProgressAdapter( Context mContext, ArrayList<ScoreMatkul> listScore) {
        this.listScore = listScore;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DetailProgressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matkul_progress, parent, false);
        return new DetailProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailProgressViewHolder holder, int position) {
        ScoreMatkul scoreMatkul = listScore.get(position);

        holder.tvName.setText(scoreMatkul.getName());
        holder.tvCode.setText(scoreMatkul.getCode());
        holder.tvSKS.setText(scoreMatkul.getSks());
        holder.tvScore.setText(scoreMatkul.getScore());
    }

    @Override
    public int getItemCount() {
        return listScore.size();
    }

     class DetailProgressViewHolder extends RecyclerView.ViewHolder {
         TextView tvName, tvCode, tvSKS, tvScore;

         DetailProgressViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_matkul);
            tvCode = itemView.findViewById(R.id.tv_code);
            tvSKS = itemView.findViewById(R.id.tv_sks);
            tvScore = itemView.findViewById(R.id.tv_value);

        }
    }
}
