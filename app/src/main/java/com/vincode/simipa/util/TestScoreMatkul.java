package com.vincode.simipa.util;

import com.vincode.simipa.model.ScoreMatkul;
import com.vincode.simipa.model.ScoreRecap;

import java.util.ArrayList;

public class TestScoreMatkul {
     static String[][] data = new String[][]{
            {"Aljabar", "COM616101", "3", "A"},
            {"Dasar-Dasar Pemrograman", "COM616102", "3","A"},
            {"Bahasa Inggris", "COM616103", "3","A"},
            {"Matematika", "COM616104", "3","A"},
            {"Logika", "COM616105", "3","A"},
            {"Statistika", "COM616106", "3","A"},
            {"Pend. Agama", "UNI616101", "3", "A"}
    };

    public static ArrayList<ScoreMatkul> getListData() {
        ArrayList<ScoreMatkul> listScore = new ArrayList<>();
        for (String[] mData: data){
            ScoreMatkul scoreMatkul = new ScoreMatkul();
            scoreMatkul.setName(mData[0]);
            scoreMatkul.setCode(mData[1]);
            scoreMatkul.setSks(mData[2]);
            scoreMatkul.setScore(mData[3]);
            listScore.add(scoreMatkul);
        }

        return listScore;

    }
}
