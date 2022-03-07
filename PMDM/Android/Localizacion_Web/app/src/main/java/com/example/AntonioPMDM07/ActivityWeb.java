package com.example.AntonioPMDM07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.pmdm07.R;

import java.util.List;

public class ActivityWeb extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        String message = intent.getStringExtra("passUrl");

        webView = (WebView) findViewById(R.id.wvWeb);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        //String finalDireccionWeb = "https://www.meneame.net";
        String finalDireccionWeb = "https://"+message;
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(finalDireccionWeb);
            }
        });

    }

}