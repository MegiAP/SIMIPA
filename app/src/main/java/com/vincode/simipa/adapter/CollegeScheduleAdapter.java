package com.vincode.simipa.adapter;

import android.app.Activity;
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

public class CollegeScheduleAdapter extends RecyclerView.Adapter<CollegeScheduleAdapter.ViewHolder>  {

    private List<CollegeScheduleResult> listCollegeSchedule = new ArrayList<>();
    private final Activity activity;

    public CollegeScheduleAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListCollegeSchedule(List<CollegeScheduleResult> collegeScheduleList){

        if (collegeScheduleList == null)return;
        this.listCollegeSchedule.clear();
        this.listCollegeSchedule.addAll(collegeScheduleList);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_college_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CollegeScheduleResult dataResult = listCollegeSchedule.get(position);

        holder.colmatkul.setText(dataResult.getMataKuliah());
        holder.colmkkode.setText(dataResult.getKodeMK());
        holder.coldosen.setText(dataResult.getDosenPJ());
        holder.colnip.setText(dataResult.getNip1());
        holder.colruang.setText(dataResult.getRuang());
        holder.coljammulai.setText(dataResult.getMulai());
        holder.coljamselesai.setText(dataResult.getSelesai());
        holder.colprodi.setText(dataResult.getProdi());
    }

    @Override
    public int getItemCount() {
        return listCollegeSchedule.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView colmatkul,colmkkode,coldosen, colnip, colruang, coljammulai, coljamselesai, colprodi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            colmatkul = itemView.findViewById(R.id.col_matkul);
            colmkkode = itemView.findViewById(R.id.col_matkode);
            coldosen= itemView.findViewById(R.id.col_dosen);
            colnip = itemView.findViewById(R.id.col_nip);
            colruang = itemView.findViewById(R.id.col_ruang);
            colprodi = itemView.findViewById(R.id.col_prodi);
            coljammulai = itemView.findViewById(R.id.col_jammulai);
            coljamselesai =itemView.findViewById(R.id.col_jamselesai);
        }
    }
}