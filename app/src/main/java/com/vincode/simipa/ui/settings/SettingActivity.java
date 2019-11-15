package com.vincode.simipa.ui.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.vincode.simipa.R;
import com.vincode.simipa.util.SharedPrefManager;
import com.vincode.simipa.ui.login.LoginActivity;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

public class SettingActivity extends AppCompatActivity {

    TelephonyManager telephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.setting);
        }

        final Button btnChange = findViewById(R.id.btn_change_language);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(btnChange, getResources().getString(R.string.text_develop), Snackbar.LENGTH_SHORT).show();
            }
        });

        Button btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        Button btnIp = findViewById(R.id.btn_ip);
        btnIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIP();
            }
        });

        Button btnImei = findViewById(R.id.btn_imei);
        btnImei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImei();
            }
        });
    }

    private void logout(){
        SharedPrefManager.getInstance(this).logout();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void getImei() {
        telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
            return;
        }
        @SuppressLint("HardwareIds") String imeiNumber = telephonyManager.getDeviceId();
        Toast.makeText(SettingActivity.this, imeiNumber, Toast.LENGTH_LONG).show();
    }

    public void getIP() {
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
                            Toast.makeText(SettingActivity.this, addr.getHostAddress(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(SettingActivity.this, phone_ip, Toast.LENGTH_LONG).show();
    }
}
