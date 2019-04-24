package com.example.mamamsehat;

public class MenuData2 {
    private String nama, harga, imgUrl, deskripsi;

    public MenuData2(String nama, String harga, String imgUrl, String deskripsi) {
        this.nama = nama;
        this.harga = harga;
        this.imgUrl = imgUrl;
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
