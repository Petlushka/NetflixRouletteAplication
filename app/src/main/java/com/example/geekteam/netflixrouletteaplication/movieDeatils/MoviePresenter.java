package com.example.geekteam.netflixrouletteaplication.movieDeatils;


import android.support.v7.widget.Toolbar;

import com.example.geekteam.netflixrouletteaplication.data.Movie;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;


class MoviePresenter implements MovieDetailContract.DetailPresenter {

    private List<Movie> data = new ArrayList<>();
    private int position;
    private Realm realm;

    MoviePresenter(List<Movie> data, int position, boolean save){
        realm = Realm.getDefaultInstance();
        this.data = data;
        this.position = position;
        if(save)
            this.data = realm.where(Movie.class).findAll();
        else
            this.data = data;
    }

    @Override
    public List<Movie> getAllMovies() {
        return data;
    }

    @Override
    public Movie getCurrentMovie(int index) {
        return data.get(index);
    }

    @Override
    public void setTitle(Toolbar toolbar){
        toolbar.setTitle(getCurrentMovie(position).getShowTitle());
    }

    boolean isSave(){
        int id =  getCurrentMovie(position).getShowId();
        Movie tmp = realm.where(Movie.class).equalTo("showId",id).findFirst();
        return tmp != null;
    }

    void setPosition(int position){
        this.position = position;
    }
}
