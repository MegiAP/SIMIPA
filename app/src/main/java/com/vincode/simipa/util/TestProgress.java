package com.vincode.simipa.util;

import com.vincode.simipa.model.ProgressStudy;

import java.util.ArrayList;

public class TestProgress {

    private static String[][]dataTest = new String[][]{
            {"Semester 1", "3.4", "23"},
            {"Semester 2", "3.6", "23"}
    };

    public static ArrayList<ProgressStudy> getListProgress(){
        ArrayList<ProgressStudy> listProgress = new ArrayList<>();
        for (String[] mData: dataTest){
            ProgressStudy progressStudy = new ProgressStudy();
            progressStudy.setSemester(mData[0]);
            progressStudy.setIpk(mData[1]);
            progressStudy.setSks(mData[2]);
            listProgress.add(progressStudy);
        }

        return listProgress;
    }
}
