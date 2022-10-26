package com.example.midtermproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FilmsViewAdapter extends RecyclerView.Adapter<FilmsViewAdapter.MyViewHolder> {
    private final FilmsViewInterface filmsViewInterface;
    Context context;
    static ArrayList<Films> films;

    public FilmsViewAdapter(Context context, ArrayList<Films> films,FilmsViewInterface filmsViewInterface) {
        this.filmsViewInterface = filmsViewInterface;
        this.context = context;
        FilmsViewAdapter.films = films;
    }
    @NonNull
    @NotNull
    @Override
    public FilmsViewAdapter.MyViewHolder onCreateViewHolder(@NonNull@NotNull ViewGroup parent,int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.film_row,parent,false);
        return new FilmsViewAdapter.MyViewHolder(view,filmsViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.filmName.setText(films.get(position).getName());
        holder.filmsAva.setImageResource(films.get(position).getFilmAva());
        holder.filmDescription.setText(films.get(position).getDescription());
        int color = films.get(position).isFavor()? Color.parseColor("#FC94AF") :Color.parseColor("#FFFFFFFF");
        holder.heartClick.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView filmsAva;
        TextView filmName;
        TextView filmDescription;
        TextView filmDayRel;
        ImageButton heartClick;
    @SuppressLint("ResourceAsColor")
        public MyViewHolder(@NotNull@NonNull View itemView, FilmsViewInterface filmsViewInterface) {
            super(itemView);
            filmsAva = itemView.findViewById(R.id.avatar);
            filmName = itemView.findViewById(R.id.name);
            filmDescription = itemView.findViewById(R.id.description);
            filmDayRel = itemView.findViewById(R.id.dayRelease);
            heartClick = itemView.findViewById(R.id.button);

            itemView.setOnClickListener(v->{
                if(filmsViewInterface!=null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        filmsViewInterface.onClickView(pos);
                    }
                }
            });
            itemView.setOnLongClickListener(v->{
                if(filmsViewInterface!=null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        filmsViewInterface.onLongClickView(pos);
                    }
                }
                return false;
            });
            heartClick.setOnClickListener(v->{
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION){
                    filmsViewInterface.onHeartClick(pos,!films.get(pos).isFavorite());
                    int color = films.get(pos).isFavor()? Color.parseColor("#FC94AF") :Color.parseColor("#FFFFFFFF");
                    heartClick.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                }
            });
        }
    }
}
