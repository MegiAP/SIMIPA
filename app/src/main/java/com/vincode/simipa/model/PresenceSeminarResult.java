package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PresenceSeminarResult {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("NPM")
    @Expose
    private String nPM;
    @SerializedName("Nama")
    @Expose
    private String nama;
    @SerializedName("Judul")
    @Expose
    private String judul;
    @SerializedName("Jenis")
    @Expose
    private String jenis;
    @SerializedName("Tanggal")
    @Expose
    private String tanggal;
    @SerializedName("Waktu")
    @Expose
    private String waktu;
    @SerializedName("Ruang")
    @Expose
    private String ruang;
    @SerializedName("Peserta")
    @Expose
    private String peserta;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNPM() {
        return nPM;
    }

    public void setNPM(String nPM) {
        this.nPM = nPM;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }


    public String getPeserta() {
        return peserta;
    }

    public void setPeserta(String peserta) {
        this.peserta = peserta;
    }
}
