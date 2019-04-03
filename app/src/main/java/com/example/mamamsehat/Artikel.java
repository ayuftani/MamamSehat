package com.example.mamamsehat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Artikel extends AppCompatActivity {

    WebView webayu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        webayu = findViewById(R.id.webayu);
        String url = "https://www.dietsehatcantik.com/";
        webayu.getSettings().setJavaScriptEnabled(true);
        webayu.setFocusable(true);
        webayu.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webayu.getSettings().setDomStorageEnabled(true);
        webayu.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webayu.getSettings().setDatabaseEnabled(true);
        webayu.getSettings().setAppCacheEnabled(true);
        webayu.loadUrl(url);
        webayu.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (webayu.canGoBack()){
            webayu.goBack();
        } else {
            super.onBackPressed();
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
                Intent intent = new Intent(Artikel.this, Home.class);
                startActivity(intent);
                return true;
            case R.id.action_produk:
                Intent intent1 = new Intent(Artikel.this, OrderActivity.class);
                startActivity(intent1);
                return true;
            case R.id.action_artikel:
                Intent intent2 = new Intent(Artikel.this, Artikel.class);
                startActivity(intent2);
                return true;
            case R.id.action_forum:
                Intent intent3 = new Intent(Artikel.this, forum.class);
                startActivity(intent3);
                return true;
            case R.id.action_contact:
                Intent intent4 = new Intent(Artikel.this, ContactUs.class);
                startActivity(intent4);
                return true;
            case R.id.action_logout:
                Intent intent5 = new Intent(Artikel.this, Login.class);
                startActivity(intent5);
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }
}
