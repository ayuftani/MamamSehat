package com.example.mamamsehat;

public class ArtikelData2 {
    private String judul, imgUrl, deskripsi;

    public ArtikelData2(String nama, String harga, String imgUrl, String deskripsi) {
        this.judul = nama;
        this.imgUrl = imgUrl;
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

