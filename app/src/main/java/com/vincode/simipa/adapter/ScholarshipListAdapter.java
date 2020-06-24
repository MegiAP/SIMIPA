package com.vincode.simipa.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.ScholarshipListResult;
import com.vincode.simipa.ui.beasiswa.FormBeasiswaActivity;
import com.vincode.simipa.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import static com.vincode.simipa.ui.beasiswa.FormBeasiswaActivity.EXTRA_DATE;
import static com.vincode.simipa.ui.beasiswa.FormBeasiswaActivity.EXTRA_ORGANIZER;
import static com.vincode.simipa.ui.beasiswa.FormBeasiswaActivity.EXTRA_YEAR;
import static com.vincode.simipa.ui.beasiswa.FormBeasiswaFragment.EXTRA_NAME;

public class ScholarshipListAdapter extends RecyclerView.Adapter<ScholarshipListAdapter.ScholarshipListViewHolder> {

    private List<ScholarshipListResult> listScholarship = new ArrayList<>();

    public void setListScholarship(List<ScholarshipListResult> listScholarship) {
        if (listScholarship == null) return;
        this.listScholarship.clear();
        this.listScholarship.addAll(listScholarship);
    }

    @NonNull
    @Override
    public ScholarshipListAdapter.ScholarshipListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_beasiswa, parent, false);
        return new ScholarshipListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScholarshipListAdapter.ScholarshipListViewHolder holder, int position) {
        final ScholarshipListResult dataScholarship = listScholarship.get(position);

        TimeUtil timeUtil = new TimeUtil();
        final String tanggal = timeUtil.getTanggalFormatInd(dataScholarship.getSelesai());

        holder.tvName.setText(dataScholarship.getNama());
        holder.tvPenyelenggara.setText(dataScholarship.getPenyelenggara());
        holder.tvTahun.setText(dataScholarship.getTahun());
        holder.tvSelesai.setText(tanggal);
        holder.tvSelesai.setPaddingRelative(0, 10, 0, 0);
        holder.tvSmstr.setVisibility(View.GONE);

        holder.cvBeasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), FormBeasiswaActivity.class);
                i.putExtra(EXTRA_NAME, dataScholarship.getNama());
                i.putExtra(EXTRA_ORGANIZER, dataScholarship.getPenyelenggara());
                i.putExtra(EXTRA_YEAR, dataScholarship.getTahun());
                i.putExtra(EXTRA_DATE, tanggal);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listScholarship.size();
    }

    public class ScholarshipListViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPenyelenggara, tvTahun, tvSelesai, tvSmstr;
        CardView cvBeasiswa;

        public ScholarshipListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_scholarship);
            tvTahun = itemView.findViewById(R.id.tv_tahun);
            tvPenyelenggara = itemView.findViewById(R.id.tv_status);
            tvSmstr = itemView.findViewById(R.id.tv_semester);
            tvSelesai = itemView.findViewById(R.id.tv_smstr);
            cvBeasiswa = itemView.findViewById(R.id.cv_beasiswa);
        }
    }
}
