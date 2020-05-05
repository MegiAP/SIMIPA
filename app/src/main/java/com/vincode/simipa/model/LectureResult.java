package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LectureResult {
    @SerializedName("NIP")
    @Expose
    private String nip;
    @SerializedName("Display_Name")
    @Expose
    private String lectureName;
    @SerializedName("Email")
    @Expose
    private String lectureEmail;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getLectureEmail() {
        return lectureEmail;
    }

    public void setLectureEmail(String lectureEmail) {
        this.lectureEmail = lectureEmail;
    }

}
