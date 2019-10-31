package com.vincode.simipa.util;

import android.util.Log;
import java.util.Calendar;

public class TimeUtil {

    public String getWaktu(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        String resultYear = null;

        if (month >= 1 && month <= 6){
            int yearPrev = year - 1;
            resultYear = year +"/"+ yearPrev;
        }else if (month >= 7){
            int yearNext = year + 1;
            resultYear = year +"/"+ yearNext;
        }else {
            Log.d("error", "ada error");
        }

        return resultYear;
    }

    public String converDate(String date){
        String[] dates = date.split("-");

        if (dates.length == 3){
            String year = dates[0];
            String month = dates[1];
            String day = dates[2];

            switch (month){
                case "01":
                    month = "Januari";
                    break;
                case "02":
                    month = "Februari";
                    break;
                case "03":
                    month = "Maret";
                    break;
                case "04":
                    month = "April";
                    break;
                case "05":
                    month = "Mei";
                    break;
                case "06":
                    month = "Juni";
                    break;
                case "07":
                    month = "Juli";
                    break;
                case "08":
                    month = "Agustus";
                    break;
                case "09":
                    month = "September";
                    break;
                case "10":
                    month = "Oktober";
                    break;
                case "11":
                    month = "November";
                    break;
                case "12":
                    month = "Desember";
                    break;
            }
            return day+" "+month+" "+year;
        }
        return date;
    }
}