package com.vincode.simipa.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BeritaResult implements Parcelable {

    @SerializedName("original_title")
    private String title;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("overview")
    private String overview;

    @SerializedName("backdrop_path")
    private String backdropPath;

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.releaseDate);
        dest.writeString(this.overview);
        dest.writeString(this.backdropPath);
    }

    public BeritaResult() {
    }

    protected BeritaResult(Parcel in) {
        this.title = in.readString();
        this.releaseDate = in.readString();
        this.overview = in.readString();
        this.backdropPath = in.readString();
    }

    public static final Parcelable.Creator<BeritaResult> CREATOR = new Parcelable.Creator<BeritaResult>() {
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
