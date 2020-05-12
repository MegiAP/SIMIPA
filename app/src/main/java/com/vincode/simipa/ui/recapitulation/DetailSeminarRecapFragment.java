package com.vincode.simipa.ui.recapitulation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vincode.simipa.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailSeminarRecapFragment extends DialogFragment {
    private TextView tvNama,tvNpm,tvJudul,tvJenis,tvDosen,tvRuang;
    private Button btnClose;
    public static final String EXTRA_NPM = "extra_npm";
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_JUDUL = "extra_judul";
    public static final String EXTRA_JENIS = "extra_jenis";
    public static final String EXTRA_DOSEN = "extra_dosen";
    public static final String EXTRA_RUANG = "extra_ruang";

    public DetailSeminarRecapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_seminar_recap, container, false);
        tvNama = view.findViewById(R.id.df_nama);
        tvNpm = view.findViewById(R.id.df_npm);
        tvJudul = view.findViewById(R.id.df_judul);
        tvJenis = view.findViewById(R.id.df_jenis);
        tvDosen = view.findViewById(R.id.df_dosen);
        tvRuang = view.findViewById(R.id.df_ruang);
        btnClose = view.findViewById(R.id.btn_close);

        Bundle bundle = getArguments();
        assert bundle != null;
        String nama = bundle.getString(EXTRA_NAME);
        String npm = bundle.getString(EXTRA_NPM);
        String jenis = bundle.getString(EXTRA_JENIS);
        String judul = bundle.getString(EXTRA_JUDUL);
        String dosen = bundle.getString(EXTRA_DOSEN);
        String ruang = bundle.getString(EXTRA_RUANG);

        tvNama.setText(nama);
        tvNpm.setText(npm);
        tvJenis.setText(jenis);
        tvJudul.setText(judul);
        tvDosen.setText(dosen);
        tvRuang.setText(ruang);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });

        return view;
    }
}
