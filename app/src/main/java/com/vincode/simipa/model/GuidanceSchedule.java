package com.vincode.simipa.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GuidanceSchedule implements Parcelable {

    private String title, lecture, location, date, time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.lecture);
        dest.writeString(this.location);
        dest.writeString(this.date);
        dest.writeString(this.time);
    }

    public GuidanceSchedule() {
    }

    protected GuidanceSchedule(Parcel in) {
        this.title = in.readString();
        this.lecture = in.readString();
        this.location = in.readString();
        this.date = in.readString();
        this.time = in.readString();
    }

    public static final Creator<GuidanceSchedule> CREATOR = new Creator<GuidanceSchedule>() {
        @Override
        public GuidanceSchedule createFromParcel(Parcel source) {
            return new GuidanceSchedule(source);
        }

        @Override
        public GuidanceSchedule[] newArray(int size) {
            return new GuidanceSchedule[size];
        }
    };
}
