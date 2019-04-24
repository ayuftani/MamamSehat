package com.example.mamamsehat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Kontak extends AppCompatActivity implements View.OnClickListener {

    private CardView wa, ig, maps, mail;
    private TextView insta, em, hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);

        wa = findViewById(R.id.wa);
        ig = findViewById(R.id.ig);
        maps = findViewById(R.id.maps);
        mail = findViewById(R.id.mail);

        insta = findViewById(R.id.insta);
        hp = findViewById(R.id.hp);
        em = findViewById(R.id.em);

        wa.setOnClickListener(this);
        ig.setOnClickListener(this);
        maps.setOnClickListener(this);
        mail.setOnClickListener(this);
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
        } else if (id == R.id.action_logout) {
            Intent intent5 = new Intent(Kontak.this, LoginActivity.class);
            startActivity(intent5);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.wa:
                wa();
                break;

            case R.id.ig:
                ig();
                break;

            case R.id.maps:
                loc();
                break;

            case R.id.mail:
                email();
                break;

            default:
                break;
        }
    }

    public void loc(){
        String loc = "Telkom University";

        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent maps = new Intent(Intent.ACTION_VIEW, addressUri);

        if (maps.resolveActivity(getPackageManager()) != null) {
            startActivity(maps);
        } else {
            Log.d("Google Maps", "Can't handle this intent!");
        }
    }
    public void ig() {
        Intent intent = new Intent(this, instagram.class);
        startActivity(intent);
    }

    public void wa() {
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("082216650194"));
        startActivity(i);
    }

    public void email() {
        String txt3 = em.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setText(txt3)
                .startChooser();
    }
}
