package com.example.midtermproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;


public class HomeFragment extends Fragment implements FilmsViewInterface{
    EditText search;
    private ArrayList<Films> films;
    private ArrayList<Films> favorFilms = new ArrayList<>();
    private ArrayList<Films> searchFilms = new ArrayList<>();
    RecyclerView recyclerview;
    FilmsViewAdapter fVA;
    Bundle bundle ;

    public HomeFragment() {
    }

    @Override
    public ArrayList<Films> getFavorites() {
        return favorFilms;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void changeDesc() {

        Collections.reverse(films);
        fVA.notifyDataSetChanged();
    }
//    public void listChanged(int pos){
//        Log.d("TAG", "listChanged" + pos);
//        fVA.notifyItemChanged(pos);
//    }

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
        search = view.findViewById(R.id.search_edit_text);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable e) {
                searchFilms.clear();
                if(e.toString().isEmpty()){
                    recyclerview.setAdapter(new FilmsViewAdapter(getContext(),films,HomeFragment.this));
                    fVA.notifyDataSetChanged();
                }
                else{
                    for (Films i: films){
                        if (i.getName().toLowerCase().contains(e.toString().toLowerCase())){
                            searchFilms.add(i);
                        }
                    }
                    recyclerview.setAdapter(new FilmsViewAdapter(getContext(),searchFilms,HomeFragment.this));
                    fVA.notifyDataSetChanged();
                }
            }
        });
        bundle = getArguments();
        films = getArguments()!=null?bundle.getParcelableArrayList("films"):null;
        recyclerview = view.findViewById(R.id.filmsView);
        fVA = new FilmsViewAdapter(getContext(),films,this);

        recyclerview.setAdapter(fVA);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
    }
    public void setFavorites(ArrayList<Films> favorites) {
        favorFilms = favorites;
    }
    @Override
    public void onClickView(int pos) {
        Intent intent = new Intent(getContext(), DisplayFilmInfo.class);
        intent.putExtra("NAME", films.get(pos).getName());
        intent.putExtra("DESCRIPTION", films.get(pos).getDescription());
        intent.putExtra("AVA", films.get(pos).getFilmAva());
        intent.putExtra("LINK",films.get(pos).getLink());
        Log.d("TAG", "onClickView: ");
        startActivity(intent);
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
        fVA.notifyItemChanged(pos);
    }
    @Override
    public void onLongClickView(int pos) {
    }
    public String array2string(ArrayList<Films> films){
        String ab = "";
        for(Films i:films){
            ab += i.toString() + "  ";
        }
        return ab;
    }
}
