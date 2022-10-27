package com.example.midtermproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements FilmsViewInterface{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ArrayList<Films> films;
    private ArrayList<Films> favorFilms = new ArrayList<>();
    FilmsViewAdapter fVA;
    // TODO: Rename and change types of parameters

    public HomeFragment() {
    }

    @Override
    public ArrayList<Films> getFavorites() {
        return favorFilms;
    }

    @Override
    public void onLongClickView(int pos) {
        Films removeFilms = films.get(pos);
        favorFilms.remove(removeFilms);
        films.remove(removeFilms);
        fVA.notifyItemRemoved(pos);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        films = getArguments()!=null?getArguments().getParcelableArrayList("films"):null;
        RecyclerView recyclerview = view.findViewById(R.id.filmsView);
        fVA = new FilmsViewAdapter(getContext(),films,this);

        recyclerview.setAdapter(fVA);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
    }
    @Override
    public void onClickView(int pos) {
        Intent intent = new Intent(getContext(), DisplayFilmInfo.class);
        intent.putExtra("NAME", films.get(pos).getName());
        intent.putExtra("DESCRIPTION", films.get(pos).getDescription());
        intent.putExtra("AVA", films.get(pos).getFilmAva());
        intent.putExtra("RELEASED",films.get(pos).getReleaseD());
        startActivity(intent);
    }
    public void setFavorites(ArrayList<Films> favorites) {
        favorFilms = favorites;
    }
    @Override
    public void onHeartClick(int pos, boolean isFavor) {
        if (isFavor) {
            films.get(pos).setFavor(true);
            favorFilms.add(films.get(pos));
        } else {
            films.get(pos).setFavor(false);
            favorFilms.remove(films.get(pos));
        }

    }
}