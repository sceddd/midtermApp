package com.example.midtermproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class FavoriteFragment extends Fragment implements FilmsViewInterface{
    FilmsViewAdapter fVA;
    private ArrayList<Films> favorFilms;
    boolean clickAble = true;
    public FavoriteFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favorFilms = getArguments()!=null?getArguments().getParcelableArrayList("favor"):null;
        RecyclerView recyclerview = view.findViewById(R.id.favRecycle);
        fVA = new FilmsViewAdapter(getContext(),favorFilms,this){
            @Override
            public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.heartClick.setVisibility(View.GONE);
            }
        };
        recyclerview.setAdapter(fVA);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }


    @Override
    public void onClickView(int pos) {
        if (clickAble)
        {
            Intent intent = new Intent(getContext(), DisplayFilmInfo.class);
            intent.putExtra("NAME", favorFilms.get(pos).getName());
            intent.putExtra("DESCRIPTION", favorFilms.get(pos).getDescription());
            intent.putExtra("AVA", favorFilms.get(pos).getFilmAva());
            startActivity(intent);
        }
    }

    @Override
    public void onHeartClick(int pos,boolean isFavor) {

    }

    @Override
    public ArrayList<Films> getFavorites() {
        return favorFilms;
    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void changeDesc() {
        Collections.reverse(favorFilms);
        fVA.notifyDataSetChanged();
    }

    @Override
    public void setClickable(boolean status) {
        clickAble = status;
    }

    @Override
    public void onLongClickView(int pos) {
        if (clickAble)
        {
            ((MainActivity) requireActivity()).updateFavor(favorFilms.get(pos));
            favorFilms.remove(pos);
            if (getArguments() != null)
                getArguments().putParcelableArrayList("favor", favorFilms);
            Log.d("position", String.valueOf(pos));
//        ((MainActivity) getActivity()).upd
            fVA.notifyItemRemoved(pos);
        }
    }
}