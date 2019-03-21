package com.example.mamamsehat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailProduk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        TextView sportsTitle = findViewById(R.id.titleDetail);
        ImageView ProdukImage = findViewById(R.id.ProdukImageDetail);

        sportsTitle.setText(getIntent().getStringExtra("title"));

        Glide.with(this)
                .load(getIntent()
                        .getIntExtra("image_resource",0))
                .into(ProdukImage);
    }
}
