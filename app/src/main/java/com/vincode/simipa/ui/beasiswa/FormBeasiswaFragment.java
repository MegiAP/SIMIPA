package com.vincode.simipa.ui.beasiswa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.vincode.simipa.R;
import com.vincode.simipa.util.SharedPrefManager;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormBeasiswaFragment extends Fragment {
    private Spinner spinYear;
    private Button tambah;

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
        String npm = SharedPrefManager.getInstance(getContext()).getUser().getUserLogin();
        int angkatan = Integer.parseInt(npm.substring(0,2));

        //get tahun sekarang
        int tahunIni = Calendar.getInstance().get(Calendar.YEAR);

        String d = String.valueOf(tahunIni).substring(2,4);
        int f = Integer.parseInt(d);

        //selisih
        int g = f-angkatan;
        int tahunMasuk = tahunIni-g;
        for (int i = tahunIni; i >= tahunMasuk; i--) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, years);

        spinYear = view.findViewById(R.id.yearspin);
        spinYear.setAdapter(adapter);

        tambah = view.findViewById(R.id.tambah_beasiswa);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = spinYear.getSelectedItem().toString();
                Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
            }
        });
    }

}
