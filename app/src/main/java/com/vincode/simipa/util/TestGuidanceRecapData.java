package com.vincode.simipa.util;

import com.vincode.simipa.model.GuidanceRecap;

import java.util.ArrayList;

public class TestGuidanceRecapData {

    private static String[][]dataTest = new String[][]{
            {"Rancang Bangun Aplikasi SIMIPA", "Ardiansyah, M.Kom.", "GIK Lt.1C", "21-10-2019", "10.00"},
            {"Implementasi REST API SIMIPA", "Ardiansyah, M.Kom.", "GIK Lt.1C", "21-10-2019", "13.30"}
    };

    public static ArrayList<GuidanceRecap> getListGuidanceRecap(){
        ArrayList<GuidanceRecap> listGuidance = new ArrayList<>();
        for (String[] mData: dataTest){
            GuidanceRecap guidanceRecap = new GuidanceRecap();
            guidanceRecap.setTitle(mData[0]);
            guidanceRecap.setLecture(mData[1]);
            guidanceRecap.setLocation(mData[2]);
            guidanceRecap.setDate(mData[3]);
            guidanceRecap.setTime(mData[4]);
            listGuidance.add(guidanceRecap);
        }

        return listGuidance;
    }
}
