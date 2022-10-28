package com.example.midtermproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements FilmsViewInterface{
    ArrayList<Films> films = new ArrayList<>();
    ArrayList<Films> lFF = new ArrayList<>();
    Bundle bundle = getArguments();
    ArrayList<Films> favor = new ArrayList<>();
    FilmsViewAdapter fVA;
    public SearchFragment() {
        // Required empty public constructor
    }
    // get Bundle from MainActivity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        films = getArguments() != null? getArguments().getParcelableArrayList("films") : null;
        favor = getArguments() != null? getArguments().getParcelableArrayList("favor") : null;
        RecyclerView recyclerview = view.findViewById(R.id.list_found);
        fVA = new FilmsViewAdapter(getContext(), films,this);
        recyclerview.setAdapter(fVA);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onClickView(int pos) {
        Intent intent = new Intent(getContext(), DisplayFilmInfo.class);
        intent.putExtra("NAME", films.get(pos).getName());
        intent.putExtra("DESCRIPTION", films.get(pos).getDescription());
        intent.putExtra("AVA", films.get(pos).getFilmAva());
        startActivity(intent);
    }

    @Override
    public void onHeartClick(int pos, boolean isFavor) {
        if (isFavor) {
            films.get(pos).setFavor(true);
            favor.add(films.get(pos));
        } else {
            films.get(pos).setFavor(false);
            favor.remove(films.get(pos));
        }
        updateFavorites();
    }

    @Override
    public ArrayList<Films> getFavorites() {
        return null;
    }

    @Override
    public void onLongClickView(int pos) {

    }
    public void updateFavorites(){
        bundle.putParcelableArrayList("favor", favor );
    }
}