package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScholarshipResult {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("npm")
    @Expose
    private String npm;
    @SerializedName("semester")
    @Expose
    private String semester;
    @SerializedName("Tahun Beasiswa")
    @Expose
    private String tahunBeasiswa;
    @SerializedName("jenis beasiswa")
    @Expose
    private String jenisBeasiswa;
    @SerializedName("nama beasiswa")
    @Expose
    private String namaBeasiswa;
    @SerializedName("no usulan")
    @Expose
    private String noUsulan;
    @SerializedName("status ajukan")
    @Expose
    private String statusAjukan;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;

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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTahunBeasiswa() {
        return tahunBeasiswa;
    }

    public void setTahunBeasiswa(String tahunBeasiswa) {
        this.tahunBeasiswa = tahunBeasiswa;
    }

    public String getJenisBeasiswa() {
        return jenisBeasiswa;
    }

    public void setJenisBeasiswa(String jenisBeasiswa) {
        this.jenisBeasiswa = jenisBeasiswa;
    }

    public String getNamaBeasiswa() {
        return namaBeasiswa;
    }

    public void setNamaBeasiswa(String namaBeasiswa) {
        this.namaBeasiswa = namaBeasiswa;
    }

    public String getNoUsulan() {
        return noUsulan;
    }

    public void setNoUsulan(String noUsulan) {
        this.noUsulan = noUsulan;
    }

    public String getStatusAjukan() {
        return statusAjukan;
    }

    public void setStatusAjukan(String statusAjukan) {
        this.statusAjukan = statusAjukan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
