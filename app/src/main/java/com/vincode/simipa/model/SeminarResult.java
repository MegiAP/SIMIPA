package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeminarResult {




    @SerializedName("Nama Pbg1")
    @Expose
    private String pem1;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("npm")
    @Expose
    private String npm;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("jenis")
    @Expose
    private String jenis;
    @SerializedName("jam")
    @Expose
    private String jam;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    public String getPem1() {
        return pem1;
    }

    public void setPem1(String pem1) {
        this.pem1 = pem1;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
