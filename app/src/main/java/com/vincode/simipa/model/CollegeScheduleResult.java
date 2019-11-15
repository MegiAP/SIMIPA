package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollegeScheduleResult {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("hari")
    @Expose
    private String hari;

    @SerializedName("kodeMK")
    @Expose
    private String kodeMK;

    @SerializedName("tahun_akademik")
    @Expose
    private String tahun_akademik;

    @SerializedName("semester")
    @Expose
    private String semester;

    @SerializedName("kodeDosen")
    @Expose
    private String kodeDosen;

    @SerializedName("namaDosen")
    @Expose
    private String namaDosen;

    @SerializedName("ruang_kuliah")
    @Expose
    private String ruang_kuliahl;

    @SerializedName("jam_kuliah")
    @Expose
    private String jam_kuliah;

}
