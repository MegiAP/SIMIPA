package com.vincode.simipa.util;

import com.vincode.simipa.model.SeminarRecap;

import java.util.ArrayList;

public class TestSeminarRecapData {
    public static String[][] data = new String[][]{
        {"Cindy Prakasa Putra", "Rancang Bangun SIMIPA", "Ardiansyah, S.Kom., M.Kom.", "29 Maret 2019"},
        {"Kelvin Putra",  "Rancang Bangun SIMIPA", "Ardiansyah, S.Kom., M.Kom.", "12 Januari 2020"}

};
        public static ArrayList<SeminarRecap> getListSeminar() {
            SeminarRecap seminarRecap= null;
            ArrayList<SeminarRecap> list = new ArrayList<>();
            for (int i = 0; i < data.length; i++) {
                seminarRecap = new SeminarRecap();
                seminarRecap.setName(data[i][0]);
                seminarRecap.setSjudul(data[i][1]);
                seminarRecap.setSdosen(data[i][2]);
                seminarRecap.setSjenis(data[i][3]);

                list.add(seminarRecap);
            }
            return list;
        }
}
