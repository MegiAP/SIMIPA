package com.vincode.simipa.ui.achievement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.vincode.simipa.R;
import com.vincode.simipa.util.SharedPrefManager;

import java.util.Objects;

public class AddAchievmentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddTeam, btnDeleteTeam;
    private EditText edtNpm, edtName, edtJurusan, edtProdi;

    private Spinner spCategory, spType, spAchieve, spLevel;

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

        btnSave.setOnClickListener(this);
        btnAddTeam.setOnClickListener(this);
        btnDeleteTeam.setOnClickListener(this);

        setDataReadonly(SharedPrefManager.getInstance(this).getUser().getUserLogin());

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

    private void setDataReadonly(String userLogin) {
        edtNpm.setText(userLogin);
        edtNpm.setEnabled(false);
        edtName.setText(SharedPrefManager.getInstance(this).getUser().getDisplayName());
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
            String a = spAchieve.getSelectedItem().toString();
            String b = spLevel.getSelectedItem().toString();
            String c = spType.getSelectedItem().toString();
//
            Toast. makeText(this, a+b+c, Toast.LENGTH_SHORT).show();
        }
    }
}
