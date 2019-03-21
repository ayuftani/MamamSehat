package com.example.mamamsehat;

class Produk {
    private String title;
    private String info;
    private final int imageResource;

    public Produk(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }

    public int getImageResource() {
        return imageResource;
    }

}
