package com.vincode.simipa.ui.beasiswa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vincode.simipa.R;
import com.vincode.simipa.model.ScholarshipPost;
import com.vincode.simipa.network.ApiClient;
import com.vincode.simipa.network.ApiInterface;
import com.vincode.simipa.util.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormBeasiswaFragment extends Fragment {
    private Spinner spinYear,semester;
    private EditText etBeasiswa,etPenyelenggara,etNama,etNpm,etJurusan,etProdi;
    private LinearLayout linearLayout;
    private Button tambah;
    private TextView showDetail;

    public FormBeasiswaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_beasiswa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> years = new ArrayList<String>();
        ArrayList<String> sems = new ArrayList<String>();
        String npm = SharedPrefManager.getInstance(getContext()).getUser().getUserLogin();
        int angkatan = Integer.parseInt(npm.substring(0,2));

        //get tahun/bulan sekarang
        int tahunIni = Calendar.getInstance().get(Calendar.YEAR);
        int bulanIni = Calendar.getInstance().get(Calendar.MONTH);

        String d = String.valueOf(tahunIni).substring(2,4);
        int f = Integer.parseInt(d);

        //selisih
        int g = f-angkatan;
        int tahunMasuk = tahunIni-g;
        years.add("Tahun");
        for (int i = tahunIni; i >= tahunMasuk; i--) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, years);

        // data spinner semester
        int hitungSem;
        sems.add("Semester");
        if (tahunIni == tahunMasuk) {
            sems.add(Integer.toString(1));
        } else {
            if (bulanIni <= 7) {
                hitungSem = (tahunIni - tahunMasuk) * 2;
                for (int i = 1; i <= hitungSem; i++) {
                    sems.add(Integer.toString(i));
                }
            } else {
                hitungSem = (tahunIni - tahunMasuk) * 2 + 1;
                for (int i = 1; i <= hitungSem; i++) {
                    sems.add(Integer.toString(i));
                }
            }
        }

        ArrayAdapter<String> adapterSems = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, sems);

        spinYear = view.findViewById(R.id.yearspin);
        spinYear.setAdapter(adapterYear);

        semester = view.findViewById(R.id.semesterspin);
        semester.setAdapter(adapterSems);

        etBeasiswa = view.findViewById(R.id.et_beasiswa);
        etPenyelenggara = view.findViewById(R.id.et_penyelenggara);
        etNama = view.findViewById(R.id.et_nama);
        etNpm = view.findViewById(R.id.et_npm);

        etNama.setText(SharedPrefManager.getInstance(getContext()).getUser().getDisplayName());
        etNpm.setText(npm);
        etNama.setEnabled(false);
        etNpm.setEnabled(false);

        etJurusan = view.findViewById(R.id.et_jurusan);
        etProdi = view.findViewById(R.id.et_prodi);
        linearLayout = view.findViewById(R.id.linear_id);

        showDetail = view.findViewById(R.id.show_detail);
        showDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linearLayout.getVisibility() == View.GONE) {
                    showDetail.setText("Show less");
                    linearLayout.setVisibility(View.VISIBLE);
                } else if (linearLayout.getVisibility() == View.VISIBLE) {
                    showDetail.setText("Show all");
                    linearLayout.setVisibility(View.GONE);
                }
            }
        });
        tambah = view.findViewById(R.id.tambah_beasiswa);

        String kdJurusan = SharedPrefManager.getInstance(getActivity()).getUser().getUserLogin().substring(4,6);
        String kdProdi = SharedPrefManager.getInstance(getActivity()).getUser().getUserLogin().substring(2,3);

        switch (kdJurusan) {
            case "01":
                etJurusan.setText("Fisika");
                etProdi.setText("Fisika");
                etJurusan.setEnabled(false);
                etProdi.setEnabled(false);
                break;
            case "02":
                etJurusan.setText("Biologi");
                etProdi.setText("Biologi");
                etJurusan.setEnabled(false);
                etProdi.setEnabled(false);
                break;
            case "03":
                etJurusan.setText("Matematika");
                etProdi.setText("Matematika");
                etJurusan.setEnabled(false);
                etProdi.setEnabled(false);
                break;
            case "04":
                etJurusan.setText("Kimia");
                etProdi.setText("Kimia");
                etJurusan.setEnabled(false);
                etProdi.setEnabled(false);
                break;
            case "05":
                etJurusan.setText("Ilmu Komputer");
                if (kdProdi.equals("1") || kdProdi.equals("5")) {
                    etProdi.setText("S1 Ilmu Komputer");
                    etJurusan.setEnabled(false);
                    etProdi.setEnabled(false);
                } else {
                    etProdi.setText("D3 Manajemen Informatika");
                    etJurusan.setEnabled(false);
                    etProdi.setEnabled(false);
                }
                break;
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    public void getData() {
        String npm = SharedPrefManager.getInstance(getContext()).getUser().getUserLogin();
        String peny = etPenyelenggara.getText().toString().trim();
        String namaB = etBeasiswa.getText().toString().trim();
        String smstr = semester.getSelectedItem().toString();
        String text = spinYear.getSelectedItem().toString();

        if (peny.isEmpty() || namaB.isEmpty() || semester.getSelectedItemPosition() == 0 || spinYear.getSelectedItemPosition() == 0){
            Toast.makeText(getActivity(), "Ops! there is an empty field", Toast.LENGTH_LONG).show();
            if (namaB.isEmpty()) {
                etBeasiswa.setError(getText(R.string.field_kosong));
            }
            if (peny.isEmpty()) {
                etPenyelenggara.setError(getText(R.string.field_kosong));
            }
            if (semester.getSelectedItemPosition() == 0){
                ((TextView)semester.getSelectedView()).setError("Pilih salah satu");
            }
            if (spinYear.getSelectedItemPosition() == 0){
                ((TextView)spinYear.getSelectedView()).setError("Pilih salah satu");
            }
        } else {
            insert(npm, smstr, text, peny, namaB);
        }
    }

    public void insert(String npm, String semester, String tahunBeasiswa, String penyelenggara, String namaBeasiswa){

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

                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ScholarshipPost> call, Throwable t) {
                Toast.makeText(getActivity(), getText(R.string.koneksi_lambat), Toast.LENGTH_LONG).show();
            }
        });
    }
}
