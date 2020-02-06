package com.vincode.simipa.adapter;

import android.app.Activity;
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
import com.vincode.simipa.model.SeminarSchedule;
import com.vincode.simipa.model.SeminarScheduleResult;

import java.util.ArrayList;
import java.util.List;

public class SeminarScheduleAdapter extends RecyclerView.Adapter<SeminarScheduleAdapter.ViewHolder> {

    private List<SeminarScheduleResult> listSeminarSchedule = new ArrayList<>();
    private final Activity activity;

    public SeminarScheduleAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListSeminarSchedule(List<SeminarScheduleResult> seminarScheduleList){

        if (seminarScheduleList == null)return;
        this.listSeminarSchedule.clear();
        this.listSeminarSchedule.addAll(seminarScheduleList);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_seminar_schedule, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SeminarScheduleAdapter.ViewHolder holder, int position) {
        final SeminarScheduleResult dataResult = listSeminarSchedule.get(position);

        holder.smNpm.setText(dataResult.getNPM());
        holder.smJudul.setText(dataResult.getJudul());
        holder.smNip.setText(dataResult.getPbg1());
        holder.smWaktu.setText(dataResult.getWaktu());
        holder.smTgl.setText(dataResult.getTanggal());
        holder.smRuang.setText(dataResult.getRuang());


    }

    @Override
    public int getItemCount() {
        return listSeminarSchedule.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button btnJoin1;
        TextView smNpm,smJudul,smRuang, smNip, smWaktu, smTgl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            smNpm = itemView.findViewById(R.id.tv_npmSem);
            smJudul = itemView.findViewById(R.id.tv_judulSem);
            smRuang= itemView.findViewById(R.id.tv_ruangSem);
            smNip = itemView.findViewById(R.id.tv_nipSeminar);
            smWaktu = itemView.findViewById(R.id.tv_jamsem);
            smTgl = itemView.findViewById(R.id.tv_tglsem);

        }
    }
}
   /* Context context;
    List<SeminarSchedule> modelList;

    public SeminarScheduleAdapter(Context context, List<SeminarSchedule> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public SeminarScheduleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_seminar_schedule, parent, false);
        SeminarScheduleAdapter.MyViewHolder myViewHolder = new SeminarScheduleAdapter.MyViewHolder(v);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(SeminarScheduleAdapter.MyViewHolder holder, int position) {

        holder.tgl.setText(modelList.get(position).getTgl());
        holder.jam.setText(modelList.get(position).getJam());
        holder.judul.setText(modelList.get(position).getJudul());
        holder.nama.setText(modelList.get(position).getNama());
        holder.ruang.setText(modelList.get(position).getRuang());
        holder.jenis.setText(modelList.get(position).getJenis());
        holder.btnJoin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Lagi tes button", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private Button btnJoin1;
        TextView tgl,jam,judul,nama,ruang,jenis;


        public MyViewHolder(View itemView) {
            super(itemView);

            tgl = itemView.findViewById(R.id.tv_tglsem);
            jam = itemView.findViewById(R.id.tv_jamsem);
            judul= itemView.findViewById(R.id.tv_judulSem);
            nama = itemView.findViewById(R.id.tv_mhsSem);
            ruang = itemView.findViewById(R.id.tv_ruangSem);
            jenis = itemView.findViewById(R.id.tv_jenisSem);
            btnJoin1 = itemView.findViewById(R.id.btn_joinSem);

        }
    }
}*/


