package com.vincode.simipa.ui.berita;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vincode.simipa.R;

public class DetailNewsActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        WebView wvNews = findViewById(R.id.wv_news);
        final ProgressBar progressBar = findViewById(R.id.progress_bar);
        String linkNews = getIntent().getStringExtra("link");
        String titleNews = getIntent().getStringExtra("title");

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(titleNews);
            getSupportActionBar().setElevation(0f);
        }

        wvNews.getSettings().setJavaScriptEnabled(true);
        wvNews.setWebChromeClient(new WebChromeClient());
        wvNews.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(DetailNewsActivity.this, "Berhasil dimuat", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                super.onReceivedError(view, request, error);
                progressBar.setVisibility(View.GONE);
            }
        });


        wvNews.loadUrl(linkNews);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
