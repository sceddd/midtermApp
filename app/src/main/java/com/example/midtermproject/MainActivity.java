package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class MainActivity extends AppCompatActivity{
    FloatingActionButton fabMore, fabAcs, fabDesc;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen = false;

    BottomNavigationView bottomNavigationView;
    ArrayList<Films> films = new ArrayList<>();
    ArrayList<Films> favorFilms = new ArrayList<>();
    int filmsAva = R.drawable.pic_28;
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
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
            return true;
        });
        fab();
    }

    private void fab(){
        fabMore = (FloatingActionButton) findViewById(R.id.fab_more);
        fabAcs = (FloatingActionButton) findViewById(R.id.fab_asc);
        fabDesc = (FloatingActionButton) findViewById(R.id.fab_desc);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        fabMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });
    }
    private void animateFab() {
        if (isOpen){
            fabMore.startAnimation(rotateForward);
            fabAcs.startAnimation(fabClose);
            fabDesc.startAnimation(fabClose);
            fabAcs.setClickable(false);
            fabDesc.setClickable(false);
            isOpen = false;
        }
        else{
            fabMore.startAnimation(rotateBackward);
            fabAcs.startAnimation(fabOpen);
            fabDesc.startAnimation(fabOpen);
            fabAcs.setClickable(true);
            fabDesc.setClickable(true);
            isOpen = true;
        }
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