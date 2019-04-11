package com.example.mamamsehat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class homeadmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.punyadmin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_beranda:
                Intent intent = new Intent(homeadmin.this, homeadmin.class);
                startActivity(intent);
                return true;
            case R.id.action_tambahproduk:
                Intent intent1 = new Intent(homeadmin.this, MainActivity.class);
                startActivity(intent1);
                return true;
            case R.id.action_tambahartikel:
                Intent intent2 = new Intent(homeadmin.this, Artikel.class);
                startActivity(intent2);
                return true;
            case R.id.action_logout:
                Intent intent5 = new Intent(homeadmin.this, Login.class);
                startActivity(intent5);
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }
}