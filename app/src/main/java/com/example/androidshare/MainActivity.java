package com.example.androidshare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webViewMain = (WebView) findViewById(R.id.webViewMain);
        webViewMain.setWebViewClient(new WebViewClient());
        webViewMain.getSettings().setJavaScriptEnabled(true);
        webViewMain.getSettings().setDatabaseEnabled(true);
        webViewMain.getSettings().setDomStorageEnabled(true);
        webViewMain.loadUrl("https://google.com/");
    }
}