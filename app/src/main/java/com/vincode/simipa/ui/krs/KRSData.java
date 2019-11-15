package com.vincode.simipa.ui.krs;

import java.util.ArrayList;

public class KRSData {
    public static String[][] data = new String[][]{
            {"Aljabar", "COM616101", "3"},
            {"Dasar-Dasar Pemrograman", "COM616102", "3"},
            {"Bahasa Inggris", "COM616103", "3"},
            {"Matematika", "COM616104", "3"},
            {"Logika", "COM616105", "3"},
            {"Statistika", "COM616106", "3"},
            {"Pend. Agama", "UNI616101", "3"}
    };

    public static ArrayList<KRS> getListData() {
        KRS krs = null;
        ArrayList<KRS> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            krs = new KRS();
            krs.setName(data[i][0]);
            krs.setRemarks(data[i][1]);
            krs.setPhoto(data[i][2]);

            list.add(krs);
        }

        return list;
    }
}
