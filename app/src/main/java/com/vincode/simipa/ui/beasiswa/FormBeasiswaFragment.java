package com.vincode.simipa.ui.beasiswa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
    private Spinner spinYear,semester;
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
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, years);

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

        ArrayAdapter<String> adapterSems = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, sems);

        spinYear = view.findViewById(R.id.yearspin);
        spinYear.setAdapter(adapterYear);

        semester = view.findViewById(R.id.semesterspin);
        semester.setAdapter(adapterSems);

        tambah = view.findViewById(R.id.tambah_beasiswa);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String smstr = semester.getSelectedItem().toString();
                String text = spinYear.getSelectedItem().toString();
                Toast.makeText(getActivity(), text + smstr, Toast.LENGTH_LONG).show();
            }
        });
    }

}
