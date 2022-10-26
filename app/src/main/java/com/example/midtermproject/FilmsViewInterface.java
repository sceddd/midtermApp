package com.example.midtermproject;

import java.util.ArrayList;

public interface FilmsViewInterface {
    void onClickView(int pos);
    void onHeartClick(int pos,boolean isFavor);
    ArrayList<Films> getFavorites();
    void onLongClickView(int pos);
}

