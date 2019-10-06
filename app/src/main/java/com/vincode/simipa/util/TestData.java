package com.vincode.simipa.util;

import com.vincode.simipa.model.CalendarAcademic;

import java.util.ArrayList;

public class TestData {

    private static String[][]dataTest = new String[][]{
            {"Pengisian KRS", "20-07-2019 s.d 27-07-2019"},
            {"Pengisian KRS", "20-07-2019 s.d 27-07-2019"},
            {"Pengisian KRS", "20-07-2019 s.d 27-07-2019"},
            {"Pengisian KRS", "20-07-2019 s.d 27-07-2019"},
            {"Pengisian KRS", "20-07-2019 s.d 27-07-2019"}
    };

    public static ArrayList<CalendarAcademic> getListCalendar(){
        ArrayList<CalendarAcademic> listCalendar = new ArrayList<>();
        for (String[] mData: dataTest){
            CalendarAcademic calendarAcademic = new CalendarAcademic();
            calendarAcademic.setData(mData[0]);
            calendarAcademic.setDate(mData[1]);

            listCalendar.add(calendarAcademic);
        }

        return listCalendar;
    }
}
