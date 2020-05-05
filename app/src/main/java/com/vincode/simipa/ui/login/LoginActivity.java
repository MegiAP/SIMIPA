package com.vincode.simipa.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vincode.simipa.ui.main.MainActivity;
import com.vincode.simipa.R;
import com.vincode.simipa.util.SharedPrefManager;
import com.vincode.simipa.model.LoginResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        Button btnLogin = findViewById(R.id.btn_login);
        edtUsername = findViewById(R.id.edt_npm_login);
        edtPassword = findViewById(R.id.edt_pw_login);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login){
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username)){
                edtUsername.setError("NPM harus diisi");
            }else if (TextUtils.isEmpty(password)){
                edtPassword.setError("Password harus diisi");
            }else {
                userLogin(username, password);
            }
        }
    }

    private void userLogin(String username, String password){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<LoginResponse> call = apiInterface.userLogin(username, password, "Mahasiswa");

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (!response.body().isError()){
                        finish();
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.body().getUser().get(0));
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "NPM atau Password anda salah", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
