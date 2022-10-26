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
        String ticketPrice = getIntent().getStringExtra("TICKET");
        try {
            Date dayRelease = new SimpleDateFormat("dd/MM/yyyy").parse(getIntent().getStringExtra("RELEASED"));
            int filmAva = getIntent().getIntExtra("AVA", 0);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        TextView nameTv = findViewById(R.id.name);
        nameTv.setText(getString(name));
    }

}
