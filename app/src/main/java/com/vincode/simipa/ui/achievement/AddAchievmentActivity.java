package com.vincode.simipa.ui.achievement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vincode.simipa.R;
import com.vincode.simipa.model.Value;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;


import java.io.File;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAchievmentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddTeam, btnDeleteTeam, btnUpload, btnSave;
    private EditText edtNpm, edtName, edtJurusan, edtProdi, edtNamaKegiatan, edtPenyelenggara, edtTahun;
    private Spinner spCategory, spType, spAchieve, spLevel;
    private TextView tvNameFile;
    String filePath;
    Uri uri;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String [] PERMISSION_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_achievment);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Form Data Prestasi");

        init();
        verifyStoragePermissions(AddAchievmentActivity.this);

        setDataReadonly(SharedPrefManager.getInstance(this).getUser().getUserLogin(), getIntent().getStringExtra("nama"));

        String [] achieve = new String[] {"Finalis", "Juara Favorite/Kategori","Juara III (Perunggu)", "Juara II (Perak)", "Juara I (Emas)"};
        String [] typeAchieve = new String[] {"Akademik", "Non Akademik"};
        String [] levelAchieve = new String[] {"Lokal (Universitas Lampung)", "Wilayah (Kab/Kota, Provinsi, Regional", "Nasional", "Internasional"};
        String [] categoryAchieve = new String[] {"Individu", "Tim"};

        ArrayAdapter<String> achieveAdapter = new ArrayAdapter<>(this, R.layout.act_item_achieve, achieve);
        ArrayAdapter<String> typeAchieveAdapter = new ArrayAdapter<>(this, R.layout.act_item_achieve, typeAchieve);
        ArrayAdapter<String> levelAchieveAdapter = new ArrayAdapter<>(this, R.layout.act_item_achieve, levelAchieve);
        ArrayAdapter<String> categoryAchieveAdapter = new ArrayAdapter<>(this, R.layout.act_item_achieve, categoryAchieve);

        spCategory.setAdapter(categoryAchieveAdapter);
        spType.setAdapter(typeAchieveAdapter);
        spAchieve.setAdapter(achieveAdapter);
        spLevel.setAdapter(levelAchieveAdapter);

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(AddAchievmentActivity.this, spCategory.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                if (spCategory.getSelectedItem().toString().equals("Tim")){
                    Toast.makeText(AddAchievmentActivity.this, spCategory.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    btnAddTeam.setVisibility(View.VISIBLE);
                    btnDeleteTeam.setVisibility(View.VISIBLE);
                }else {
                    btnAddTeam.setVisibility(View.GONE);
                    btnDeleteTeam.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSave.setOnClickListener(this);
        btnAddTeam.setOnClickListener(this);
        btnDeleteTeam.setOnClickListener(this);
        btnUpload.setOnClickListener(this);

    }

    private void setDataReadonly(String userLogin, String name) {
        edtNpm.setText(userLogin);
        edtNpm.setEnabled(false);
        edtName.setText(name);
//        edtName.setEnabled(false);
        String codeJurusan = userLogin.substring(4,6);
        String codeProdi = userLogin.substring(2,3);
        switch (codeJurusan){
            case "01":
                edtJurusan.setText(R.string.chemistry);
                edtJurusan.setEnabled(false);
                break;
            case "02":
                edtJurusan.setText(R.string.biology);
                edtJurusan.setEnabled(false);
                break;
            case "03":
                edtJurusan.setText(R.string.math);
                edtJurusan.setEnabled(false);
                break;
            case "04":
                edtJurusan.setText(R.string.physics);
                edtJurusan.setEnabled(false);
                break;
            case "05":
                edtJurusan.setText(R.string.cs);
                edtJurusan.setEnabled(false);
                if (codeProdi.equals("1") || codeProdi.equals("5")){
                    edtProdi.setText(R.string.cs1);
                    edtProdi.setEnabled(false);
                }else{
                    edtProdi.setText(R.string.cs2);
                    edtProdi.setEnabled(false);
                }
                break;
        }
    }

    private void init(){
        btnSave = findViewById(R.id.btn_save_achieve);
        btnAddTeam = findViewById(R.id.btn_add_team);
        btnDeleteTeam = findViewById(R.id.btn_delete_team);
        btnUpload = findViewById(R.id.btn_upload_achieve);

        edtNpm = findViewById(R.id.edt_npm_achieve);
        edtName = findViewById(R.id.edt_name_achieve);
        edtJurusan = findViewById(R.id.edt_jurusan_achieve);
        edtProdi = findViewById(R.id.edt_prodi_achieve);
        edtNamaKegiatan = findViewById(R.id.edt_event_achieve);
        edtTahun = findViewById(R.id.edt_year_achieve);
        edtPenyelenggara = findViewById(R.id.edt_organize_achieve);

        spCategory = findViewById(R.id.sp_category);
        spType = findViewById(R.id.sp_type);
        spAchieve = findViewById(R.id.sp_achieve);
        spLevel = findViewById(R.id.sp_level);

        tvNameFile = findViewById(R.id.tv_name_file);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_save_achieve:
                String namaKegiatan = edtNamaKegiatan.getText().toString();
                String penyelenggara = edtPenyelenggara.getText().toString();
                String tahun = edtPenyelenggara.getText().toString();
                String npm = edtNpm.getText().toString();


                String tingkat = spLevel.getSelectedItem().toString();
                String prestasi = spAchieve.getSelectedItem().toString();
                String kategori = spType.getSelectedItem().toString();
                String jenis;

                if (spCategory.getSelectedItem().toString().equals("Tim")){
                    jenis = "2";
                }else{
                    jenis = "1";
                }

                if (TextUtils.isEmpty(tahun)){
                    edtTahun.setError("Tahun Kegiatan harus diisi");
                }else if (TextUtils.isEmpty(namaKegiatan)){
                    edtNamaKegiatan.setError("Nama Kegiatan harus diisi");
                }else if (TextUtils.isEmpty(penyelenggara)) {
                    edtPenyelenggara.setError("Penyelenggara harus diisi");
                }else if (TextUtils.isEmpty(filePath)) {
                    btnUpload.setError("File harus dipilih");
                }else {

                    File file = new File(filePath);
                    RequestBody fileSertifikat = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part fileUpload = MultipartBody.Part.createFormData("sertifikat", file.getName(), fileSertifikat);

                    RequestBody reqNamaKegiatan = RequestBody.create(MediaType.parse("text/plain"), namaKegiatan);
                    RequestBody reqPenyelenggara = RequestBody.create(MediaType.parse("text/plain"), penyelenggara);
                    RequestBody reqKategori = RequestBody.create(MediaType.parse("text/plain"), kategori);
                    RequestBody reqTingkat = RequestBody.create(MediaType.parse("text/plain"), tingkat);
                    RequestBody reqPrestasi = RequestBody.create(MediaType.parse("text/plain"), prestasi);
                    RequestBody reqTahun = RequestBody.create(MediaType.parse("text/plain"), tahun);
                    RequestBody reqJenis = RequestBody.create(MediaType.parse("text/plain"), jenis);
                    RequestBody reqNPM= RequestBody.create(MediaType.parse("text/plain"), npm);


                    addFormAchieve(fileUpload, reqNamaKegiatan, reqPenyelenggara, reqKategori, reqTingkat, reqPrestasi,
                            reqTahun, reqJenis, reqNPM);
                }
                break;
            case R.id.btn_upload_achieve:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 102);
                break;
        }

    }

    private void addFormAchieve(MultipartBody.Part fileUpload, RequestBody reqNamaKegiatan, RequestBody reqPenyelenggara,
                                RequestBody reqKategori, RequestBody reqTingkat, RequestBody reqPrestasi, RequestBody reqTahun,
                                RequestBody reqJenis, RequestBody reqNPM) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Value> call = apiInterface.addAchieveStudent(fileUpload, reqNamaKegiatan, reqPenyelenggara, reqKategori, reqTingkat,
                reqPrestasi, reqTahun, reqJenis, reqNPM );
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(@NonNull Call<Value> call, @NonNull Response<Value> response) {
                progressDialog.dismiss();
                assert response.body() != null;
                if (response.body().getError().equals("false")){
                    Toast.makeText(AddAchievmentActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(AddAchievmentActivity.this, AchievementActivity.class));
                }else{
                    Toast.makeText(AddAchievmentActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Value> call, @NonNull Throwable t) {
                Toast.makeText(AddAchievmentActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == 102){
                assert data != null;
                uri = data.getData();
                filePath = getRealPathFromUri(uri, AddAchievmentActivity.this);
                tvNameFile.setText(new File(filePath).getName());
            }
        }
    }



    public String getRealPathFromUri(Uri uri, Activity activity){
        @SuppressLint("Recycle") Cursor cursor = activity.getContentResolver().query(uri, null, null, null, null);
        if (cursor == null){
            return uri.getPath();
        }else{
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    public static void verifyStoragePermissions(Activity activity){
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSION_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
