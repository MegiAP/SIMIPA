package com.vincode.simipa.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BeritaResult implements Parcelable {

    @SerializedName("Title")
    private String title;

    @SerializedName("Date")
    private String releaseDate;

    @SerializedName("Content")
    private String content;

    @SerializedName("Foto")
    private String photo;

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getContent() {
        return content;
    }

    public String getPhoto() {
        return photo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.releaseDate);
        dest.writeString(this.content);
        dest.writeString(this.photo);
    }

    public BeritaResult() {
    }

    protected BeritaResult(Parcel in) {
        this.title = in.readString();
        this.releaseDate = in.readString();
        this.content = in.readString();
        this.photo = in.readString();
    }

    public static final Creator<BeritaResult> CREATOR = new Creator<BeritaResult>() {
        @Override
        public BeritaResult createFromParcel(Parcel source) {
            return new BeritaResult(source);
        }

        @Override
        public BeritaResult[] newArray(int size) {
            return new BeritaResult[size];
        }
    };
}
