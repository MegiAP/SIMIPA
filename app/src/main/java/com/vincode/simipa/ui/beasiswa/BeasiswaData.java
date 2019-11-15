package com.vincode.simipa.ui.beasiswa;

import java.util.ArrayList;

public class BeasiswaData {
    public static String[][] data = new String[][]{
            {"PPA", "1 Semester", "Lulus"},
            {"Bank Indonesia", "1 Tahun", "Lulus"},
            {"Djarum", "1 Tahun", "Lulus"}
    };

    public static ArrayList<Beasiswa> getListData() {
        Beasiswa beasiswa = null;
        ArrayList<Beasiswa> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            beasiswa = new Beasiswa();
            beasiswa.setName(data[i][0]);
            beasiswa.setRemarks(data[i][1]);
            beasiswa.setStatus(data[i][2]);

            list.add(beasiswa);
        }

        return list;
    }
}
