package com.vincode.simipa.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.SeminarResult;
import com.vincode.simipa.util.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SeminarPresenceAdapter extends RecyclerView.Adapter<SeminarPresenceAdapter.ViewHolder> {

    private List<SeminarResult> listSeminar = new ArrayList<>();
    private final Activity activity;

    public SeminarPresenceAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListSeminar(List<SeminarResult> seminarResults){

        if (seminarResults == null)return;
        this.listSeminar.clear();
        this.listSeminar.addAll(seminarResults);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_presence_seminar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SeminarResult data = listSeminar.get(position);
        TimeUtil timeUtil = new TimeUtil();
        holder.tvName.setText(data.getNama());
        holder.tvNpm.setText(data.getNpm());
        holder.tvTitle.setText(data.getJudul());
        holder.tvJam.setText(data.getJam());
        holder.tvTgl.setText(timeUtil.converDate(data.getTanggal()));
        holder.tvJenis.setText(data.getJenis());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd", Locale.getDefault());
        String date = dateFormat.format(new Date());
        String[] dates = date.split("-");
        int tgl = Integer.parseInt(dates[0]);
        int tglSeminar = Integer.parseInt(timeUtil.converDate(data.getTanggal()).substring(0,2));
        int selisih = tgl - tglSeminar;

        if (selisih == 0 || selisih > 0 ){//seminar komprehensif
            holder.btnHadir.setVisibility(View.GONE);
        }else if(selisih == -2 || selisih == -1){
            holder.btnHadir.setVisibility(View.VISIBLE);
        }else {
            holder.btnHadir.setVisibility(View.GONE);
        }

        String jurusan = data.getNpm().substring(4,6);
        //String jurusan = hasil.substring(0,1);
        Toast.makeText(activity, jurusan, Toast.LENGTH_SHORT).show();
        switch (jurusan){
            case "01":
                holder.tvJenis.setBackgroundResource(R.drawable.bg_btn_blue);
                break;
            case "02":
                holder.tvJenis.setBackgroundResource(R.drawable.bg_green);
                break;
            case "03":
                holder.tvJenis.setBackgroundResource(R.drawable.bg_red);
                break;
            case "04":
                holder.tvJenis.setBackgroundResource(R.drawable.bg_blue_dark);
                break;
            case "05":
                holder.tvJenis.setBackgroundResource(R.drawable.bg_yellow);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listSeminar.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvNpm, tvTitle, tvJam, tvTgl, tvJenis;
        Button btnHadir;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvNpm = itemView.findViewById(R.id.tv_npm);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvJam = itemView.findViewById(R.id.tv_time);
            tvTgl = itemView.findViewById(R.id.tv_date);
            tvName = itemView.findViewById(R.id.tv_name);
            tvJenis = itemView.findViewById(R.id.tv_jenis);
            btnHadir = itemView.findViewById(R.id.btn_hadir);
        }
    }
}
