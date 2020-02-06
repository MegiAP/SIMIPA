package com.vincode.simipa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeminarScheduleResult {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("NPM")
        @Expose
        private String nPM;
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
        @SerializedName("Pbg1")
        @Expose
        private String pbg1;
        @SerializedName("Pbg2")
        @Expose
        private String pbg2;
        @SerializedName("Pbs1")
        @Expose
        private String pbs1;
        @SerializedName("Pbs2")
        @Expose
        private String pbs2;

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

        public String getPbg1() {
            return pbg1;
        }

        public void setPbg1(String pbg1) {
            this.pbg1 = pbg1;
        }

        public String getPbg2() {
            return pbg2;
        }

        public void setPbg2(String pbg2) {
            this.pbg2 = pbg2;
        }

        public String getPbs1() {
            return pbs1;
        }

        public void setPbs1(String pbs1) {
            this.pbs1 = pbs1;
        }

        public String getPbs2() {
            return pbs2;
        }

        public void setPbs2(String pbs2) {
            this.pbs2 = pbs2;
        }

    }

