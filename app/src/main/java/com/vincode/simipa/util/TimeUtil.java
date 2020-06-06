package com.vincode.simipa.util;


import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

    public String getWaktuNow(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M", Locale.getDefault());
        return dateFormat.format(new Date());
    }

    public  String getHari() {
        SimpleDateFormat hari = new SimpleDateFormat("EEEE", new Locale("in", "ID"));

        return hari.format(new Date());
    }

    public String getTanggal() {
        SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        return tanggal.format(new Date());
    }

    public String getTanggalFormatInd(String oldDateString) {
        final String OLD_FORMAT = "yyyy-MM-dd";
        final String NEW_FORMAT = "dd-MMM-yyyy";
        SimpleDateFormat tglInd = new SimpleDateFormat(OLD_FORMAT);
        Date d = null;
        try {
            d = tglInd.parse(oldDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tglInd.applyPattern(NEW_FORMAT);
        return tglInd.format(d);
    }

    public String getWaktu(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy", Locale.getDefault());
        String date = dateFormat.format(new Date());
        String[] dates = date.split("-");

        int month = Integer.parseInt(dates[1]);
        int year = Integer.parseInt(dates[2]);
        String resultYear = "";

        if (month >= 1 && month <= 6){
            int yearPrev = year - 1;
            resultYear = yearPrev +"/"+ year;
        }else if (month >= 7){
            int yearNext = year + 1;
            resultYear = year +"/"+ yearNext;
        }else {
            Log.d("error", "ada error");
        }

        return resultYear;
    }

    public String getSemester() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat now = new SimpleDateFormat("dd-M-yyyy");
        String tanggal = now.format(new Date());
        String[] mtanggal = tanggal.split("-");
        int bulan = Integer.parseInt(mtanggal[1]);
        String semester;

        if (bulan >= 2 && bulan <= 7) {
            semester = "Genap";
        } else {
            semester = "Ganjil";
        }
        return semester;

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
