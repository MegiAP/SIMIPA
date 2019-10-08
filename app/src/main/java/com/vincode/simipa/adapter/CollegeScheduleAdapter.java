package com.vincode.simipa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.CollegeSchedule;

import java.util.List;

public class CollegeScheduleAdapter extends RecyclerView.Adapter<CollegeScheduleAdapter.MyViewHolder>  {
    Context context;
    List<CollegeSchedule> modelList;

    public CollegeScheduleAdapter(Context context, List<CollegeSchedule> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public CollegeScheduleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_college_schedule, parent, false);
        CollegeScheduleAdapter.MyViewHolder myViewHolder = new CollegeScheduleAdapter.MyViewHolder(v);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(CollegeScheduleAdapter.MyViewHolder holder, int position) {

        holder.jdmatkul.setText(modelList.get(position).getJdmatkul());
        holder.jdmatkode.setText(modelList.get(position).getJdmatkode());
        holder.jddosen.setText(modelList.get(position).getJddosen());
        holder.jdruang.setText(modelList.get(position).getJdruang());
        holder.jdjam.setText(modelList.get(position).getJdjam());


    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView jdmatkul,jdmatkode,jddosen, jdruang, jdjam;


        public MyViewHolder(View itemView) {
            super(itemView);

            jdmatkul = itemView.findViewById(R.id.jd_matkul);
            jdmatkode = itemView.findViewById(R.id.jd_matkode);
            jddosen= itemView.findViewById(R.id.jd_dosen);
            jdruang = itemView.findViewById(R.id.jd_ruang);
            jdjam = itemView.findViewById(R.id.jd_jam);

        }
    }
}

