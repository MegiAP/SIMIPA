package com.vincode.simipa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.AchievementResult;
import com.vincode.simipa.model.ScholarshipResult;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder> {

    private List<AchievementResult> listAchievement = new ArrayList<>();

    public void setListAchievement(List<AchievementResult> listAchievement) {
        if (listAchievement == null) return;
        this.listAchievement.clear();
        this.listAchievement.addAll(listAchievement);
    }

    @NonNull
    @Override
    public AchievementAdapter.AchievementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_achievement, parent, false);
        AchievementAdapter.AchievementViewHolder viewHolder = new AchievementAdapter.AchievementViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AchievementAdapter.AchievementViewHolder holder, int position) {
        final AchievementResult dataAchievement = listAchievement.get(position);

        holder.tvAchievement.setText(dataAchievement.getNamaKegiatan());
    }

    @Override
    public int getItemCount() {
        return listAchievement.size();
    }

    public class AchievementViewHolder extends RecyclerView.ViewHolder {
        TextView tvAchievement;

        public AchievementViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAchievement = itemView.findViewById(R.id.tv_achievement);
        }
    }
}
