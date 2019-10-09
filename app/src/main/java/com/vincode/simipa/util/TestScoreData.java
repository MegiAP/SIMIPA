package com.vincode.simipa.util;

import com.vincode.simipa.model.ScoreRecap;

import java.util.ArrayList;

public class TestScoreData {
    public static String[][] data = new String[][]{
            {"Aljabar", "COM616101", "A"},
            {"Dasar-Dasar Pemrograman", "COM616102", "A"},
            {"Bahasa Inggris", "COM616103", "A"},
            {"Matematika", "COM616104", "A"},
            {"Logika", "COM616105", "A"},
            {"Statistika", "COM616106", "A"},
            {"Pend. Agama", "UNI616101", "A"}
    };

    public static ArrayList<ScoreRecap> getListData() {
        ScoreRecap scoreRecap = null;
        ArrayList<ScoreRecap> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            scoreRecap = new ScoreRecap();
            scoreRecap.setName(data[i][0]);
            scoreRecap.setRemarks(data[i][1]);
            scoreRecap.setPhoto(data[i][2]);

            list.add(scoreRecap);
        }

        return list;
    }
}
