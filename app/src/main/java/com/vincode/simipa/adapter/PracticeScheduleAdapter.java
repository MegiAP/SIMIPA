package com.vincode.simipa.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.PracticeScheduleResult;

import java.util.ArrayList;
import java.util.List;

public class PracticeScheduleAdapter extends RecyclerView.Adapter<PracticeScheduleAdapter.ViewHolder> {
    private List<PracticeScheduleResult> listPracticeSchedule = new ArrayList<>();
    private final Activity activity;

    public PracticeScheduleAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListPracticeSchedule(List<PracticeScheduleResult> practiceScheduleList){
        if(practiceScheduleList == null) return;
        this.listPracticeSchedule.clear();
        this.listPracticeSchedule.addAll(practiceScheduleList);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(activity).inflate(R.layout.item_practice_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PracticeScheduleResult dataResult = listPracticeSchedule.get(position);

        holder.prac_matkul.setText(dataResult.getMataKuliah());
        holder.prac_mkkode.setText(dataResult.getKodeMK());
        holder.prac_dosen.setText(dataResult.getDosenPJ());
        holder.prac_nip.setText(dataResult.getNip1());
        holder.prac_ruang.setText(dataResult.getRuang());
        holder.prac_jammulai.setText(dataResult.getMulai());
        holder.prac_jamselesai.setText(dataResult.getSelesai());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prac_matkul,prac_mkkode,prac_dosen, prac_nip, prac_ruang, prac_jammulai, prac_jamselesai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            prac_matkul = itemView.findViewById(R.id.practice_matkul);
            prac_mkkode = itemView.findViewById(R.id.practice_mkkode);
            prac_dosen= itemView.findViewById(R.id.practice_dosen);
            prac_nip = itemView.findViewById(R.id.practice_nip);
            prac_ruang = itemView.findViewById(R.id.practice_ruang);
            prac_jammulai = itemView.findViewById(R.id.practice_jamMulai);
            prac_jamselesai =itemView.findViewById(R.id.practice_jamSelesai);
        }
    }
}
/*
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
*/
