package com.example.androidshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.HashMap;

public class SendActivity extends AppCompatActivity {
    TextView textShared;
    Button buttonShared, buttonCansel;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        HashMap<String, String> post = new HashMap<>();

        textShared = (TextView) findViewById(R.id.textShared);
        buttonShared = (Button) findViewById(R.id.buttonShared);
        buttonCansel = (Button) findViewById(R.id.buttonCancel);
        webView = (WebView) findViewById(R.id.webView);

        if (Intent.ACTION_SEND.equals(action) && type != null && "text/plain".equals(type)) {
            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            if (sharedText != null) {
                textShared.setText(sharedText);

                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        post.put("title", view.getTitle());
                        post.put("url", sharedText);
                    }
                });
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setDatabaseEnabled(true);
                webView.getSettings().setDomStorageEnabled(true);
                webView.loadUrl(sharedText);
            }
        }

        buttonShared.setOnClickListener(view -> {
            post.put("created", new Date().toString());

            DatabaseReference db = FirebaseDatabase.getInstance().getReference();
            db.child("posts").push().setValue(post);
            finish();
        });

        buttonCansel.setOnClickListener(view -> {
            finish();
        });
    }
}
