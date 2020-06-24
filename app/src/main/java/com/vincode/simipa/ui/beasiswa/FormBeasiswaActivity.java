package com.vincode.simipa.ui.beasiswa;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vincode.simipa.R;
import com.vincode.simipa.model.ScholarshipPost;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;
import com.vincode.simipa.util.TimeUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class FormBeasiswaActivity extends AppCompatActivity {

    private EditText etTahun,etSemester;
    private EditText etBeasiswa,etPenyelenggara,etNama,etNpm,etJurusan,etProdi;
    private LinearLayout linearLayout;
    private Button tambah,batal;
    private TextView showDetail;

    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_ORGANIZER = "extra_organizer";
    public static final String EXTRA_YEAR = "extra_year";
    public static final String EXTRA_DATE = "extra_date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_beasiswa);
        
        String npm = SharedPrefManager.getInstance(this).getUser().getUserLogin();
        int angkatan = Integer.parseInt(npm.substring(0,2));

        etTahun = findViewById(R.id.yearspin);
        etSemester = findViewById(R.id.semesterspin);

        etBeasiswa = findViewById(R.id.et_beasiswa);
        etPenyelenggara = findViewById(R.id.et_penyelenggara);
        etNama = findViewById(R.id.et_nama);
        etNpm = findViewById(R.id.et_npm);

        etNama.setText(SharedPrefManager.getInstance(this).getUser().getDisplayName());
        etNpm.setText(npm);
        etNama.setEnabled(false);
        etNpm.setEnabled(false);

        etJurusan = findViewById(R.id.et_jurusan);
        etProdi = findViewById(R.id.et_prodi);
        linearLayout = findViewById(R.id.linear_id);

        etNama.setTextColor(Color.GRAY);
        etNpm.setTextColor(Color.GRAY);
        etJurusan.setTextColor(Color.GRAY);
        etProdi.setTextColor(Color.GRAY);

        showDetail = findViewById(R.id.show_detail);
        showDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linearLayout.getVisibility() == View.GONE) {
                    showDetail.setText(R.string.show_less);
                    linearLayout.setVisibility(View.VISIBLE);
                } else if (linearLayout.getVisibility() == View.VISIBLE) {
                    showDetail.setText(R.string.show_all);
                    linearLayout.setVisibility(View.GONE);
                }
            }
        });

        tambah = findViewById(R.id.tambah_beasiswa);
        batal = findViewById(R.id.batal_beasiswa);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent back = new Intent(FormBeasiswaActivity.this, BeasiswaActivity.class);
                startActivity(back);
            }
        });

        String kdJurusan = SharedPrefManager.getInstance(this).getUser().getUserLogin().substring(4,6);
        String kdProdi = SharedPrefManager.getInstance(this).getUser().getUserLogin().substring(2,3);

        switch (kdJurusan) {
            case "01":
                etJurusan.setText(R.string.physics);
                etProdi.setText(R.string.physics);
                etJurusan.setEnabled(false);
                etProdi.setEnabled(false);
                break;
            case "02":
                etJurusan.setText(R.string.biology);
                etProdi.setText(R.string.biology);
                etJurusan.setEnabled(false);
                etProdi.setEnabled(false);
                break;
            case "03":
                etJurusan.setText(R.string.math);
                etProdi.setText(R.string.math);
                etJurusan.setEnabled(false);
                etProdi.setEnabled(false);
                break;
            case "04":
                etJurusan.setText(R.string.chemistry);
                etProdi.setText(R.string.chemistry);
                etJurusan.setEnabled(false);
                etProdi.setEnabled(false);
                break;
            case "05":
                etJurusan.setText(R.string.cs);
                if (kdProdi.equals("1") || kdProdi.equals("5")) {
                    etProdi.setText(R.string.cs1);
                    etJurusan.setEnabled(false);
                    etProdi.setEnabled(false);
                } else {
                    etProdi.setText(R.string.cs2);
                    etJurusan.setEnabled(false);
                    etProdi.setEnabled(false);
                }
                break;
        }

        Intent i = getIntent();
        String nama = i.getStringExtra(EXTRA_NAME);
        String tahun = i.getStringExtra(EXTRA_YEAR);
        String penyelenggara = i.getStringExtra(EXTRA_ORGANIZER);
        TimeUtil timeUtil = new TimeUtil();
        String sem = timeUtil.getSemester();

        etBeasiswa.setText(nama);
        etTahun.setText(tahun);
        etPenyelenggara.setText(penyelenggara);
        etSemester.setText(sem);
    }

    public void getData() {
        String npm = SharedPrefManager.getInstance(this).getUser().getUserLogin();
        String peny = etPenyelenggara.getText().toString().trim();
        String namaB = etBeasiswa.getText().toString().trim();
        String smstr = etSemester.getText().toString().trim();
        String tahun = etTahun.getText().toString().trim();

        if (peny.isEmpty() || namaB.isEmpty() || smstr.isEmpty() || tahun.isEmpty()) {
            Toast.makeText(FormBeasiswaActivity.this, R.string.empty_warning, Toast.LENGTH_LONG).show();
            if (namaB.isEmpty()) {
                etBeasiswa.setError(getText(R.string.field_kosong));
            }
            if (peny.isEmpty()) {
                etPenyelenggara.setError(getText(R.string.field_kosong));
            }
            if (smstr.isEmpty()){
                etSemester.setError(getText(R.string.field_kosong));
            }
            if (tahun.isEmpty()){
                etTahun.setError(getText(R.string.field_kosong));
            }
        } else {
            insert(npm, smstr, tahun, peny, namaB);
        }
    }

    public void insert(String npm, String semester, String tahunBeasiswa, String penyelenggara, String namaBeasiswa) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("npm", npm);
            jsonObject.put("semester", semester);
            jsonObject.put("tahun_beasiswa", tahunBeasiswa);
            jsonObject.put("penyelenggara", penyelenggara);
            jsonObject.put("nama_beasiswa", namaBeasiswa);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ScholarshipPost> call = apiInterface.insertBeasiswa(jsonObject.toString());
        call.enqueue(new Callback<ScholarshipPost>() {
            @Override
            public void onResponse(Call<ScholarshipPost> call, Response<ScholarshipPost> response) {
                assert response.body() != null;
                String message = response.body().getMessage();

                Toast.makeText(FormBeasiswaActivity.this, message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ScholarshipPost> call, Throwable t) {
                Toast.makeText(FormBeasiswaActivity.this, getText(R.string.koneksi_lambat), Toast.LENGTH_LONG).show();
            }
        });
    }
}
