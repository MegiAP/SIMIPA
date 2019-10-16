package com.vincode.simipa.model;

public class Agenda  {

        public String getJam1() {
    return jam1;
}

        public void setJam1(String jam1) {
            this.jam1 = jam1;
        }

        public String getJam2() {
            return jam2;
        }

        public void setJam2(String jam2) {
            this.jam2 = jam2;
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

        private String jam1;
        private String jam2;
        private String judul;
        private String nama;
        private String ruang;
        private String jenis;

        public Agenda(String jam1, String jam2, String judul, String nama, String ruang, String jenis) {
            this.jam1 = jam1;
            this.jam2 = jam2;
            this.judul = judul;
            this.nama = nama;
            this.ruang = ruang;
            this.jenis = jenis;
        }




}
