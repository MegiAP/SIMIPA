package com.vincode.simipa.ui.recapitulation;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.vincode.simipa.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailSeminarRecapFragment extends DialogFragment {
    private TextView tvNama,tvNpm,tvJudul,tvJenis,tvDosen,tvRuang,tvTanggal;
    private Button btnClose;
    public static final String EXTRA_NPM = "extra_npm";
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_JUDUL = "extra_judul";
    public static final String EXTRA_JENIS = "extra_jenis";
    public static final String EXTRA_DOSEN = "extra_dosen";
    public static final String EXTRA_RUANG = "extra_ruang";
    public static final String EXTRA_TANGGAL = "extra_tanggal";

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
        tvTanggal = view.findViewById(R.id.df_tanggal);
        btnClose = view.findViewById(R.id.btn_close);

        Bundle bundle = getArguments();
        assert bundle != null;
        String nama = bundle.getString(EXTRA_NAME) + " / ";
        String npm = bundle.getString(EXTRA_NPM);
        String jenis = bundle.getString(EXTRA_JENIS);
        String judul = bundle.getString(EXTRA_JUDUL);
        String dosen = bundle.getString(EXTRA_DOSEN);
        String ruang = bundle.getString(EXTRA_RUANG);
        String tanggal = bundle.getString(EXTRA_TANGGAL);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        assert jenis != null;
        switch (jenis) {
            case "Seminar Kerja Praktek" :
                tvJenis.setBackgroundResource(R.drawable.bg_green_beach);
                //tvJenis.setPaddingRelative(0, 16, 25, 16);
                btnClose.setTextColor(Color.rgb(0, 195, 164));
                break;
            case "Seminar Usul" :
                tvJenis.setBackgroundResource(R.drawable.bg_blue_skies);
                btnClose.setTextColor(Color.rgb(26, 127, 226));
                break;
            case "Seminar Hasil" :
                tvJenis.setBackgroundResource(R.drawable.bg_red_gradient);
                btnClose.setTextColor(Color.RED);
                break;
        }

        tvNama.setText(nama);
        tvNpm.setText(npm);
        tvJenis.setText(jenis);
        tvJudul.setText(judul);
        tvDosen.setText(dosen);
        tvRuang.setText(ruang);
        tvTanggal.setText(tanggal);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });

        return view;
    }
}
