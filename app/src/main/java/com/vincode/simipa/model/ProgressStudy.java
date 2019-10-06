package com.vincode.simipa.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProgressStudy implements Parcelable {

    private String semester, ipk, sks;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getIpk() {
        return ipk;
    }

    public void setIpk(String ipk) {
        this.ipk = ipk;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.semester);
        dest.writeString(this.ipk);
        dest.writeString(this.sks);
    }

    public ProgressStudy() {
    }

    protected ProgressStudy(Parcel in) {
        this.semester = in.readString();
        this.ipk = in.readString();
        this.sks = in.readString();
    }

    public static final Creator<ProgressStudy> CREATOR = new Creator<ProgressStudy>() {
        @Override
        public ProgressStudy createFromParcel(Parcel source) {
            return new ProgressStudy(source);
        }

        @Override
        public ProgressStudy[] newArray(int size) {
            return new ProgressStudy[size];
        }
    };
}
