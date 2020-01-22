package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PresenceResult {

    @SerializedName("ID_presensi")
    @Expose
    private String idPresence;

    @SerializedName("MataKuliah")
    @Expose
    private String mataKuliah;
    @SerializedName("KodeMK")
    @Expose
    private String kodeMK;


    @SerializedName("Kelas")
    @Expose
    private String kelas;

    @SerializedName("Dosen PJ")
    @Expose
    private String dosenPJ;

    @SerializedName("Ruang")
    @Expose
    private String ruang;

    @SerializedName("Mulai")
    @Expose
    private String mulai;

    @SerializedName("Selesai")
    @Expose
    private String selesai;

    @SerializedName("Due Date Absen")
    @Expose
    private String dueDateAbsen;

    public String getIdPresence() {
        return idPresence;
    }

    public String getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(String mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public String getKodeMK() {
        return kodeMK;
    }

    public void setKodeMK(String kodeMK) {
        this.kodeMK = kodeMK;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getDosenPJ() {
        return dosenPJ;
    }

    public void setDosenPJ(String dosenPJ) {
        this.dosenPJ = dosenPJ;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getMulai() {
        return mulai;
    }

    public void setMulai(String mulai) {
        this.mulai = mulai;
    }

    public String getSelesai() {
        return selesai;
    }

    public void setSelesai(String selesai) {
        this.selesai = selesai;
    }

    public String getDueDateAbsen() {
        return dueDateAbsen;
    }

    public void setDueDateAbsen(String dueDateAbsen) {
        this.dueDateAbsen = dueDateAbsen;
    }
}
