package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class MainActivity extends AppCompatActivity{
    BottomNavigationView bottomNavigationView;
    ArrayList<Films> films = new ArrayList<>();
    ArrayList<Films> favorFilms = new ArrayList<>();
    int filmsAva;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        setUpFilms();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("films",films);
        HomeFragment hFragment = new HomeFragment();
        hFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, hFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = new Fragment();
            switch (item.getItemId()){
                case R.id.nav_home:
                    hFragment.setFavorites(favorFilms);
                    fragment = hFragment;
                    break;
                case R.id.nav_favorite:
                    fragment = new FavoriteFragment();
                    favorFilms = hFragment.getFavorites();
                    bundle.putParcelableArrayList("favor",favorFilms);
                    fragment.setArguments(bundle);
                    break;
                case R.id.nav_search:
                    fragment = new SearchFragment();
                    fragment.setArguments(bundle);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
            return true;
        });
    }

    private void setUpFilms() {
        String[] name = getResources().getStringArray(R.array.films);
        String[] description = getResources().getStringArray(R.array.description);
        String[] rating = getResources().getStringArray(R.array.rating);
        for (int i = 0; i < name.length; i++) {
            films.add(new Films(name[i], description[i], filmsAva, Float.parseFloat(rating[i]), false));
        }
        films.sort((a, b) -> Float.compare(b.getRating(),a.getRating()));
//            films.sort((a, b) -> Float.compare(a.getRating(),b.getRating()));
    }

}