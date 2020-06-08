package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScholarshipPost {
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

/*    @SerializedName("npm")
    @Expose
    private String npm;

    @SerializedName("semester")
    @Expose
    private String semester;

    @SerializedName("tahun_beasiswa")
    @Expose
    private String tahunBeasiswa;

    @SerializedName("nama_beasiswa")
    @Expose
    private String namaBeasiswa;

    @SerializedName("penyelenggara")
    @Expose
    private String penyelenggara;

    public String getTahunBeasiswa() {
        return tahunBeasiswa;
    }

    public String getSemester() {
        return semester;
    }

    public String getNpm() {
        return npm;
    }

    public String getNamaBeasiswa() {
        return namaBeasiswa;
    }

    public String getPenyelenggara() {
        return penyelenggara;
    }*/
}
