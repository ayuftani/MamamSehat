package com.example.mamamsehat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class instagram extends AppCompatActivity {

    WebView webayu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);

        webayu = findViewById(R.id.webayu);
        String url = "https://www.instagram.com/mamam.sehat";
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
}
