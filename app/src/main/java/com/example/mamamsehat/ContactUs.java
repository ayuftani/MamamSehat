package com.example.mamamsehat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactUs extends AppCompatActivity {

    private ImageView wa;
    private ImageView email;
    private ImageView ig;
    private ImageView maps;
    private TextView website_edittext;
    private TextView teext;
    private TextView mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        wa = findViewById(R.id.wa);
        email = findViewById(R.id.email);
        ig = findViewById(R.id.ig);
        maps = findViewById(R.id.maps);
        website_edittext = findViewById(R.id.website_edittext);
        teext = findViewById(R.id.textView2);
        mail = findViewById(R.id.textView13);

    }


    public void maps(View view) {

        String loc = "Telkom University";

        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("Google Maps", "Can't handle this intent!");
        }
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

    public void ig(View view) {
        String txt = website_edittext.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_text_with)
                .setText(txt)
                .startChooser();
    }

    public void whatsapp(View view) {
        String txt = mail.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_text_with)
                .setText(txt)
                .startChooser();
    }

    public void email(View view) {
        String txt = teext.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_text_with)
                .setText(txt)
                .startChooser();
    }
    }
