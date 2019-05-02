package com.example.mamamsehat;

public class ArtikelData {
    private String judul, deskripsi, imgUrl;

    public ArtikelData(String nama, String harga, String deskripsi, String imgUrl) {
        this.judul = nama;
        this.deskripsi = deskripsi;
        this.imgUrl = imgUrl;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}


