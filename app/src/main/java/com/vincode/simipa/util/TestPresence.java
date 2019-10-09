package com.vincode.simipa.util;

import com.vincode.simipa.model.Presence;

import java.util.ArrayList;

public class TestPresence {

    private static String[][]dataTest = new String[][]{
            {"07.30", "09.10", "Teknologi Mobile", "COM616163", "Ardiansyah, M.Kom.", "GIK L1.C", "1"}
    };

    public static ArrayList<Presence> getListPresence(){
        ArrayList<Presence> listPresence = new ArrayList<>();
        for (String[] mData: dataTest){
            Presence presence = new Presence();
            presence.setTimeOne(mData[0]);
            presence.setTimeTwo(mData[1]);
            presence.setTitle(mData[2]);
            presence.setCode(mData[3]);
            presence.setLecture(mData[4]);
            presence.setRoom(mData[5]);
            presence.setBab(mData[6]);
            listPresence.add(presence);
        }

        return listPresence;
    }
}
