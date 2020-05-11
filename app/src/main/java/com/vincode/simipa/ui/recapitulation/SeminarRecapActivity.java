package com.vincode.simipa.ui.recapitulation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.SeminarRecapAdapter;
import com.vincode.simipa.adapter.SeminarRecapHasilAdapter;
import com.vincode.simipa.adapter.SeminarRecapUsulAdapter;
import com.vincode.simipa.model.SeminarResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;

import java.util.Objects;

public class SeminarRecapActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rvCategory;

    SeminarRecapAdapter seminarRecapAdapter;
    SeminarRecapUsulAdapter seminarRecapUsulAdapter;
    SeminarRecapHasilAdapter seminarRecapHasilAdapter;
    RelativeLayout relativeLayout, relativeLayout1, relativeLayout2;
    private ProgressBar pgBar;
    private TextView jmlKP, jmlUsul, jmlHasil;
    private ImageView icPresentation, icPresentation1, icPresentation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_recap);

        jmlKP = findViewById(R.id.jumlah_kp);
        jmlUsul = findViewById(R.id.jumlah_usul);
        jmlHasil = findViewById(R.id.jumlah_hasil);

        relativeLayout = findViewById(R.id.klikKP);
        relativeLayout.setOnClickListener(this);
        relativeLayout1 = findViewById(R.id.klikUsul);
        relativeLayout1.setOnClickListener(this);
        relativeLayout2 = findViewById(R.id.klikHasil);
        relativeLayout2.setOnClickListener(this);

        pgBar = findViewById(R.id.pg_bar);
        pgBar.setVisibility(View.VISIBLE);
        icPresentation = findViewById(R.id.ic_presentation_kp);
        icPresentation1 = findViewById(R.id.ic_presentation_usul);
        icPresentation2 = findViewById(R.id.ic_presentation_hasil);
        rvCategory = findViewById(R.id.rv_seminar_recap);

        seminarRecapAdapter = new SeminarRecapAdapter();
        seminarRecapUsulAdapter = new SeminarRecapUsulAdapter();
        seminarRecapHasilAdapter = new SeminarRecapHasilAdapter();

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.seminar);

        showRecyclerCardView();
        getData();
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        rvCategory.setHasFixedSize(true);
        rvCategory.setAdapter(seminarRecapAdapter);
    }

    private void getData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SeminarResponse> call = apiInterface.getRecapSeminar(SharedPrefManager.getInstance(this).getUser().getUserLogin());
        call.enqueue(new Callback<SeminarResponse>() {
            @Override
            public void onResponse(@NonNull Call<SeminarResponse> call, @NonNull Response<SeminarResponse> response) {

                if (response.body() != null) {
                    seminarRecapAdapter.setListSeminar(response.body().getResult());
                    seminarRecapUsulAdapter.setListSeminar(response.body().getResult());
                    seminarRecapHasilAdapter.SeminarRecapHasilAdapter(response.body().getResult());
                    jmlHasil.setText(response.body().getJumlahSemHas());
                    jmlUsul.setText(response.body().getJumlahSemUsul());
                    jmlKP.setText(response.body().getJumlahSemKP());
                    seminarRecapAdapter.notifyDataSetChanged();
                    pgBar.setVisibility(View.GONE);
                } else {
                    Log.d("Tes ", " ");
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeminarResponse> call, @NonNull Throwable t) {
                Log.d("c", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refresh, menu);

        MenuItem refresh = menu.findItem(R.id.action_refresh);
        refresh.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent refresh = new Intent(SeminarRecapActivity.this, SeminarRecapActivity.class);
                startActivity(refresh);
                finish();
                return true;
            }
        });

        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.klikKP :
                icPresentation.setImageResource(R.drawable.presentation_active);
                icPresentation1.setImageResource(R.drawable.presentation);
                icPresentation2.setImageResource(R.drawable.presentation);

                rvCategory.setAdapter(seminarRecapAdapter);
                seminarRecapAdapter.getFilter().filter("seminar kerja");
                break;
            case R.id.klikUsul :
                icPresentation1.setImageResource(R.drawable.presentation_active);
                icPresentation2.setImageResource(R.drawable.presentation);
                icPresentation.setImageResource(R.drawable.presentation);

                rvCategory.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                rvCategory.setHasFixedSize(true);
                rvCategory.setAdapter(seminarRecapUsulAdapter);
                seminarRecapUsulAdapter.getFilter().filter("seminar usul");
                break;
            case R.id.klikHasil :
                icPresentation2.setImageResource(R.drawable.presentation_active);
                icPresentation1.setImageResource(R.drawable.presentation);
                icPresentation.setImageResource(R.drawable.presentation);

                rvCategory.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                rvCategory.setHasFixedSize(true);
                rvCategory.setAdapter(seminarRecapHasilAdapter);
                seminarRecapHasilAdapter.getFilter().filter("seminar hasil");
                break;
        }
    }
}