package com.vincode.simipa.util;

import com.vincode.simipa.model.GuidanceSchedule;

import java.util.ArrayList;

public class TestGuidance {

    private static String[][]dataTest = new String[][]{
            {"Rancang Bangun Aplikasi SIMIPA", "Ardiansyah, M.Kom.", "GIK Lt.1C", "21-10-2019", "10.00"},
            {"Implementasi REST API SIMIPA", "Ardiansyah, M.Kom.", "GIK Lt.1C", "21-10-2019", "13.30"}
    };

    public static ArrayList<GuidanceSchedule> getListGuidance(){
        ArrayList<GuidanceSchedule> listGuidance = new ArrayList<>();
        for (String[] mData: dataTest){
            GuidanceSchedule guidanceSchedule = new GuidanceSchedule();
            guidanceSchedule.setTitle(mData[0]);
            guidanceSchedule.setLecture(mData[1]);
            guidanceSchedule.setLocation(mData[2]);
            guidanceSchedule.setDate(mData[3]);
            guidanceSchedule.setTime(mData[4]);
            listGuidance.add(guidanceSchedule);
        }

        return listGuidance;
    }
}
