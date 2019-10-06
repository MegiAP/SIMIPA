package com.vincode.simipa.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CalendarAcademic implements Parcelable {

//    public CalendarAcademic(String data, String date) {
//        this.data = data;
//        this.date = date;
//    }

    private String data, date;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.data);
        dest.writeString(this.date);
    }

    public CalendarAcademic() {
    }

    protected CalendarAcademic(Parcel in) {
        this.data = in.readString();
        this.date = in.readString();
    }

    public static final Creator<CalendarAcademic> CREATOR = new Creator<CalendarAcademic>() {
        @Override
        public CalendarAcademic createFromParcel(Parcel source) {
            return new CalendarAcademic(source);
        }

        @Override
        public CalendarAcademic[] newArray(int size) {
            return new CalendarAcademic[size];
        }
    };
}
