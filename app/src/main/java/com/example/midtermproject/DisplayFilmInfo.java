package com.example.midtermproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayFilmInfo extends AppCompatActivity {
    @SuppressLint({"SimpleDateFormat", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_filminfo);
        String name = getIntent().getStringExtra("NAME");
        String description = getIntent().getStringExtra("DESCRIPTION");
        String rating = getIntent().getStringExtra("RATING");
        int filmAva = getIntent().getIntExtra("AVA", 0);
        String link = getIntent().getStringExtra("LINK");

        TextView nameTv = findViewById(R.id.txtName);
        TextView descriptionTv = findViewById(R.id.txtDescription);
        TextView ratingTv = findViewById(R.id.txtRating);
        ImageView imageView = findViewById(R.id.imgPoster);

        String myUrl = "https://www.youtube.com/embed/"+link;
        String dataUrl = "<html><body><iframe width=\"400\" height=\"315\" src=\" "+myUrl+ "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        WebView myWebView = findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadData(dataUrl, "text/html", "utf-8");

        imageView.setImageResource(filmAva);
        nameTv.setText(name);
        descriptionTv.setText(description);
        ratingTv.setText(rating);
    }

}
