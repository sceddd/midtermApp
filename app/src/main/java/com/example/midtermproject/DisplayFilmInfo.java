package com.example.midtermproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayFilmInfo extends AppCompatActivity {
    boolean isFavor;
    @SuppressLint( "SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_filminfo);
        String name = getIntent().getStringExtra("NAME");
        String description = getIntent().getStringExtra("DESCRIPTION");
        String rating = getIntent().getStringExtra("RATING");
        int filmAva = getIntent().getIntExtra("AVA", 0);
        String link = getIntent().getStringExtra("LINK");
        isFavor = getIntent().getBooleanExtra("ISFAVOR",false);

        Button bookB = findViewById(R.id.btnBook);
        ImageButton heartClick = findViewById(R.id.favorAddv2);
        TextView nameTv = findViewById(R.id.txtName);
        TextView descriptionTv = findViewById(R.id.txtDescription);
        TextView ratingTv = findViewById(R.id.txtRating);
        ImageView imageView = findViewById(R.id.imgPoster);
        Log.d("TAG", ""+heartClick);
        heartClick.setOnClickListener(v->{
            isFavor = !isFavor;
            int color = isFavor? Color.parseColor("#FC94AF") :Color.parseColor("#FFFFFFFF");
            heartClick.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        });

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
        bookB.setOnClickListener(v->{
            Intent launchApp = getPackageManager().getLaunchIntentForPackage("com.cgv.cinema.vn");
            if (launchApp != null){
                    startActivity(launchApp);
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("ISFAVOR", isFavor);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }


}
