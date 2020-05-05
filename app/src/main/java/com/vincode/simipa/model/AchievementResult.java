package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AchievementResult {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Kode_Program_Studi")
    @Expose
    private String kodeProgramStudi;
    @SerializedName("npm")
    @Expose
    private String npm;
    @SerializedName("Nama_Kegiatan")
    @Expose
    private String namaKegiatan;
    @SerializedName("Kategori")
    @Expose
    private String kategori;
    @SerializedName("Tingkat")
    @Expose
    private String tingkat;
    @SerializedName("Prestasi")
    @Expose
    private String prestasi;
    @SerializedName("Tahun_Prestasi")
    @Expose
    private String tahunPrestasi;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKodeProgramStudi() {
        return kodeProgramStudi;
    }

    public void setKodeProgramStudi(String kodeProgramStudi) {
        this.kodeProgramStudi = kodeProgramStudi;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getTingkat() {
        return tingkat;
    }

    public void setTingkat(String tingkat) {
        this.tingkat = tingkat;
    }

    public String getPrestasi() {
        return prestasi;
    }

    public void setPrestasi(String prestasi) {
        this.prestasi = prestasi;
    }

    public String getTahunPrestasi() {
        return tahunPrestasi;
    }

    public void setTahunPrestasi(String tahunPrestasi) {
        this.tahunPrestasi = tahunPrestasi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
