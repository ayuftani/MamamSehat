package com.example.mamamsehat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_beranda:
                Intent intent = new Intent(ContactUs.this, Home.class);
                startActivity(intent);
                return true;
            case R.id.action_produk:
                Intent intent1 = new Intent(ContactUs.this, OrderActivity.class);
                startActivity(intent1);
                return true;
            case R.id.action_artikel:
                Intent intent2 = new Intent(ContactUs.this, Artikel.class);
                startActivity(intent2);
                return true;
            case R.id.action_forum:
                Intent intent3 = new Intent(ContactUs.this, forum.class);
                startActivity(intent3);
                return true;
            case R.id.action_contact:
                Intent intent4 = new Intent(ContactUs.this, ContactUs.class);
                startActivity(intent4);
                return true;
            case R.id.action_logout:
                Intent intent5 = new Intent(ContactUs.this, Login.class);
                startActivity(intent5);
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }
}
