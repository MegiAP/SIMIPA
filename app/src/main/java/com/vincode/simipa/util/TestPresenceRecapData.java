package com.vincode.simipa.util;

import com.vincode.simipa.model.PresenceRecap;
import com.vincode.simipa.model.ScoreRecap;

import java.util.ArrayList;

public class TestPresenceRecapData {
    public static String[][] data = new String[][]{
            {"Kapita Selekta", "COM616xxx", "Ardiansyah, S.Kom., M.Kom."},
            {"Mobile Lanjut", "COM616xxxx", "Ardiansyah, S.Kom., M.Kom."},
            {"Temu Kembali Informasi", "COM616xxx", "Aristoteles"},

    };
    public static ArrayList<PresenceRecap> getListPresence() {
        PresenceRecap presenceRecap= null;
        ArrayList<PresenceRecap> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            presenceRecap = new PresenceRecap();
            presenceRecap.setName(data[i][0]);
            presenceRecap.setRkode(data[i][1]);
            presenceRecap.setRdosen(data[i][2]);

            list.add(presenceRecap);
        }

        return list;
    }
}
