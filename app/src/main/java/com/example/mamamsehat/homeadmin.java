package com.example.mamamsehat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class homeadmin extends AppCompatActivity implements  View.OnClickListener{

    private CardView profile, menu, artikel, kontak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        profile = findViewById(R.id.profile);
        menu = findViewById(R.id.menu);
        artikel = findViewById(R.id.artikel);
        kontak = findViewById(R.id.kontak);

        profile.setOnClickListener(this);
        menu.setOnClickListener(this);
        artikel.setOnClickListener(this);
        kontak.setOnClickListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else  if (id == R.id.action_logout) {
            Intent intent5 = new Intent(homeadmin.this, LoginActivity.class);
            startActivity(intent5);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.profile :
                Intent intent = new Intent(this, homeadmin.class);
                startActivity(intent);
                break;

            case R.id.menu :
                Intent intent1 = new Intent(this, MenuActivity.class);
                startActivity(intent1);
                break;

            case R.id.artikel :
                Intent intent2 = new Intent(this, homeadmin.class);
                startActivity(intent2);
                break;

            case R.id.kontak :
                Intent intent3 = new Intent(this, Kontak.class);
                startActivity(intent3);
                break;

                default:break;
        }
    }
}
