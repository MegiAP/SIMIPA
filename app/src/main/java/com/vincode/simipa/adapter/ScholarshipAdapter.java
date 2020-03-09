package com.vincode.simipa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.ScholarshipResult;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScholarshipAdapter extends RecyclerView.Adapter<ScholarshipAdapter.ScholarshipViewHolder> {

    private List<ScholarshipResult> listScholarship = new ArrayList<>();

    public void setListScholarship(List<ScholarshipResult> listScholarship) {
        if (listScholarship == null) return;
        this.listScholarship.clear();
        this.listScholarship.addAll(listScholarship);
    }

    @NonNull
    @Override
    public ScholarshipAdapter.ScholarshipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_beasiswa, parent, false);
        return new ScholarshipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScholarshipAdapter.ScholarshipViewHolder holder, int position) {
        final ScholarshipResult dataScholarship = listScholarship.get(position);

        holder.tvNameScholarship.setText(dataScholarship.getNamaBeasiswa());
        holder.tvTahun.setText(dataScholarship.getTahunBeasiswa());
        holder.tvStatus.setText(dataScholarship.getStatusAjukan());
        holder.tvSemester.setText(dataScholarship.getSemester());

    }

    @Override
    public int getItemCount() {
        return listScholarship.size();
    }

    static class ScholarshipViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameScholarship, tvStatus, tvTahun,tvSemester;

        ScholarshipViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameScholarship = itemView.findViewById(R.id.tv_scholarship);
            tvTahun = itemView.findViewById(R.id.tv_tahun);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvSemester = itemView.findViewById(R.id.tv_semester);


        }
    }
}
