package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudyResult {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("npm")
    @Expose
    private String npm;
    @SerializedName("ip")
    @Expose
    private String ip;
    @SerializedName("sks")
    @Expose
    private String sks;
    @SerializedName("jumlah_matkul")
    @Expose
    private String jumlahMatkul;
    @SerializedName("semester")
    @Expose
    private String semester;

    public String getId() {
        return id;
    }

    public String getNpm() {
        return npm;
    }

    public String getIp() {
        return ip;
    }

    public String getSks() {
        return sks;
    }

    public String getJumlahMatkul() {
        return jumlahMatkul;
    }

    public String getSemester() {
        return semester;
    }
}
