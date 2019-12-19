package com.vincode.simipa.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.KRSResult;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KRSAdapter extends RecyclerView.Adapter<KRSAdapter.KRSViewHolder> {
    private List<KRSResult> listKRS = new ArrayList<>();
    private final Activity activity;

    public void setListKRS(List<KRSResult> krsList) {
        this.listKRS.clear();
        this.listKRS.addAll(krsList);
    }

    public KRSAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public KRSAdapter.KRSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_cardview_krs, parent, false);
        return new KRSViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KRSAdapter.KRSViewHolder holder, int position) {
        final KRSResult dataKRS = listKRS.get(position);

        holder.tvCodeName.setText(dataKRS.getKodeMK());
        holder.tvSemester.setText(dataKRS.getSemester());
        holder.tvSks.setText(dataKRS.getPengambilanKe());
    }

    @Override
    public int getItemCount() {
        return listKRS.size();
    }

    class KRSViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCodeName, tvSemester, tvSks;

        public KRSViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCodeName = itemView.findViewById(R.id.tv_code_name);
            tvSemester = itemView.findViewById(R.id.tv_item_semester);
            tvSks = itemView.findViewById(R.id.tv_item_sks);
        }
    }
}
