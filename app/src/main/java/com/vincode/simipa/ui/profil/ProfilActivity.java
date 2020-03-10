package com.vincode.simipa.ui.profil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vincode.simipa.R;
import com.vincode.simipa.util.SharedPrefManager;
import com.vincode.simipa.model.ProfileResponse;
import com.vincode.simipa.model.UserProfile;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

import java.util.List;


public class ProfilActivity extends AppCompatActivity {

    private TextView tvProfilName, tvProfilNPM, tvProfilEmail, tvProfilJurusan, tvTanggalLahir, tvTempatLahir;
    private CircleImageView imageUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        tvProfilName = findViewById(R.id.tv_name_profil);
        tvProfilNPM = findViewById(R.id.tv_npm_profil);
        tvProfilEmail = findViewById(R.id.tv_email_profil);
        tvProfilJurusan = findViewById(R.id.tv_jurusan_profil);
        tvTanggalLahir = findViewById(R.id.tv_tanggal_profil);
        tvTempatLahir = findViewById(R.id.tv_tempat_profil);
        imageUser = findViewById(R.id.img_user);

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

                if (response.body() != null) {
                    List<UserProfile> userProfile = response.body().getUserProfiles();
                    tvProfilName.setText(userProfile.get(0).getDisplayName());
                    tvProfilNPM.setText(userProfile.get(0).getNpm());
                    tvProfilEmail.setText(userProfile.get(0).getEmail());
                    tvProfilJurusan.setText(userProfile.get(0).getJurusan());
                    tvTempatLahir.setText(userProfile.get(0).getTempatLahir());
                    String Birth = userProfile.get(0).getTanggalLahir();
                    if (Birth != null){
                        String tanggal = Birth.substring(8,10);
                        String bulan = Birth.substring(5,7);
                        String tahun = Birth.substring(0,4);
                        tvTanggalLahir.setText(String.format(" / %s-%s-%s", tanggal, bulan, tahun));
                    }

                    Glide.with(getApplicationContext())
                            .load(userProfile.get(0).getFoto())
                            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                            .into(imageUser);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProfileResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
