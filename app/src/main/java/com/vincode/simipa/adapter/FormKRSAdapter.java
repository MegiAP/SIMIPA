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

public class FormKRSAdapter extends RecyclerView.Adapter<FormKRSAdapter.FormViewHolder> {

    private List<KRSResult> listFormKRS = new ArrayList<>();
    private final Activity activity;

    public void setListFormKRS(List<KRSResult> listFormKRS) {
        this.listFormKRS = listFormKRS;
    }

    public FormKRSAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public FormKRSAdapter.FormViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_form_krs, parent, false);
        return new FormViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormKRSAdapter.FormViewHolder holder, int position) {
        final KRSResult dataKRS = listFormKRS.get(position);

        holder.tvCodeName.setText(dataKRS.getKodeMK());
        holder.tvSemester.setText(dataKRS.getSemester());

    }

    @Override
    public int getItemCount() {
        return listFormKRS.size();
    }

    public class FormViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCodeName, tvSemester;

        public FormViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCodeName = itemView.findViewById(R.id.tv_code_name);
            tvSemester = itemView.findViewById(R.id.tv_item_semester);
        }
    }
}
