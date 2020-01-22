package com.vincode.simipa.adapter;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.StudyResult;
import com.vincode.simipa.ui.study_progress.DetailMatkulActivity;

import java.util.ArrayList;
import java.util.List;

//api latihan
public class ProgressStudyAdapter extends RecyclerView.Adapter<ProgressStudyAdapter.ProgressViewHolder> {

    private List<StudyResult> listStudy = new ArrayList<>();
    private final Activity activity;

    public ProgressStudyAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListCalendar(List<StudyResult> studyList){

        if (studyList == null)return;
        this.listStudy.clear();
        this.listStudy.addAll(studyList);

    }


    @NonNull
    @Override
    public ProgressViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_progress_study, viewGroup, false);
        return new ProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgressViewHolder progressViewHolder, int i) {
        final StudyResult progressStudy = listStudy.get(i);

        progressViewHolder.tvSemester.setText(progressStudy.getSemester());
        progressViewHolder.tvValueIpk.setText(progressStudy.getIp());
        progressViewHolder.tvValueSks.setText(progressStudy.getSks());
        progressViewHolder.cvProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveIntent = new Intent(activity, DetailMatkulActivity.class);
                //moveIntent.putExtra(DetailMatkulActivity.EXTRA_INTENT, );
                activity.startActivity(moveIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listStudy.size();
    }

     class ProgressViewHolder extends RecyclerView.ViewHolder {
        TextView tvSemester, tvValueIpk, tvValueSks;
        CardView cvProgress;

         ProgressViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSemester = itemView.findViewById(R.id.tv_progress_semester);
            tvValueIpk = itemView.findViewById(R.id.tv_value_ipk);
            tvValueSks = itemView.findViewById(R.id.tv_value_sks);
            cvProgress = itemView.findViewById(R.id.cv_item_progress);
        }
    }
}
