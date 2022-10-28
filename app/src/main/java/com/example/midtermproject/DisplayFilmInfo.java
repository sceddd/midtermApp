package com.example.midtermproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayFilmInfo extends AppCompatActivity {
    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_filminfo);
        String name = getIntent().getStringExtra("NAME");
        String description = getIntent().getStringExtra("DESCRIPTION");
        String rating = getIntent().getStringExtra("RATING");
        int filmAva = getIntent().getIntExtra("AVA", 0);

        TextView nameTv = findViewById(R.id.txtName);
        TextView descriptionTv = findViewById(R.id.txtDescription);
        TextView ratingTv = findViewById(R.id.txtRating);

        nameTv.setText(name);
        descriptionTv.setText(description);
        ratingTv.setText(rating);
    }

}
