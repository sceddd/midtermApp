package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity{
    FloatingActionButton fabMore, fabAcs, fabDesc;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen = false;
    boolean isDesc = false;
    BottomNavigationView bottomNavigationView;

    ArrayList<Films> films = new ArrayList<>();
    ArrayList<Films> favorFilms = new ArrayList<>();

    HomeFragment hFragment;
    Fragment fragment;

    Bundle bundle;
    int[] filmsAva = {R.drawable.pic_1, R.drawable.pic_2,
            R.drawable.pic_3, R.drawable.pic_4,
            R.drawable.pic_5, R.drawable.pic_6,
            R.drawable.pic_7, R.drawable.pic_7,
            R.drawable.pic_9, R.drawable.pic_8,
            R.drawable.pic_11, R.drawable.pic_9,
            R.drawable.pic_11,R.drawable.pic_12,
            R.drawable.pic_13,R.drawable.pic_14,
            R.drawable.pic_15,R.drawable.pic_16,
            R.drawable.pic_17,R.drawable.pic_18,
            R.drawable.pic_19,R.drawable.pic_20,
            R.drawable.pic_21,R.drawable.pic_22,
            R.drawable.pic_23
    };



    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        setUpFilms();

        bundle = new Bundle();
        bundle.putParcelableArrayList("films",films);
        hFragment = new HomeFragment();
        hFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, hFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            bundle.putParcelableArrayList("films",films);
            fragment = new Fragment();
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
        fabMore = findViewById(R.id.fab_more);
        fabAcs =  findViewById(R.id.fab_asc);
        fabDesc =  findViewById(R.id.fab_desc);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        fabMore.setOnClickListener(view -> animateFab());
    }

    public void updateFavor(Films film){
        int pos= films.indexOf(film);
        films.get(pos).setFavor(false);
//        hFragment.listChanged(pos);
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
            fabAcs.setOnClickListener(v->{
                isDesc = false;
                fabDesc.setClickable(true);
                fabAcs.setClickable(false);
                ((FilmsViewInterface) fragment).changeDesc();
            });
            fabDesc.setOnClickListener(v->{
                isDesc = true;
                fabAcs.setClickable(true);
                fabDesc.setClickable(false);
                hFragment.changeDesc();
                ((FilmsViewInterface) fragment).changeDesc();
            });

            isOpen = true;
        }
    }
    private void setUpFilms() {
        String[] name = getResources().getStringArray(R.array.films);
        String[] description = getResources().getStringArray(R.array.description);
        String[] rating = getResources().getStringArray(R.array.rating);
        String[] link = getResources().getStringArray(R.array.link);
        for (int i = 0; i < name.length; i++) {
            Log.d("TAG", "setUpFilms: "+i);
            films.add(new Films(name[i], description[i], filmsAva[i], Float.parseFloat(rating[i]), false, link[i]));
        }
        films.sort((a, b) -> Float.compare(b.getRating(),a.getRating()));
    }

}