package com.vincode.simipa.ui.profil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vincode.simipa.MainActivity;
import com.vincode.simipa.R;
import com.vincode.simipa.SharedPrefManager;
import com.vincode.simipa.model.LoginResponse;
import com.vincode.simipa.model.ProfileResponse;
import com.vincode.simipa.model.UserProfile;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

import java.util.List;

public class ProfilActivity extends AppCompatActivity {

    TextView tvProfilName, tvProfilNPM, tvProfilEmail, tvProfilJurusan, tvTanggalLahir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        tvProfilName = findViewById(R.id.tv_name_profil);
        tvProfilNPM = findViewById(R.id.tv_npm_profil);
        tvProfilEmail = findViewById(R.id.tv_email_profil);
        tvProfilJurusan = findViewById(R.id.tv_jurusan_profil);
        tvTanggalLahir = findViewById(R.id.tv_tanggal_profil);

        getUserData();

        ImageView imgBack = findViewById(R.id.img_back_profil);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getUserData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ProfileResponse> call = apiInterface.userProfile(SharedPrefManager.getInstance(this).getUser().getUserLogin());

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProfileResponse> call, @NonNull Response<ProfileResponse> response) {
                progressDialog.dismiss();
                List<UserProfile> userProfile = response.body().getUserProfiles();
                tvProfilName.setText(userProfile.get(0).getDisplayName());
                tvProfilNPM.setText(userProfile.get(0).getNpm());
                tvProfilEmail.setText(userProfile.get(0).getEmail());
                tvProfilJurusan.setText(userProfile.get(0).getJurusan());
                String Birth = userProfile.get(0).getTanggalLahir();
                String tanggal = Birth.substring(8,10);
                String bulan = Birth.substring(5,7);
                String tahun = Birth.substring(0,4);
                tvTanggalLahir.setText(tanggal + "-" + bulan + "-" + tahun);
            }

            @Override
            public void onFailure(@NonNull Call<ProfileResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
