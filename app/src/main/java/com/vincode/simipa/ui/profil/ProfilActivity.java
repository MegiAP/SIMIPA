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
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

public class ProfilActivity extends AppCompatActivity {

    TextView tvProfilName, tvProfilNPM, tvProfilEmail;
    String ProfilEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        tvProfilName = findViewById(R.id.tv_name_profil);
        tvProfilNPM = findViewById(R.id.tv_npm_profil);
        tvProfilEmail = findViewById(R.id.tv_email_profil);

        tvProfilName.setText(SharedPrefManager.getInstance(this).getUser().getDisplayName());
        tvProfilNPM.setText(SharedPrefManager.getInstance(this).getUser().getUserLogin());
        tvProfilEmail.setText(ProfilEmail);

        getUserData(SharedPrefManager.getInstance(this).getUser().getUserLogin());

        ImageView imgBack = findViewById(R.id.img_back_profil);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getUserData(String email) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ProfileResponse> call = apiInterface.UserProfile(email);

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProfileResponse> call, @NonNull Response<ProfileResponse> response) {
                progressDialog.dismiss();
                ProfilEmail = response.body().getProfile().toString();
            }

            @Override
            public void onFailure(@NonNull Call<ProfileResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
