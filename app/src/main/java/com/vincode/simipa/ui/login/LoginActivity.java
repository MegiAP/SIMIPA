package com.vincode.simipa.ui.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.vincode.simipa.R;
import com.vincode.simipa.model.LoginResponse;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.ui.main.MainActivity;
import com.vincode.simipa.util.SharedPrefManager;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private int PHONE_STATE_CODE = 1;
    private EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reqPhonePermission();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
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
                makeToken(username, password);
            }
        }
    }

    private void userLogin(final String username, final String password, final String token) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        String imei = getUniqueIMEIId(getApplicationContext());
        String ip = getIP();

        Call<LoginResponse> call = apiInterface.userLogin(username, password, imei, ip, token, "Mahasiswa");

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (!response.body().isError()) {

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

    public void makeToken(final String username, final String password) {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Token", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = Objects.requireNonNull(task.getResult()).getToken();

                        userLogin(username, password, token);

                        Log.d("Token", token);
                    }
                });
    }

    @SuppressLint("HardwareIds")
    public String getUniqueIMEIId(Context context) {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei;

        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                imei = "Permission granted - Android Q or newer - " + Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                assert telephonyManager != null;
                imei = telephonyManager.getImei();
            } else {
                assert telephonyManager != null;
                imei = telephonyManager.getDeviceId();
            }

            if (imei != null && !imei.isEmpty()) {
                return imei;
            } else {
                return android.os.Build.SERIAL;
            }

        }

        return "Permission denied - Android P or older -" + Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    private void reqPhonePermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PHONE_STATE_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PHONE_STATE_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(findViewById(android.R.id.content), getString(R.string.izin_diberikan),
                        Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(findViewById(android.R.id.content), getString(R.string.izin_ditolak),
                        Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    public String getIP() {
        String phone_ip;

        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        assert wm != null;
        phone_ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        if (phone_ip.equals("0.0.0.0")) {
            try {
                List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
                for (NetworkInterface intf : interfaces) {
                    List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                    for (InetAddress addr : addrs) {
                        if (!addr.isLoopbackAddress()) {
                            return addr.getHostAddress();
                        }
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }

        return phone_ip;
    }
}
