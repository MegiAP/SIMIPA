package com.vincode.simipa.ui.presence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.vincode.simipa.R;

public class ScanPresenceActivity extends AppCompatActivity {

    private CodeScanner codeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_presence);

        CodeScannerView codeScannerView = findViewById(R.id.scanner_view);

        final String location = getIntent().getStringExtra("a");
        final double lat = getIntent().getDoubleExtra("b", 0);
        final double longi = getIntent().getDoubleExtra("c", 0);

        codeScanner = new CodeScanner(this, codeScannerView);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getDataTointent(result.getText(), longi, lat, location);
                    }
                });
            }
        });

        checkPermissionCamera();
    }

    private void getDataTointent(String result, double longi, double lati, String location){

        Intent moveIntent = new Intent(ScanPresenceActivity.this, ResultPresenceActivity.class);
        moveIntent.putExtra(ResultPresenceActivity.EXTRA_INTENT, result);
        moveIntent.putExtra(ResultPresenceActivity.EXTRA_LONGI, longi);
        moveIntent.putExtra(ResultPresenceActivity.EXTRA_LATI, lati);
        moveIntent.putExtra(ResultPresenceActivity.EXTRA_ADDRESS, location);
        startActivity(moveIntent);
    }

    private void checkPermissionCamera(){
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        codeScanner.startPreview();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
}
