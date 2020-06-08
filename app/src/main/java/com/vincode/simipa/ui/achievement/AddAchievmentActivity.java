package com.vincode.simipa.ui.achievement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vincode.simipa.R;
import com.vincode.simipa.model.Status;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;

import java.io.File;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAchievmentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddTeam, btnDeleteTeam, btnUpload;
    private EditText edtNpm, edtName, edtJurusan, edtProdi;
    private Spinner spCategory, spType, spAchieve, spLevel;
    private TextView tvNameFile;
    String file_path;
    Uri uriFix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_achievment);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Form Data Prestasi");

        String [] achieve = new String[] {"Finalis", "Juara Favorite/Kategori","Juara III (Perunggu)", "Juara II (Perak)", "Juara I (Emas)"};
        String [] typeAchieve = new String[] {"Akademik", "Non Akademik"};
        String [] levelAchieve = new String[] {"Lokal (Universitas Lampung)", "Wilayah (Kab/Kota, Provinsi, Regional", "Nasional", "Internasional"};
        String [] categoryAchieve = new String[] {"Individu", "Tim"};

        ArrayAdapter<String> achieveAdapter = new ArrayAdapter<>(this, R.layout.act_item_achieve, achieve);
        ArrayAdapter<String> typeAchieveAdapter = new ArrayAdapter<>(this, R.layout.act_item_achieve, typeAchieve);
        ArrayAdapter<String> levelAchieveAdapter = new ArrayAdapter<>(this, R.layout.act_item_achieve, levelAchieve);
        ArrayAdapter<String> categoryAchieveAdapter = new ArrayAdapter<>(this, R.layout.act_item_achieve, categoryAchieve);

        Button btnSave = findViewById(R.id.btn_save_achieve);
        btnAddTeam = findViewById(R.id.btn_add_team);
        btnDeleteTeam = findViewById(R.id.btn_delete_team);
        btnUpload = findViewById(R.id.btn_upload_achieve);

        edtNpm = findViewById(R.id.edt_npm_achieve);
        edtName = findViewById(R.id.edt_name_achieve);
        edtJurusan = findViewById(R.id.edt_jurusan_achieve);
        edtProdi = findViewById(R.id.edt_prodi_achieve);

        spCategory = findViewById(R.id.sp_category);
        spType = findViewById(R.id.sp_type);
        spAchieve = findViewById(R.id.sp_achieve);
        spLevel = findViewById(R.id.sp_level);

        spCategory.setAdapter(categoryAchieveAdapter);
        spType.setAdapter(typeAchieveAdapter);
        spAchieve.setAdapter(achieveAdapter);
        spLevel.setAdapter(levelAchieveAdapter);

        tvNameFile = findViewById(R.id.tv_name_file);

        btnSave.setOnClickListener(this);
        btnAddTeam.setOnClickListener(this);
        btnDeleteTeam.setOnClickListener(this);
        btnUpload.setOnClickListener(this);

        setDataReadonly(SharedPrefManager.getInstance(this).getUser().getUserLogin(), getIntent().getStringExtra("name"));

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

    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id
//        }
        if (view.getId() == R.id.btn_save_achieve){
//            String a = spAchieve.getSelectedItem().toString();
//            String b = spLevel.getSelectedItem().toString();
//            String c = spType.getSelectedItem().toString();
////
            Toast. makeText(this, uriFix+" "+file_path, Toast.LENGTH_SHORT).show();
            uploadFIle(uriFix,file_path,file_path);
        }

        if (view == btnUpload){
            //get all files
            Intent fileIntent = new Intent();
            fileIntent.setType("*/*");
            fileIntent.setAction(Intent.ACTION_PICK);
            startActivityForResult(Intent.createChooser(fileIntent, "Pilih File Upload"), 102);//must constant
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == 102){
                if (data == null){
                    return;
                }
//                belum kelar
                uriFix = data.getData();
                String paths = FilePath.getFilePath(AddAchievmentActivity.this, uriFix);
                if (paths != null){
                    tvNameFile.setText(""+ new File(paths).getName());
                }
                file_path = paths;
//                Toast.makeText()
            }
        }
    }

    private void uploadFIle(Uri uri, String desc, String filePath){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        File file = new File(filePath);

        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(uri)), file);
        RequestBody descBody = RequestBody.create(MediaType.parse("text/plain"), desc);

        ApiInterface apiInterface = ApiClient.getClientMovie().create(ApiInterface.class);

        Call<Status> call = apiInterface.uploadFile(requestFile, descBody);

        call.enqueue(new Callback<Status>() {

            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                progressDialog.dismiss();
                assert response.body() != null;
                if (response.body().getError().equals("false")){
                    Toast.makeText(AddAchievmentActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddAchievmentActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AddAchievmentActivity.this, "Error!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getRealPathFromUri(Uri uri){
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor=getContentResolver().query(uri,proj,null,null,null);
        if(cursor==null){
            return uri.getPath();
        }
        else{
            cursor.moveToFirst();
            int id=cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(id);
        }
    }
}
