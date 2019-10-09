package com.vincode.simipa.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Presence implements Parcelable {

    private String timeOne, timeTwo, title, code, lecture, room, state, bab;

    public String getTimeOne() {
        return timeOne;
    }

    public void setTimeOne(String timeOne) {
        this.timeOne = timeOne;
    }

    public String getTimeTwo() {
        return timeTwo;
    }

    public void setTimeTwo(String timeTwo) {
        this.timeTwo = timeTwo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBab() {
        return bab;
    }

    public void setBab(String bab) {
        this.bab = bab;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.timeOne);
        dest.writeString(this.timeTwo);
        dest.writeString(this.title);
        dest.writeString(this.code);
        dest.writeString(this.lecture);
        dest.writeString(this.room);
        dest.writeString(this.state);
        dest.writeString(this.bab);
    }

    public Presence() {
    }

    protected Presence(Parcel in) {
        this.timeOne = in.readString();
        this.timeTwo = in.readString();
        this.title = in.readString();
        this.code = in.readString();
        this.lecture = in.readString();
        this.room = in.readString();
        this.state = in.readString();
        this.bab = in.readString();
    }

    public static final Creator<Presence> CREATOR = new Creator<Presence>() {
        @Override
        public Presence createFromParcel(Parcel source) {
            return new Presence(source);
        }

        @Override
        public Presence[] newArray(int size) {
            return new Presence[size];
        }
    };
}
