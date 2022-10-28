package com.example.midtermproject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Films implements Parcelable {
    private String name;
    private String description;
    private int filmAva;
    private float rating;
    private boolean isFavor;


    public Films(String name, String description, int filmAva, float rating, boolean isFavor) {
        this.name = name;
        this.description = description;
        this.filmAva = filmAva;
        this.rating = rating;
        this.isFavor = isFavor;
    }

    public Films(Parcel in) {
        super();
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        name = in.readString();
        description = in.readString();
        filmAva = in.readInt();
        rating = in.readFloat();
    }

    public static final Parcelable.Creator<Films> CREATOR = new Parcelable.Creator<Films>() {
        public Films createFromParcel(Parcel in) {
            return new Films(in);
        }

        public Films[] newArray(int size) {

            return new Films[size];
        }

    };

    public float getRating() {
        return rating;
    }

    public boolean isFavorite() {
        return isFavor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Films{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", filmAva=" + filmAva +
                ", rating=" + rating +
                ", isFavor=" + isFavor +
                '}';
    }


    public int getFilmAva() {
        return filmAva;
    }

    public boolean isFavor() {
        return isFavor;
    }

    public void setFavor(boolean favor) {
        isFavor = favor;
    }

    public void setFilmAva(int filmAva) {
        this.filmAva = filmAva;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeInt(filmAva);
        parcel.writeFloat(rating);
    }
}
