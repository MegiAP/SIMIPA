package com.vincode.simipa.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ScoreMatkul implements Parcelable {
    private String name, code, sks, score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.code);
        dest.writeString(this.sks);
        dest.writeString(this.score);
    }

    public ScoreMatkul() {
    }

    protected ScoreMatkul(Parcel in) {
        this.name = in.readString();
        this.code = in.readString();
        this.sks = in.readString();
        this.score = in.readString();
    }

    public static final Parcelable.Creator<ScoreMatkul> CREATOR = new Parcelable.Creator<ScoreMatkul>() {
        @Override
        public ScoreMatkul createFromParcel(Parcel source) {
            return new ScoreMatkul(source);
        }

        @Override
        public ScoreMatkul[] newArray(int size) {
            return new ScoreMatkul[size];
        }
    };
}
