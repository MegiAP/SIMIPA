package com.vincode.simipa.util;

import com.vincode.simipa.model.SeminarRecap;

import java.util.ArrayList;

public class TestSeminarRecapData {
    public static String[][] data = new String[][]{
        {"Cindy Prakasa Putra", "1617051108","Ardiansyah, S.Kom., M.Kom.", "Rancang Bangun SIMIPA", "29 Maret 2019"},
        {"Kelvin Putra",  "1617051108", "Ardiansyah, S.Kom., M.Kom.", "Rancang Bangun SIMIPA", "12 Januari 2020"}

};
        public static ArrayList<SeminarRecap> getListSeminar() {
            SeminarRecap seminarRecap= null;
            ArrayList<SeminarRecap> list = new ArrayList<>();
            for (int i = 0; i < data.length; i++) {
                seminarRecap = new SeminarRecap();
                seminarRecap.setName(data[i][0]);
                seminarRecap.setNpm(data[i][1]);
                seminarRecap.setSjudul(data[i][2]);
                seminarRecap.setSdosen(data[i][3]);
                seminarRecap.setSjenis(data[i][4]);

                list.add(seminarRecap);
            }
            return list;
        }
}
