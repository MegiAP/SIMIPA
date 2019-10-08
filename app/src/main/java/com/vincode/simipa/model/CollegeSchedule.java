package com.vincode.simipa.model;

public class CollegeSchedule {public String getJdmatkul() { return jdmatkul; }

    public void setJdmatkul(String tgl) {
        this.jdmatkul = jdmatkul;
    }

    public String getJdmatkode() {
        return jdmatkode;
    }

    public void setJdmatkode(String jam) {
        this.jdmatkode = jdmatkode;
    }

    public String getJddosen() {
        return jddosen;
    }

    public void setJddosen(String judul) {
        this.jddosen = jddosen;
    }

    public String getJdruang() {
        return jdruang;
    }

    public void setJdruang(String ruang) {
        this.jdruang = jdruang;
    }

    public String getJdjam() {
        return jdjam;
    }

    public void setJdjam(String jenis) {
        this.jdjam = jdjam;
    }

    private String jdmatkul;
    private String jdmatkode;
    private String jddosen;
    private String jdruang;
    private String jdjam;

    public CollegeSchedule(String jdmatkul, String jdmatkode, String jddosen, String jdruang, String jdjam) {
        this.jdmatkul = jdmatkul;
        this.jdmatkode = jdmatkode;
        this.jddosen = jddosen;
        this.jdruang = jdruang;
        this.jdjam = jdjam;
    }
}

