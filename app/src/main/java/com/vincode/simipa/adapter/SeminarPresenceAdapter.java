package com.vincode.simipa.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vincode.simipa.R;
import com.vincode.simipa.model.SeminarPresenceResult;
import com.vincode.simipa.model.Value;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.ui.presence.PresenceActivity;
import com.vincode.simipa.util.SharedPrefManager;
import com.vincode.simipa.util.TimeUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeminarPresenceAdapter extends RecyclerView.Adapter<SeminarPresenceAdapter.ViewHolder> {

    private List<SeminarPresenceResult> listSeminar = new ArrayList<>();
    private final Activity activity;

    public SeminarPresenceAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListSeminar(List<SeminarPresenceResult> seminarResults){

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final SeminarPresenceResult data = listSeminar.get(position);
        TimeUtil timeUtil = new TimeUtil();
        holder.tvName.setText(data.getNama());
        holder.tvNpm.setText(data.getNpm());
        holder.tvTitle.setText(data.getJudul());
        holder.tvJam.setText(data.getJam());
        holder.tvTgl.setText(timeUtil.converDate(data.getTanggal()));
        holder.tvJenis.setText(data.getJenis());

        //fungsi untuk menentukan status button hadir
        String tglNow = timeUtil.getWaktuNow();
        String[] now = tglNow.split("-");
        String[] tanggalSeminar = data.getTanggal().split("-");

        final int selisihTgl = Integer.parseInt(now[0]) - Integer.parseInt(tanggalSeminar[2]);//12-14
        final int selisihBulan = Integer.parseInt(now[1]) - Integer.parseInt(tanggalSeminar[1]);

        //Log.d("waktu", selisihBulan +" "+selisihTgl);

//        if (selisihBulan == 0){
//            if (selisihTgl < -1 ){//seminar komprehensif
//                holder.btnHadir.setVisibility(View.VISIBLE);
//            }else if(selisihTgl > 0){
//                holder.btnHadir.setVisibility(View.GONE);
//            }else{
//                holder.btnCancel.setVisibility(View.GONE);
//            }
//        }else{
//            holder.btnHadir.setVisibility(View.GONE);
//        }
        //akhir fungsi


        // Fungsi untuk menentukan jurusan mana yg seminar
        String jurusan = data.getNpm().substring(4,6);
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

        holder.btnHadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                builder.setMessage("Apakah anda yakin untuk daftar menghadiri seminar ini?").
                        setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(activity, "insert data", Toast.LENGTH_SHORT).show();
                        insertData(SharedPrefManager.getInstance(activity).getUser().getUserLogin(),
                                SharedPrefManager.getInstance(activity).getUser().getDisplayName() , data.getId());

                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.cancel();
                    }
                });

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        //akhir fungsi

        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                builder.setMessage("Apakah anda yakin untuk membatalkan menghadiri seminar ini?").
                        setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Toast.makeText(activity, "update data", Toast.LENGTH_SHORT).show();
                                updateData(SharedPrefManager.getInstance(activity).getUser().getUserLogin(), data.getId());

                            }
                        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        ApiInterface apiInterface = ApiClient.getClientLocal().create(ApiInterface.class);
        Call<Value> call = apiInterface.cekDaftarSeminar(SharedPrefManager.getInstance(activity).getUser().getUserLogin(),
                data.getId());
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(@NotNull Call<Value> call, @NotNull Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
//                String message = response.body().getMessage();
                Double kuota = (Double.parseDouble(response.body().getKuota())/30) * 100;
//                Toast.makeText(activity, kuota,Toast.LENGTH_SHORT).show();
                Log.d("kuota", String.valueOf(kuota));
                holder.progressKuota.setProgress(kuota.intValue());
                holder.tvKuota.setText(String.format("%s/30", response.body().getKuota()));

                if (value.equals("1")){
                    //Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(AddActivity.this, MainActivity.class));
                    if (selisihBulan == 0 ){
                        if (selisihTgl == 0 || selisihTgl == -1 ){//seminar komprehensif
                            holder.btnCancel.setVisibility(View.GONE);
                        }else{
                            holder.btnCancel.setVisibility(View.VISIBLE);
                        }
                    }else if (selisihBulan < 0){
                        holder.btnCancel.setVisibility(View.VISIBLE);
                    }else{
                        holder.btnCancel.setVisibility(View.GONE);
                    }
                }else {
                    //Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
//                    holder.btnHadir.setVisibility(View.VISIBLE);
                    if (selisihBulan == 0){
                        if (selisihTgl <= 0 ){//seminar komprehensif
                            holder.btnHadir.setVisibility(View.VISIBLE);
                        }else{
                            holder.btnHadir.setVisibility(View.GONE);
                        }
                    }else if (selisihBulan < 0){
                        holder.btnHadir.setVisibility(View.VISIBLE);
                    }else {
                        holder.btnHadir.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<Value> call, @NotNull Throwable t) {
                t.printStackTrace();
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateData(String npm, String id_seminar) {
        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        ApiInterface apiInterface = ApiClient.getClientLocal().create(ApiInterface.class);

        Call<Value> call = apiInterface.updateHadirSeminar(npm, id_seminar);

        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(@NotNull Call<Value> call, @NotNull Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progressDialog.dismiss();
                if (value.equals("1")){
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    activity.startActivity(new Intent(activity, PresenceActivity.class));
                }else {
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Value> call, @NotNull Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertData(String npm, String nama,  String id_seminar) {
        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        ApiInterface apiInterface = ApiClient.getClientLocal().create(ApiInterface.class);

        Call<Value> call = apiInterface.setHadirSeminar(npm, nama, "1", id_seminar);

        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(@NotNull Call<Value> call, @NotNull Response<Value> response) {
                assert response.body() != null;
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progressDialog.dismiss();
                if (value.equals("1")){
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    activity.startActivity(new Intent(activity, PresenceActivity.class));
                }else {
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Value> call, @NotNull Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listSeminar.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvNpm, tvTitle, tvJam, tvTgl, tvJenis, tvKuota;
        Button btnHadir, btnCancel;
        ProgressBar progressKuota;

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
            btnCancel = itemView.findViewById(R.id.btn_batal);
            progressKuota = itemView.findViewById(R.id.pb_kuota);
            tvKuota = itemView.findViewById(R.id.tv_kuota);
        }
    }
}
