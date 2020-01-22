package com.vincode.simipa.ui.study_progress;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.vincode.simipa.R;
import com.vincode.simipa.adapter.ProgressStudyAdapter;
import com.vincode.simipa.model.StudyResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudyProgressActivity extends AppCompatActivity {

    private ProgressStudyAdapter progressStudyAdapter;
    private RecyclerView rvProgressStudy;
    private ProgressBar progressBar;
//    private ArrayList<ProgressStudy> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_progress);

        rvProgressStudy = findViewById(R.id.rv_study_progress);
        progressBar = findViewById(R.id.progress_bar);

        //list.addAll(TestProgress.getListProgress());
        progressStudyAdapter = new ProgressStudyAdapter(this);
        progressBar.setVisibility(View.VISIBLE);

        ImageView imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        drawLineChart();
        setLayout();
        getData();
    }

    private void getData() {
        //api latihan
        ApiInterface apiInterface = ApiClient.getClientLocal().create(ApiInterface.class);

        Call<StudyResponse> call = apiInterface.getStudi("1617051103");
        call.enqueue(new Callback<StudyResponse>() {
            @Override
            public void onResponse(@NonNull Call<StudyResponse> call, @NonNull Response<StudyResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    progressStudyAdapter.setListCalendar(response.body().getResult());
                    progressStudyAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<StudyResponse> call, @NonNull Throwable t) {

            }
        });
    }

    private void drawLineChart(){
        LineChart lineChart = findViewById(R.id.line_chart);

        List<Entry> lineEntry = getDataSet();

        LineDataSet lineDataSet = new LineDataSet(lineEntry, "Kemajuan Studi");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setLineWidth(2);
        lineDataSet.setColor(Color.WHITE);
        lineDataSet.setCircleColor(Color.parseColor("#1E90FF"));
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleHoleRadius(3);
        lineDataSet.setDrawHighlightIndicators(true);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setValueTextColor(Color.DKGRAY);

        LineData lineData = new LineData(lineDataSet);
        lineChart.getDescription().setText("semester");
        lineChart.getDescription().setTextSize(12);
        lineChart.getDescription().setTextColor(Color.WHITE);
        lineChart.setDrawMarkers(true);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setDrawAxisLine(false);

        lineChart.animateY(1000);
        lineChart.getXAxis().setGranularityEnabled(true);
        lineChart.getXAxis().setGranularity(1.0f);
        lineChart.getXAxis().setTextColor(Color.WHITE);
        //lineChart.getXAxis().setLabelCount(1);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.setBackgroundResource(R.drawable.bg_gradient_blue);
        lineChart.setData(lineData);


    }

    private List<Entry> getDataSet() {

        List<Entry> lineEntry = new ArrayList<>();
        lineEntry.add(new Entry(1, 3.3f));
        lineEntry.add(new Entry(2,3.45f));

        return lineEntry;
    }

    void setLayout(){
        rvProgressStudy.setLayoutManager(new LinearLayoutManager(this));
        rvProgressStudy.setHasFixedSize(true);
        rvProgressStudy.setAdapter(progressStudyAdapter);
    }

}
