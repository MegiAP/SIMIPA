package com.vincode.simipa.model;

public class SeminarSchedule {
    public String getTgl() { return tgl; }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    private String tgl;
    private String jam;
    private String judul;
    private String nama;
    private String ruang;
    private String jenis;

    public SeminarSchedule(String tgl, String jam, String judul, String nama, String ruang, String jenis) {
        this.tgl = tgl;
        this.jam = jam;
        this.judul = judul;
        this.nama = nama;
        this.ruang = ruang;
        this.jenis = jenis;
    }
}