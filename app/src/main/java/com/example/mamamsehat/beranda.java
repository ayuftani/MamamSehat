package com.example.mamamsehat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
public class beranda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_beranda:
                Intent intent = new Intent(beranda.this, beranda.class);
                startActivity(intent);
                return true;
            case R.id.action_produk:
                Intent intent1 = new Intent(beranda.this, OrderActivity.class);
                startActivity(intent1);
                return true;
            case R.id.action_artikel:
                Intent intent2 = new Intent(beranda.this, Artikel.class);
                startActivity(intent2);
                return true;
            case R.id.action_forum:
                Intent intent3 = new Intent(beranda.this, forum.class);
                startActivity(intent3);
                return true;
            case R.id.action_contact:
                Intent intent4 = new Intent(beranda.this, ContactUs.class);
                startActivity(intent4);
                return true;
            case R.id.action_logout:
                Intent intent5 = new Intent(beranda.this, Login.class);
                startActivity(intent5);
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }
}
