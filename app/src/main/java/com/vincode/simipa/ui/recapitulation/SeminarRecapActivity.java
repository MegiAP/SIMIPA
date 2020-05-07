package com.vincode.simipa.ui.recapitulation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.vincode.simipa.R;
import com.vincode.simipa.adapter.SeminarRecapAdapter;
import com.vincode.simipa.model.SeminarResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;

import java.util.Objects;

public class SeminarRecapActivity extends AppCompatActivity {
    private RecyclerView rvCategory;

    SeminarRecapAdapter seminarRecapAdapter;
    private ProgressBar pgBar;
    private TextView jmlKP, jmlUsul, jmlHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_recap);

        jmlKP = findViewById(R.id.jumlah_kp);
        jmlUsul = findViewById(R.id.jumlah_usul);
        jmlHasil = findViewById(R.id.jumlah_hasil);

        pgBar = findViewById(R.id.pg_bar);
        pgBar.setVisibility(View.VISIBLE);
        seminarRecapAdapter = new SeminarRecapAdapter();

        rvCategory = findViewById(R.id.rv_seminar_recap);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.seminar);

        showRecyclerCardView();
//        getCountSeminar();
        getData();
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        rvCategory.setHasFixedSize(true);
        rvCategory.setAdapter(seminarRecapAdapter);
    }

//    private void getCountSeminar() {
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<CountSeminarResponse> call = apiInterface.getCountSeminar(SharedPrefManager.getInstance(this).getUser().getUserLogin());
//        call.enqueue(new Callback<CountSeminarResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<CountSeminarResponse> call, @NonNull Response<CountSeminarResponse> response) {
//                assert response.body() != null;
//                List<CountSeminarResult> countSeminar = response.body().getResult();
//                jmlKP.setText(countSeminar.get(0).getJumlahSemKP());
//                jmlUsul.setText(countSeminar.get(0).getJumlahSemUsul());
//                jmlHasil.setText(countSeminar.get(0).getJumlahSemHas());
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<CountSeminarResponse> call, @NonNull Throwable t) {
//                Log.d("c", Objects.requireNonNull(t.getMessage()));
//            }
//        });
//    }

    private void getData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<SeminarResponse> call = apiInterface.getRecapSeminar(SharedPrefManager.getInstance(this).getUser().getUserLogin());
        call.enqueue(new Callback<SeminarResponse>() {
            @Override
            public void onResponse(@NonNull Call<SeminarResponse> call, @NonNull Response<SeminarResponse> response) {

                if (response.body() != null) {
                    seminarRecapAdapter.setListSeminar(response.body().getResult());
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

    // button filter
/*    public void klikKP(View view) {
        seminarRecapAdapter.getFilter().filter("seminar kp");
    }

    public void klikUsul(View view) {
        seminarRecapAdapter.getFilter().filter("seminar usul");
    }

    public void klikHasil(View view) {
        seminarRecapAdapter.getFilter().filter("seminar_hasil");
    }*/

    // search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_search, menu);

        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) search.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                seminarRecapAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

}