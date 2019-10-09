package com.vincode.simipa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.ScoreRecap;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScoreRecapAdapter extends RecyclerView.Adapter<ScoreRecapAdapter.CardViewViewHolder> {
    private ArrayList<ScoreRecap> listScore;
    private Context context;

    public ScoreRecapAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<ScoreRecap> getlistScore() {
        return listScore;
    }

    public void setlistScore(ArrayList<ScoreRecap> listScore) {
        this.listScore = listScore;
    }

    @NonNull
    @Override
    public ScoreRecapAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score_recap, parent, false);
        CardViewViewHolder viewHolder = new CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreRecapAdapter.CardViewViewHolder holder, int position) {
        ScoreRecap p = getlistScore().get(position);

        holder.tvName.setText(p.getName());
        holder.tvRemarks.setText(p.getRemarks());
        holder.tvScore.setText(p.getPhoto());
    }

    @Override
    public int getItemCount() {
        return getlistScore().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvRemarks, tvScore;
        public CardViewViewHolder(View itemView) {
            super(itemView);
            tvScore = (TextView)itemView.findViewById(R.id.tv_score_recap);
            tvName = (TextView)itemView.findViewById(R.id.tv_item_name);
            tvRemarks = (TextView)itemView.findViewById(R.id.tv_item_remarks);
        }
    }
}
