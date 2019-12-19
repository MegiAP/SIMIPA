package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KRSResult {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Npm")
    @Expose
    private String npm;
    @SerializedName("kodeMK")
    @Expose
    private String kodeMK;
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("tahun_akademik")
    @Expose
    private String tahunAkademik;
    @SerializedName("Pengambilan Ke")
    @Expose
    private String pengambilanKe;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getKodeMK() {
        return kodeMK;
    }

    public void setKodeMK(String kodeMK) {
        this.kodeMK = kodeMK;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTahunAkademik() {
        return tahunAkademik;
    }

    public void setTahunAkademik(String tahunAkademik) {
        this.tahunAkademik = tahunAkademik;
    }

    public String getPengambilanKe() {
        return pengambilanKe;
    }

    public void setPengambilanKe(String pengambilanKe) {
        this.pengambilanKe = pengambilanKe;
    }

}
