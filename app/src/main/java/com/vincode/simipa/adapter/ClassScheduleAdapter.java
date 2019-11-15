package com.vincode.simipa.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.ClassScheduleResult;

import java.util.ArrayList;
import java.util.List;

public class ClassScheduleAdapter extends RecyclerView.Adapter<ClassScheduleAdapter.ViewHolder> {

    private List<ClassScheduleResult> listClassSchedule = new ArrayList<>();
    private final Activity activity;

    public ClassScheduleAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListClassSchedule(List<ClassScheduleResult> calendarList){

        if (calendarList == null)return;
        this.listClassSchedule.clear();
        this.listClassSchedule.addAll(calendarList);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_class_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ClassScheduleResult dataResult = listClassSchedule.get(position);

        holder.clmatkul.setText(dataResult.getMataKuliah());
        holder.clmkkode.setText(dataResult.getKodeMK());
        holder.cldosen.setText(dataResult.getDosenPj());
        holder.clnip.setText(dataResult.getNip1());
        holder.clruang.setText(dataResult.getRuang());
        holder.cljammulai.setText(dataResult.getMulai());
        holder.cljamselesai.setText(dataResult.getSelesai());
        holder.cljenis.setText(dataResult.getJenis());
    }

    @Override
    public int getItemCount() {
        return listClassSchedule.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView clmatkul,clmkkode,cldosen, clnip, clruang, cljammulai, cljamselesai, cljenis;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            clmatkul = itemView.findViewById(R.id.class_matkul);
            clmkkode = itemView.findViewById(R.id.class_mkkode);
            cldosen= itemView.findViewById(R.id.class_dosen);
            clnip = itemView.findViewById(R.id.class_nip);
            clruang = itemView.findViewById(R.id.class_ruang);
            cljenis = itemView.findViewById(R.id.class_jenis);
            cljammulai = itemView.findViewById(R.id.class_jamMulai);
            cljamselesai =itemView.findViewById(R.id.class_jamSelesai);
        }
    }
}
