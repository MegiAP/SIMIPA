package com.vincode.simipa.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeritaResult {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Content")
    @Expose
    private String content;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Author")
    @Expose
    private String author;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

}
