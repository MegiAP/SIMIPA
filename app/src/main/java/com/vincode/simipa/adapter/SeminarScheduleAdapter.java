package com.vincode.simipa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.SeminarSchedule;

import java.util.List;

public class SeminarScheduleAdapter extends RecyclerView.Adapter<SeminarScheduleAdapter.MyViewHolder>{
    Context context;
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
}

